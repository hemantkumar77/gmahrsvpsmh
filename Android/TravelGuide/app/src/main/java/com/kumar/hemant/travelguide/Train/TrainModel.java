package com.kumar.hemant.travelguide.Train;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

final class TrainModel
{
    private static final String DB_NAME = "trains_db";
    private static final String TABLE_NAME = "tblTrain";
    private static final String TABLE_NAME2 = "tblStation";
    private static final String TABLE_NAME3 = "tblTrainStation";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " + TrainModel.TABLE_NAME + " (train_no text primary key, train_name text, source text, destination text, days_of_week text);";
    private static final String DB_CREATE_QUERY_STATION = "CREATE TABLE " + TrainModel.TABLE_NAME2 + " (station_code text primary key, station_name);";
    private static final String DB_CREATE_QUERY_TRAIN_STATION = "CREATE TABLE " + TrainModel.TABLE_NAME3 + " (train_no text, station_code text, time_arrival text, time_departure text);";
    private final SQLiteDatabase database;
    private final SQLiteOpenHelper helper;
    private String str1="";

    public TrainModel(final Context ctx)
    {
        this.helper = new SQLiteOpenHelper(ctx, TrainModel.DB_NAME, null, TrainModel.DB_VERSION)
        {
            @Override
            public void onCreate(final SQLiteDatabase db)
            {
                db.execSQL(TrainModel.DB_CREATE_QUERY);
                db.execSQL(TrainModel.DB_CREATE_QUERY_STATION);
                db.execSQL(TrainModel.DB_CREATE_QUERY_TRAIN_STATION);
                str1 = "DDDDDDDDDDDDDATE...145: "+db.getPath();
            }
            @Override
            public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion)
            {
                db.execSQL("DROP TABLE IF EXISTS " + TrainModel.TABLE_NAME);
                db.execSQL("DROP TABLE IF EXISTS " + TrainModel.TABLE_NAME2);
                db.execSQL("DROP TABLE IF EXISTS " + TrainModel.TABLE_NAME3);
                str1 = "DDDDDDDDDDDDDATE...145: "+db.getPath();
//                db.getPath()
                this.onCreate(db);
            }
        };

        this.database = this.helper.getWritableDatabase();
    }

    public void addTrain(ContentValues data) {
        this.database.insert(TrainModel.TABLE_NAME, null, data);
    }
    public void deleteTrain(final String field_params)
    {
        this.database.delete(TrainModel.TABLE_NAME, field_params, null);
    }
    public Cursor loadAllTrains()
    {
        Log.d(TrainView.APP_TAG, "loadAllTasks()");
        final Cursor c = this.database.query(TrainModel.TABLE_NAME, new String[] { "train_no" , "train_name" , "source" , "destination" , "days_of_week"}, null, null, null, null, "train_no", null);
        return c;
    }
    public Cursor loadAllTrainTime(String station_selected)
    {
        Log.d(TrainView.APP_TAG, "loadAllTasks()");
        //String station_selected = "deh";
        //final Cursor c = this.database.rawQuery("SELECT ts.train_no, t.train_name, s.station_code, s.station_name, ts.time_arrival, t.days_of_week, (select s1.station_name from tblStation s1 where s1.station_code=t.source) as Source, (select s2.station_name from tblStation s2 where s2.station_code=t.destination) as Destination FROM tblTrain AS t, tblStation AS s, tblTrainStation AS ts WHERE ts.station_code=s.station_code and t.train_no=ts.train_no ORDER BY t.train_no, ts.time_arrival;",null);
        final Cursor c = this.database.rawQuery("SELECT ts.train_no, t.train_name, t.days_of_week, t.source as source_station_code, (select s1.station_name from tblStation s1 where s1.station_code=t.source) AS Source, (select ts1.time_arrival from tblTrainStation ts1 where ts1.train_no=ts.train_no and ts1.station_code=t.source) as SourceTime, t.destination as destination_station_code, (select s2.station_name from tblStation s2 where s2.station_code=t.destination) AS Destination, (select ts2.time_arrival from tblTrainStation ts2 where ts2.train_no=ts.train_no and ts2.station_code=t.destination) as DestinationTime, s.station_name, ts.time_arrival FROM tblTrain AS t, tblStation AS s, tblTrainStation AS ts WHERE ts.station_code=s.station_code and t.train_no=ts.train_no and s.station_code='"+ station_selected +"' ORDER BY ts.time_arrival;",null);
        String dbPath1 = this.database.getPath();
        Log.v("DDDDDDDDATE","...144: "+dbPath1);
        return c;
    }
    public void addStation(ContentValues data) {
        this.database.insert(TrainModel.TABLE_NAME2, null, data);
    }
    public void deleteStation(final String field_params)
    {
        this.database.delete(TrainModel.TABLE_NAME2, field_params, null);
        Log.v("DDDDDDDDATE","...144: "+str1);
    }
    public Cursor loadAllStations()
    {
        Log.d(TrainView.APP_TAG, "loadAllStations()");
        final Cursor c1 = this.database.query(TrainModel.TABLE_NAME2, new String[] { "station_code" , "station_name"}, null, null, null, null, "station_name",null);
        return c1;
    }
    public Cursor loadOneStations(String station_code)
    {
        Log.d(TrainView.APP_TAG, "loadOneStations()");
        final Cursor c1 = this.database.query(TrainModel.TABLE_NAME2, new String[] { "station_code" , "station_name"}, null, null, station_code, null, "station_name",null);
        return c1;
    }
    public void addTrainStation(ContentValues data) {
        this.database.insert(TrainModel.TABLE_NAME3, null, data);
    }
    public void updateTrainTime(String train_no, String station_code, String time_arrival, String time_departure) {
        //this.database.insert(TrainModel.TABLE_NAME3, null, data);
        //this.database.update(TrainModel.TABLE_NAME3,data,);
        String strUpdateQuery = "UPDATE tblTrainStation SET time_arrival='0123', time_departure='0123' WHERE station_code='deh' and train_no='99901'";
        //this.database.rawQuery("UPDATE tblTrainStation SET time_arrival='"+time_arrival+"', time_departure='"+time_departure+"' WHERE station_code='"+station_code+"' and train_no='"+train_no+"'",null);
        this.database.rawQuery(strUpdateQuery,null);
        Log.v("BBBBBBBBBbbb","...155: "+ strUpdateQuery);
    }
    public void deleteTrainStation(final String field_params)
    {
        //this.database.delete(TrainModel.TABLE_NAME3, field_params, null);
        String a= field_params.substring(0,5);
        String b = field_params.substring(6,9);
        String c = "delete from tblTrainStation where train_no='"+a+"' and station_code='"+b+"'";
        this.database.rawQuery(c ,null).moveToFirst();
        Log.v("DDDDDDDDATE","...155: "+a+"...."+c);
    }
    public Cursor loadTrainStations(String trainNo)
    {
        String selectTrain = "train_no = '"+trainNo+"'";
        final Cursor c1 = this.database.query(TrainModel.TABLE_NAME3, new String[] { "train_no" , "station_code", "time_arrival", "time_departure"}, selectTrain, null, null, null, "time_arrival",null);
        return c1;
    }
    public Cursor loadAllTrainStations()
    {
        Log.d(TrainView.APP_TAG, "loadAllTrainStations()");
        final Cursor c1 = this.database.query(TrainModel.TABLE_NAME3, new String[] { "train_no" , "station_code", "time_arrival", "time_departure"}, null, null, null, null, "time_arrival",null);
        return c1;
    }
}
