package com.example.verificientqa1.myapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ActionsDataSource {

		// Database fields
		private SQLiteDatabase database;
		private TtDSQLiteHelper dbHelper;
		private String[] allColumns = { TtDSQLiteHelper.COLUMN_ID
				, TtDSQLiteHelper.COLUMN_00INI
				, TtDSQLiteHelper.COLUMN_016HR
				, TtDSQLiteHelper.COLUMN_02PST
				, TtDSQLiteHelper.COLUMN_03KBT
				, TtDSQLiteHelper.COLUMN_044GL
				, TtDSQLiteHelper.COLUMN_05PRL
				, TtDSQLiteHelper.COLUMN_06GLY
				, TtDSQLiteHelper.COLUMN_07EDS
				, TtDSQLiteHelper.COLUMN_08TT1
				, TtDSQLiteHelper.COLUMN_09PWR
				, TtDSQLiteHelper.COLUMN_10SQA
				, TtDSQLiteHelper.COLUMN_11FBK
				, TtDSQLiteHelper.COLUMN_12TT2
				, TtDSQLiteHelper.COLUMN_13AND
				, TtDSQLiteHelper.COLUMN_14DDY
				, TtDSQLiteHelper.COLUMN_15GLY
				, TtDSQLiteHelper.COLUMN_16SRL
				, TtDSQLiteHelper.COLUMN_17LPY
				, TtDSQLiteHelper.COLUMN_18DBR
				, TtDSQLiteHelper.COLUMN_19TT3
				, TtDSQLiteHelper.COLUMN_20PRY};

		public ActionsDataSource(Context context) {
			dbHelper = new TtDSQLiteHelper(context);
		}

		public void open() throws SQLException {
			database = dbHelper.getWritableDatabase();
		}

		public void close() {
			dbHelper.close();
		}

		public Action createAction(int action[]) {
			ContentValues values = new ContentValues();

			Cursor cursor1 = database.rawQuery("select * from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where "+ TtDSQLiteHelper.COLUMN_00INI+"=" + getIntDate(), null);
			int jcount = cursor1.getCount();
			cursor1.close();
			if(jcount>0)
			{
				//Log.v("Deleting All Rows Deleting All Rows Deleting All Rows Deleting All Rows Deleting All Rows ",""+jcount+"");
				cursor1=database.rawQuery("delete from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where "+ TtDSQLiteHelper.COLUMN_00INI+"=" + getIntDate(), null);
				database.delete(TtDSQLiteHelper.TABLE_ACTIONS,TtDSQLiteHelper.COLUMN_00INI+"="+getIntDate(),null);
				cursor1.close();
			}
			//Log.v("Adding new row of current Date Adding new row of current Date Adding new row of current Date Adding new row of current Date ","hh");
			values.put(TtDSQLiteHelper.COLUMN_00INI, action[0]);
			values.put(TtDSQLiteHelper.COLUMN_016HR, action[1]);
			values.put(TtDSQLiteHelper.COLUMN_02PST, action[2]);
			values.put(TtDSQLiteHelper.COLUMN_03KBT, action[3]);
			values.put(TtDSQLiteHelper.COLUMN_044GL, action[4]);
			values.put(TtDSQLiteHelper.COLUMN_05PRL, action[5]);
			values.put(TtDSQLiteHelper.COLUMN_06GLY, action[6]);
			values.put(TtDSQLiteHelper.COLUMN_07EDS, action[7]);
			values.put(TtDSQLiteHelper.COLUMN_08TT1, action[8]);
			values.put(TtDSQLiteHelper.COLUMN_09PWR, action[9]);
			values.put(TtDSQLiteHelper.COLUMN_10SQA, action[10]);
			values.put(TtDSQLiteHelper.COLUMN_11FBK, action[11]);
			values.put(TtDSQLiteHelper.COLUMN_12TT2, action[12]);
			values.put(TtDSQLiteHelper.COLUMN_13AND, action[13]);
			values.put(TtDSQLiteHelper.COLUMN_14DDY, action[14]);
			values.put(TtDSQLiteHelper.COLUMN_15GLY, action[15]);
			values.put(TtDSQLiteHelper.COLUMN_16SRL, action[16]);
			values.put(TtDSQLiteHelper.COLUMN_17LPY, action[17]);
			values.put(TtDSQLiteHelper.COLUMN_18DBR, action[18]);
			values.put(TtDSQLiteHelper.COLUMN_19TT3, action[19]);
			values.put(TtDSQLiteHelper.COLUMN_20PRY, action[20]);
			long insertId = database.insert(TtDSQLiteHelper.TABLE_ACTIONS, null, values);
			Cursor cursor = database.query(TtDSQLiteHelper.TABLE_ACTIONS, allColumns, TtDSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Action newAction = cursorToAction(cursor);
			cursor.close();
			return newAction;
		}
		public int getMonth(int month1)
		{
			return(month1/100);
		}
		public void deleteAction() {
			database.delete(TtDSQLiteHelper.TABLE_ACTIONS,TtDSQLiteHelper.COLUMN_00INI+"="+getIntDate(),null);
		}
		public void deleteAllAction() {
			database.delete(TtDSQLiteHelper.TABLE_ACTIONS,TtDSQLiteHelper.COLUMN_00INI+"/100="+getMonth(getIntDate()),null);
//			Cursor cursor1 = database.rawQuery("select * from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where "+ TtDSQLiteHelper.COLUMN_00INI+"=" + getIntDate(), null);
		}
		public String summary()
		{
			String strSummary="";
			int intCount, intSum;
			String currMonth1=""+getIntDate();
			//Log.v("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH::",currMonth1);
			String currMonth=currMonth1;
			currMonth=currMonth.substring(4,6);
			//Log.v("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC::",currMonth);
			//currMonth="06";
			String query1="select count(*) as count1, sum("
					+ TtDSQLiteHelper.COLUMN_016HR + "+"
					+ TtDSQLiteHelper.COLUMN_02PST + "+"
					+ TtDSQLiteHelper.COLUMN_03KBT + "+"
					+ TtDSQLiteHelper.COLUMN_044GL + "+"
					+ TtDSQLiteHelper.COLUMN_05PRL + "+"
					+ TtDSQLiteHelper.COLUMN_06GLY + "+"
					+ TtDSQLiteHelper.COLUMN_07EDS + "+"
					+ TtDSQLiteHelper.COLUMN_08TT1 + "+"
					+ TtDSQLiteHelper.COLUMN_09PWR + "+"
					+ TtDSQLiteHelper.COLUMN_10SQA + "+"
					+ TtDSQLiteHelper.COLUMN_11FBK + "+"
					+ TtDSQLiteHelper.COLUMN_12TT2 + "+"
					+ TtDSQLiteHelper.COLUMN_13AND + "+"
					+ TtDSQLiteHelper.COLUMN_14DDY + "+"
					+ TtDSQLiteHelper.COLUMN_15GLY + "+"
					+ TtDSQLiteHelper.COLUMN_16SRL + "+"
					+ TtDSQLiteHelper.COLUMN_17LPY + "+"
					+ TtDSQLiteHelper.COLUMN_18DBR + "+"
					+ TtDSQLiteHelper.COLUMN_19TT3 + "+"
					+ TtDSQLiteHelper.COLUMN_20PRY 
					+ ") as sum1 from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where substr("+TtDSQLiteHelper.COLUMN_00INI+",5,2)='"+currMonth+"'";
			Cursor cursor = database.rawQuery(query1, null);
					//+ ") as sum1 from "+ TtDSQLiteHelper.TABLE_ACTIONS, null);
//				jcount = cursor.getInt(1);
					//Log.v("IN Count Action IN Count Action IN Count Action IN Count Action IN Count Action IN Count Action", "+++++="+cursor.getCount());
					cursor.moveToFirst();
					intCount=cursor.getInt(cursor.getColumnIndex("count1"));
					intSum=cursor.getInt(cursor.getColumnIndex("sum1"));
					strSummary="#Days-"+intCount+", "+intSum+"("+((intSum*100)/(intCount*20))+"%)";
				return(""+strSummary);
		}
		public int countAction() {
			int jcount=0;
			Cursor cursor = database.rawQuery("select "
				+ TtDSQLiteHelper.COLUMN_016HR + "+"
				+ TtDSQLiteHelper.COLUMN_02PST + "+"
				+ TtDSQLiteHelper.COLUMN_03KBT + "+"
				+ TtDSQLiteHelper.COLUMN_044GL + "+"
				+ TtDSQLiteHelper.COLUMN_05PRL + "+"
				+ TtDSQLiteHelper.COLUMN_06GLY + "+"
				+ TtDSQLiteHelper.COLUMN_07EDS + "+"
				+ TtDSQLiteHelper.COLUMN_08TT1 + "+"
				+ TtDSQLiteHelper.COLUMN_09PWR + "+"
				+ TtDSQLiteHelper.COLUMN_10SQA + "+"
				+ TtDSQLiteHelper.COLUMN_11FBK + "+"
				+ TtDSQLiteHelper.COLUMN_12TT2 + "+"
				+ TtDSQLiteHelper.COLUMN_13AND + "+"
				+ TtDSQLiteHelper.COLUMN_14DDY + "+"
				+ TtDSQLiteHelper.COLUMN_15GLY + "+"
				+ TtDSQLiteHelper.COLUMN_16SRL + "+"
				+ TtDSQLiteHelper.COLUMN_17LPY + "+"
				+ TtDSQLiteHelper.COLUMN_18DBR + "+"
				+ TtDSQLiteHelper.COLUMN_19TT3 + "+"
				+ TtDSQLiteHelper.COLUMN_20PRY 
				+ " as count1 from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where "+ TtDSQLiteHelper.COLUMN_00INI+"=" + getIntDate(), null);
//			jcount = cursor.getInt(1);
			//Log.v("IN Count Action IN Count Action IN Count Action IN Count Action IN Count Action IN Count Action", "INto Count Action ");
			if(cursor.getCount()>0)
			{
				cursor.moveToFirst();
				jcount=cursor.getInt(cursor.getColumnIndex("count1"));
			}
			//Log.v("Count Action Count Action Count Action Count Action Count Action Count Action ",""+jcount+"");
			return(jcount);
		}

		public List<Action> getAllActions() {
			List<Action> Actions = new ArrayList<Action>();

			Cursor cursor = database.query(TtDSQLiteHelper.TABLE_ACTIONS,
					allColumns, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
//				Action action = cursorToAction(cursor);
//				Actions.add(action);
				cursor.moveToNext();
			}
			// Make sure to close the cursor
			cursor.close();
			return Actions;
		}
		
		public boolean NotEmpty()
		{
			Cursor cursor = database.rawQuery("select * from "+ TtDSQLiteHelper.TABLE_ACTIONS+" where "+TtDSQLiteHelper.COLUMN_00INI+"="+getIntDate(), null);
			int jcount = cursor.getCount();
			//Log.v("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXYZ New Database","Frm action datasource hemant"+jcount+"Hemant");
			if(jcount==0)
				return false;
			else
				return true;
		}

	public int getIntDate()
	{
//			Calendar c1=Calendar.getInstance();
		Calendar c1=TtDSQLiteHelper.cal1;
		int year1=c1.get(Calendar.YEAR);
		int month1=c1.get(Calendar.MONTH);
		month1=month1+1;
		int day1=c1.get(Calendar.DATE);
		return(year1*10000+month1*100+day1);
	}
	public int getIntDate(String date1)
	{
        String dateStr = ""+date1.subSequence(8,10);
		Calendar c1=TtDSQLiteHelper.cal1;
//        int year1=c1.get(Calendar.YEAR);
  //      int month1=c1.get(Calendar.MONTH);
        //int year1=c1.get(Calendar.YEAR);
        //int month1=c1.get(Calendar.MONTH);
        //int date1=(2*(Integer.parseInt(""+dateStr)%100))-1;s
		Log.v("WWWWWWWW..."," WW"+date1.subSequence(0,4));
		Log.v("WWWWWWWW..."," WW"+date1.subSequence(5,7));
		Log.v("WWWWWWWW..."," WW"+date1.subSequence(8,10));
        int year1 = (Integer.parseInt(""+date1.subSequence(0,4)));
		int month1 = (Integer.parseInt(""+date1.subSequence(5,7)));
		int day1 = (Integer.parseInt(""+date1.subSequence(8,10)));
		return(year1*10000+month1*100+day1);
	}

	public String getStringDate()
		{
//			Calendar c1=Calendar.getInstance();
			Calendar c1=TtDSQLiteHelper.cal1;
			int year1=c1.get(Calendar.YEAR);
			int month1=c1.get(Calendar.MONTH);
			month1=month1+1;
			String month2="";
			if(month1<10)
				month2="0"+month1;
			else
				month2=""+month1;

			int day1=c1.get(Calendar.DATE);
			String day2="";
			if(day1<10)
				day2="0"+day1;
			else
				day2=""+day1;
			String weekDay="";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
				weekDay="SUN";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
				weekDay="Mon";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY)
				weekDay="Tue";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
				weekDay="Wed";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY)
				weekDay="Thu";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
				weekDay="Fri";
			if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
				weekDay="Sat";
			//return(""+weekDay+", "+ day1);
			Log.v("WWWWWWWW..."," WW..."+""+year1+" "+month1+" "+day2+", "+weekDay);
			return(""+year1+" "+month2+" "+day2+", "+weekDay);
		}
		public int[] loadActions()
		{
			int intAction[]=new int[30];
			//Log.v("IFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIF", "IFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIFIF");
			Cursor cursor = database.query(TtDSQLiteHelper.TABLE_ACTIONS, allColumns, TtDSQLiteHelper.COLUMN_00INI + " = "+getIntDate(),null,null,null,null);
			if(cursor.getCount()==0)
			{
				for(int i=0;i<21;i++)
					intAction[i]=0;
			}
			else
			{
				cursor.moveToFirst();
				for(int i=0;i<21;i++)
					intAction[i]=cursor.getInt(i);
			}
			// Make sure to close the cursor
			cursor.close();			
			return(intAction);
		}
		private Action cursorToAction(Cursor cursor) {
			Action action = new Action();
			action.setId(cursor.getLong(0));
			action.setAction(cursor.getString(1)+""+cursor.getString(2));
			return action;
		}
	}