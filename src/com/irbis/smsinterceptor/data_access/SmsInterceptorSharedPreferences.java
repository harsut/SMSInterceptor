package com.irbis.smsinterceptor.data_access;

import java.util.LinkedList;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SmsInterceptorSharedPreferences
{	
	//============================================================
	// Constants
	// ===========================================================
		private final static String SHARED_PREFERENCES = "SMS_Monitor";
		private final static String COUNT = "Count";
	
	// ===========================================================
	// Fields
	// ===========================================================		
		private Context mContext = null;
		private LinkedList<ISharedPrefsListener> mSharedPrefsListeners = new  LinkedList<ISharedPrefsListener>();
		
		private static SmsInterceptorSharedPreferences mInstance;
	// ===========================================================
	// Static Methods
	// ===========================================================
		static synchronized public SmsInterceptorSharedPreferences getInstance(Context inContext)
		{
			if(null == mInstance)
			{
				mInstance = new SmsInterceptorSharedPreferences(inContext);
			}
			return mInstance;
		}
	// ===========================================================
	// Constructors
	// ===========================================================
		/*private SmsInterceptorSharedPreferences()
		{
				    	
		}*/
		private SmsInterceptorSharedPreferences(Context inContext)
		{
			mContext = inContext;	
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		synchronized public void setCount(int inCount)
		{
			Editor editor = getWriteableSharedPreferences().edit(); 
			editor.putInt(COUNT, inCount);			
			editor.commit();
			fireCountChanged(inCount);
		}
		synchronized public int getCount()
		{
			return getReadableSharedPreferences().getInt(COUNT, 0);
		}
		synchronized public void incCount(int inValue)
		{
			int value = getReadableSharedPreferences().getInt(COUNT, 0);
			value += inValue;
			Editor editor = getWriteableSharedPreferences().edit(); 
			editor.putInt(COUNT, value);			
			editor.commit();
			fireCountChanged(value);
		}
		
		private SharedPreferences getReadableSharedPreferences()
		{
			return  mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_WORLD_READABLE);
			
		}
		private SharedPreferences getWriteableSharedPreferences()
		{
			return  mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_WORLD_WRITEABLE);
			
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

		
	// ===========================================================
	// Methods
	// ===========================================================
		public void registerListener(ISharedPrefsListener inSharedPrefsListener)
		{	
			if(null == inSharedPrefsListener)
			{
				throw new IllegalArgumentException("inSharedPrefsListener must be not null");
			}			
			synchronized(mSharedPrefsListeners)
			{
				mSharedPrefsListeners.add(inSharedPrefsListener);	
			}
		}
		public boolean removeListener(ISharedPrefsListener inSharedPrefsListener)
		{
			//null check isn't necessary. mSharedPrefsListeners.remove(null) works correctly
			boolean result = false;
			synchronized(mSharedPrefsListeners)
			{
				result = mSharedPrefsListeners.remove(inSharedPrefsListener);
			}
			return result; 
		}
		private void fireCountChanged(int inCount)
		{
			synchronized(mSharedPrefsListeners)
			{
				for(ISharedPrefsListener sharedPrefsListener : mSharedPrefsListeners)
				{
					sharedPrefsListener.onCountChanged(inCount);
				}
			}
		}
		
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
		public interface ISharedPrefsListener
		{
			void onCountChanged(int inNewValue);
		}
}
