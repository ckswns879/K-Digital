package com.rubypaper.domain.Log;

public interface LogDao {

	void addLog(String method, String sqlstring, boolean success);
}
