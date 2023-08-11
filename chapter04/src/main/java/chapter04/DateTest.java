package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now);
		
		pritDate01(now);
		pritDate02(now);
	}

	private static void pritDate02(Date d) {
		// 년도(+1900)
		int year = d.getYear();
		
		// 월(0 ~ 11 m +1)
		int month = d.getMonth();
		
		// 일
		int date = d.getDate();
		
		// 시
		int hours = d.getHours();
		
		// 분
		int minutes = d.getMinutes();
		
		// 초
		int seconds = d.getSeconds();
		
		System.out.println(
				(year + 1900) + "/" + 
				(month + 1) + "/" +
				date + " " + 
				hours + ":" +
				minutes + ":" + 
				seconds);
	}

	private static void pritDate01(Date d) {
		// 2023/08/11 15:00:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = sdf.format(d);
		
		System.out.println(date);
	}

}
