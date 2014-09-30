package com.irbis.smsinterceptor.data;

import java.util.Collection;
import java.util.LinkedList;

import android.content.Context;

import com.irbis.smsinterceptor.data.action.Action;
import com.irbis.smsinterceptor.data.condition.Condition;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Rules")
final public class Rule
{
	//============================================================
	// Constants
	// ===========================================================
	public static final String	     RULE_ID	       = "ID";
	public static final String	     RULE_CONDITION	   = "RuleCondition";
	public static final String	     RULE_ACTIVE	   = "RuleActive";
	public static final String	     RULE_NAME	       = "RuleName";
	public static final String	     RULE_CATCHEDCOUNT	= "RuleCatchedCount";

	// ===========================================================
	// Fields
	// ===========================================================
	@DatabaseField(generatedId = true, dataType = DataType.LONG, columnName = RULE_ID)
	private long	                 mID;
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, columnName = RULE_CONDITION)
	private Condition	             mCondition;
	@DatabaseField(canBeNull = false, dataType = DataType.BOOLEAN, columnName = RULE_ACTIVE)
	private boolean	                 mActive	       = false;
	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = RULE_NAME)
	private String	                 mName	           = "";
	@DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = RULE_CATCHEDCOUNT)
	private int	                     mCatchedCount	   = 0;

	@ForeignCollectionField(eager = true)
	private final Collection<Action>	mActions	   = new LinkedList<Action>();

	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public Rule()
	{

	}

	public Rule(final Condition inCondition, final Collection<Action> inActions)
	{
		mCondition = inCondition;
		mActions.addAll(inActions);
	}

	public Rule(final Condition inCondition, final Action inAction)
	{
		mCondition = inCondition;
		addAction(inAction);
	}

	public Rule(final Condition inCondition, final Action... inActions)
	{
		mCondition = inCondition;
		for (final Action action : inActions)
		{
			addAction(action);
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================		
	public long getID()
	{
		return mID;
	}

	public void setID(final long inID)
	{
		this.mID = inID;
	}

	public Condition getCondition()
	{
		return mCondition;
	}

	public void setCondition(final Condition inCondition)
	{
		this.mCondition = inCondition;
	}

	public void addAction(final Action inAction)
	{
		inAction.setRule(this);
		mActions.add(inAction);
	}

	public void addActions(final Collection<Action> actions)
	{
		for (final Action action : actions)
		{
			addAction(action);
		}
	}

	public void setActive(final boolean inActive)
	{
		mActive = inActive;
	}

	public boolean isActive()
	{
		return mActive;
	}

	/**
	 * warning returns link to field
	 * */
	public Collection<Action> getActions()
	{
		return mActions;
	}

	public String getName()
	{
		return mName;
	}

	public void setName(final String inName)
	{
		this.mName = inName;
	}

	public int getCatchedCount()
	{
		return mCatchedCount;
	}

	public void incCatchedCount(final int inValue)
	{
		mCatchedCount += inValue;
	}

	public void incCatchedCount()
	{
		++mCatchedCount;
	}

	public void setCatchedCount(final int inCatchedCount)
	{
		this.mCatchedCount = inCatchedCount;
	}

	public void clearActions()
	{
		mActions.clear();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public String toString()
	{
		return "Rule [mID=" + mID + ", mCondition=" + mCondition + ", mActive=" + mActive + ", mActions=" + mActions
		        + "]";
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public boolean applyRule(final Context inContext, final SmsMessage inExtendedSmsMessage)
	{
		boolean result = mCondition.matches(inExtendedSmsMessage);
		if (result)
		{
			for (final Action action : mActions)
			{
				result &= action.perform(inContext,
				                         inExtendedSmsMessage);
			}
		}
		return result;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
