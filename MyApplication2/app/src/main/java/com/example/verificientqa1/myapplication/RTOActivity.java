package com.example.verificientqa1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class RTOActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    TextView txtSummary;//=(TextView)findViewById(R.id.textView1);
    EditText etRTOState, etRTOCode;
    String s1="";
    String str11=null;
    String fileName = "hemant.xml";
    String[] strStateCode={"AN","AP","AR","AS","BR","CH","CG","DD","DL","DN","GA","HP","HR","JH","JK","KA","KL","LD","MH","ML","MN","MP","MZ","NL","OR","PB","PY","RJ","SK","TN","TR","UA","UP","WB"};
    String strRTOCode[] = new String[100];
    Spinner spnStateCode, spnRTOCode;
    static final int DATE_DIALOG_ID = 1;
    int intRTOCode;
    String strState;

    String strAN[] = new String[3];
    String strAP[] = new String[38];
    String strAR[] = new String[15];
    String strAS[] = new String[32];
    String strBR[] = new String[57];
    String strCG[] = new String[21];
    String strCH[] = new String[5];
    String strDD[] = new String[4];
    String strDL[] = new String[19];
    String strDN[] = new String[2];
    String strGA[] = new String[12];
    String strGJ[] = new String[31];
    String strHP[] = new String[77];
    String strHR[] = new String[89];
    String strJH[] = new String[23];
    String strJK[] = new String[23];
    String strKA[] = new String[58];
    String strKL[] = new String[66];
    String strLD[] = new String[2];
    String strMH[] = new String[51];
    String strML[] = new String[11];
    String strMN[] = new String[5];
    String strMP[] = new String[70];
    String strMZ[] = new String[9];
    String strNL[] = new String[9];
    String strOR[] = new String[32];
    String strPB[] = new String[76];
    String strPY[] = new String[5];
    String strRJ[] = new String[39];
    String strSK[] = new String[5];
    String strTN[] = new String[79];
    String strTR[] = new String[4];
    String strUK[] = new String[14];
    String strUP[] = new String[97];
    String strWB[] = new String[80];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rto);
        txtSummary=(TextView)findViewById(R.id.txtSummary);
        etRTOState=(EditText)findViewById(R.id.rtoState);
        etRTOState.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etRTOState, InputMethodManager.SHOW_IMPLICIT);

        etRTOCode=(EditText)findViewById(R.id.rtoCode);

        strAN[0]="Andaman and Nicobar Islands";
        strAP[0]="Andhra Pradesh";
        strAR[0]="Arunachal Pradesh";
        strAS[0]="Assam";
        strBR[0]="Bihar";
        strCG[0]="Chhattisgarh";
        strCH[0]="Chandigarh";
        strDD[0]="Daman and Diu";
        strDL[0]="Delhi";
        strDN[0]="Dadra and Nagar Haveli";
        strGA[0]="Goa";
        strGJ[0]="Gujarat";
        strHP[0]="Himachal Pradesh";
        strHR[0]="Haryana";
        strJH[0]="Jharkhand";
        strJK[0]="Jammu and Kashmir";
        strKA[0]="Karnataka";
        strKL[0]="Kerala";
        strLD[0]="Lakshadweep";
        strMH[0]="Maharashtra";
        strML[0]="Meghalaya";
        strMN[0]="Manipur";
        strMP[0]="Madhya Pradesh";
        strMZ[0]="Mizoram";
        strNL[0]="Nagaland";
        strOR[0]="Orissa";
        strPB[0]="Punjab";
        strPY[0]="Pondicherry";
        strRJ[0]="Rajasthan";
        strSK[0]="Sikkim";
        strTN[0]="Tamil Nadu";
        strTR[0]="Tripura";
        strUK[0]="Uttarakhand";
        strUP[0]="Uttar Pradesh";
        strWB[0]="West Bengal";

        strAN[0] = "Andaman District";
        strAN[1] = "Port Blair, Andaman District";
        strAN[2] = "Car Nicobar, Nicobar District";

        strAP[1] = "Adilabad / Mancherial / Nirmal";
        strAP[2] = "Anantapur / Hindupur";
        strAP[3] = "Chittoor / Tirupati / Madanapalle / Rajampet";
        strAP[4] = "Kadapa / Proddatur";
        strAP[5] = "Kakinada / Amalapuram / Rajahmundry";
        strAP[6] = "Kakinada";
        strAP[7] = "Guntur / Piduguralla / Narasaraopet / Repalle";
        strAP[8] = "Guntur";
        strAP[9] = "Khairtabad, Hyderabad District";
        strAP[10] = "Secunderabad, Hyderabad District";
        strAP[11] = "Malakpet, Hyderabad District";
        strAP[12] = "Bahadurpura, Hyderabad District";
        strAP[13] = "Mehdipatnam, Hyderabad District";
        strAP[14] = "Karimnagar";
        strAP[15] = "Karimnagar / Jagtial / Peddapalli";
        strAP[16] = "Bejawada / Gudivada / Machilipatnam / Nandigama";
        strAP[17] = "Bejawada / Gudivada / Machilipatnam / Nandigama";
        strAP[18] = "Nuzvid";
        strAP[19] = "Nuzvid";
        strAP[20] = "Khammam / Kothagudem / Sathupally / Yellandu";
        strAP[21] = "Kurnool / Adoni / Nandyal";
        strAP[22] = "Mahaboobnagar / Pebbair / Gadwal";
        strAP[23] = "Medak / Siddipet / Sangareddy (RC puram)";
        strAP[24] = "Nalgonda / Bhongir / Suryapet";
        strAP[25] = "Nizamabad / Kamareddy";
        strAP[26] = "Nellore / Kavali / Gudur / Sullurpeta";
        strAP[27] = "Ongole / Chirala / Markapur / Prakasam";
        strAP[28] = "Rangareddy";
        strAP[29] = "Rangareddy";
        strAP[30] = "Srikakulam /Tekkali /Palasa/Sompeta/Ichchapuram/Kaviti";
        strAP[31] = "Visakhapatnam / Gajuwaka / Anakapalli";
        strAP[32] = "Visakhapatnam";
        strAP[33] = "Gajuwaka";
        strAP[34] = "Gajuwaka";
        strAP[35] = "Vizianagaram";
        strAP[36] = "Warangal / Jangaon / Mahabubabad";
        strAP[37] = "Eluru / Jangareddygudem / Kovvur / Tadepalligudem / Tanuku / Bhimavaram / Palakollu / Chintalapudi";

        strAR[1] = "Itanagar";
        strAR[2] = "Itanagar (commercial vehicles of state transport)";
        strAR[3] = "Tawang";
        strAR[4] = "Bomdila";
        strAR[5] = "Seppa";
        strAR[6] = "NA";
        strAR[7] = "Daporijo";
        strAR[8] = "Along";
        strAR[9] = "Pasighat";
        strAR[10] = "Anini";
        strAR[11] = "Tezu";
        strAR[12] = "Changlang";
        strAR[13] = "Khonsa";
        strAR[14] = "Yingkiong";

        strAS[1] = "Guwahati, Kamrup Metropolitan and Rural";
        strAS[2] = "Nagaon";
        strAS[3] = "Jorhat";
        strAS[4] = "Sibsagar";
        strAS[5] = "Golaghat";
        strAS[6] = "Dibrugarh";
        strAS[7] = "Lakhimpur district";
        strAS[8] = "Dima Hasao";
        strAS[9] = "Karbi Anglong";
        strAS[10] = "Karimganj";
        strAS[11] = "Cachar";
        strAS[12] = "Sonitpur";
        strAS[13] = "Darrang";
        strAS[14] = "Nalbari";
        strAS[15] = "Barpeta";
        strAS[16] = "Kokrajhar";
        strAS[17] = "Dhubri";
        strAS[18] = "Goalpara";
        strAS[19] = "Bongaigaon";
        strAS[20] = "Assam State Transport Corporation (ASTC)";
        strAS[21] = "Marigaon";
        strAS[22] = "Dhemaji";
        strAS[23] = "Tinsukia";
        strAS[24] = "Hailakandi";
        strAS[25] = "Guwahati";
        strAS[26] = "Udalguri";
        strAS[27] = "NA";
        strAS[28] = "NA";
        strAS[29] = "NA";
        strAS[30] = "Assam Police vehicles";
        strAS[31] = "Assam Police";

        strBR[1] = "Patna";
        strBR[2] = "Gaya";
        strBR[3] = "Bhojpur";
        strBR[4] = "Chapra";
        strBR[5] = "Motihari";
        strBR[6] = "Muzaffarpur";
        strBR[7] = "Darbhanga";
        strBR[8] = "Munger";
        strBR[9] = "Begusarai";
        strBR[10] = "Bhagalpur";
        strBR[11] = "Purnea";
        strBR[12] = "NA";
        strBR[13] = "NA";
        strBR[14] = "NA";
        strBR[15] = "NA";
        strBR[16] = "NA";
        strBR[17] = "NA";
        strBR[18] = "Chaibasa";
        strBR[19] = "Saharsa";
        strBR[20] = "(not allotted after Jharkhand Division)";
        strBR[21] = "Nalanda";
        strBR[22] = "Bettiah";
        strBR[23] = "Giridih";
        strBR[24] = "Dehri";
        strBR[25] = "Jehanabad";
        strBR[26] = "Aurangabad";
        strBR[27] = "Nawada";
        strBR[28] = "Gopalganj";
        strBR[29] = "Siwan";
        strBR[30] = "Sitamarhi";
        strBR[31] = "Vaishali district";
        strBR[32] = "Madhubani";
        strBR[33] = "Samastipur";
        strBR[34] = "Khagaria";
        strBR[35] = "(not allotted after Jharkhand Division)";
        strBR[36] = "(not allotted after Jharkhand Division)";
        strBR[37] = "Kishanganj";
        strBR[38] = "Araria";
        strBR[39] = "Katihar";
        strBR[40] = "(not allotted after Jharkhand Division)";
        strBR[41] = "(not allotted after Jharkhand Division)";
        strBR[42] = "(not allotted after Jharkhand Division)";
        strBR[43] = "Madhepura";
        strBR[44] = "Buxar";
        strBR[45] = "Bhabua";
        strBR[46] = "Jamui";
        strBR[47] = "Koderma";
        strBR[48] = "NA";
        strBR[49] = "NA";
        strBR[50] = "Supaul";
        strBR[51] = "Banka";
        strBR[52] = "Sheikhpura";
        strBR[53] = "Lakhisarai";
        strBR[54] = "(not allotted)";
        strBR[55] = "(not allotted)";
        strBR[56] = "Arwal";

        strCG[1] = "Governor of Chattisgarh";
        strCG[2] = "Government of Chattisgarh";
        strCG[3] = "Chattisgarh Police";
        strCG[4] = "Raipur";
        strCG[5] = "Dhamtari";
        strCG[6] = "Mahasamund";
        strCG[7] = "Durg";
        strCG[8] = "Rajnandgaon";
        strCG[9] = "Kawardha";
        strCG[10] = "Bilaspur";
        strCG[11] = "Janjgir Champa";
        strCG[12] = "Korba";
        strCG[13] = "Raigarh";
        strCG[14] = "Jashpur";
        strCG[15] = "Ambikapur";
        strCG[16] = "Baikunthpur";
        strCG[17] = "Jagdalpur";
        strCG[18] = "Dantewada";
        strCG[19] = "Kanker";
        strCG[20] = "Bijapur";

        strCH[1] = "Chandigarh";
        strCH[2] = "Chandigarh ";
        strCH[3] = "Chandigarh";
        strCH[4] = "Chandigarh";

        strDD[1] = "N/A";
        strDD[2] = "Diu";
        strDD[3] = "Daman";

        strDL[1] = "Delhi North: Mall Road";
        strDL[2] = "New Delhi: Tilak Marg";
        strDL[3] = "South Delhi South: Sheikh Sarai";
        strDL[4] = "West Delhi West 1: West Janakpuri";
        strDL[5] = "North East Delhi: Loni Road";
        strDL[6] = "Central Delhi Central: Sarai Kale Khan";
        strDL[7] = "East Delhi East 1: Mayur Vihar";
        strDL[8] = "North West Delhi 1: Wazir Pur";
        strDL[9] = "South West Delhi South West 1: Janakpuri / Palam";
        strDL[10] = "West Delhi";
        strDL[11] = "North West Delhi North West Zone II: Rohini";
        strDL[12] = "South West Delhi South West II: Vasant Vihar";
        strDL[13] = "East Delhi II: Surajmal Vihar / Shahdara";
        strDL[14] = "Sonipat due to the NCR, effectively situated in Haryana";
        strDL[15] = "Gurgaon due to the NCR, effectively situated in Haryana";
        strDL[16] = "Faridabad due to the NCR, effectively situated in Haryana";
        strDL[17] = "Noida due to the NCR, effectively situated in Uttar Pradesh";
        strDL[18] = "Ghaziabad due to the NCR, effectively situated in Uttar Pradesh";

        strDN[1] = "Silvassa";

        strGA[1] = "Panaji";
        strGA[2] = "Margao";
        strGA[3] = "Mapusa";
        strGA[4] = "Bicholim";
        strGA[5] = "Ponda";
        strGA[6] = "Vasco da Gama";
        strGA[7] = "Panaji";
        strGA[8] = "Margao";
        strGA[9] = "Quepem";
        strGA[10] = "Canacona";
        strGA[11] = "Pernem";

        strGJ[1] = "Ahmedabad";
        strGJ[2] = "Mehsana";
        strGJ[3] = "Rajkot";
        strGJ[4] = "Bhavnagar";
        strGJ[5] = "Surat";
        strGJ[6] = "Vadodara";
        strGJ[7] = "Kheda";
        strGJ[8] = "Palanpur";
        strGJ[9] = "Sabarkantha";
        strGJ[10] = "Jamnagar";
        strGJ[11] = "Junagadh";
        strGJ[12] = "Kutch";
        strGJ[13] = "Surendranagar";
        strGJ[14] = "Amreli";
        strGJ[15] = "Valsad";
        strGJ[16] = "Bharuch";
        strGJ[17] = "Panchmahal (Godhara)";
        strGJ[18] = "Gandhinagar,";
        strGJ[19] = "Bardoli";
        strGJ[20] = "Dahod";
        strGJ[21] = "Navsari";
        strGJ[22] = "Narmada";
        strGJ[23] = "Anand";
        strGJ[24] = "Patan";
        strGJ[25] = "Porbandar";
        strGJ[26] = "Vyara";
        strGJ[27] = "East Ahmedabad";
        strGJ[28] = "West Surat";
        strGJ[29] = "Vadodara rural";
        strGJ[30] = "Dang Ahwa";

        strHP[1] = "Shimla Urban (tourist buses and taxis)";
        strHP[2] = "Shimla Urban (tourist buses and taxis)";
        strHP[3] = "Shimla Urban";
        strHP[4] = "Dharamsala (auto rikshaws)";
        strHP[5] = "Mandi (auto rikshaws)";
        strHP[6] = "Rampur Bushar";
        strHP[7] = "Shimla";
        strHP[8] = "Chaupal";
        strHP[9] = "Theog";
        strHP[10] = "Rohru";
        strHP[11] = "Arki";
        strHP[12] = "Nalagarh";
        strHP[13] = "Kandaghat";
        strHP[14] = "Solan";
        strHP[15] = "Parwanoo";
        strHP[16] = "Rajgarh";
        strHP[17] = "Paonta Sahib";
        strHP[18] = "Nahan";
        strHP[19] = "Amb";
        strHP[20] = "Una";
        strHP[21] = "Barsar";
        strHP[22] = "Hamirpur";
        strHP[23] = "Ghumarwin";
        strHP[24] = "Bilaspur";
        strHP[25] = "Kalpa";
        strHP[26] = "Nichar (Bhaba Nagar)";
        strHP[27] = "Pooh";
        strHP[28] = "Sarkaghat";
        strHP[29] = "Jogindernagar";
        strHP[30] = "Karsog";
        strHP[31] = "Sundernagar";
        strHP[32] = "Mandi";
        strHP[33] = "Mandi";
        strHP[34] = "Kullu";
        strHP[35] = "Anni";
        strHP[36] = "Dehra";
        strHP[37] = "Palampur";
        strHP[38] = "Nurpur";
        strHP[39] = "Dharamshala";
        strHP[40] = "Kangra";
        strHP[41] = "Kaza";
        strHP[42] = "Keylong";
        strHP[43] = "Udaipur";
        strHP[44] = "Churah";
        strHP[45] = "Pangi";
        strHP[46] = "Bharmour";
        strHP[47] = "Dalhousie";
        strHP[48] = "Chamba";
        strHP[49] = "Banjar";
        strHP[50] = "Shimla (auto rikshaws)";
        strHP[51] = "HP Shimla (rural)";
        strHP[52] = "Shimla (rural)";
        strHP[53] = "Baijnath";
        strHP[54] = "Jawali";
        strHP[55] = "Nadaun / Hamirpur";
        strHP[56] = "Jaisinghpur";
        strHP[57] = "Chowari";
        strHP[58] = "Manali";
        strHP[59] = "N/A";
        strHP[60] = "N/A";
        strHP[61] = "N/A";
        strHP[62] = "DodraKwar";
        strHP[63] = "Shimla";
        strHP[64] = "Solan";
        strHP[65] = "Mandi";
        strHP[66] = "Kullu";
        strHP[67] = "Hamirpur";
        strHP[68] = "Kangra";
        strHP[69] = "Bilaspur";
        strHP[70] = "N/A";
        strHP[71] = "Nahan";
        strHP[72] = "Una";
        strHP[73] = "Chamba";
        strHP[74] = "Bhoranj";
        strHP[75] = "N/A";
        strHP[76] = "Paddhar";

        strHR[1] = "Ambala";
        strHR[2] = "Yamuna Nagar";
        strHR[3] = "Panchkula";
        strHR[4] = "Naraingarh";
        strHR[5] = "Karnal";
        strHR[6] = "Panipat";
        strHR[7] = "Kurukshetra";
        strHR[8] = "Kaithal";
        strHR[9] = "Guhla";
        strHR[10] = "Sonepat";
        strHR[11] = "Gohana";
        strHR[12] = "Rohtak";
        strHR[13] = "Bahadurgarh";
        strHR[14] = "Jhajjar";
        strHR[15] = "Meham";
        strHR[16] = "Bhiwani";
        strHR[17] = "Siwani";
        strHR[18] = "Loharu";
        strHR[19] = "Charkhi Dadri";
        strHR[20] = "Hisar";
        strHR[21] = "Hansi";
        strHR[22] = "Fatehabad";
        strHR[23] = "Tohana";
        strHR[24] = "Sirsa";
        strHR[25] = "Dabwali";
        strHR[26] = "Gurgaon";
        strHR[27] = "Nuh";
        strHR[28] = "Ferozepur Jhirka";
        strHR[29] = "Ballabgarh";
        strHR[30] = "Palwal";
        strHR[31] = "Jind";
        strHR[32] = "Narwana";
        strHR[33] = "Safidon";
        strHR[34] = "Mohindergarh";
        strHR[35] = "Narnaul";
        strHR[36] = "Rewari";
        strHR[37] = "Ambala (commercial vehicles only)";
        strHR[38] = "Faridabad (commercial vehicles only)";
        strHR[39] = "Hisar (commercial vehicles only)";
        strHR[40] = "Assandh";
        strHR[41] = "Pehowa";
        strHR[42] = "Ganaur";
        strHR[43] = "Kosli";
        strHR[44] = "Ellenabad";
        strHR[45] = "Karnal (commercial vehicles only)";
        strHR[46] = "Rohtak (commercial vehicles only)";
        strHR[47] = "Rewari (commercial vehicles only)";
        strHR[48] = "Tosham";
        strHR[49] = "Kalka";
        strHR[50] = "Hodal";
        strHR[51] = "Faridabad";
        strHR[52] = "Hatin";
        strHR[53] = "Adampur";
        strHR[54] = "Ambala Barara";
        strHR[55] = "Gurgaon (commercial vehicles only)";
        strHR[56] = "Jind (commercial vehicles only)";
        strHR[57] = "Sirsa (commercial vehicles only)";
        strHR[58] = "Jagadhari, Yamuna Nagar (commercial vehicles only)";
        strHR[59] = "Ratia";
        strHR[60] = "Samalkha";
        strHR[61] = "Bhiwani (commercial vehicles only)";
        strHR[62] = "Fatehabad (commercial vehicles only)";
        strHR[63] = "Jhajjar (commercial vehicles only)";
        strHR[64] = "Kaithal (commercial vehicles only)";
        strHR[65] = "Kurukshetra (commercial vehicles only)";
        strHR[66] = "Narnaul (commercial vehicles only)";
        strHR[67] = "Panipat (commercial vehicles only)";
        strHR[68] = "Panchkula (commercial vehicles only)";
        strHR[69] = "Sonipat (commercial vehicles only)";
        strHR[70] = "Chandigardh";
        strHR[71] = "Bilaspur";
        strHR[72] = "Gurgaon (South)";
        strHR[73] = "Palwal";
        strHR[74] = "Mewat (Nuh)";
        strHR[75] = "Indri";
        strHR[76] = "Pataudi";
        strHR[77] = "Beri";
        strHR[78] = "Sahabad";
        strHR[79] = "Kharkhoda";
        strHR[80] = "Barwala";
        strHR[81] = "N/A";
        strHR[82] = "N/A";
        strHR[83] = "N/A";
        strHR[84] = "N/A";
        strHR[85] = "N/A";
        strHR[86] = "Sonipat";
        strHR[87] = "N/A";
        strHR[88] = "Kundli";

        strJH[1] = "Ranchi";
        strJH[2] = "Hazaribagh / Ramgarh";
        strJH[3] = "Daltonganj";
        strJH[4] = "Dumka";
        strJH[5] = "Jamshedpur";
        strJH[6] = "Chaibasa";
        strJH[7] = "Gumla";
        strJH[8] = "Lohardaga";
        strJH[9] = "Bokaro Steel City";
        strJH[10] = "Dhanbad";
        strJH[11] = "Giridih";
        strJH[12] = "[[Koderma]";
        strJH[13] = "Chatra";
        strJH[14] = "Garhwa";
        strJH[15] = "Deoghar";
        strJH[16] = "Pakur";
        strJH[17] = "Godda";
        strJH[18] = "Sahibganj";
        strJH[19] = "Latehar";
        strJH[20] = "Simdega";
        strJH[21] = "Jamtara";
        strJH[22] = "Saraikela-Kharsawan";

        strJK[1] = "Srinagar";
        strJK[2] = "Jammu";
        strJK[3] = "Anantnag";
        strJK[4] = "Budgam";
        strJK[5] = "Baramulla";
        strJK[6] = "Doda";
        strJK[7] = "Kargil";
        strJK[8] = "Kathua";
        strJK[9] = "Kupwara";
        strJK[10] = "Leh";
        strJK[11] = "Rajouri";
        strJK[12] = "Poonch";
        strJK[13] = "Pulwama";
        strJK[14] = "Udhampur";
        strJK[15] = "Bandipora";
        strJK[16] = "Ganderbal";
        strJK[17] = "Kishtwar";
        strJK[18] = "Kulgam";
        strJK[19] = "Ramban";
        strJK[20] = "Reasi";
        strJK[21] = "Samba";
        strJK[22] = "Shopian";

        strKA[1] = "Bangalore Central (Koramangala)";
        strKA[2] = "Bangalore West (Rajajinagar)";
        strKA[3] = "Bangalore East (Indiranagar)";
        strKA[4] = "Bangalore North (Yeshwanthpur)";
        strKA[5] = "Bangalore South (Jayanagar)]]";
        strKA[6] = "Tumkur";
        strKA[7] = "Kolar";
        strKA[8] = "Kolar Gold Fields (KGF)";
        strKA[9] = "Mysore West";
        strKA[10] = "Chamrajnagar";
        strKA[11] = "Mandya";
        strKA[12] = "Madikeri";
        strKA[13] = "Hassan";
        strKA[14] = "Shimoga";
        strKA[15] = "Sagara";
        strKA[16] = "Chitradurga";
        strKA[17] = "Davangere";
        strKA[18] = "Chickmagalur";
        strKA[19] = "Mangalore";
        strKA[20] = "Udupi";
        strKA[21] = "Puttur / Sullia";
        strKA[22] = "Belgaum";
        strKA[23] = "Chikkodi";
        strKA[24] = "Bailhongal";
        strKA[25] = "Dharwad";
        strKA[26] = "Gadag";
        strKA[27] = "Haveri";
        strKA[28] = "Bijapur";
        strKA[29] = "Bagalkot";
        strKA[30] = "Karwar";
        strKA[31] = "Sirsi";
        strKA[32] = "Gulbarga";
        strKA[33] = "Yadgir";
        strKA[34] = "Bellary";
        strKA[35] = "Hospet";
        strKA[36] = "Raichur";
        strKA[37] = "Koppal";
        strKA[38] = "Bidar";
        strKA[39] = "Bhalki";
        strKA[40] = "Chikkaballapur";
        strKA[41] = "Rajarajeshwari Nagar, Bangalore Urban district";
        strKA[42] = "Ramanagara";
        strKA[43] = "Devanahalli, Bangalore Rural district";
        strKA[44] = "Tiptur";
        strKA[45] = "Hunsur";
        strKA[46] = "Sakleshpur";
        strKA[47] = "Honnavar";
        strKA[48] = "Jamkhandi";
        strKA[49] = "Gokak";
        strKA[50] = "Yelahanka, Bangalore Urban district";
        strKA[51] = "Electronics City, Bangalore Urban district";
        strKA[52] = "Nelamangala, Bangalore Urban district";
        strKA[53] = "K.R. Puram, Bangalore Urban district";
        strKA[54] = "Nagamangala";
        strKA[55] = "Mysore East";
        strKA[56] = "Basavakalyan";
        strKA[57] = "Shantinagar";

        strKL[1] = "Thiruvananthapuram City";
        strKL[2] = "Kollam";
        strKL[3] = "Pathanamthitta";
        strKL[4] = "Alappuzha";
        strKL[5] = "Kottayam";
        strKL[6] = "Idukki";
        strKL[7] = "Eranakulam";
        strKL[8] = "Thrissur";
        strKL[9] = "Palakkad";
        strKL[10] = "Malappuram";
        strKL[11] = "Kozhikode";
        strKL[12] = "Wayanad";
        strKL[13] = "Kannur";
        strKL[14] = "Kasargode";
        strKL[15] = "Thiruvananthapuram";
        strKL[16] = "Thiruvananthapuram Attingal";
        strKL[17] = "Muvattupuzha";
        strKL[18] = "Vadakara";
        strKL[19] = "Thiruvananthapuram Parassala";
        strKL[20] = "Thiruvananthapuram Neyyattinkara";
        strKL[21] = "Thiruvananthapuram Nedumangad";
        strKL[22] = "Kazhakkoottam";
        strKL[23] = "Karunagapally";
        strKL[24] = "Kottarakara";
        strKL[25] = "Punalur";
        strKL[26] = "Adoor";
        strKL[27] = "Thiruvalla";
        strKL[28] = "Mallappally";
        strKL[29] = "Kayamkulam";
        strKL[30] = "Chengannur";
        strKL[31] = "Mavelikara";
        strKL[32] = "Cherthala";
        strKL[33] = "Changanassery";
        strKL[34] = "Kanjirappally";
        strKL[35] = "Pala";
        strKL[36] = "Vaikom";
        strKL[37] = "Vandiperiyar";
        strKL[38] = "Thodupuzha";
        strKL[39] = "Thripunithura";
        strKL[40] = "Perumbavoor";
        strKL[41] = "Aluva";
        strKL[42] = "North Paravur";
        strKL[43] = "Mattancherry";
        strKL[44] = "kothamangalam";
        strKL[45] = "Irinjalakuda";
        strKL[46] = "Guruvayur";
        strKL[47] = "Kodungallur";
        strKL[48] = "Wadakkancherry";
        strKL[49] = "Alathur";
        strKL[50] = "Mannarghat";
        strKL[51] = "Ottappalam";
        strKL[52] = "Pattambi";
        strKL[53] = "Perinthalmanna";
        strKL[54] = "Ponnani";
        strKL[55] = "Tirur";
        strKL[56] = "Koyilandi";
        strKL[57] = "Koduvally";
        strKL[58] = "Thalassery";
        strKL[59] = "Thaliparamba";
        strKL[60] = "Kanhangad";
        strKL[61] = "Kunnathur";
        strKL[62] = "Ranni";
        strKL[63] = "Angamaly";
        strKL[64] = "Chalakkudy";
        strKL[65] = "Thirurangadi";

        strLD[1] = "Kavaratti";

        strMH[1] = "Mumbai (Central) ";
        strMH[2] = "Mumbai (West)";
        strMH[3] = "Mumbai (East)";
        strMH[4] = "Thane";
        strMH[5] = "Thane";
        strMH[6] = "Raigad";
        strMH[7] = "Sindhudurg";
        strMH[8] = "Ratnagiri";
        strMH[9] = "Kolhapur";
        strMH[10] = "Sangli";
        strMH[11] = "Satara";
        strMH[12] = "Pune";
        strMH[13] = "Solapur";
        strMH[14] = "Pimpri Chinchwad";
        strMH[15] = "Nashik";
        strMH[16] = "Ahmednagar";
        strMH[17] = "Shrirampur";
        strMH[18] = "Dhule";
        strMH[19] = "Jalgaon";
        strMH[20] = "Aurangabad";
        strMH[21] = "Jalna";
        strMH[22] = "Parbhani";
        strMH[23] = "Beed";
        strMH[24] = "Latur";
        strMH[25] = "Osmanabad";
        strMH[26] = "Nanded";
        strMH[27] = "Amravati";
        strMH[28] = "Buldana";
        strMH[29] = "Yavatmal";
        strMH[30] = "Akola";
        strMH[31] = "Nagpur";
        strMH[32] = "Wardha";
        strMH[33] = "Gadchiroli";
        strMH[34] = "Chandrapur";
        strMH[35] = "Gondia";
        strMH[36] = "Bhandara";
        strMH[37] = "Washim";
        strMH[38] = "Hingoli";
        strMH[39] = "Nandurbar";
        strMH[40] = "Wadi, Nagpur (rural)";
        strMH[41] = "Malegaon";
        strMH[42] = "Baramati";
        strMH[43] = "Navi Mumbai Vashi (Sanpada)";
        strMH[44] = "Ambejogai";
        strMH[45] = "Akluj";
        strMH[46] = "Panvel";
        strMH[47] = "Borivali,Mumbai (Western Suburbs from Goregaon to Dahisar) Not yet functional (As of June 2012)[2]";
        strMH[48] = "Thane (Western Suburbs from Vasai) RTO is located in Virar";
        strMH[49] = "Nagpur (East) RTO is located on Bhandara Road";
        strMH[50] = "Karad (Satara Rural) RTO is located in Karad";

        strML[1] = "Shillong (government vehicles)";
        strML[2] = "Shillong (police vehicles)";
        strML[3] = "Shillong (vehicles owned by transport corporations)";
        strML[4] = "Jaintia Hills";
        strML[5] = "East Khasi Hills";
        strML[6] = "West Khasi Hills";
        strML[7] = "East Garo Hills";
        strML[8] = "West Garo Hills";
        strML[9] = "South Garo Hills";
        strML[10] = "Ri-Bhoi";

        strMN[1] = "Imphal";
        strMN[2] = "Churachandpur";
        strMN[3] = "Kangpokpi";
        strMN[4] = "Thoubal";

        strMP[1] = "State Governors Vehicle";
        strMP[2] = "M.P. Government Vehicle";
        strMP[3] = "M.P. Police Vehicle";
        strMP[4] = "Bhopal";
        strMP[5] = "Hoshangabad";
        strMP[6] = "Morena";
        strMP[7] = "Gwalior";
        strMP[8] = "Guna";
        strMP[9] = "Indore";
        strMP[10] = "Khargone";
        strMP[11] = "Dhar";
        strMP[12] = "Khandwa";
        strMP[13] = "Ujjain";
        strMP[14] = "Mandsaur";
        strMP[15] = "Sagar";
        strMP[16] = "Chhatarpur";
        strMP[17] = "Rewa";
        strMP[18] = "Shahdol";
        strMP[19] = "Satna";
        strMP[20] = "Jabalpur";
        strMP[21] = "Katni";
        strMP[22] = "Seoni";
        strMP[23] = "Raipur (not in use)";
        strMP[24] = "Durg (not in use)";
        strMP[25] = "Jagdalpur (not in use)";
        strMP[26] = "Bilaspur (not in use)";
        strMP[27] = "Ambikapur (not in use)";
        strMP[28] = "Chhindwara";
        strMP[29] = "Rajnandgaon (not in use)";
        strMP[30] = "Bhind";
        strMP[31] = "Sheopur";
        strMP[32] = "Datia";
        strMP[33] = "Shivpuri";
        strMP[34] = "Damoh";
        strMP[35] = "Panna";
        strMP[36] = "Tikamgarh";
        strMP[37] = "Sehore";
        strMP[38] = "Raisen";
        strMP[39] = "Rajgarh";
        strMP[40] = "Vidisha";
        strMP[41] = "Dewas";
        strMP[42] = "Shajapur";
        strMP[43] = "Ratlam";
        strMP[44] = "Neemuch";
        strMP[45] = "Jhabua";
        strMP[46] = "Badwani";
        strMP[47] = "Harda";
        strMP[48] = "Betul";
        strMP[49] = "Narsinghpur";
        strMP[50] = "Balaghat";
        strMP[51] = "Mandla";
        strMP[52] = "Dindori";
        strMP[53] = "Sidhi";
        strMP[54] = "Umariya";
        strMP[55] = "not in use";
        strMP[56] = "not in use";
        strMP[57] = "(not alloted)";
        strMP[58] = "not in use";
        strMP[59] = "(not alloted)";
        strMP[60] = "(not alloted)";
        strMP[61] = "(not alloted)";
        strMP[62] = "(not alloted)";
        strMP[63] = "(not alloted)";
        strMP[64] = "(not alloted)";
        strMP[65] = "Anoopur";
        strMP[66] = "Singrauli";
        strMP[67] = "Ashoknagar";
        strMP[68] = "Burhanpur";
        strMP[69] = "Alirajpur (upcoming)";

        strMZ[1] = "Aizawl";
        strMZ[2] = "Lunglei";
        strMZ[3] = "Saiha";
        strMZ[4] = "Champhai";
        strMZ[5] = "Kolasib";
        strMZ[6] = "Serchhip";
        strMZ[7] = "Lawngtlai";
        strMZ[8] = "Mamit";

        strNL[1] = "Kohima";
        strNL[2] = "Mokokchung";
        strNL[3] = "Tuensang";
        strNL[4] = "Mon";
        strNL[5] = "Wokha";
        strNL[6] = "Zunheboto";
        strNL[7] = "Dimapur";
        strNL[8] = "Phek";

        strOR[1] = "Balasore";
        strOR[2] = "Bhubaneswar";
        strOR[3] = "Bolangir";
        strOR[4] = "Chandikhole";
        strOR[5] = "Cuttack";
        strOR[6] = "Dhenkanal";
        strOR[7] = "Ganjam";
        strOR[8] = "Kalahandi";
        strOR[9] = "Keonjhar";
        strOR[10] = "Koraput";
        strOR[11] = "Mayurbhanj";
        strOR[12] = "Phulabani";
        strOR[13] = "Puri";
        strOR[14] = "Rourkela";
        strOR[15] = "Sambalpur";
        strOR[16] = "Sundergarh";
        strOR[17] = "Baragarh";
        strOR[18] = "Rayagada";
        strOR[19] = "Angul";
        strOR[20] = "Gajapati";
        strOR[21] = "Jagatsinghpur";
        strOR[22] = "Bhadrak";
        strOR[23] = "Jharsuguda";
        strOR[24] = "Nabarangpur";
        strOR[25] = "Nayagarh";
        strOR[26] = "Nuapada";
        strOR[27] = "Boudh";
        strOR[28] = "Debagarh";
        strOR[29] = "Kendarapara";
        strOR[30] = "Malkangiri";
        strOR[31] = "Sonepur";

        strPB[1] = "Taxis";
        strPB[2] = "Amritsar";
        strPB[3] = "Bathinda";
        strPB[4] = "Faridkot";
        strPB[5] = "Ferozepur";
        strPB[6] = "Gurdaspur";
        strPB[7] = "Hoshiarpur";
        strPB[8] = "Jalandhar";
        strPB[9] = "Kapurthala";
        strPB[10] = "Ludhiana";
        strPB[11] = "Patiala";
        strPB[12] = "Ropar";
        strPB[13] = "Sangrur";
        strPB[14] = "Ajnala";
        strPB[15] = "Abohar";
        strPB[16] = "Anandpur Sahib";
        strPB[17] = "Baba Bakala";
        strPB[18] = "Batala";
        strPB[19] = "Barnala";
        strPB[20] = "Balachaur";
        strPB[21] = "Dasua";
        strPB[22] = "Fazilka";
        strPB[23] = "Fatehgarh Sahib";
        strPB[24] = "Garhshankar";
        strPB[25] = "Jagraon";
        strPB[26] = "Khanna";
        strPB[27] = "Kharar";
        strPB[28] = "Malerkotla";
        strPB[29] = "Moga";
        strPB[30] = "Muktsar";
        strPB[31] = "Mansa";
        strPB[32] = "Nawanshahr";
        strPB[33] = "Nakodar";
        strPB[34] = "Nabha";
        strPB[35] = "Pathankot";
        strPB[36] = "Phagwara";
        strPB[37] = "Phillaur";
        strPB[38] = "Patti";
        strPB[39] = "Rajpura";
        strPB[40] = "Rampura Phul";
        strPB[41] = "Sultanpur Lodhi";
        strPB[42] = "Samana";
        strPB[43] = "Samrala";
        strPB[44] = "Sunam";
        strPB[45] = "Talwandi Sabo";
        strPB[46] = "Tarn Taran";
        strPB[47] = "Zira";
        strPB[48] = "Amloh";
        strPB[49] = "Khamono";
        strPB[50] = "Budhlada";
        strPB[51] = "Jhunir / Sardulgarh";
        strPB[52] = "Bassi Pathana";
        strPB[53] = "Malout";
        strPB[54] = "Mukerian";
        strPB[55] = "Payal";
        strPB[56] = "Raikot";
        strPB[57] = "Bhulath";
        strPB[58] = "Dera Baba Nanak";
        strPB[59] = "Dhuri";
        strPB[60] = "Gidderbaha";
        strPB[61] = "Jalalabad";
        strPB[62] = "Jaitu";
        strPB[63] = "Khadoor Sahib";
        strPB[64] = "Moonak";
        strPB[65] = "Mohali";
        strPB[66] = "Nihal Singh Wala";
        strPB[67] = "Shahkot";
        strPB[68] = "Dhar";
        strPB[69] = "Bagha Purana";
        strPB[70] = "Dera Bassi";
        strPB[71] = "Chamkaur Sahib";
        strPB[72] = "Patran";
        strPB[73] = "Tappa Mandi";
        strPB[74] = "Nangal";
        strPB[75] = "Lehra";

        strPY[1] = "Pondicherry";
        strPY[2] = "Karaikal";
        strPY[3] = "Mahe";
        strPY[4] = "Yanam";

        strRJ[1] = "Ajmer";
        strRJ[2] = "Alwar";
        strRJ[3] = "Banswara";
        strRJ[4] = "Barmer";
        strRJ[5] = "Bharatpur";
        strRJ[6] = "Bhilwara";
        strRJ[7] = "Bikaner";
        strRJ[8] = "Bundi";
        strRJ[9] = "Chittaurgarh";
        strRJ[10] = "Churu";
        strRJ[11] = "Dholpur";
        strRJ[12] = "Dungarpur";
        strRJ[13] = "Sri Ganganagar";
        strRJ[14] = "Jaipur";
        strRJ[15] = "Jaisalmer";
        strRJ[16] = "Jalore";
        strRJ[17] = "Jhalawar";
        strRJ[18] = "Jhunjhunu";
        strRJ[19] = "Jodhpur";
        strRJ[20] = "Kota";
        strRJ[21] = "Nagaur";
        strRJ[22] = "Pali";
        strRJ[23] = "Sikar";
        strRJ[24] = "Sirohi";
        strRJ[25] = "Sawai Madhopur";
        strRJ[26] = "Tonk";
        strRJ[27] = "Udaipur";
        strRJ[28] = "Baran";
        strRJ[29] = "Dausa";
        strRJ[30] = "Rajsamand";
        strRJ[31] = "Hanumangarh";
        strRJ[32] = "Kotputli";
        strRJ[33] = "Ramganj Mandi";
        strRJ[34] = "Karauli";
        strRJ[35] = "Pratapgarh";
        strRJ[36] = "Beawar";
        strRJ[37] = "Didwana";
        strRJ[38] = "Rawatbhata";

        strSK[1] = "Gangtok, East Sikkim";
        strSK[2] = "Gyalshing, West Sikkim";
        strSK[3] = "Mangan, North Sikkim";
        strSK[4] = "Jorethang, South Sikkim";

        strTN[1] = "Chennai Central (Ayanavaram)";
        strTN[2] = "Chennai West (Anna Nagar)";
        strTN[3] = "Chennai North East (Tondiarpet)";
        strTN[4] = "Chennai East (Basin Bridge)";
        strTN[5] = "Chennai North (Vyasarpadi)";
        strTN[6] = "Chennai South East (Mandaveli)";
        strTN[7] = "Chennai South (Thiruvanmiyur)";
        strTN[8] = "N/A";
        strTN[9] = "Chennai West (K.K. Nagar)";
        strTN[10] = "Chennai South West (Valasarawakkam)";
        strTN[11] = "Tambaram";
        strTN[12] = "N/A";
        strTN[13] = "N/A";
        strTN[14] = "N/A";
        strTN[15] = "N/A";
        strTN[16] = "Tindivanam";
        strTN[17] = "N/A";
        strTN[18] = "Sengundram";
        strTN[19] = "Chengalpattu";
        strTN[20] = "Tiruvallur";
        strTN[21] = "Kanchipuram";
        strTN[22] = "Meenambakkam";
        strTN[23] = "Vellore";
        strTN[24] = "Krishnagiri";
        strTN[25] = "Thiruvannamalai";
        strTN[26] = "N/A";
        strTN[27] = "N/A";
        strTN[28] = "Namakkal";
        strTN[29] = "Dharmapuri";
        strTN[30] = "Salem (West)";
        strTN[31] = "Cuddalore";
        strTN[32] = "Villupuram";
        strTN[33] = "Erode";
        strTN[34] = "Thiruchengode";
        strTN[35] = "N/A";
        strTN[36] = "Gobichettipalayam";
        strTN[37] = "Coimbatore South";
        strTN[38] = "Coimbatore North";
        strTN[39] = "Tirupur (North)";
        strTN[40] = "Mettupalayam";
        strTN[41] = "Pollachi";
        strTN[42] = "Tirupur (South)";
        strTN[43] = "Uthagamandalam";
        strTN[44] = "N/A";
        strTN[45] = "Tiruchirapalli";
        strTN[46] = "Perambalur";
        strTN[47] = "Karur";
        strTN[48] = "Srirangam (Tiruchirapalli)";
        strTN[49] = "Thanjavur";
        strTN[50] = "Tiruvarur";
        strTN[51] = "Nagapattinam";
        strTN[52] = "Sankagiri";
        strTN[53] = "N/A";
        strTN[54] = "Salem (East)";
        strTN[55] = "Pudukottai";
        strTN[56] = "Perundurai";
        strTN[57] = "Dindigul";
        strTN[58] = "Madurai (South)";
        strTN[59] = "Madurai (North)";
        strTN[60] = "Theni";
        strTN[61] = "Ariyalur";
        strTN[62] = "N/A";
        strTN[63] = "Sivagangai";
        strTN[64] = "Madurai (Central)";
        strTN[65] = "Ramanathapuram";
        strTN[66] = "Coimbatore (Central)";
        strTN[67] = "Virudhunagar";
        strTN[68] = "Kumbakonam";
        strTN[69] = "Tuticorin";
        strTN[70] = "Hosur";
        strTN[71] = "N/A";
        strTN[72] = "Tirunelveli";
        strTN[73] = "Ranipet";
        strTN[74] = "Nagercoil";
        strTN[75] = "Marthandam";
        strTN[76] = "Tenkasi";
        strTN[77] = "Attur";
        strTN[78] = "Dharapuram";

        strTR[1] = "Agartala";
        strTR[2] = "Kailashahar";
        strTR[3] = "Udaipura";

        strUK[1] = "Almora";
        strUK[2] = "Bageshwar";
        strUK[3] = "Champawat";
        strUK[4] = "Nanital";
        strUK[5] = "Pithoragarh";
        strUK[6] = "Udham Singh Nagar";
        strUK[7] = "Dehra Dun (UK 07TC for taxis)";
        strUK[8] = "Haridwar";
        strUK[9] = "Tehri";
        strUK[10] = "Uttarkashi";
        strUK[11] = "Chamoli";
        strUK[12] = "Pauri";
        strUK[13] = "Rudraprayag";

        strUP[1] = "Almora (not longer in use)";
        strUP[2] = "Haldwani (not longer in use ?)";
        strUP[3] = "Pithoragarh (not longer in use)";
        strUP[4] = "Udham Singh Nagar (not longer in use)";
        strUP[5] = "Chamoli (not longer in use)";
        strUP[6] = "Pauri (not longer in use)";
        strUP[7] = "Dehradun (not longer in use)";
        strUP[8] = "Tehri (not longer in use)";
        strUP[9] = "Uttarkashi (not longer in use)";
        strUP[10] = "Haridwar (not longer in use)";
        strUP[11] = "Saharanpur";
        strUP[12] = "Muzaffarnagar";
        strUP[13] = "Bulandshahr";
        strUP[14] = "Ghaziabad";
        strUP[15] = "Meerut";
        strUP[16] = "NOIDA / Gautam Buddha Nagar";
        strUP[17] = "Baghpat";
        strUP[18] = "Prabudh Nagar";
        strUP[19] = "N/A";
        strUP[20] = "Bijnor";
        strUP[21] = "Moradabad";
        strUP[22] = "Rampur";
        strUP[23] = "Jyotiba Phule Nagar";
        strUP[24] = "Badaun";
        strUP[25] = "Bareilly";
        strUP[26] = "Pilibhit";
        strUP[27] = "Shahjahanpur";
        strUP[28] = "Ayodhya";
        strUP[29] = "to be alloted";
        strUP[30] = "Hardoi";
        strUP[31] = "Kheri";
        strUP[32] = "Lucknow";
        strUP[33] = "Raebareli";
        strUP[34] = "Sitapur";
        strUP[35] = "Unnao";
        strUP[36] = "Lucknow";
        strUP[37] = "Hapur";
        strUP[38] = "N/A";
        strUP[39] = "N/A";
        strUP[40] = "Bahraich";
        strUP[41] = "Barabanki";
        strUP[42] = "Faizabad";
        strUP[43] = "Gonda";
        strUP[44] = "Sultanpur";
        strUP[45] = "Ambedkar Nagar";
        strUP[46] = "Shrawasti";
        strUP[47] = "Balrampur";
        strUP[48] = "N/A";
        strUP[49] = "N/A";
        strUP[50] = "Azamgarh";
        strUP[51] = "Basti";
        strUP[52] = "Deoria";
        strUP[53] = "Gorakhpur for private vehicles";
        strUP[54] = "Mau";
        strUP[55] = "Siddharth Nagar";
        strUP[56] = "Mahrajganj";
        strUP[57] = "Padrauna";
        strUP[58] = "Sant Kabir Nagar";
        strUP[59] = "to be alloted";
        strUP[60] = "Ballia";
        strUP[61] = "Ghazipur";
        strUP[62] = "Jaunpur";
//        strUP[62T] = "Jaunpur for taxis, private vehicle";
        strUP[63] = "Mirzapur";
        strUP[64] = "Sonbhadra";
        strUP[65] = "Varanasi";
        strUP[66] = "Bhadohi";
        strUP[67] = "Chandauli";
        strUP[68] = "to be alloted";
        strUP[69] = "to be alloted";
        strUP[70] = "Allahabad";
        strUP[71] = "Fatehpur";
        strUP[72] = "Pratapgarh";
        strUP[73] = "Kaushambi";
        strUP[74] = "Kannauj";
        strUP[75] = "Etawah";
        strUP[76] = "Farrukhabad";
        strUP[77] = "Kanpur Dehat (rural)";
        strUP[78] = "Kanpur Urban";
        strUP[79] = "Auraiya";
        strUP[80] = "Agra";
        strUP[81] = "Aligarh";
        strUP[82] = "Etah";
        strUP[83] = "Firozabad";
        strUP[84] = "Mainpuri";
        strUP[85] = "Mathura";
        strUP[86] = "Mahamaya Nagar";
        strUP[87] = "Kanshiram Nagar";
        strUP[88] = "to be alloted";
        strUP[89] = "to be alloted";
        strUP[90] = "Banda";
        strUP[91] = "Hamirpur";
        strUP[92] = "Jalaun";
        strUP[93] = "Jhansi";
        strUP[94] = "Lalitpur";
        strUP[95] = "Mahoba";
        strUP[96] = "Chitrakoot Dham (Karwi)";

        strWB[1] = "Kolkata (two wheelers) ";
        strWB[2] = "Kolkata (private four wheelers) The series of WB";
        strWB[3] = "Kolkata (goods carriages)";
        strWB[4] = "Kolkata (passenger vehicles)";
        strWB[5] = "Kolkata (tractors, trailers, cranes)";
        strWB[6] = "Kolkata (private four wheelers)";
        strWB[7] = "Kolkata (two wheelers)";
        strWB[8] = "Kolkata";
        strWB[9] = "Kolkata";
        strWB[10] = "Kolkata";
        strWB[11] = "Howrah (transport vehicles)";
        strWB[12] = "Howrah Other than transport vehicles";
        strWB[13] = "Howrah to be used";
        strWB[14] = "Howrah (other than transport vehicles)";
        strWB[15] = "Hooghly (transport vehicles)";
        strWB[16] = "Hooghly (other than transport vehicles)";
        strWB[17] = "N/A";
        strWB[18] = "Hooghly (other than transport vehicles)";
        strWB[19] = "South 24 Parganas, Alipore (transport vehicles)";
        strWB[20] = "South 24 Parganas, Alipore (other than transport vehicles) exhausted for two wheelers";
        strWB[21] = "N/A";
        strWB[22] = "South 24 Parganas, Alipore (other than transport vehicles)";
        strWB[23] = "Barrackpore (transport vehicles)";
        strWB[24] = "Barrackpore (other than transport vehicles)";
        strWB[25] = "Barasat (transport vehicles)";
        strWB[26] = "Barasat (other than transport vehicles)";
        strWB[27] = "N/A";
        strWB[28] = "N/A";
        strWB[29] = "Tamluk (transport vehicles)";
        strWB[30] = "Tamluk (other than transport vehicles)";
        strWB[31] = "Contai (transport vehicles)";
        strWB[32] = "Contai (other than transport vehicles)";
        strWB[33] = "Midnapur (transport vehicles)";
        strWB[34] = "Midnapur (other than transport vehicles)";
        strWB[35] = "N/A";
        strWB[36] = "N/A";
        strWB[37] = "Asansol (transport vehicles)";
        strWB[38] = "Asansol (other than transport vehicles)";
        strWB[39] = "Durgapur (transport vehicles)";
        strWB[40] = "Durgapur (other than transport vehicles)";
        strWB[41] = "Burdwan (transport vehicles)";
        strWB[42] = "Burdwan (other than transport vehicles)";
        strWB[43] = "N/A";
        strWB[44] = "Asansol (other than transport vehicles)";
        strWB[45] = "N/A";
        strWB[46] = "N/A";
        strWB[47] = "N/A";
        strWB[48] = "N/A";
        strWB[49] = "N/A";
        strWB[50] = "N/A";
        strWB[51] = "Nadia (transport vehicles)";
        strWB[52] = "Nadia (other than transport vehicles)";
        strWB[53] = "Birbhum (transport vehicles)";
        strWB[54] = "Birbhum (other than transport vehicles)";
        strWB[55] = "Purulia (transport vehicles)";
        strWB[56] = "Purulia (other than transport vehicles)";
        strWB[57] = "Murshidabad (transport vehicles)";
        strWB[58] = "Murshidabad (other than transport vehicles)";
        strWB[59] = "Raiganj (transport vehicles)";
        strWB[60] = "Raiganj (other than transport vehicles)";
        strWB[61] = "Balurghat (transport vehicles)";
        strWB[62] = "Balurghat (other than transport vehicles)";
        strWB[63] = "Cooch Behar (transport vehicles)";
        strWB[64] = "Cooch Behar (other than transport vehicles)";
        strWB[65] = "Malda (transport vehicles)";
        strWB[66] = "Malda (other than transport vehicles)";
        strWB[67] = "Bankura (transport vehicles)";
        strWB[68] = "Bankura (other than transport vehicles)";
        strWB[69] = "Alipurduar (transport vehicles)";
        strWB[70] = "Alipurduar (other than transport vehicles)";
        strWB[71] = "Jalpaiguri (transport vehicles)";
        strWB[72] = "Jalpaiguri (other than transport vehicles)";
        strWB[73] = "Siliguri (transport vehicles)";
        strWB[74] = "Siliguri (other than transport vehicles)";
        strWB[75] = "N/A";
        strWB[76] = "Darjeeling (transport vehicles)";
        strWB[77] = "Darjeeling (other than transport vehicles)";
        strWB[78] = "Kalimpong (transport vehicles)";
        strWB[79] = "Kalimpong (other than transport vehicles)";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail:
                intRTOCode = Integer.parseInt("0"+ etRTOCode.getText());
                strState = ""+ etRTOState.getText();
                strState = strState.trim();
                String tempString="";
                if(strState.toUpperCase().contains("AN") && strState.length()==2){tempString=(strAN[intRTOCode]+ ", "+ strAN[0]);}
                else if(strState.toUpperCase().contains("AP") && strState.length()==2){tempString=(strAP[intRTOCode]+ ", "+ strAP[0]);}
                else if(strState.toUpperCase().contains("AR") && strState.length()==2){tempString=(strAR[intRTOCode]+ ", "+ strAR[0]);}
                else if(strState.toUpperCase().contains("AS") && strState.length()==2){tempString=(strAS[intRTOCode]+ ", "+ strAS[0]);}
                else if(strState.toUpperCase().contains("BR") && strState.length()==2){tempString=(strBR[intRTOCode]+ ", "+ strBR[0]);}
                else if(strState.toUpperCase().contains("CG") && strState.length()==2){tempString=(strCG[intRTOCode]+ ", "+ strCG[0]);}
                else if(strState.toUpperCase().contains("CH") && strState.length()==2){tempString=(strCH[intRTOCode]+ ", "+ strCH[0]);}
                else if(strState.toUpperCase().contains("DD") && strState.length()==2){tempString=(strDD[intRTOCode]+ ", "+ strDD[0]);}
                else if(strState.toUpperCase().contains("DL") && strState.length()==2){tempString=(strDL[intRTOCode]+ ", "+ strDL[0]);}
                else if(strState.toUpperCase().contains("DN") && strState.length()==2){tempString=(strDN[intRTOCode]+ ", "+ strDN[0]);}
                else if(strState.toUpperCase().contains("GA") && strState.length()==2){tempString=(strGA[intRTOCode]+ ", "+ strGA[0]);}
                else if(strState.toUpperCase().contains("GJ") && strState.length()==2){tempString=(strGJ[intRTOCode]+ ", "+ strGJ[0]);}
                else if(strState.toUpperCase().contains("HP") && strState.length()==2){tempString=(strHP[intRTOCode]+ ", "+ strHP[0]);}
                else if(strState.toUpperCase().contains("HR") && strState.length()==2){tempString=(strHR[intRTOCode]+ ", "+ strHR[0]);}
                else if(strState.toUpperCase().contains("JH") && strState.length()==2){tempString=(strJH[intRTOCode]+ ", "+ strJH[0]);}
                else if(strState.toUpperCase().contains("JK") && strState.length()==2){tempString=(strJK[intRTOCode]+ ", "+ strJK[0]);}
                else if(strState.toUpperCase().contains("KA") && strState.length()==2){tempString=(strKA[intRTOCode]+ ", "+ strKA[0]);}
                else if(strState.toUpperCase().contains("KL") && strState.length()==2){tempString=(strKL[intRTOCode]+ ", "+ strKL[0]);}
                else if(strState.toUpperCase().contains("LD") && strState.length()==2){tempString=(strLD[intRTOCode]+ ", "+ strLD[0]);}
                else if(strState.toUpperCase().contains("MH") && strState.length()==2){tempString=(strMH[intRTOCode]+ ", "+ strMH[0]);}
                else if(strState.toUpperCase().contains("ML") && strState.length()==2){tempString=(strML[intRTOCode]+ ", "+ strML[0]);}
                else if(strState.toUpperCase().contains("MN") && strState.length()==2){tempString=(strMN[intRTOCode]+ ", "+ strMN[0]);}
                else if(strState.toUpperCase().contains("MP") && strState.length()==2){tempString=(strMP[intRTOCode]+ ", "+ strMP[0]);}
                else if(strState.toUpperCase().contains("MZ") && strState.length()==2){tempString=(strMZ[intRTOCode]+ ", "+ strMZ[0]);}
                else if(strState.toUpperCase().contains("NL") && strState.length()==2){tempString=(strNL[intRTOCode]+ ", "+ strNL[0]);}
                else if(strState.toUpperCase().contains("OR") && strState.length()==2){tempString=(strOR[intRTOCode]+ ", "+ strOR[0]);}
                else if(strState.toUpperCase().contains("PB") && strState.length()==2){tempString=(strPB[intRTOCode]+ ", "+ strPB[0]);}
                else if(strState.toUpperCase().contains("PY") && strState.length()==2){tempString=(strPY[intRTOCode]+ ", "+ strPY[0]);}
                else if(strState.toUpperCase().contains("RJ") && strState.length()==2){tempString=(strRJ[intRTOCode]+ ", "+ strRJ[0]);}
                else if(strState.toUpperCase().contains("SK") && strState.length()==2){tempString=(strSK[intRTOCode]+ ", "+ strSK[0]);}
                else if(strState.toUpperCase().contains("TN") && strState.length()==2){tempString=(strTN[intRTOCode]+ ", "+ strTN[0]);}
                else if(strState.toUpperCase().contains("TR") && strState.length()==2){tempString=(strTR[intRTOCode]+ ", "+ strTR[0]);}
                else if(strState.toUpperCase().contains("UK") && strState.length()==2){tempString=(strUK[intRTOCode]+ ", "+ strUK[0]);}
                else if(strState.toUpperCase().contains("UP") && strState.length()==2){tempString=(strUP[intRTOCode]+ ", "+ strUP[0]);}
                else if(strState.toUpperCase().contains("WB") && strState.length()==2){tempString=(strWB[intRTOCode]+ ", "+ strWB[0]);}
                else{
                    for(int i=1;i<strAN.length;i++){if(strAN[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strAN[i];}}
                    for(int i=1;i<strAP.length;i++){if(strAP[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strAP[i];}}
                    for(int i=1;i<strAR.length;i++){if(strAR[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strAR[i];}}
                    for(int i=1;i<strAS.length;i++){if(strAS[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strAS[i];}}
                    for(int i=1;i<strBR.length;i++){if(strBR[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strBR[i];}}
                    for(int i=1;i<strCG.length;i++){if(strCG[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strCG[i];}}
                    for(int i=1;i<strCH.length;i++){if(strCH[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strCH[i];}}
                    for(int i=1;i<strDD.length;i++){if(strDD[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strDD[i];}}
                    for(int i=1;i<strDL.length;i++){if(strDL[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strDL[i];}}
                    for(int i=1;i<strDN.length;i++){if(strDN[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strDN[i];}}
                    for(int i=1;i<strGA.length;i++){if(strGA[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strGA[i];}}
                    for(int i=1;i<strGJ.length;i++){if(strGJ[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strGJ[i];}}
                    for(int i=1;i<strHP.length;i++){if(strHP[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strHP[i];}}
                    for(int i=1;i<strHR.length;i++){if(strHR[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strHR[i];}}
                    for(int i=1;i<strJH.length;i++){if(strJH[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strJH[i];}}
                    for(int i=1;i<strJK.length;i++){if(strJK[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strJK[i];}}
                    for(int i=1;i<strKA.length;i++){if(strKA[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strKA[i];}}
                    for(int i=1;i<strKL.length;i++){if(strKL[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strKL[i];}}
                    for(int i=1;i<strLD.length;i++){if(strLD[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strLD[i];}}
                    for(int i=1;i<strMH.length;i++){if(strMH[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strMH[i];}}
                    for(int i=1;i<strML.length;i++){if(strML[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strML[i];}}
                    for(int i=1;i<strMN.length;i++){if(strMN[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strMN[i];}}
                    for(int i=1;i<strMP.length;i++){if(strMP[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strMP[i];}}
                    for(int i=1;i<strMZ.length;i++){if(strMZ[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strMZ[i];}}
                    for(int i=1;i<strNL.length;i++){if(strNL[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strNL[i];}}
                    for(int i=1;i<strOR.length;i++){if(strOR[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strOR[i];}}
                    for(int i=1;i<strPB.length;i++){if(strPB[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strPB[i];}}
                    for(int i=1;i<strPY.length;i++){if(strPY[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strPY[i];}}
                    for(int i=1;i<strRJ.length;i++){if(strRJ[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strRJ[i];}}
                    for(int i=1;i<strSK.length;i++){if(strSK[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strSK[i];}}
                    for(int i=1;i<strTN.length;i++){if(strTN[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strTN[i];}}
                    for(int i=1;i<strTR.length;i++){if(strTR[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strTR[i];}}
                    for(int i=1;i<strUK.length;i++){if(strUK[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strUK[i];}}
                    for(int i=1;i<strUP.length;i++){if(strUP[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strUP[i];}}
                    for(int i=1;i<strWB.length;i++){if(strWB[i].toUpperCase().contains(strState.toUpperCase())){tempString=tempString+","+strWB[i];}}
                }
                txtSummary.setText(tempString);
                if(tempString.length()<30)
                    txtSummary.setTextSize(50);
                else if(tempString.length()<100)
                    txtSummary.setTextSize(30);
                else if(tempString.length()<200)
                    txtSummary.setTextSize(20);
                else
                    txtSummary.setTextSize(10);
                break;
        }
    }
        //adapter.notifyDataSetChanged();
    private String round(float countAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(RTOActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(RTOActivity.this, RTOActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(RTOActivity.this, LocalActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(RTOActivity.this, SettingsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){}
    public void onNothingSelected(AdapterView<?> parent) {
        txtSummary.setText("");
    }
}
