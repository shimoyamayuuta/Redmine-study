package com.example.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.Common.NowDateTime;
import com.example.Config.DbConnect;

public class SaveDao {
	// DbConnectクラスのインスタンスをフィールドとして保持
	public DbConnect db;

	// コンストラクタでDbConnectをインスタンス化
	public SaveDao() {
		db = new DbConnect();
	}
	
	/**
	 * 目標を作成する
	 * @param settedGoal
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insertGoal(String settedGoal) throws SQLException, ClassNotFoundException {
		Connection conn = db.getConnection();

		// 現在時刻の取得
		NowDateTime nowTime = new NowDateTime();
		String now = nowTime.nowDateTime();
		String sql = "INSERT INTO Goal (goal_name, create_date_time) VALUES (?, ?)"; // INSERT文
		PreparedStatement stmt = conn.prepareStatement(sql); // SQL文の準備
		stmt.setString(1, settedGoal);
		stmt.setString(2, now);
		stmt.executeUpdate();
	}
}