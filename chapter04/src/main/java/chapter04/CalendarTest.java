package chapter04;

import java.util.Calendar;

// 상속 금지
//public final class ClendarTest{
public class CalendarTest {

//  오버라이딩 금지
//	public final void m() {}
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		printDate(cal);
		
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, 11); // 12월, (month - 1)
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(1998, 9, 25);
		cal.add(Calendar.DATE, 10000);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); // 1(일) ~ 7(토)
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		System.out.println(
				year + "/" + 
				month + "/" +
				date + " " + 
				DAYS[day -1] + "요일 " +
				hour + ":" +
				minute + ":" + 
				second);
	}

}
