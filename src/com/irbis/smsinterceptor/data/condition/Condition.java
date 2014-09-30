package com.irbis.smsinterceptor.data.condition
;

import com.irbis.smsinterceptor.data.SmsMessage;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Conditions")
public class  Condition
{
	//============================================================
	// Constants
	// ===========================================================		
		public final static String CONDITION_STRATEGY = "Strategy";
		public final static String CONDITION_ID = "ID";
		public final static String CONDITION_SIMPLE = "Simple";
		
	// ===========================================================
	// Fields
	// ===========================================================
		@DatabaseField(canBeNull = false, generatedId = true,dataType = DataType.LONG, columnName = CONDITION_ID)
		private long mID;
		@DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE, columnName = CONDITION_STRATEGY)
		private ConditionStrategy mConditionStrategy;
		/*@DatabaseField(canBeNull = false, dataType = DataType.BOOLEAN, columnName = CONDITION_SIMPLE)
		private boolean mSimple = false;*/
	// ===========================================================
	// Static Methods
	// ===========================================================

	
	// ===========================================================
	// Constructors
	// ===========================================================
		public Condition()
		{
			
		}
		public Condition(ConditionStrategy inConditionStrategy )
		{
			mConditionStrategy = inConditionStrategy;
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
		
		/*public boolean isSimple()
		{
			return mSimple;
		}
		
		public void setSimple(boolean inSimple)
		{
			mSimple = inSimple;
		}*/
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================		
		@Override
		public String toString()
		{
			return "Condition [mID=" + mID + ","
					+ " mConditionStrategy=" + mConditionStrategy + "]";
		}
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((mConditionStrategy == null) ? 0 : mConditionStrategy
							.hashCode());
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
			Condition other = (Condition) obj;
			if (mConditionStrategy == null)
			{
				if (other.mConditionStrategy != null)
				{
					return false;
				}
			} else if (!mConditionStrategy.equals(other.mConditionStrategy))
			{
				return false;
			}
			if (mID != other.mID)
			{
				return false;
			}
			return true;
		}
	// ===========================================================
	// Methods
	// ===========================================================		
		
		public boolean matches(SmsMessage inSmsMessage)
		{
			return mConditionStrategy.matches(inSmsMessage);
		}
		public ConditionStrategy getStrategy()
		{
			return mConditionStrategy;
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
