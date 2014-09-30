package com.irbis.smsinterceptor;

import java.util.LinkedList;

import net.jcip.annotations.ThreadSafe;
import android.app.Activity;

@ThreadSafe
public class Application
{
	private static Application	       mInstance;
	private final LinkedList<Activity>	Activities	= new LinkedList<Activity>();
	private boolean	                   mFinished	= false;

	public static synchronized Application getInstance()
	{
		if (null == mInstance)
		{
			mInstance = new Application();
		}
		return mInstance;
	}

	private Application()
	{
	}

	public void finishWithReport(final Exception inException)
	{
		// TODO inform user and ask him to send bug repor(and handle sending it)	
		finish();
		throw new IllegalStateException("finishWithReport" + inException.getMessage());
	}

	public void finish()
	{
		synchronized (Activities)
		{
			for (final Activity activity : Activities)
			{
				activity.finish();
			}
			mFinished = true;
		}
	}

	public void attach(final Activity inActivity)
	{
		synchronized (Activities)
		{
			if (mFinished)
			{
				inActivity.finish();
			}
			else
			{
				Activities.add(inActivity);
			}
		}
	}

	public void detach(final Activity inActivity)
	{
		synchronized (Activities)
		{
			Activities.remove(inActivity);
		}
	}
}
