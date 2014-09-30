package com.irbis.smsinterceptor.data.action;

import android.content.Context;

import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.reports.Log;

public class BlockActionStrategy extends ActionStrategy
{	
	private static final long serialVersionUID = -6166328568566258557L;

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public boolean performAction(Context inContext, SmsMessage inSmsMessage)
		{
			Log.debug(this, "Sms blocked");
			return false;
		}	
		@Override
		public String toString()
		{
			return "BlockActionStrategy";
		}
}
	
