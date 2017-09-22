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
        final Cursor c = this.database.query(TrainModel.TABLE_NAME, new String[] { "train_no" , "train_name" , "source" , "destination" , "days_of_week"}, null, null, null, null, null); return c;
    }
    public Cursor loadAllTrainTime()
    {
        Log.d(TrainView.APP_TAG, "loadAllTasks()");
        /*SELECT ts.[trainno], s.stationname, ts.timearrival
        FROM tblTrainStation AS ts, tblStation AS s
        WHERE ts.stationcode=s.stationcode
        ORDER BY trainno, timearrival;*/

//        final Cursor c = this.database.rawQuery("select t.train_no, t.train_name, t.source, t.destination, s.station_code, s.station_name from tblTrain t, tblStation s, tblTrainStation ts where t.source='pun' and ts.train_no=t.train_no and ts.station_code=s.station_code order by t.train_no",null);
        final Cursor c = this.database.rawQuery("select ts.train_no, s.station_name, ts.time_arrival, ts.train_no, s.station_name, ts.time_arrival  from tblTrainStation ts, tblStation s where ts.station_code=s.station_code order by train_no, time_arrival;",null);
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
    public void addTrainStation(ContentValues data) {
        this.database.insert(TrainModel.TABLE_NAME3, null, data);
    }
    public void deleteTrainStation(final String field_params)
    {
        this.database.delete(TrainModel.TABLE_NAME3, field_params, null);
        Log.v("DDDDDDDDATE","...144: "+str1);
    }
    public Cursor loadAllTrainStations()
    {
        Log.d(TrainView.APP_TAG, "loadAllTrainStations()");
        final Cursor c1 = this.database.query(TrainModel.TABLE_NAME3, new String[] { "train_no" , "station_code", "time_arrival", "time_departure"}, null, null, null, null, null);
        return c1;
    }
}
