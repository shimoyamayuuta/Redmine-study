package com.example.Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NowDateTime {
	public String nowDateTime() {
		// 現在時刻の取得
		LocalDateTime nowTime = LocalDateTime.now();

		// 現在時刻の表記指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS");

		// 表記を指定したうえで、現在時刻の取得
		String formatNowTime = nowTime.format(formatter);

		return formatNowTime;
	}
}
