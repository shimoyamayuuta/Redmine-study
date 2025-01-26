package com.example.Resourse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Logic.GoalLogic;

/**
 * 目標を取得する
 */
@WebServlet("/goal")
public class GoalResource extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//目標を取得する
			GoalLogic goalLogic = new GoalLogic();
			String goalName = goalLogic.selectGoalName();
			// 必要な処理をした後、レスポンスを返す
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write("{\"goalName\":\"" + goalName + "\"}");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
