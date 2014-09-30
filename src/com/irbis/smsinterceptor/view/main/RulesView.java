package com.irbis.smsinterceptor.view.main;

import com.irbis.smsinterceptor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class RulesView extends LinearLayout
{

	
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public RulesView(Context context)
	{
		super(context);
		initialize();
	}
	
	private void initialize()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.rules, this);
		
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onFinishInflate() 
	{
		super.onFinishInflate();
		findViewById(R.id.rules_label);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
