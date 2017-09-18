package com.kumar.hemant.travelguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

public class TtDSQLiteHelper extends SQLiteOpenHelper {

    public static Calendar c1= Calendar.getInstance();
    public static int month1=c1.get(Calendar.MONTH);
    public static String TABLE_ACTIONS = "actions5";
    public static String TABLE_VEHICLES = "vehicles5";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_016HR = "A016HR";
    public static String COLUMN_02PST = "A02PST";
    public static String COLUMN_03KBT = "A03KBT";
    public static String COLUMN_044GL = "A044GL";
    public static String COLUMN_00INI = "A00INI";
    public static String COLUMN_05PRL = "A05PRL";
    public static String COLUMN_06GLY = "A06GLY";
    public static String COLUMN_07EDS = "A07EDS";
    public static String COLUMN_08TT1 = "A08TT1";
    public static String COLUMN_09PWR = "A09PWR";
    public static String COLUMN_10SQA = "A10SQA";
    public static String COLUMN_11FBK = "A11FBK";
    public static String COLUMN_12TT2 = "A12TT2";
    public static String COLUMN_13AND = "A13AND";
    public static String COLUMN_14DDY = "A14DDY";
    public static String COLUMN_15GLY = "A15GLY";
    public static String COLUMN_16SRL = "A16SRL";
    public static String COLUMN_17LPY = "A17LPY";
    public static String COLUMN_18DBR = "A18DBR";
    public static String COLUMN_19TT3 = "A19TT3";
    public static String COLUMN_20PRY = "A20PRY";
    public static String COLUMN_VEHICLE_ID = "VEHICLE_ID";
    public static String COLUMN_STATE_CODE = "STATE_CODE";
    public static String COLUMN_RTO = "RTO_CODE";
    public static String COLUMN_RTO_NAME = "RTO_NAME";
    public static String COLUMN_STATE_NAME = "STATE_NAME";
    public static String COLUMN_COMMENTS = "COMMENTS";
    public static Calendar cal1= Calendar.getInstance();
    private static final String DATABASE_NAME = "actions5.db";//+TABLE_SUFFIX+".db";
    private static final int DATABASE_VERSION = 1;

    private static String DATABASE_CREATE_VEHICLE = "create table " + TABLE_VEHICLES + "( " + COLUMN_VEHICLE_ID + " integer primary key autoincrement, "
    + COLUMN_STATE_CODE + " text not null, "
    + COLUMN_RTO + " text, "
    + COLUMN_RTO_NAME + " text, "
    + COLUMN_STATE_NAME + " text, "
    + COLUMN_COMMENTS + " text);";

    private static String DATABASE_CREATE = "create table " + TABLE_ACTIONS + "( " + COLUMN_ID + " integer primary key autoincrement, "
    + COLUMN_00INI + " integer not null, "
    + COLUMN_016HR+ " integer not null, "
    + COLUMN_02PST+ " integer not null, "
    + COLUMN_03KBT+ " integer not null, "
    + COLUMN_044GL+ " integer not null, "
    + COLUMN_05PRL+ " integer not null, "
    + COLUMN_06GLY+ " integer not null, "
    + COLUMN_07EDS+ " integer not null, "
    + COLUMN_08TT1+ " integer not null, "
    + COLUMN_09PWR+ " integer not null, "
    + COLUMN_10SQA+ " integer not null, "
    + COLUMN_11FBK+ " integer not null, "
    + COLUMN_12TT2+ " integer not null, "
    + COLUMN_13AND+ " integer not null, "
    + COLUMN_14DDY+ " integer not null, "
    + COLUMN_15GLY+ " integer not null, "
    + COLUMN_16SRL+ " integer not null, "
    + COLUMN_17LPY+ " integer not null, "
    + COLUMN_18DBR+ " integer not null, "
    + COLUMN_19TT3+ " integer not null, "
    + COLUMN_20PRY+ " integer not null);";

    public TtDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_VEHICLE);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TtDSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
        onCreate(db);
    }
    public void onDelete1(SQLiteDatabase database)
    {
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACTIONS);
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_VEHICLES);
    }
}