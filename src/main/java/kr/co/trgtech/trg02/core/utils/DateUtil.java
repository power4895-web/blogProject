package kr.co.trgtech.trg02.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	
	/**
	 * 현재 년
	 * @return int
	 */
	public static int getYear()	 {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	 }
	
	/***
	 * 현재 월
	 * @return int
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH)+1;
	}
	
	/***
	 * 현재 일 <p>
	 * @return int
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * 현재 날짜
	 * @return String
	 */
	public static String getDate() {
		Date date = new Date();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 return df.format(date);
	}
	
	/**
	 * 현재 날짜 
	 * @param format - yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDate( String format){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return  sdf.format(date);
	}
	
	/**
	 * 날짜 변환 
	 * @param date
	 * @param format - yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return  sdf.format(date);
	}
	
	/**
	 * n일 전/후 날짜 변환
	 * @param ymd - 대상날짜(yyyy-MM-dd)
	 * @param n  - n일 전/후 날자
	 * @return String
	 * @throws ParseException
	 */
	public static String getDate (String ymd, int n, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();    
		cal.setTime(sdf.parse(ymd));
	    cal.add ( Calendar.DAY_OF_MONTH, n );
	    return getDate(cal.getTime(), format);
	}
	
	/***
	 * 현재 요일 <p>
	 * @return String
	 */
	public static String getWeek() {
		Calendar cal = Calendar.getInstance();
		String [] week = {"","일","월","화","수","목","금","토"};
		return week[cal.get(Calendar.DAY_OF_WEEK)]; 
	}	
	
	/***
	 * 현재달 주 (1주, 2주 ...)
	 * @return int
	 */
	public static int getCurrentWeek(){
		Calendar cal = Calendar.getInstance();
		int result = cal.get(Calendar.WEEK_OF_MONTH) ;
		return result;
	} 
	
	/***
	 * 해당년월의 마지막 날짜
	 * @param nYear : 년도
	 * @param nMonth : 월
	 * @return int
	 */
	public static int getLastDay(int nYear, int nMonth){
		GregorianCalendar cld = new GregorianCalendar (nYear, nMonth - 1, 1);
		int result = cld.getActualMaximum(Calendar.DAY_OF_MONTH);
		return result;
	}
	
	/***
	 * 해당년월의 첫번째 날짜의 요일(1:SUNDAY, 2:MONDAY...)
	 * @param nYear
	 * @param nMonth
	 * @return int
	 */
	public static int getFirstWeekday(int nYear, int nMonth){
		GregorianCalendar cld = new GregorianCalendar (nYear, nMonth - 1, 1);
		int result = cld.get(Calendar.DAY_OF_WEEK);
		return result;
	}
	
	/***
	 * 해당년월의 주의 개수
	 * @param nFristWeekday : 그 달의 첫째날의 요일
	 * @param nToDay : 그 달의 날짜 수
	 * @return int
	 */
	public static int getWeekCount(int nFristWeekday, int nToDay){
		int nCountDay = nFristWeekday + nToDay - 1;
		int result = (nCountDay / 7);
		if ((nCountDay % 7) > 0) {
			result++;
		}
		return result;
	}

	
	public static Date timestampToDate (long timestamp){
		Timestamp stamp = new Timestamp(timestamp);
		return  new Date(stamp.getTime());
	}
	
	/***
	 * 시작일과 종료일 사이 날짜 구하기
	 * @param startDt : 시작일
	 * @param endDt : 종료일
	 * @return List<String>
	 */
	public static List<Date> getPriodDate(Date startDt, Date endDt) {
		 
        Calendar cal = Calendar.getInstance();
        
        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
        cal.setTime(startDt);
         
        List<Date> list = new ArrayList<Date>();
        while(true) {

        	list.add(cal.getTime());
        	
            // Calendar의 날짜를 하루씩 증가한다.
            cal.add(Calendar.DATE, 1); // one day increment
             
            // 현재 날짜가 종료일자보다 크면 종료 
            if( getDateByInteger(cal.getTime()) > getDateByInteger(endDt) ) break;
        }
        return list;
    }
	
	public static int getDateByInteger(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(date));
    }

	public static String getDateByString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
