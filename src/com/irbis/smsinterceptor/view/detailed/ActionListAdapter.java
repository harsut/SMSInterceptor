package com.irbis.smsinterceptor.view.detailed;

import java.util.ArrayList;
import com.irbis.smsinterceptor.data.action.Action;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ActionListAdapter extends ArrayAdapter<Action>
{
	
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	ArrayList<Action> mActions;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ActionListAdapter(Context inContext, ArrayList<Action> inActions)
	{
		super(inContext, 0, inActions);
		mActions = inActions;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public Action getAction(int inPosition)
	{
		return mActions.get(inPosition);
	}
	public int getActionsCount()
	{
		return mActions.size(); 
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
   	public View getView(int position, View convertView, ViewGroup parent) 
    {
    	Action action = mActions.get(position);
    	ActionListItemView listItemView;
    	
    	if(null == convertView)
    	{
    		listItemView = ActionListItemView.makeView(getContext(), action);
    	}
    	else
    	{
    		listItemView  = (ActionListItemView)convertView;
    		listItemView.init(action);
    	}
   		return listItemView;
   	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
