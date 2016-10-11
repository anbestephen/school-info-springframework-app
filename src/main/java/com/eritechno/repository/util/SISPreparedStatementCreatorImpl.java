package com.eritechno.repository.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class SISPreparedStatementCreatorImpl implements
		PreparedStatementCreator {
	private final String sql;
	private final List<Object> parameters = new ArrayList<Object>();
	private final boolean generatedKeys;

	public SISPreparedStatementCreatorImpl(String sql, Object[] parameters,
			boolean generatedKeys) {
		this.sql = sql;
		for (Object parameter : parameters) {
			this.parameters.add(parameter);
		}
		this.generatedKeys = generatedKeys;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		if (this.generatedKeys) {
			preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
		} else {
			preparedStatement = connection.prepareStatement(sql);
		}
		int parameterIndex = 0;
		for (Object parameter : this.parameters) {
			preparedStatement.setObject(++parameterIndex, parameter);
		}
		return preparedStatement;
	}

}
