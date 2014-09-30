package com.irbis.smsinterceptor;

import java.util.LinkedList;

import net.jcip.annotations.ThreadSafe;

import android.app.Activity;

@ThreadSafe
public class Application
{
	private static Application mInstance;
	private LinkedList<Activity> Activities = new LinkedList<Activity>();
	private boolean mFinished = false; 
	public static synchronized Application getInstance()
	{
		if(null == mInstance)
		{
			mInstance = new Application();
		}
		return mInstance;
	}
	private Application() {}
	
	
	public void finishWithReport(Exception inException)
	{
		// TODO inform user and ask him to send bug repor(and probably handle sending it)	
		finish();	
		throw new IllegalStateException("finishWithReport" + inException.getMessage());
	}
	public void finish()
	{
		synchronized(Activities)
		{
			for(Activity activity : Activities)
			{
				activity.finish();
			}
			mFinished = true;
		}
	}
	public void attach(Activity inActivity)
	{
		synchronized(Activities)
		{
			if(mFinished)
			{
				inActivity.finish();
			}
			else
			{
				Activities.add(inActivity);
			}
		}
	}
	public void detach(Activity inActivity)
	{
		synchronized(Activities)
		{			
			Activities.remove(inActivity);
		}
	}
}
