var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1sNBxDLeo9leLDVq3mMyCa38KqbcagwSUaA5H4MYQphk/edit#gid=2069066300");
function doPost(e) {
  var action = e.parameter.action;

  if(action == 'addItem'){
    return addItem(e);
  }
}

function doGet(e){
  var action = e.parameter.action;
  if(action == 'getItems'){
    return getItems(e);
  }
  if(action == 'getSummary'){
    return getSummary(e);
  }
  if(action == 'getRTODetails'){
    return getRTODetails(e);
  }
  if(action == 'getRTOList'){
    return getRTOList(e);
  }
  if(action == 'getTrainStationTime'){
    return getTrainStationTime(e);
  }
  if(action == 'getTrainStationTimeNew'){
    return getTrainStationTimeNew(e);
  }
  else{
    return ContentService.createTextOutput("No Methods Found ...").setMimeType(ContentService.MimeType.JSON);
    //return "ABC";
  }

//https://script.google.com/macros/s/AKfycbxKp0kp8Bgotjv81AgwnkROZ5MI8U19Hmq0r6GLAc36Gb_MNdA/exec?action=getRTOLists&rtoText=pune


}

function getItems(e){
  var sheet = ss.getSheetByName(e.parameter.monthNumber); // Sheet Name
  var dateNumber = e.parameter.dateNumber;
  var taskData = ""
  var rows = sheet.getRange(2, dateNumber, sheet.getLastRow() - 1, 1).getValues();
  for (var r = 0, l = rows.length - 2; r < l; r++){
    taskData = taskData + rows[r]
  }
  taskData = "HHHHH"+taskData + ","+ rows[rows.length - 2] + ","+ rows[rows.length - 1]
  return ContentService.createTextOutput(taskData).setMimeType(ContentService.MimeType.JSON);
}

function getRTODetails(e){
  var sheetStateDetail = ss.getSheetByName("State Detail"); // Sheet Name
  var sheetRTODetail = ss.getSheetByName("RTO Detail"); // Sheet Name
  //Get State Name
  var rowsState = sheetStateDetail.getRange(2,1,sheetStateDetail.getLastRow() - 1, sheetStateDetail.getLastColumn()).getValues();
  var rowsRTO = sheetRTODetail.getRange(2,1,sheetRTODetail.getLastRow() - 1, sheetRTODetail.getLastColumn()).getValues();
  var stateCode = e.parameter.stateCode.toUpperCase()
  var rtoCode = e.parameter.rtoCode
//  dataState = [];
  var RTODetails =""
  for (var row02 in rowsState) {
    if(rowsState[row02][0]==stateCode){
      RTODetails = RTODetails + rowsState[row02][1];
      break;
    }
  }
  RTODetails = RTODetails + ";"

  for (var row02 in rowsRTO) {
    for (var col02 in rowsRTO[row02]) {
      if(rowsRTO[row02][0]==stateCode && rowsRTO[row02][1]==rtoCode )
        RTODetails = RTODetails + rowsRTO[row02][2];
        break;
    }
  }
  //RTODetails = "Maharashtra" + ";Pune"
  return ContentService.createTextOutput(RTODetails).setMimeType(ContentService.MimeType.JSON);
  //return ContentService.createTextOutput("Hemant").setMimeType(ContentService.MimeType.JSON);
}

function getRTOList(e){
  var sheetRTODetail = ss.getSheetByName("RTO Detail"); // Sheet Name
  //Get State Name
  var rowsRTO = sheetRTODetail.getRange(2,1,sheetRTODetail.getLastRow() - 1, sheetRTODetail.getLastColumn()).getValues();
  var rtoCode = e.parameter.rtoText
  //dataState = [];
  var RTODetails = ""
  for (var row02 in rowsRTO) {
    if(rowsRTO[row02][2].toString().indexOf(rtoCode) > -1){
      RTODetails = RTODetails + rowsRTO[row02][0] + " - " + rowsRTO[row02][2] + "; ";
    }
  }

  return ContentService.createTextOutput(RTODetails).setMimeType(ContentService.MimeType.JSON);
}

function getTrainStationTimeOld(e){
  var sheetTrainTime = ss.getSheetByName(e.parameter.upOrDown); // Sheet Name
  var sheetTrainDetails = ss.getSheetByName("train"); // Sheet Name
  //Get State Name
  var trainTimes = sheetTrainTime.getRange(1,1,sheetTrainTime.getLastRow() - 1, sheetTrainTime.getLastColumn()).getValues();
  var trainDetails = sheetTrainDetails.getRange(1,1,sheetTrainDetails.getLastRow() - 1, sheetTrainDetails.getLastColumn()).getValues();
  var trainStation = e.parameter.trainStation
  //dataState = [];
  var trainDetail = 'aaa';
  var RTODetails = '{"TrainTime":['; //e.parameter.upOrDown
  for (var row01 in trainTimes) {
    if(trainTimes[row01][0].toString()==trainStation){
      for (var col01 in trainTimes[row01]) {
        for(var row02 in trainDetails)
        {
          if(trainTimes[0][col01] == trainDetails[row02][0])
          {
            trainDetail = '"TrainNo":"'+ trainDetails[row02][0] + '","TrainName":"' + trainDetails[row02][1] + '","Source":"' + trainDetails[row02][2] + '","Via":"' + trainDetails[row02][3] + '","Destination":"' + trainDetails[row02][4]+'"';
            break;
          }
          else
          {
            //trainDetail = '"TrainNo":"Train No.","TrainName":"Train Name","Source":"Source","Via":"Via","Destination":"Destination"';
            trainDetail = "ABC";
            //break;
          }

        }
        //Train No not found
        if(trainDetail != "ABC")
        {
          RTODetails = RTODetails  + '{"Time":"' + trainTimes[row01][col01] + '",' + trainDetail + "}";}

        if(trainTimes[row01].length-1==col01)
        {
          RTODetails = RTODetails  + ']}';
          break;
        }
        else if(trainDetail == "ABC")
        {
          RTODetails = RTODetails  + '';
        }
        else
        {
          RTODetails = RTODetails  + ',';
        }

      }
    }
  }
  //RTODetails = RTODetails  + ']}'
  return ContentService.createTextOutput(RTODetails).setMimeType(ContentService.MimeType.JSON);
}

function getTrainStationTime(e){
  var sheetTrainTime = ss.getSheetByName(e.parameter.upOrDown); // Sheet Name
  var sheetTrainDetails = ss.getSheetByName("train"); // Sheet Name
  //Get State Name
  var trainTimes = sheetTrainTime.getRange(1,1,sheetTrainTime.getLastRow(), sheetTrainTime.getLastColumn()).getValues();
  var trainDetails = sheetTrainDetails.getRange(1,1,sheetTrainDetails.getLastRow(), sheetTrainDetails.getLastColumn()).getValues();
  var trainStation = e.parameter.trainStation
  //dataState = [];
  var trainDetail = 'aaa';
  var RTODetails = '{"TrainTime":['; //e.parameter.upOrDown
  for (var row01 in trainTimes) {
    if(trainTimes[row01][0].toString()==trainStation){
      for (var col01 in trainTimes[row01]) {
        for(var row02 in trainDetails)
        {
          if(trainTimes[0][col01] == trainDetails[row02][0])
          {
            trainDetail = '"TrainNo":"'+ trainDetails[row02][0] + '","TrainName":"' + trainDetails[row02][1] + '","Source":"' + trainDetails[row02][2] + '","Via":"' + trainDetails[row02][3] + '","Destination":"' + trainDetails[row02][4]+'"';
            break;
          }
          else
          {
            //trainDetail = '"TrainNo":"Train No.","TrainName":"Train Name","Source":"Source","Via":"Via","Destination":"Destination"';
            trainDetail = "ABC";
            //break;
          }

        }
        //Train No not found
        if(trainDetail != "ABC")
        {
          RTODetails = RTODetails  + '{"Time":"' + trainTimes[row01][col01] + '",' + trainDetail + "}";}

        if(trainTimes[row01].length-1==col01)
        {
          RTODetails = RTODetails  + ']}';
          break;
        }
        else if(trainDetail == "ABC")
        {
          RTODetails = RTODetails  + '';
        }
        else
        {
          RTODetails = RTODetails  + ',';
        }

      }
    }
  }
  //RTODetails = RTODetails  + ']}'
  //RTODetails = trainTimes  + 'ccccccccc]}'
  //return ContentService.createTextOutput(RTODetails).setMimeType(ContentService.MimeType.JSON);
  return ContentService.createTextOutput(RTODetails).setMimeType(ContentService.MimeType.JSON);
}

function addItem(e){
  var sheet = ss.getSheetByName(e.parameter.monthNumber); // Sheet Name
  var date = new Date();
  var id = "Item" + sheet.getLastRow();
  var itemName = e.parameter.dateNumber;
  //var brand = e.parameter.dataNumber;

  var str1 = e.parameter.dataAlphanumeric;
  var brandArray = new Array(42);
  for (var i = 0; i < 42; i++){
    brandArray[i] = str1.substring(i,i+1)
  }
  //sheet.getRange( )

  var values1 = [
  [brandArray[0]],
  [brandArray[1]],
  [brandArray[2]],
  [brandArray[3]],
  [brandArray[4]],
  [brandArray[5]],
  [brandArray[6]],
  [brandArray[7]],
  [brandArray[8]],
  [brandArray[9]],
  [brandArray[10]],
  [brandArray[11]],
  [brandArray[12]],
  [brandArray[13]],
  [brandArray[14]],
  [brandArray[15]],
  [brandArray[16]],
  [brandArray[17]],
  [brandArray[18]],
  [brandArray[19]],
  [brandArray[20]],
  [brandArray[21]],
  [brandArray[22]],
  [brandArray[23]],
  [brandArray[24]],
  [brandArray[25]],
  [brandArray[26]],
  [brandArray[27]],
  [brandArray[28]],
  [brandArray[29]],
  [brandArray[30]],
  [brandArray[31]],
  [brandArray[32]],
  [brandArray[33]],
  [brandArray[34]]
  ];

  sheet.getRange(2,e.parameter.dateNumber,35,1).setValues(values1);
  return ContentService.createTextOutput("Success").setMimeType(ContentService.MimeType.TEXT);
}

function convertMethod(subStr){
  var result = "11111"
  if (subStr == '0'){
    result = "00000"
  }
  if (subStr == '1'){
    result = "00001"
  }
  if (subStr == '2'){
    result = "00010"
  }
  if (subStr == '3'){
    result = "00011"
  }
  if (subStr == '4'){
    result = "00100"
  }
  if (subStr == '5'){
    result = "00101"
  }
  if (subStr == '6'){
    result = "00110"
  }
  if (subStr == '7'){
    result = "00111"
  }
  if (subStr == '8'){
    result = "01000"
  }
  if (subStr == '9'){
    result = "01001"
  }
  if (subStr == 'a'){
    result = "01010"
  }
  if (subStr == 'b'){
    result = "01011"
  }
  if (subStr == 'c'){
    result = "01100"
  }
  if (subStr == 'd'){
    result = "01101"
  }
  if (subStr == 'e'){
    result = "01110"
  }
  if (subStr == 'f'){
    result = "01111"
  }
  if (subStr == 'g'){
    result = "10000"
  }
  if (subStr == 'h'){
    result = "10001"
  }
  if (subStr == 'i'){
    result = "10010"
  }
  if (subStr == 'j'){
    result = "10011"
  }
  if (subStr == 'k'){
    result = "10100"
  }
  if (subStr == 'l'){
    result = "10101"
  }
  if (subStr == 'm'){
    result = "10110"
  }
  if (subStr == 'n'){
    result = "10111"
  }
  if (subStr == 'o'){
    result = "11000"
  }
  if (subStr == 'p'){
    result = "11001"
  }
  if (subStr == 'q'){
    result = "11010"
  }
  if (subStr == 'r'){
    result = "11011"
  }
  if (subStr == 's'){
    result = "11100"
  }
  if (subStr == 't'){
    result = "11101"
  }
  if (subStr == 'u'){
    result = "11110"
  }
  if (subStr == 'v'){
    result = "11111"
  }
  return result
}