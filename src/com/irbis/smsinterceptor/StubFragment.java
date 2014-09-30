package com.irbis.smsinterceptor;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.view.INamedFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StubFragment extends Fragment implements INamedFragment
{
	public static final String TAG = StubFragment.class.getName();
	private String mName;
	@Override
	public View onCreateView(LayoutInflater inInflater, ViewGroup inContainer, Bundle inSavedInstanceState) 
	{
		 View view = inInflater.inflate(R.layout.fragment_stub, null);		 		 
		 return view;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
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
}; 