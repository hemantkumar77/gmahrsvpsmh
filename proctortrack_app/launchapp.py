import getpass
import os
import platform
import sys
import shutil
import time


def launchapp(*argv, **kwargs):
    username = getpass.getuser()
    os_name = platform.system()
    if "Windows" in os_name:
        download_path = "C:\\Users\\" + username + "\\Downloads\\"
        print download_path
        app_name = "ProctortrackFullOffline_dev.exe"
        if "dev" in argv:
            app_name = "ProctortrackFullOffline_dev.exe"
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

    print "Launching App"
    timeout = 0
    if "Windows" in os_name:
        while(timeout < 60 and not (os.path.isfile(app_name))):
            print "Downloading..."
            time.sleep(1)
            timeout+=1
        os.system("%s --autorun" % path_to_app)
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
