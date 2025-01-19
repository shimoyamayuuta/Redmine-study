package com.example.goalSetting.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveServlet {
	@WebServlet("/goal")
	public class GoalServlet extends HttpServlet {
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// CORSヘッダーを追加
			response.setHeader("Access-Control-Allow-Origin", "*"); // すべてのドメインを許可
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");

			// リクエストからJSONデータを取得
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			// 受け取ったJSONデータを処理
			String json = sb.toString();
			System.out.println("Received goal: " + json); // 受け取ったデータを確認

			// 必要な処理をした後、レスポンスを返す
			response.setContentType("application/json");
			response.getWriter().write("{\"status\":\"success\"}");
		}
	    @Override
	    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // プレフライトリクエストに対応
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setStatus(HttpServletResponse.SC_OK);  // 200 OK
	    }
	}
}
