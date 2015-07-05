package com.eritechno.repository.util;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class SISPreparedStatementCreatorFactory {

	public static PreparedStatementCreator createPreparedStatementCreator(
			final String sql, Object[] parameters) {
		return new SISPreparedStatementCreatorImpl(sql, parameters, false);
	}

	public static PreparedStatementCreator createPreparedStatementCreator(
			final String sql, Object[] parameters, boolean getGeneratedKeys) {
		return new SISPreparedStatementCreatorImpl(sql, parameters,
				getGeneratedKeys);
	}

}
