package com.irbis.smsinterceptor.data.condition;

import com.irbis.smsinterceptor.data.SmsMessage;


public final class ContentConditionStrategy extends ConditionStrategy
{
		private static final long serialVersionUID = 12L;
	//============================================================
	// Constants
	// ===========================================================
		public final static transient String TAG = ContentConditionStrategy.class.getName();
	// ===========================================================
	// Fields
	// ===========================================================
		private String mPattern; 
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
		public ContentConditionStrategy(String inPattern)
		{
			mPattern = inPattern;
		}		
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
				
		@Override
		public boolean matches(SmsMessage inSmsMessage)
		{			
			return inSmsMessage.getBody().contains(mPattern);
		}		
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((mPattern == null) ? 0 : mPattern.hashCode());
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
			ContentConditionStrategy other = (ContentConditionStrategy) obj;
			if (mPattern == null)
			{
				if (other.mPattern != null)
				{
					return false;
				}
			} 
			else if (!mPattern.equals(other.mPattern))
			{
				return false;
			}
			return true;
		}
	// ===========================================================
	// Methods
	// ===========================================================
		public String getContent()
		{
			return mPattern;
		}

		public void setContent(String inContent)
		{
			mPattern = inContent;
		}
				
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================	
}
