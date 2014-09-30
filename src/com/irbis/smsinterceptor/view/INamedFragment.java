package com.irbis.smsinterceptor.view;

import android.support.v4.app.Fragment;

public interface INamedFragment
{
	String getName();
	void setName(String inName);
	Fragment getFragment();	
}
