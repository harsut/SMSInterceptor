package com.irbis.smsinterceptor;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.SmsHistory;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.detailed.HistoryListAdapter;

public class HistoryFragment extends ListFragment implements INamedFragment
{
	// ===========================================================
	// Constants
	// ===========================================================
	public static final String TAG = HistoryFragment.class.getName();
	// ===========================================================
	// Fields
	// ===========================================================	
	private String mName;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	
	@Override
	public View onCreateView(LayoutInflater inInflater, ViewGroup inContainer, Bundle inSavedInstanceState) 
	{
		 View view = inInflater.inflate(R.layout.history, null);		 		 
		 return view;
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public void  setSmsHistory(SmsHistory inSmsHistory)
	{
		final ArrayList<SmsMessage> smsMessages = inSmsHistory.getMessages();
		setListAdapter(new HistoryListAdapter(getActivitySecured(),smsMessages));
	}
	public void  setSmsHistory(String inSender)
	{		
		setSmsHistory(getActivitySecured(),inSender);
	}
	public void  setSmsHistory(Context inContext,SmsHistory inSmsHistory)
	{
		final ArrayList<SmsMessage> smsMessages = inSmsHistory.getMessages();
		setListAdapter(new HistoryListAdapter(inContext,smsMessages));
	}
	public void  setSmsHistory(Context inContext, String inSender)
	{		
		Model model = Model.getInstance(inContext);
		try
		{
			SmsHistory smsHistory  = model.getSmsHistory(inSender);
			setSmsHistory(inContext,smsHistory);			
		} 
		catch (DataAccessException e)
		{
			Application.getInstance().finishWithReport(e);			
		}		
	}
	
	public FragmentActivity getActivitySecured()
	{
		FragmentActivity fragmentActivity = getActivity();
		if(null == fragmentActivity)
		{
			throw new IllegalStateException("Getting access to Activity from unattached Fragment");
		}
		return fragmentActivity;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================	
	@Override
	public String getName()
	{
		return mName;
	}
	@Override
	public void setName(String inName)
	{
		mName = inName;		
	}
	@Override
	public Fragment getFragment()
	{
		return this;
	}
}
