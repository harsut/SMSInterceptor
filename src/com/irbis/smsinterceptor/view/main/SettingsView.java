package com.irbis.smsinterceptor.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.model.Model;

public class SettingsView extends LinearLayout
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
	public SettingsView(Context inContext) 
	{
		super(inContext);
		init();
	}
		
	private void init()
	{
		Context context = getContext(); 
		LayoutInflater.from(context).inflate(R.layout.settings, this);
		Button setContentButton = (Button) findViewById(R.id.set_db_content_button);		
        setContentButton.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					Model.getInstance(getContext()).testFillDB();
				}
			});
        Button getContentButton = (Button) findViewById(R.id.get_db_content_button);
        getContentButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				Model.getInstance(getContext()).testReadDB(); 
				
			}
		});
        //final EditText counterText = (EditText)findViewById(R.id.counter_edit_text);        
        
        //NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        //mNotificationManager.
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
