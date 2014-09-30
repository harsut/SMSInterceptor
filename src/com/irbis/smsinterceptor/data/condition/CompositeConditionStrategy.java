package com.irbis.smsinterceptor.data.condition;

import java.util.LinkedList;


public abstract class CompositeConditionStrategy extends ConditionStrategy
{	
	//============================================================
	// Constants
	// ===========================================================

		private static final long serialVersionUID = -8458825459022741308L;
	
	// ===========================================================
	// Fields
	// ===========================================================
		private LinkedList<ConditionStrategy> mConditionStrategies = new LinkedList<ConditionStrategy>();
	// ===========================================================
	// Static Methods
	// ===========================================================

	
	// ===========================================================
	// Constructors
	// ===========================================================
		CompositeConditionStrategy(ConditionStrategy ...inConditionStrategies)
		{
			for(ConditionStrategy conditionStrategy : inConditionStrategies)
			{
				mConditionStrategies.add(conditionStrategy);	
			}
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public LinkedList<ConditionStrategy> getConditionStrategies()
		{
			return mConditionStrategies;
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((mConditionStrategies == null) ? 0 : mConditionStrategies
							.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeConditionStrategy other = (CompositeConditionStrategy) obj;
			if (mConditionStrategies == null)
			{
				if (other.mConditionStrategies != null)
				{
					return false;
				}
			} 
			else if (!mConditionStrategies.equals(other.mConditionStrategies))
			{
				return false;
			}
			return true;
		}
	// ===========================================================
	// Methods
	// ===========================================================
		public void add(ConditionStrategy inConditionStrategy)
		{
			mConditionStrategies.add(inConditionStrategy);
		}
		public boolean remove(ConditionStrategy inConditionStrategy)
		{	
			return mConditionStrategies.remove(inConditionStrategy);
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
