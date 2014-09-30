package com.irbis.smsinterceptor.data_access;

import java.sql.SQLException;
import java.util.List;

import com.irbis.smsinterceptor.data.SmsMessage;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class SmsMessageDAO extends BaseDaoImpl<SmsMessage, Integer>
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
	protected SmsMessageDAO(final ConnectionSource inConnectionSource, final Class<SmsMessage> inDataClass) throws SQLException
	{
		super(inConnectionSource, inDataClass);
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
	public List<SmsMessage> getAll() throws SQLException
	{
		return queryForAll();
	}

	public List<SmsMessage> getBySender(final String inSender) throws SQLException
	{
		/*QueryBuilder<SmsMessage, Integer> queryBuilder = queryBuilder();
		queryBuilder.where().eq(SmsMessage.SMS_SENDER, inSender);
		return query(queryBuilder.prepare());*/
		return query(queryBuilder().where().eq(SmsMessage.SMS_SENDER,
		                                       inSender).prepare());
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
