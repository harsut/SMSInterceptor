package com.irbis.smsinterceptor.data.action;

import android.content.Context;

import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Actions")
public class Action
{	
	//============================================================
	// Constants
	// ===========================================================

	

		public final static String ACTION_STRATEGY = "Strategy";
		public final static String ACTION_ID = "ID";
		public static final String RULE_ID = "rule_id";
	// ===========================================================
	// Fields
	// ===========================================================
	
		@DatabaseField(generatedId = true, dataType = DataType.LONG, columnName = ACTION_ID)
		private long mID;	
		@DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE, columnName = ACTION_STRATEGY)
		private ActionStrategy mActionStrategy;
						
		@SuppressWarnings("unused")
		@DatabaseField(foreign = true, foreignAutoRefresh = true,columnName = RULE_ID)
		private Rule mRuleORMBackLink;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
		public Action()
		{
			
		}		
		public Action(ActionStrategy inActionStrategy)
		{
			if ( null == inActionStrategy ) 
			{
				throw new IllegalArgumentException("inActionStrategy must be not null.");
			}
			mActionStrategy = inActionStrategy;
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public long getID()
		{
			return mID;
		}
		public void setID(long inID)
		{
			mID = inID; 
		}
		public void setRule(Rule inRule)
		{
			if ( null == inRule ) 
			{
				throw new IllegalArgumentException("inRule must be not null.");
			}
			mRuleORMBackLink = inRule;
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((mActionStrategy == null) ? 0 : mActionStrategy.hashCode());
			result = prime * result + (int) (mID ^ (mID >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (getClass() != obj.getClass())
			{
				return false;
			}
			Action other = (Action) obj;
			if (mActionStrategy == null)
			{
				if (other.mActionStrategy != null)
				{
					return false;
				}
			} else if (!mActionStrategy.equals(other.mActionStrategy))
			{
				return false;
			}
			if (mID != other.mID)
			{
				return false;
			}
			return true;
		}
		@Override
		public String toString()
		{
			return "Action [mID=" + mID + ", mActionStrategy=" + mActionStrategy
					+ "]";
		}
	// ===========================================================
	// Methods
	// ===========================================================
		
		public boolean perform(Context inContext, SmsMessage inSmsMessage)
		{
			return mActionStrategy.performAction(inContext, inSmsMessage);
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
