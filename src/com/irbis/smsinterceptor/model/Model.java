package com.irbis.smsinterceptor.model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;

import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsHistory;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.data.action.Action;
import com.irbis.smsinterceptor.data.comparator.PhoneNumber;
import com.irbis.smsinterceptor.data.comparator.PhoneNumberResolver;
import com.irbis.smsinterceptor.data_access.DatabaseHelper;
import com.irbis.smsinterceptor.data_access.SmsInterceptorSharedPreferences;

public class Model
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		private DatabaseHelper mDatabaseHelper;
		private SmsInterceptorSharedPreferences mSharedPreferences;
		
		private LinkedList<ISmsMessageListener> mSmsMessageListeners = new LinkedList<ISmsMessageListener>();
		private static Model mInstance;
	// ===========================================================
	// Static Methods
	// ===========================================================
		synchronized public static Model getInstance(Context inContext)
		{
			if(null == mInstance)
			{
				mInstance = new Model(inContext);
			}
			else
			{				
				mInstance.mDatabaseHelper = DatabaseHelper.getInstance(inContext);
			}
			return mInstance;			
		}
		
	// ===========================================================
	// Constructors
	// ===========================================================
		private Model(Context inContext)
		{
			mDatabaseHelper = DatabaseHelper.getInstance(inContext);
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public List<SmsMessage> getSmsMessages() throws DataAccessException
		{
			List<SmsMessage> messages = null;
			try
			{
				messages = mDatabaseHelper.getSmsMessages();
				return messages;
			}
			catch (SQLException e)
			{
				throw new DataAccessException(e);
			}
		}
		public List<SmsMessage> getSmsMessages(String inSender) throws DataAccessException
		{
			List<SmsMessage> messages = null;
			try
			{
				messages = mDatabaseHelper.getSmsMessages(inSender);
				return messages;
			} 
			catch (SQLException e)
			{
				throw new DataAccessException(e);
			}		
		}
		public List<Action> getActions() throws DataAccessException
		{
			List<Action> actions = null; 
			try
			{
				actions = mDatabaseHelper.getActions();
				return actions;
			} 
			catch (SQLException e)
			{
				
				throw new DataAccessException(e);
			}		
		}
		public Collection<Rule> getRules() throws DataAccessException
		{ 
			Collection<Rule> rules  =  null;
			try
			{	
				rules = mDatabaseHelper.getRules();
				return rules;
			} 
			catch (SQLException e)
			{
				throw new DataAccessException(e);
			}
		}
		public Rule getRule(String rule_id)throws DataAccessException
		{
			Rule rule;
			try
			{	
				rule = mDatabaseHelper.getRule(rule_id);
				return rule;
			} 
			catch (SQLException e)
			{
				throw new DataAccessException(e);
			}
		}
		public SmsHistory getSmsHistory(String inSender) throws DataAccessException
		{
			PhoneNumber phoneNumber = PhoneNumberResolver.getInstance().getPhoneNumberByNumber(inSender);
	        SmsHistory smsHistory  = new SmsHistory(phoneNumber);
	        
			List<SmsMessage> smsMessages = getSmsMessages(inSender);
			for(SmsMessage smsMessage : smsMessages )
			{
				boolean success = smsHistory.addSmsMessage(smsMessage);
				assert(success);//smsHistory's comparator must accept everything
			}
			return smsHistory;
		}		
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		protected void finalize()
		{
			release();
		}
	// ===========================================================
	// Methods
	// ===========================================================
		public void saveSmsMessage(SmsMessage inSmsMessage) throws DataAccessException
		{
			try
			{
				mDatabaseHelper.saveSmsMessage(inSmsMessage);
				synchronized(mSmsMessageListeners)
				{
					for(ISmsMessageListener smsMessageListener  :mSmsMessageListeners)
					{
						smsMessageListener.onSmsMessageSaved(inSmsMessage);
					}
				}
			} 
			catch (SQLException e)
			{
				throw new DataAccessException(e);
			}
		}
		public void release()
		{
			if(mDatabaseHelper != null)
				mDatabaseHelper = DatabaseHelper.release();
		}
		@Deprecated
		public void testFillDB()
		{
			mDatabaseHelper.testFillDB();
		}
		@Deprecated
		public void testReadDB()
		{
			mDatabaseHelper.testReadDB();
		}
		void addSmsMessageListener(ISmsMessageListener inSmsMessageListener)
		{
			synchronized (inSmsMessageListener)
			{
				mSmsMessageListeners.add(inSmsMessageListener);	
			}
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
		interface ISmsMessageListener
		{
			void onSmsMessageSaved(SmsMessage inSmsMessage );			
		}
	
}
