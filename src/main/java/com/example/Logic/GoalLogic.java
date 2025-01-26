package com.example.Logic;

import java.sql.SQLException;

import com.example.Dao.GoalDao;

/**
 * 目標ロジック
 */
public class GoalLogic {
	/**
	 * 目標を設定する
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String selectGoalName() throws ClassNotFoundException, SQLException {
		GoalDao goalDao = new GoalDao();
		// 目標を取得する
		String goalName = goalDao.selectGoalName();
		return goalName;
	}
}
