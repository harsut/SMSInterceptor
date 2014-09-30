package com.irbis.smsinterceptor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.detailed.SimpleRuleView;

public class RuleFragment extends Fragment implements INamedFragment
{

	//============================================================
	// Constants
	// ===========================================================

	public static final String TAG = RuleFragment.class.getName();
		// ===========================================================
	// Fields
	// ===========================================================
		private String mName;
		private Rule mRule;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
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
			return SimpleRuleView.makeView(getActivity(),mRule);
		}
		
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
	// ===========================================================
	// Methods
	// ===========================================================
		public void setRule(Rule inRule)
		{
			mRule = inRule;
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
