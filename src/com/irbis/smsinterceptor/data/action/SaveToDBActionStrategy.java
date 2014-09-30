package com.irbis.smsinterceptor.data.action;

import android.content.Context;

import com.irbis.smsinterceptor.Application;
import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.reports.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class SaveToDBActionStrategy extends ActionStrategy
{
		private static final long serialVersionUID = 7091201066015224974L;
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public boolean performAction(Context inContext, SmsMessage inSmsMessage)
		{
			Log.debug(this, "Sms saved");			
			try
			{
				Model.getInstance(inContext).saveSmsMessage(inSmsMessage);
				
			} 
			catch (DataAccessException e)
			{
				Application.getInstance().finishWithReport(e);
			}
			finally
			{
				OpenHelperManager.releaseHelper();
			}
			return true;
		}
		@Override
		public String toString()
		{
			return "SaveToDBActionStrategy";
		}
}
