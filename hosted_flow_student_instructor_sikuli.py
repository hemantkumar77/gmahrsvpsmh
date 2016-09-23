'''
python hosted_flow_student_instructor_sikuli.py -s preprod -f student -t ZTE_16071456
python hosted_flow_student_instructor_sikuli.py -s dev -f student -t ZTE_201604292342
python hosted_flow_student_instructor_sikuli.py -s preprod -f student -t ZTE_201511110000 -a false
python hosted_flow_student_instructor_sikuli.py -s prod -f student -t ZTE_201511110000 -a false
python hosted_flow_student_instructor_sikuli.py -s prod -f complete -a false
python hosted_flow_student_instructor_sikuli.py -s prod -f complete -a false
-s = server = dev, preprod, prod
-f = flow = instructor, student, complete
-t = test_name = your_test_name
-a = autorun = true (false for sikuli)
Created by: Tushar Thantharate; Date: 12-19-2014
Updated by: Peter McLaughlin: 01-16-2015, Parth Buch: 12-30-2014, G Hemant Kumar: 07-02-2015, 09-22-2015
Description: This is an automated test to run through the Hosted flow which include instructor test creation, register student* and attempt test as student and instructor review'''

import logging, time, os, csv, datetime, pdb, getpass, platform, sys, shutil, random
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import (NoSuchElementException, NoAlertPresentException, WebDriverException)
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from datetime import datetime
#from proctortrack_app.launchapp import launchapp
from selenium.webdriver.common.action_chains import ActionChains
#from proctortrack_app.launchapp import launchapp
from selenium.webdriver.support.ui import Select
from optparse import OptionParser

# Globals
driver = webdriver.Chrome()
wait = WebDriverWait(driver, 20)
driver.implicitly_wait(20)

parser = OptionParser()
parser.add_option('--server_name', '-s', dest='server_name')
parser.add_option('--flow', '-f', dest='flow')
parser.add_option('--test_name', '-t', dest='test_name')
parser.add_option('--auto_run', '-a', dest='auto_run')
(options, args) = parser.parse_args()

server_name = options.server_name
flow = options.flow
test_name = options.test_name
auto_run = options.auto_run
server_url = 'https://dev.verificient.com:8338'
login_id = 'hemant@verificient.com'
password = '123'
if server_name == 'dev':
    server_url = 'https://prestaging.verificient.com:8338'
    login_id = 'hemant@verificient.com'
    password = 'Hemant123'
elif server_name == 'preprod':
    server_url = 'https://preproduction.verificient.com'
    login_id = 'hemant@verificient.com'
    password = 'Hemant123'
elif server_name == 'prod':
    server_url = 'https://testing.verificient.com'
    login_id = 'hemant@verificient.com'
    password = '123'

now = datetime.now().strftime("%y%m%d")
test_prefix = int(random.random()*100)


if test_name == None:
    test_name = "ZTE_" + str(now) +""+str(test_prefix)
#print test_name
#assert True
email = 'hemant@verificient.com'
full_name = 'Hemant Kumar'

signup_data1 = {
    'first_name':'Anup', 
    'last_name':'Dhabharde', 
    'email':'anup@verificient.com', 
}
signup_data2 = {
    'first_name':'Tushar', 
    'last_name':'Thantharate', 
    'email':'tushar@verificient.com', 
}

# os.environ['webdriver.chrome.driver'] = "/home/verificient/verificient/app/ChromeDriver"
class HostedFlowTestWithPayment(object):
    def __init__(self):
        driver.maximize_window()
        driver.get(server_url)
        time.sleep(2)
        #os.system('clear')

    def run_tests(self):
        self.login(signup_data1)
        if flow == 'complete' or flow == 'instructor':
            self.change_role_instructor()
            self._run_instructor_flow()
        if flow == 'complete' or flow == 'student':
            #self.change_role_student()
            self._run_student_flow()

    def login(self, kwargs):
        wait.until(EC.element_to_be_clickable((By.ID, "id_email"))).send_keys(login_id)
        wait.until(EC.element_to_be_clickable((By.ID, "id_password"))).send_keys(password)
        wait.until(EC.element_to_be_clickable((By.ID, 'log-in'))).click()
        time.sleep(2)

    def change_role_instructor(self):
        step = "Instructor Flow: Instructor View"
        # Try to change the role to Instructor if logged in as Student.
        try:
            elem = driver.find_element_by_id('id_change_role_instructor')
            elem.click()
        except NoSuchElementException as e:
            logging.exception(e)
            pass
        assertion_text = "Test Progress Overview"
        time.sleep(5)
        homepage_text = wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"][1]/div/div/h1')))
        assert assertion_text == homepage_text.text

    def change_role_student(self):
        step = "Student Flow: Student View"
        # Try to change the role to Student if logged in as Insturctor.
        time.sleep(5)
        try:
            time.sleep(20)
            #Click on account and then click on switch to student
            elem = driver.find_element_by_xpath('//body/nav/div[2]/ul/li/a')
            elem.click()

            time.sleep(1)
            wait.until(EC.element_to_be_clickable((By.ID, 'func-change-role-to-student'))).click()
        except NoSuchElementException as e:
            logging.exception(e)
            pass

        print "Into Student View"
        assert True

    def _run_instructor_flow(self):
        self.create_test()
        self.add_questions()
        # self.configure_payment()
        # self.generate_registration_data_csv()
        self.register_student()
        self.remove_student()
        self.register_student_direct()
        #self.switch_to_student_flow()

    def _run_student_flow(self):
        self.change_role_student()
        self.student_view()
        self.best_practices()

        # Hack: Click on Live Test Flow Icon on the top
        # self.go_to_launch_test_page()
        # If autorun is false Sikuli code is executed
        if auto_run == "true":
            self.download_launch_app()
            self.launch_test()
            self.take_test()
        else:
            self.download_app_sikuli()
            self.launch_app_sikuli()

    def add_questions(self):
        self.add_single_answer_multiple_choice()
        self.add_multiple_answer_multiple_choice()
        self.add_true_false()
        self.add_essay()
        self.add_fill_in_the_blanks()
        self.save_questions()

    def time_delay(self):
        time.sleep(1)

        # pdb.set_trace()

    def _get_timestamp(self):
        return datetime.now().strftime("%Y%m%d%H%M%S%f")

    def _generate_test_name(self):
        return test_name

    def create_test(self):
        self.step = "Instructor Flow: Create Test"
        try:
            wait.until(EC.element_to_be_clickable((By.ID, "id_edit_menu"))).click()
            wait.until(EC.element_to_be_clickable((By.ID, "id_edit_test_details_menu"))).click()
            wait.until(EC.element_to_be_clickable((By.ID, "func-add-test"))).click()
            time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.ID, "id_name"))).send_keys(test_name)
            wait.until(EC.element_to_be_clickable((By.ID, "id_attempts_allowed"))).send_keys("100")

            wait.until(EC.element_to_be_clickable((By.ID, "id_start_at_0"))).send_keys("12/12/2013")
            wait.until(EC.element_to_be_clickable((By.ID, "id_start_at_1"))).send_keys("12:12AM")
            wait.until(EC.element_to_be_clickable((By.ID, "id_end_at_0"))).send_keys("12/12/2016")
            wait.until(EC.element_to_be_clickable((By.ID, "id_end_at_1"))).send_keys("12:12AM")
            wait.until(EC.element_to_be_clickable((By.ID, "id_duration"))).send_keys("20")

            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, "func-save-test"))).click()
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False

    def add_single_answer_multiple_choice(self):
        step = "Instructor Flow: Add Questions"
        try:
            driver.execute_script("window.scrollTo(0, 0);")
            wait.until(EC.element_to_be_clickable((By.ID, "add_item"))).click()
            time.sleep(1)

            wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="qtypemodal"]/div/div/div[3]/button[2]'))).click()
            time.sleep(10) # add WebDriverWait
            elem2 = wait.until(EC.element_to_be_clickable((By.XPATH, '//iframe[@class="cke_wysiwyg_frame cke_reset"]')))
            driver.switch_to_frame(elem2)
            time.sleep(5)
            elem3=driver.find_element_by_xpath('//html/body')
            elem3.send_keys("Which is the largest continent?")

            driver.switch_to_default_content()


            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div/div/div[2]/div/form/div/label/div/input')))
            elem4.clear()
            elem4.send_keys("5")
            time.sleep(1)

            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="multiple-choice"]/div/div/textarea')))
            elem4.clear()
            elem4.send_keys("Asia")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="multiple-choice"]/div[2]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Africa")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="multiple-choice"]/div[3]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Europe")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="multiple-choice"]/div[4]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Antarctica")
            time.sleep(1)
            
            # mark the correct answer
            radio_button = driver.find_elements_by_class_name('specialRadio')[0]
            radio_button.find_element_by_tag_name('label').click()
            time.sleep(1)


            #driver.switch_to_alert().accept()
            #time.sleep(2)

            # save the question
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            save_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-question')))
            save_button.click()
            time.sleep(2)

            '''save_test_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-test')))
            save_test_button.click()
            time.sleep(2)'''

            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False

        assert status

    def add_multiple_answer_multiple_choice(self):
        step = "Instructor Flow: Add Questions"
        try:
            driver.execute_script("window.scrollTo(0, 0);")
            wait.until(EC.element_to_be_clickable((By.ID, "add_item"))).click()
            time.sleep(1)

            selecttest = Select(driver.find_element_by_id("q-type-select"))
            selecttest.select_by_visible_text("Multi Answer Multiple Choice")
            #time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="qtypemodal"]/div/div/div[3]/button[2]'))).click()
            time.sleep(3) # add WebDriverWait

            elem2 = wait.until(EC.element_to_be_clickable((By.XPATH, '//iframe[@class="cke_wysiwyg_frame cke_reset"]')))
            driver.switch_to_frame(elem2)
            time.sleep(5)
            elem3=driver.find_element_by_xpath('//html/body')
            elem3.send_keys("Which continents are joined?")

            driver.switch_to_default_content()

            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div/div/div[2]/div/form/div/label/div/input')))
            elem4.clear()
            elem4.send_keys("5")
            time.sleep(1)

            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div[2]/div/div[2]/div/form/div[4]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Asia")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div[2]/div/div[2]/div/form/div[5]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Africa")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div[2]/div/div[2]/div/form/div[6]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Europe")
            time.sleep(1)
            
            elem4 = wait.until(EC.element_to_be_clickable((By.XPATH,'//div[@id="accordion"]/div[2]/div/div[2]/div/form/div[7]/div/textarea')))
            elem4.clear()
            elem4.send_keys("Antarctica")
            time.sleep(1)
            
            # mark the correct answer
            radio_button = driver.find_elements_by_class_name('specialCheckbox')[0]
            radio_button.find_element_by_tag_name('label').click()
            radio_button = driver.find_elements_by_class_name('specialCheckbox')[1]
            radio_button.find_element_by_tag_name('label').click()
            time.sleep(1)


            # pdb.set_trace()
            #driver.switch_to_alert().accept()
            #time.sleep(2)

            # save the question
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            save_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-question')))
            save_button.click()
            time.sleep(2)

            '''save_test_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-test')))
            save_test_button.click()
            time.sleep(2)'''

            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False

        assert status

    def add_true_false(self):
        step = "Instructor Flow: Add Questions"
        try:
            driver.execute_script("window.scrollTo(0, 0);")
            wait.until(EC.element_to_be_clickable((By.ID, "add_item"))).click()
            time.sleep(1)

            selecttest = Select(driver.find_element_by_id("q-type-select"))
            selecttest.select_by_visible_text("True or False")
            #time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="qtypemodal"]/div/div/div[3]/button[2]'))).click()
            time.sleep(3) # add WebDriverWait

            elem2 = wait.until(EC.element_to_be_clickable((By.XPATH, '//iframe[@class="cke_wysiwyg_frame cke_reset"]')))
            driver.switch_to_frame(elem2)
            time.sleep(5)
            elem3=driver.find_element_by_xpath('//html/body')
            elem3.send_keys("Ocean currents are warm and cold.")

            driver.switch_to_default_content()
            time.sleep(1)
            
            # mark the correct answer
            #pdb.set_trace()
            radio_button1 = driver.find_elements_by_class_name('specialRadio')[0]
            radio_button2 = radio_button1.find_element_by_tag_name('label')
            driver.execute_script('window.scrollTo(0, {0})'.format(radio_button2.location['y']))
            radio_button2.click()
            time.sleep(1)


            # pdb.set_trace()
            #driver.switch_to_alert().accept()
            #time.sleep(2)

            # save the question
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            save_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-question')))
            save_button.click()
            time.sleep(2)
            status = True

        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def add_essay(self):
        step = "Instructor Flow: Add Questions"
        try:
            driver.execute_script("window.scrollTo(0, 0);")
            wait.until(EC.element_to_be_clickable((By.ID, "add_item"))).click()
            time.sleep(1)
            #pdb.set_trace()

            selecttest = Select(driver.find_element_by_id("q-type-select"))
            selecttest.select_by_visible_text("Essay")
            #time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="qtypemodal"]/div/div/div[3]/button[2]'))).click()
            time.sleep(3) # add WebDriverWait

            elem2 = wait.until(EC.element_to_be_clickable((By.XPATH, '//iframe[@class="cke_wysiwyg_frame cke_reset"]')))
            driver.switch_to_frame(elem2)
            time.sleep(5)
            elem3=driver.find_element_by_xpath('//html/body')
            elem3.send_keys("Explain different layers of atmosphere.")

            driver.switch_to_default_content()
            time.sleep(1)
            
            # save the question
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            save_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-question')))
            save_button.click()
            time.sleep(2)
            status = True

        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def add_fill_in_the_blanks(self):
        step = "Instructor Flow: Add Questions"
        try:
            driver.execute_script("window.scrollTo(0, 0);")
            wait.until(EC.element_to_be_clickable((By.ID, "add_item"))).click()
            time.sleep(1)
            #pdb.set_trace()

            selecttest = Select(driver.find_element_by_id("q-type-select"))
            selecttest.select_by_visible_text("Fill In The Blank")
            #time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="qtypemodal"]/div/div/div[3]/button[2]'))).click()
            time.sleep(3) # add WebDriverWait

            elem2 = wait.until(EC.element_to_be_clickable((By.XPATH, '//iframe[@class="cke_wysiwyg_frame cke_reset"]')))
            driver.switch_to_frame(elem2)
            time.sleep(5)
            elem3=driver.find_element_by_xpath('//html/body')
            elem3.send_keys("The name of oceans in order of decreasing size are: [[Pacific]], [[Atlantic]], [[Indian]], [[Antartic]]")

            driver.switch_to_default_content()
            time.sleep(1)
          
            # save the question
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            save_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-question')))
            save_button.click()
            time.sleep(2)
            status = True

        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def save_questions(self):
        step = "Save Questions"
        try:
            save_test_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-test')))
            driver.execute_script('window.scrollTo(0, {0})'.format(save_test_button.location['y']))
            save_test_button.click()
            time.sleep(2)

            save_test_button = wait.until(EC.element_to_be_clickable((By.ID,'func-save-test')))
            driver.execute_script('window.scrollTo(0, {0})'.format(save_test_button.location['y']))
            save_test_button.click()

            time.sleep(2)
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    '''def check_dash(self):
        if not self.status:
            return

        self.step = "Instructor Flow: Dashboard (Check Test Present)"
        try:
            self.driver.find_element_by_link_text('Dashboard').click()
            time.sleep(4)
            body = self.driver.find_element_by_tag_name('body').text
            if self.test_name in body:
                print "Successfully created test!"
                self.status = True
            else:
                print "Failed to create test!"
                self.status = False
        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status'''

    def configure_payment(self):
        self.driver.find_element_by_id('id_edit_menu').click()
        self.driver.find_element_by_id('id_edit_test_details_menu').click()
        self.driver.find_element_by_id('id_payments_menu').click()
        self.driver.implicitly_wait(5)
        # default will CC.
        input_number = self.driver.find_element_by_id('id_retail_price')
        input_number.send_keys('1')
        self.driver.find_element_by_id('func-add-edit-payment-type').click()

        self.driver.implicitly_wait(2)
        self.driver.find_element_by_id('ba-name').send_keys("John Doe")
        self.driver.find_element_by_id('ba-routing').send_keys("110000000")
        self.driver.find_element_by_id('ba-number').send_keys("000123456789")
        self.driver.find_element_by_id('ba-submit').click()

        success_text = "Success! Bank account verified. "

        WebDriverWait(self.driver, 30).until(EC.text_to_be_present_in_element(
            (By.XPATH, '//*[@id="response"]/div/div[2]/pre/p'), success_text))

        bank_reponse = self.driver.find_element_by_xpath('//*[@id="response"]/div/div[2]/pre/p').text
        assert bank_reponse == success_text

    def generate_registration_data_csv(self):
        self.step = "Instructor Flow: Generate Registration Data CSV"
        self.registration_data_csv_filepath = os.getcwd() + '/hosted_student_accounts.csv'
        print self.registration_data_csv_filepath
        status = True
        try:
            with open(self.registration_data_csv_filepath, 'wb') as csvfile:
                spamwriter = csv.writer(csvfile, delimiter=',', quoting=csv.QUOTE_MINIMAL)
                spamwriter.writerow(['StudentCode','First Name','Last Name','Email'])
                spamwriter.writerow([1, signup_data1['first_name'],
                                     signup_data1['last_name'], signup_data1['email']])
                spamwriter.writerow([2, signup_data2['first_name'],
                                     signup_data2['last_name'], signup_data2['email']])
        except csv.Error as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status

    def register_student(self):
        self.step = "Instructor Flow: Register Student"
        try:
            driver.get(server_url + '/tests/registrations/upload/')
            time.sleep(3)

            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            driver.find_element_by_id("funct-manage-{0}".format(test_name)).click()

            driver.find_element_by_id("funct-import-{0}".format(test_name)).click()
            time.sleep(3)

            #csv_file_field = self.driver.find_element_by_id('id_file')
            csv_file_field = wait.until(EC.element_to_be_clickable((By.ID,'id_file')))
            csv_file_field.send_keys(os.getcwd() + '/hosted_student_accounts.csv')
            time.sleep(2)

            upload_invites = driver.find_element_by_id('func-upload-invitations')
            driver.execute_script('window.scrollTo(0, {0})'.format(upload_invites.location['y']))
            upload_invites.click()
            time.sleep(2)
            status = True
            email_elem = wait.until(EC.element_to_be_clickable((By.XPATH,'//table/tbody/tr[2]/td[4]')))
            print email_elem.text
            assert("Email: anup@verificient.com"==email_elem.text)
            
            '''try:
                os.remove(os.getcwd() + '/hosted_student_accounts.csv')
            except OSError as e:
                logging.exception(e)
                pass'''

        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status

    def remove_student(self):
        #pdb.set_trace()
        step = "Remove all student registered from CSV file"
        try:
            #Remove first student
            wait.until(EC.element_to_be_clickable((By.XPATH,'//table/tbody/tr[2]/td[7]/a'))).click()
            wait.until(EC.element_to_be_clickable((By.XPATH,'//html/body/div[@class="container"]/div/div/form/input[2]'))).click()

            time.sleep(10)
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            time.sleep(3)
            driver.find_element_by_id("funct-manage-{0}".format(test_name)).click()
            time.sleep(3)

            driver.find_element_by_id("funct-registrants-{0}".format(test_name)).click()
            time.sleep(3)

            #Remove second studnent
            wait.until(EC.element_to_be_clickable((By.XPATH,'//table/tbody/tr[2]/td[7]/a'))).click()
            wait.until(EC.element_to_be_clickable((By.XPATH,'//html/body/div[@class="container"]/div/div/form/input[2]'))).click()



            status = True

        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except NoAlertPresentException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def register_student_direct(self):
        step = "Instructor Flow: Register Student"
        try:
            driver.get(server_url)
            time.sleep(3)

            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            list_test = driver.find_elements_by_xpath('//body/div[@class="container"][2]/div/div/table/tbody/tr')
            time.sleep(10)

            i = 0
            for last_elem in list_test:
                i = i+1
                if test_name in last_elem.text:
                    break
            print test_name + ": " + str(i)
            time.sleep(10)
            # pdb.set_trace()

            # self.driver.find_element_by_xpath('//body/div[6]/div/div/table/tbody/tr[' + str(i) + ']/td[7]/button').click()
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/table/tbody/tr[' + str(i) + ']/td[5]/button'))).click()
            # self.driver.find_element_by_xpath('//body/div[7]/div/div/table/tbody/tr[' + str(i) + ']/td[5]').click()
            time.sleep(5)
            driver.switch_to_alert().accept()
            time.sleep(2)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/table/tbody/tr[' + str(i) + ']/td[7]'))).click()
            #self.driver.find_element_by_xpath('//body/div[7]/div/div/table/tbody/tr[' + str(i) + ']/td[7]').click()
            time.sleep(3)
            register_student_button =  wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/table/tbody/tr['+str(i+1)+']/td/div/div/div[2]/a[3]')))
            driver.execute_script('window.scrollTo(0, {0})'.format(register_student_button.location['y']))
            register_student_button.click()
            # self.driver.find_element_by_xpath('//body/div[7]/div/div/table/tbody/tr['+str(i+1)+']/td/div/div/div[2]/a[3]').click()
            time.sleep(5)
            list_test = driver.find_elements_by_xpath('//body/div[@class="container"]/div/div/table/tbody/tr')
            time.sleep(10)

            i = 0
            for last_elem in list_test:
                i = i+1
                if test_name in last_elem.text:
                    break
            print "Registering Student - " + test_name + ": " +str(i)

            #pdb.set_trace()

            # view_detail = '//body/div[3]/div/div/table/tbody/tr['+str(i)+']/td[4]/div/a'
            manage_button = wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/table/tbody/tr['+str(i)+']/td[4]/div/a')))
            # self.driver.find_element_by_xpath('//body/div[4]/div/div/table/tbody/tr['+str(i)+']/td[4]/div/a')
            time.sleep(3)
            
            driver.execute_script('window.scrollTo(0, {0})'.format(manage_button.location['y']))


            # scroll_to_element(manage_button)
            manage_button.click()
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/table/tbody/tr['+str(i)+']/td[4]/div/ul/li[2]/a'))).click()
            # view_detail = '//body/div[4]/div/div/table/tbody/tr['+str(i)+']/td[4]/div/ul/li[2]/a'
            #driver.find_element_by_xpath(view_detail).click()
            time.sleep(3)

            # pdb.set_trace()

            wait.until(EC.element_to_be_clickable((By.ID, "id_student_first_name"))).send_keys('Hemant')
            wait.until(EC.element_to_be_clickable((By.ID, "id_student_last_name"))).send_keys('Kumar')
            wait.until(EC.element_to_be_clickable((By.ID, "id_student_id"))).send_keys('1234')
            wait.until(EC.element_to_be_clickable((By.ID, "id_student_email"))).send_keys(email)
            # submit_registration_details = wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[6]/div/div/div[2]/form/input[2]')))
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="container"]/div/div/div[2]/form/input[2]'))).click()
            time.sleep(10)
            status = True

            #self.driver.find_element_by_id("id_student_first_name".format(self.test_name)).send_keys('Test')
            #self.driver.find_element_by_id("id_student_last_name".format(self.test_name)).send_keys('Test')
            #self.driver.find_element_by_id("id_student_id".format(self.test_name)).send_keys('1234')
            #self.driver.find_element_by_id("id_student_email".format(self.test_name)).send_keys(email)
            # view_detail = '//body/div[5]/div/div/div[2]/form/p/input'
            #self.driver.find_element_by_xpath('//body/div[6]/div/div/div[2]/form/input[2]').click()

        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def switch_to_student_flow(self):
        step = "Instructor Flow: Change Role to Student"
        try:
            #time.sleep(10)
            wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'dropdown-toggle'))).click()
            #driver.find_element_by_class_name('dropdown-toggle').click()
            time.sleep(3)
            wait.until(EC.element_to_be_clickable((By.ID, 'func-change-role-to-student'))).click()
            #driver.find_element_by_id('func-change-role-to-student').click()
            time.sleep(3)
            if flow == 'instructor':
                driver.close()
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status

    def student_view(self):
        step = "Student Flow: Student View"
        # Try to change the role to Student if logged in as Insturctor.
        '''try:
            #wait.until(EC.element_to_be_clickable((By.CLASS_NAME, 'dropdown-toggle'))).click()
            time.sleep(5)
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/nav/div[2]/ul/li/a'))).click()
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/nav/div[2]/ul/li/ul/ul/li/a'))).click()


            #wait.until(EC.element_to_be_clickable((By.XPATH, '//body/nav/div[2]/ul/li/a'))).click()
            #driver.find_element_by_class_name('dropdown-toggle').click()
            time.sleep(3)
            driver.find_element_by_id('func-change-role-to-student').click()
            #wait.until(EC.element_to_be_clickable((By.ID, 'func-change-role-to-student'))).click()
        except NoSuchElementException as e:
            logging.exception(e)
            pass'''


        try:
            # Click on "View Tests" button
            wait.until(EC.element_to_be_clickable((By.ID, 'view-tests'))).click()
            time.sleep(10)
            list_test = driver.find_elements_by_xpath('//body/div[@class="content"]/div/div/div/table/tbody/tr')
            time.sleep(5)

            i = 0
            for last_elem in list_test:
                i = i+1
                if test_name in last_elem.text:
                    break
            print "Registering Student - " + test_name + ": " +str(i)

            continue_button = wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[@class="content"]/div/div/div/table/tbody/tr['+str(i)+']/td[6]/center/a')))
            time.sleep(3)
           
            driver.execute_script('window.scrollTo(0, {0})'.format(continue_button.location['y']))
            continue_button.click()
            time.sleep(4)
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    '''def student_payment(self):
        self.driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        self.driver.find_element_by_id("funct-pay-{0}".format(self.test_name)).click()
        self.driver.implicitly_wait(3)

        # Enter Students CC Details.
        self.driver.find_element_by_id('cc-number').send_keys("4111111111111111")
        self.driver.find_element_by_id('ex-cvv').send_keys("123")
        name_on_card = "{0} {1}".format(signup_data['first_name'], signup_data['last_name'])
        self.driver.find_element_by_id('cc-name').send_keys(name_on_card)
        self.driver.find_element_by_id('cc-ex-month').send_keys("08")
        self.driver.find_element_by_id('cc-ex-year').send_keys("2016")
        self.driver.find_element_by_id('ex-postal-code').send_keys("94301")
        self.driver.find_element_by_id('cc-submit').click()

        # Wait until confirm button is visible and clikable
        WebDriverWait(self.driver, 30).until(EC.element_to_be_clickable(
           (By.ID, "id_confirm_payment_btn")))

        # Check the disclaimer checkbox.
        confirm_checkbox = self.driver.find_element_by_id("id_confirm_chkbx")
        if not confirm_checkbox.is_selected():
           confirm_checkbox.click()
        assert confirm_checkbox.is_selected() == True

        # Confirm Payment
        self.driver.find_element_by_id('id_confirm_payment_btn').click()
        self.driver.implicitly_wait(5)'''

    def best_practices(self):
        step = "Student Flow: Best Practices"
        try:
            time.sleep(10)
            #Clickj on buttons for how it works and best practices
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.XPATH, '//div[@class="container"][1]/div/div/a'))).click()

            time.sleep(20)
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'confirm_checkbox_container'))).click()
            wait.until(EC.element_to_be_clickable((By.ID, 'next_step_button'))).click()
            # driver.find_element_by_id('checkbox1').click()
            # driver.find_element_by_id('next_step_button').click()
            status = True
            time.sleep(2)
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def download_launch_app(self):
        step = "Student Flow: Download APP"
        # pdb.set_trace()

        try:
            time.sleep(20)
            driver.execute_script("window.scrollTo(0, 400);")
            print "Downloading the app...."
            #launchapp('dev')
            time.sleep(90)
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status

    def download_app_sikuli(self):
        step = "Student Flow: Downloading APP"
        username = getpass.getuser()
        os_name = platform.system()
        if "Windows" in os_name:
            print "Into Windows loop"
            download_path = "C:\\Users\\" + username + "\\Downloads\\"
            print download_path
            app_name = "Proctortrack_2.0.66_0701.exe"
            path_to_app = download_path + app_name
        elif "Darwin" in os_name:
            username = getpass.getuser()
            download_path = "/Users/" + username + "/Downloads/"
            os.chdir(download_path)
            data_dir = "/Users/" + username + "Verificient"
            if(os.path.exists(data_dir)):shutil.rmtree(data_dir)
            zip_name = "ProctortrackFullOffline.zip"
            if "dev" in argv:
                zip_name = "ProctortrackFullOffline_dev.zip"
            path_to_app = download_path + "ProctortrackFullOffline.app"
        else:
            raise Exception("Unknown platform %s" % os_name)

        print "Launching App..."
        timeout = 0
        if "Windows" in os_name:
            while(timeout < 60 and not (os.path.isfile(app_name))):
                print "Downloading....."
                time.sleep(1)
                timeout+=5
            os.system("%s" % path_to_app)
        elif "Darwin" in os_name:
            while(timeout < 60 and not (os.path.isfile(zip_name))):
                print "Downloading..."
                time.sleep(1)
                timeout+=1
            print path_to_app
            print zip_name
            if(os.path.exists(path_to_app)): shutil.rmtree(path_to_app)
            os.system("unzip %s" % zip_name)
            os.system("open %s --args --autorun" % path_to_app)

    def launch_app_sikuli(self):
        step = "Student Flow: Lanuncing APP"
        try:
            print("Going to sleep for 40 secs... Before launching sikuli")
            time.sleep(40)
            os.chdir("C:/SikuliX")
            #os.system("dir")
            #os.system("runsikulix.cmd -r Proctortrack_Launch_App.sikuli --args Hemant Kumar")
            os.system("runsikulix.cmd -r ProctortrackLaunchApp_2066.sikuli --args Hemant Kumar")
            time.sleep(10)
            self.launch_test()
            self.take_test()
            time.sleep(10)
            #os.system("runsikulix.cmd -r Proctortrack_Terminate_App.sikuli")
            os.system("runsikulix.cmd -r ProctortrackTerminateAppHosted.sikuli")
            print "Completed Hosted Flow..."
            now_end = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            print 'Automation of Hosted - Finish time: ' + str(now_end)
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            self.error = e
            self.status = False
        except Exception as e:
            logging.exception(e)
            self.error = e
            self.status = False
        assert status

    def launch_test(self):
        step = "Student Flow: Launch Test"
        try:
            time.sleep(10)
            wait.until(EC.element_to_be_clickable((By.ID, 'id_launch_test'))).click()
            #driver.find_element_by_id('id_launch_test').click()
            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
#            self.status = False
        except Exception as e:
            logging.exception(e)
            error = e
#            self.status = False
        assert status

    def take_test(self):
        step = "Student Flow: Take Test"
        try:
            # pdb.set_trace()
            #wait.until(EC.element_to_be_clickable((By.ID, 'id_launch_test'))).click()
            time.sleep(20)
            #Attempting Single Correct Multiple Choice question
            driver.find_elements_by_id("id_answer_choice")[0].click()
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'id_question_next'))).click()
            time.sleep(5)

            #Attempting Multiple Correct Multiple Choice question
            driver.find_elements_by_id("id_answer_choice")[0].click()
            driver.find_elements_by_id("id_answer_choice")[1].click()
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'id_question_next'))).click()
            time.sleep(5)

            #Attempting True false question
            driver.find_elements_by_id("id_answer_choice")[0].click()
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'id_question_next'))).click()
            time.sleep(5)

            #Attempting Essay question
            essay_elem = wait.until(EC.element_to_be_clickable((By.XPATH, '//div[@id="testApp"]/div[4]/div/div[2]/div/div/div/div[2]/div[2]/div/div/textarea')))
            essay_elem.send_keys("The Different Layers of the Atmosphere are - Troposphere, Stratosphere, Mesosphere, Thermosphere, Exosphere and Ionosphere.")
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'id_question_next'))).click()
            time.sleep(5)

            #Attempting Fill in the blanks question
            wait.until(EC.element_to_be_clickable((By.XPATH, '//ul[@class="list-unstyled"]/li[1]/div/input'))).send_keys("Pacific")
            wait.until(EC.element_to_be_clickable((By.XPATH, '//ul[@class="list-unstyled"]/li[2]/div/input'))).send_keys("Atlantic")
            wait.until(EC.element_to_be_clickable((By.XPATH, '//ul[@class="list-unstyled"]/li[3]/div/input'))).send_keys("Indian")
            wait.until(EC.element_to_be_clickable((By.XPATH, '//ul[@class="list-unstyled"]/li[4]/div/input'))).send_keys("Antartic")

            time.sleep(2)
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            wait.until(EC.element_to_be_clickable((By.ID, 'id_question_next'))).click()
            time.sleep(5)

            # driver.switch_to_alert().accept()
            wait.until(EC.element_to_be_clickable((By.XPATH, '//body/div[3]/div[4]/div/div[2]/div/div/div/div[3]/div/div/button[2]'))).click()

            # self.driver.find_element_by_id('id_question_next').click()

            # To be able to see the Success Page
            now_end = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            if auto_run == "true":
                print "Completed Hosted Flow..."
                time.sleep(205)
                print 'Automation of Hosted - Finish time: ' + str(now_end)

            # driver.close()
            #self.student_end_proctoring(k_test_vars)

            status = True
        except NoSuchElementException as e:
            logging.exception(e)
            error = e
            status = False
        except Exception as e:
            logging.exception(e)
            error = e
            status = False
        assert status

    def student_end_proctoring(self, k_test_vars):
        print "inside end proctoring step 1"
        siki = os.getcwd() + k_test_vars["current_sikuli_path"]
        print "inside end proctoring step 2"
        os.system("jython %s EndProctoring dev" % siki)
        print "called end proctoring"

    def _display_status(self):
        print "\nTest Execution Status: ", self.status
        if not self.status:
            print "\nError occured: ", self.error
            print "\nFailed Step: ", self.step
        assert self.error == ''

    def scroll_to_element(element):
        y = element.location['y']
        driver.execute_script('window.scrollTo(0, {0})'.format(y))  # scrolls element into view to make it clickable

    def __del__(self):
        #driver.close()
        print '########### Automation of Hosted - Finished.  ###############'

if __name__ == '__main__':
    # HostedFlowTestWithPayment()
    e = HostedFlowTestWithPayment()
    e.run_tests()
