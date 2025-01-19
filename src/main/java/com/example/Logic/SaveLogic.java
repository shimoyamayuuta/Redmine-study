package com.example.Logic;

import java.sql.SQLException;

import com.example.Dao.SaveDao;

public class SaveLogic {
	public void saveSettedGoal(String settedGoal) throws SQLException, ClassNotFoundException {
		//TODO DBにINSERTする
		SaveDao save = new SaveDao();
		save.insertGoal(settedGoal);
	}
}
