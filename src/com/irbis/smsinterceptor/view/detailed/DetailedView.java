package com.irbis.smsinterceptor.view.detailed;

import com.irbis.smsinterceptor.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class DetailedView extends LinearLayout
{
	public static DetailedView makeView(Context inContext)
	{
		return (DetailedView) LayoutInflater.from(inContext).inflate(R.layout.detailed, null);
	}	
	public DetailedView(Context inContext, AttributeSet inAttributeSet)
	{
		super(inContext,inAttributeSet);
	}
	public void setFragment(FragmentManager inFragmentManager, Fragment inFragment,String inTAG)
	{
		FragmentTransaction fragmentTransaction = inFragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.detailedFragmentContainer, inFragment,inTAG);
		fragmentTransaction.commit();
	}
}
