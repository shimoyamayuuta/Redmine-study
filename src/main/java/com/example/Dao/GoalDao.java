package com.example.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.Config.DbConnect;

public class GoalDao {
	// DbConnectクラスのインスタンスをフィールドとして保持
	public DbConnect db;

	// コンストラクタでDbConnectをインスタンス化
	public GoalDao() {
		db = new DbConnect();
	}

	/**
	 * 目標をDBから取得する
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String selectGoalName() throws SQLException, ClassNotFoundException {
		Connection conn = db.getConnection();

		String sql = "SELECT goal_name FROM goal"; // INSERT文
		PreparedStatement stmt = conn.prepareStatement(sql); // SQL文の準備
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) { // next()で結果セットを1行進めて、データがある場合に処理を行う
			String goalName = rs.getString("goal_name");
			return goalName;
		} else {
			// データがない場合の処理
			return "";
		}
	}
}
