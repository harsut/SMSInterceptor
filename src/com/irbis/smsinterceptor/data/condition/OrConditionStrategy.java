package com.irbis.smsinterceptor.data.condition;

import com.irbis.smsinterceptor.data.SmsMessage;


public final class OrConditionStrategy extends CompositeConditionStrategy
{
	private static final long serialVersionUID = 2799893131259665307L;
	
	// ===========================================================
	// Constructors
	// ===========================================================
		public OrConditionStrategy(ConditionStrategy ...inConditionStrategies)
		{
			super(inConditionStrategies);
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public boolean matches(SmsMessage inSmsMessage)
		{			
			boolean matches = false;
			for(ConditionStrategy conditionStrategy : getConditionStrategies())
			{
				matches |=conditionStrategy.matches(inSmsMessage);
			}
			return matches;
		}
}
