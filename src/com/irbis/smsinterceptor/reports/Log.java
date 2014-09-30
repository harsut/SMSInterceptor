package com.irbis.smsinterceptor.reports;

public class Log
{
	public static void  error(Object inSource, String inMessage)
	{
		error(inSource.getClass().getSimpleName(),inMessage);
	}
	public static void info(Object inSource, String inMessage)
	{
		info(inSource.getClass().getSimpleName(),inMessage);
	}
	public static void debug(Object inSource, String inMessage)
	{
		debug(inSource.getClass().getSimpleName(),inMessage);
	}
	
	public static void  error(String inSource, String inMessage)
	{
		android.util.Log.e(inSource,getMessage(inMessage));		
	}
	public static void info(String inSource, String inMessage)
	{
		android.util.Log.i(inSource,getMessage(inMessage));
	}
	public static void debug(String inSource, String inMessage)
	{
		android.util.Log.d(inSource,getMessage(inMessage));
	}
	private static String getMessage(String inMessage)
	{
		return (null == inMessage)? "" : inMessage;
	}
}
