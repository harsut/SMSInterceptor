package com.irbis.smsinterceptor.data.condition;

import com.irbis.smsinterceptor.data.SmsMessage;


public final class AndConditionStrategy extends CompositeConditionStrategy
{
	private static final long serialVersionUID = -7212722549459302902L;
	
	// ===========================================================
	// Constructors
	// ===========================================================
		public AndConditionStrategy(ConditionStrategy ...inConditionStrategies)
		{
			super(inConditionStrategies);
		}
		/*public AndConditionStrategy()
		{			
		}*/
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public boolean matches(SmsMessage inSmsMessage)
		{
			boolean matches = true;
			for(ConditionStrategy conditionStrategy : getConditionStrategies())
			{
				matches &= conditionStrategy.matches(inSmsMessage);
			}
			return matches;
		}
	// ===========================================================
	// Methods
	// ===========================================================
}
