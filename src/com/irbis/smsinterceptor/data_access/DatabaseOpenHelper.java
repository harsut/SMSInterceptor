package com.irbis.smsinterceptor.data_access;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.data.action.Action;
import com.irbis.smsinterceptor.data.condition.Condition;
import com.irbis.smsinterceptor.reports.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper
{
	//============================================================
	// Constants
	// ===========================================================		
		private static final String DATABASE_NAME ="SmsIntercepter.db";
		private static final int DATABASE_VERSION = 2;
	// ===========================================================
	// Fields
	// ===========================================================
		private Dao<Condition, Integer> mConditionDAO = null;		   
		private SmsMessageDAO mSmsMessageDAO = null;		
		private Dao<Action, Integer> mActionDAO = null;
		private Dao<Rule, Integer> mRuleDAO = null;
		//static private DatabaseHelper mInstance;
		
		//private Context mContext;
	// ===========================================================
	// Static Methods
	// ===========================================================	
		/*public static DatabaseHelper getInstance(Context inContext)
		{
			if(null == mInstance)
			{
				mInstance = new  DatabaseHelper(inContext);
			}
			return mInstance;
		}*/
	// ===========================================================
	// Constructors
	// ===========================================================
		public DatabaseOpenHelper(Context inContext)
		{
			super(inContext,DATABASE_NAME, null, DATABASE_VERSION);
			//mContext = inContext;
		}
		
	// ===========================================================
	// Getter & Setter
	// ===========================================================	
				
		synchronized protected SmsMessageDAO getSmsMessageDAO() 
		{
			try
			{
				if(mSmsMessageDAO == null)
				{	
					mSmsMessageDAO = new SmsMessageDAO(getConnectionSource(), SmsMessage.class);
				}
								
			} 
			catch (SQLException e)
			{
				Log.error(this,"Error on retreiving DataTable "+SmsMessage.class.getName());
				throw new RuntimeException(e);	
			}
		    return mSmsMessageDAO;
		}
		synchronized protected Dao<Condition, Integer> getConditionDAO() 
		{
			try
			{
				if(mConditionDAO == null)
				{
					
						mConditionDAO = DaoManager.createDao(getConnectionSource(), Condition.class);
					
				}
			} 
			catch (SQLException e)
			{
				Log.error(this,"Error on retreiving DataTable "+Condition.class.getName());
				throw new RuntimeException(e);	
			}
		    return mConditionDAO;
		}
		synchronized protected Dao<Action, Integer> getActionDAO() 
		{
			try
			{
				if(mActionDAO == null)
				{
					mActionDAO = DaoManager.createDao(getConnectionSource(), Action.class);
				}		
			} 
			catch (SQLException e)
			{
				Log.error(this,"Error on retreiving DataTable "+Action.class.getName());
				throw new RuntimeException(e);	
			}
		    return mActionDAO;
		}
		synchronized protected Dao<Rule, Integer> getRuleDAO() 
		{
			try
			{
				if(mRuleDAO == null)
				{
					mRuleDAO = DaoManager.createDao(getConnectionSource(), Rule.class);
				}
								
			} 
			catch (SQLException e)
			{
				Log.error(this,"Error on retreiving DataTable "+Rule.class.getName());
				throw new RuntimeException(e);	
			}
		    return mRuleDAO;
		}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		synchronized public void onCreate(SQLiteDatabase inSQLiteDatabase, ConnectionSource inConnectionSource)
		{
			try
			{
				TableUtils.createTable(inConnectionSource, Condition.class);
				TableUtils.createTable(inConnectionSource, Action.class);
				TableUtils.createTable(inConnectionSource, Rule.class);
				TableUtils.createTable(inConnectionSource, SmsMessage.class);
			}
			catch (SQLException e)
			{
				Log.error(this, "Error on creating DB " + DATABASE_NAME);
				throw new RuntimeException(e);
			}
		}
		@Override
		public void onUpgrade(SQLiteDatabase inSQLiteDatabase, ConnectionSource inConnectionSource, int inOldVer, int inNewVer)
		{			
			ClearDB();//FIXME do something more sensible, before update			
		}
		
	// ===========================================================
	// Methods
	// ===========================================================
		
		synchronized public void ClearDB()
		{
			try
			{				
				TableUtils.dropTable(getConnectionSource(), Condition.class, true);
				TableUtils.dropTable(getConnectionSource(), Action.class, true);
				TableUtils.dropTable(getConnectionSource(), Rule.class, true);
				TableUtils.dropTable(getConnectionSource(), SmsMessage.class,true);
				
				onCreate(null, getConnectionSource());
			}
			catch (SQLException e)
			{
				Log.error(this,"Error on clearing DataBase "+DATABASE_NAME);
				throw new RuntimeException(e);				
			}
			
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}

