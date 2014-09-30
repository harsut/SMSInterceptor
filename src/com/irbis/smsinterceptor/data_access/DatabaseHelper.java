package com.irbis.smsinterceptor.data_access;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import net.jcip.annotations.ThreadSafe;

import android.content.Context;

import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.data.action.Action;
import com.irbis.smsinterceptor.data.action.BlockActionStrategy;
import com.irbis.smsinterceptor.data.action.SaveToDBActionStrategy;
import com.irbis.smsinterceptor.data.condition.Condition;
import com.irbis.smsinterceptor.data.condition.ContentConditionStrategy;
import com.irbis.smsinterceptor.reports.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

@ThreadSafe
public class DatabaseHelper
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		private DatabaseOpenHelper mDatabaseOpenHelper;
		
		
		Context mContext;
		static private DatabaseHelper mInstance;	
		
	// ===========================================================
	// Static Methods
	// ===========================================================
		synchronized public static DatabaseHelper getInstance(Context inContext)
		{
			if(null == mInstance)
			{
				mInstance = new DatabaseHelper(inContext);
			}
			else
			{
				if(!inContext.equals(mInstance.mContext))
				{
					mInstance = DatabaseHelper.release();
					mInstance = new DatabaseHelper(inContext);
				}
			}
			return mInstance;			
		}
		
	// ===========================================================
	// Constructors
	// ===========================================================
		private DatabaseHelper(Context inContext)
		{			
			mDatabaseOpenHelper = OpenHelperManager.getHelper(inContext, DatabaseOpenHelper.class);
			mContext = inContext;
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================		
		synchronized public List<SmsMessage> getSmsMessages() throws SQLException
		{
			return mDatabaseOpenHelper.getSmsMessageDAO().getAll();
		}
		synchronized public List<SmsMessage> getSmsMessages(String inSender) throws SQLException
		{
			return mDatabaseOpenHelper.getSmsMessageDAO().getBySender(inSender);		
		}
		synchronized public Collection<Rule> getRules() throws SQLException
		{
			Collection<Rule>  rules = mDatabaseOpenHelper.getRuleDAO().queryForAll();
			for(Rule rule : rules)
			{
				mDatabaseOpenHelper.getRuleDAO().refresh(rule);
			}
			return rules;
		}
		synchronized public List<Action> getActions() throws SQLException
		{
			return mDatabaseOpenHelper.getActionDAO().queryForAll();
		}

		synchronized public Rule getRule(String rule_id) throws SQLException
		{	
			Dao<Rule, Integer> dao = mDatabaseOpenHelper.getRuleDAO();
			List<Rule> list = dao.query(dao.queryBuilder().where().eq(Rule.RULE_ID,rule_id).prepare());
			if(!list.isEmpty())
				return list.get(0);
			return null;
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
		synchronized public static DatabaseHelper release()
		{
			if(null != mInstance)
			{
				OpenHelperManager.releaseHelper();
				mInstance = null;
			}
			return null;
		}
		synchronized public void saveSmsMessage(SmsMessage inSmsMessage) throws SQLException
		{
			mDatabaseOpenHelper.getSmsMessageDAO().create(inSmsMessage);
		}
		@Deprecated
		synchronized public void testFillDB()
		{
			Dao<Rule, Integer> ruleDAO = mDatabaseOpenHelper.getRuleDAO();
			Dao<Action, Integer> actionDAO = mDatabaseOpenHelper.getActionDAO();
			Dao<Condition, Integer> conditionDAO = mDatabaseOpenHelper.getConditionDAO();
			Dao<SmsMessage, Integer> smsMessageDAO = mDatabaseOpenHelper.getSmsMessageDAO();
	        
	        Condition condition;
	        Action actionBlock = new Action(new BlockActionStrategy());
	        Action actionSave  = new Action(new SaveToDBActionStrategy());			        
	        Rule rule;
	        try
			{
	        	mDatabaseOpenHelper.ClearDB();
	        	
	        	condition = new Condition(new ContentConditionStrategy("Spam1"));
		        conditionDAO.create(condition);				        
		        rule = new Rule(condition);				        
		        
		        
		        actionBlock = new Action(new BlockActionStrategy());
		        actionSave  = new Action(new SaveToDBActionStrategy());
		        rule.addAction(actionBlock);
		        rule.addAction(actionSave);
		        rule.setName("Spam1");
		        rule.setActive(true);
		        ruleDAO.create(rule);
		        actionDAO.create(actionBlock);
	        	actionDAO.create(actionSave);
	        	
		        Log.info(this, rule.toString());
		        
				condition = new Condition(new ContentConditionStrategy("Spam2"));
				conditionDAO.create(condition);
		        rule = new Rule(condition);
		        
		        actionBlock = new Action(new BlockActionStrategy());
		        actionSave  = new Action(new SaveToDBActionStrategy());
		        rule.addAction(actionBlock);
		        rule.addAction(actionSave);
		        rule.setName("Spam2");
		        rule.setActive(true);
		        ruleDAO.create(rule);
		        actionDAO.create(actionBlock);
	        	actionDAO.create(actionSave);
	        	Log.info(this, rule.toString());
	        	
	        	smsMessageDAO.create(new SmsMessage("911","Hello Spam1",1));			        	
	        	smsMessageDAO.create(new SmsMessage("911","Hello Spam2",1));
	        	smsMessageDAO.create(new SmsMessage("911","Hello Spam3",1));
	        	smsMessageDAO.create(new SmsMessage("911","Hello Spam4",1));
	        	smsMessageDAO.create(new SmsMessage("911","Hello Spam5",1));
	        	
	        	smsMessageDAO.create(new SmsMessage("02","Hello Spam1",1));
	        	smsMessageDAO.create(new SmsMessage("02","Hello Spam2",1));
	        	smsMessageDAO.create(new SmsMessage("02","Hello Spam3",1));
	        	
	        	smsMessageDAO.create(new SmsMessage("03","Hello Spam1",1));
	        	smsMessageDAO.create(new SmsMessage("03","Hello Spam2",1));
	        	smsMessageDAO.create(new SmsMessage("03","Hello Spam3",1));
	        	
	        	smsMessageDAO.create(new SmsMessage("04","Hello Spam1",1));
	        	smsMessageDAO.create(new SmsMessage("04","Hello Spam2",1));
	        	smsMessageDAO.create(new SmsMessage("04","Hello Spam3",1));
	        		        	
			} 
	        catch (SQLException e)
			{	
	        	Log.error(this, e.getMessage());
			}
		}
		@Deprecated
		synchronized public void testReadDB()
		{
			try
			{
				List<Condition> ñonditions =  mDatabaseOpenHelper.getConditionDAO().queryForAll();
				for(Condition condition : ñonditions)
				{
					Log.info(this,condition.toString());
				}		        	
				List<Action> actions = mDatabaseOpenHelper.getActionDAO().queryForAll();
	        	for(Action action : actions)
				{
					Log.info(this,action.toString());						
				}
	        	List<Rule> rules = mDatabaseOpenHelper.getRuleDAO().queryForAll();
	        	for(Rule rule : rules)
				{
	        		
					Log.info(this,rule.toString());
					Log.error(this,  "(Not error just test) Actions count ="+rule.getActions().size());
				}
			} 
	        catch (SQLException e)
			{
	        	Log.error(this, e.getMessage());
			}
		}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

		
		

		
}
