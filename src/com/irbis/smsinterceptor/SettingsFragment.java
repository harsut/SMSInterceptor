package com.irbis.smsinterceptor;

import com.irbis.smsinterceptor.reports.Log;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.main.SettingsView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment implements INamedFragment
{	
	public static final String TAG = "SettingsFragment";
	private String mName;
	@Override
	public View onCreateView(LayoutInflater inInflater, ViewGroup inContainer, Bundle inSavedInstanceState) 
	{		
		SettingsView view = new SettingsView(inInflater.getContext());
		return view;
	}	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		Log.info("Fragments",this+"onCreate");		
	}
	
	@Override
	public void onDestroy() 
	{
	    super.onDestroy();
	    Log.info("Fragments",this+"onDestroy");
	}	
	@Override
	public String getName()
	{		
		return mName;
	}
	@Override
	public Fragment getFragment()
	{
		return this;
	}
	@Override
	public void setName(String inName)
	{
		mName = inName;
	}	
}
