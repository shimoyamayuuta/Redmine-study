package com.example.Resourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.example.Logic.SaveLogic;


@WebServlet("/save")
public class SaveResourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストエンコーディングをUTF-8に設定
		request.setCharacterEncoding("UTF-8");
		
		// リクエストからJSONデータを取得
		BufferedReader reader = request.getReader();
		String jsonLineData = reader.readLine();
		JSONObject jsonObj = new JSONObject(jsonLineData);
		
		//入力した目標の値を取得
		String settedGoal = jsonObj.getString("goalSetting");
		SaveLogic saveLogic = new SaveLogic();
		//目標を保存する
		try {
			saveLogic.saveSettedGoal(settedGoal);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 必要な処理をした後、レスポンスを返す
		response.setContentType("application/json");
		response.getWriter().write("{\"status\":\"success\"}");
	}
}
