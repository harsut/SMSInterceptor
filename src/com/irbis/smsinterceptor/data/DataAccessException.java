package com.irbis.smsinterceptor.data;

public class DataAccessException extends Exception
{
	private static final long	serialVersionUID	= -5444944712316392499L;

	public DataAccessException(final Exception inException)
	{
		super(inException);
	}

}
