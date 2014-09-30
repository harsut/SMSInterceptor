package com.irbis.smsinterceptor.data.condition;

import com.irbis.smsinterceptor.data.SmsMessage;

public class SenderConditionStrategy extends ConditionStrategy
{
	//============================================================
	// Constants
	// ===========================================================
	private static final long serialVersionUID = 2741790155639009098L;
	// ===========================================================
	// Fields
	// ===========================================================
	private String mSender;  
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public SenderConditionStrategy(String inSender)
	{
		mSender = inSender;
		if(mSender.isEmpty())
			throw new IllegalStateException("inSender must be not null");
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
		return mSender.equals(inSmsMessage.getSender());
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public String getSender()
	{
		return mSender; 
	}

	public void setSender(String inSender)
	{
		mSender = inSender;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	

}
