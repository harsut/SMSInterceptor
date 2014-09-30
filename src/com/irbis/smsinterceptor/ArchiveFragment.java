package com.irbis.smsinterceptor;

import java.util.ArrayList;
import java.util.List;

import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.SmsHistory;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.reports.Log;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.main.ArchiveListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class ArchiveFragment extends ListFragment implements INamedFragment
{	
	//============================================================
	// Constants
	// ===========================================================
	public static final String TAG = ArchiveFragment.class.getName();;
	// ===========================================================
	// Fields
	// ===========================================================
	private String mName;
	private ArrayList<SmsHistory> mSmsHistories; 
	private IItemClickListener mItemClickListener;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public void setItemClickListener(IItemClickListener inItemClickListener)
	{
		mItemClickListener = inItemClickListener;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		Log.info("Fragments",this+"onCreate"); 
		fillContent();
	}
	@Override
	public void onListItemClick(ListView inListView, View inView, int inPosition, long inId) 
	{
	    super.onListItemClick(inListView, inView, inPosition, inId);	    
	    if(null != mItemClickListener)
	    {
	    	mItemClickListener.onListItemClick(mSmsHistories.get(inPosition));
	    }
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
	// ===========================================================
	// Methods
	// ===========================================================	
	private void fillContent()
	{ 
		try
		{
			List<SmsMessage> smsMessages = Model.getInstance(getActivity()).getSmsMessages();
			mSmsHistories = SmsHistory.parseSmsMessages(smsMessages);
			setListAdapter(new ArchiveListAdapter(getActivity(),mSmsHistories));
		} 
		catch (DataAccessException e)
		{
			Application.getInstance().finishWithReport(e);
		}		
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public interface IItemClickListener
	{
		void onListItemClick(SmsHistory inSmsHistory);
	}
	
}
