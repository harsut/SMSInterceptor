package com.irbis.smsinterceptor.data.condition;

import com.irbis.smsinterceptor.data.SmsMessage;

public class SimpleConditionStrategy extends CompositeConditionStrategy
{
	
	private static final long serialVersionUID = -6839529721404453225L;
	
	// ===========================================================
	// Fields
	// ===========================================================
	SenderConditionStrategy mSenderConditionStrategy;
	
	ContentConditionStrategy mContentConditionStrategy;
	// ===========================================================
	// Constructors
	// ===========================================================
		public SimpleConditionStrategy(	SenderConditionStrategy inSenderConditionStrategy,
										ContentConditionStrategy inContentConditionStrategy)
		{
			super(inSenderConditionStrategy,inContentConditionStrategy);
			mSenderConditionStrategy = inSenderConditionStrategy;
			mContentConditionStrategy = inContentConditionStrategy;
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public boolean matches(SmsMessage inSmsMessage)
		{			
			boolean matches = false;
			for(ConditionStrategy conditionStrategy : getConditionStrategies())
			{
				matches &=conditionStrategy.matches(inSmsMessage);
			}
			return matches;
		} 
	// ===========================================================
	// Getters & Setters
	// ===========================================================
		public SenderConditionStrategy getSenderConditionStrategy()
		{
			return mSenderConditionStrategy;
		}
		public void setSenderConditionStrategy(SenderConditionStrategy inSenderConditionStrategy)
		{
			mSenderConditionStrategy = inSenderConditionStrategy;
		}
		public ContentConditionStrategy getContentConditionStrategy()
		{
			return mContentConditionStrategy;
		}
		public void setmContentConditionStrategy(ContentConditionStrategy inContentConditionStrategy)
		{
			mContentConditionStrategy = inContentConditionStrategy;
		}
	
}
