package org.ltsh.common.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 * @author fengjianzhong
 *
 */
public class Dates {
	
	//日期格式
	public static final String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD="yyyy-MM-dd";
	public static final String HH_MM_SS="HH:mm:ss";
	public static final String HH_MM="HH:mm";
	public static final String HHMMSS="HHmmss";
	public static final String YYYYMMDDHHMISS="yyyyMMddHHmmss";
	public static final String YYYYMMDD="yyyyMMdd";
	/** 锁对象 */
    private static final Object lockObj = new Object();
    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * SimpleDateFormat有线程安全问题
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }
    /**
     * 默认是yyyy-MM-dd HH:mm:ss
     */
    public static String toStr(Date date){
    	 return getSdf(YYYY_MM_DD_HH_MM_SS).format(date);
    }
    /**
     * yyyy-MM-dd
     */
    public static String toStrYYYYMMDD(Date date){
   	 return getSdf(YYYY_MM_DD).format(date);
   }
    /**
     * HH:mm:ss
     */
    public static String toStrHHmmss(Date date){
   	 return getSdf(HH_MM_SS).format(date);
    }
    /**
     * 默认是yyyy-MM-dd HH:mm:ss
     */
    public static Date toDate(String dateStr){
    	 return toDate(dateStr,YYYY_MM_DD_HH_MM_SS);
    }
    /**
     * yyyy-MM-dd
     */
    public static Date toDateYYYYMMDD(String dateStr){
   	 return toDate(dateStr,YYYY_MM_DD);
    }
    /**
     * HH:mm:ss
     */
    public static Date toDateHHmmss(String dateStr){
    	return toDate(dateStr,HH_MM_SS);
    }
    public static String now(){
    	return getSdf(YYYY_MM_DD_HH_MM_SS).format(new Date());
    }
    public static String nowDate(){
    	return getSdf(YYYY_MM_DD).format(new Date());
    }
    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     */
    public static String toStr(Date date, String fmat) {
        return getSdf(fmat).format(date);
    }
    public static Date toDate(String dateStr, String fmat){
        try {
			return getSdf(fmat).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null;
    }

	static int[] weeks={-1,7,1,2,3,4,5,6};
	static String template="第%s周%s 至  %s";
	public static String nextWeekStr(String date,int weekNex){
		DateTime dateTime=new DateTime(toDate(date));
		dateTime=dateTime.plusWeeks(weekNex);
		return dateTime.toString(YYYY_MM_DD);
	}
	/**
	 * 取得这周和下几周的日期
	 * @param date
	 * @param weekNext
	 * @return
	 */
	public static List<Item<String, String>> getWeekNext(Date date,int weekNext){
		DateTime dateTime=new DateTime(date);
		int yearOfWeek=dateTime.getWeekOfWeekyear();
		dateTime=dateTime.minusDays(dateTime.getDayOfWeek()-1);
		String startDate="";
		String endDate="";
		List<Item<String, String>> list=new ArrayList<Item<String, String>>();
		for (int i = 0; i < weekNext; i++) {
			startDate=dateTime.toString(YYYY_MM_DD);
			endDate=dateTime.plusDays(6).toString(YYYY_MM_DD);
			list.add(new Item<String, String>(startDate+","+endDate,String.format(template,yearOfWeek+i,startDate,endDate)));
			dateTime=dateTime.plusWeeks(1);
		}
		return list;
	}
	/**
	 * 取得时间的周的第一天
	 * @return
	 */
	public static Date getWeekFirstDay(Date date){
		DateTime dateTime=new DateTime(date);
		dateTime=dateTime.minusDays(dateTime.getDayOfWeek()-1);
		return dateTime.toDate();
	}
	/**
	 * 取得时间的周的最后一天
	 * @return
	 */
	public static Date getWeekLastDay(Date date){
		DateTime dateTime=new DateTime(date);
		dateTime=dateTime.plusDays(7-dateTime.getDayOfWeek());
		return dateTime.toDate();
	}
	public static int getYearByWeek(String dateStr){//计算当前时间是第几周
		DateTime dateTime=new DateTime(toDate(dateStr));
		return dateTime.getWeekOfWeekyear();
	}
	/**
	 * 一年的第几周，周一为起点，而不是周日
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getYearByWeek(Date date){//计算当前时间是第几周
		DateTime dateTime=new DateTime(date);
		return dateTime.getWeekOfWeekyear();
		
//		//一年中的第几周，按星期一是第一天开始算
//		//把一年有多少个周算出来
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(parse(format(date,"yyyy"),"yyyy"));
//        Calendar calendarNow = new GregorianCalendar();//现在的时间
//        calendarNow.setTime(date);
//        int count=0;
//        Calendar next= new GregorianCalendar();//下一年
//        next.add(Calendar.YEAR, 1);
//        next.setTime(date);
//        int seven=7;
//        int weekday=0;
//        while (calendar.compareTo(next)==-1) {
//        	weekday= weeks[calendar.get(Calendar.DAY_OF_WEEK)];//星期天为第一天转星期天为最后一天
//        	//如果不是从一月一日算第一周 算法
//        	calendar.add(Calendar.DATE, -(weekday-1));//周的第一天
//        	if(calendarNow.compareTo(calendar)==-1){
//        		return count;
//        	}
//    		calendar.add(Calendar.DATE, seven);// 下一周
//    		++count;
//		}
//        return count;
	}
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	 /** 
     * 获取当年的第一天
     * @return 
     */  
    public static Date getCurrYearFirst(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    }
	/***
	 * 	public static Date[] getWeekDay(Date date)
	 * 获取对应Date值的整星期的时间（Date Arrays）以星期一为第一天
	 * @param date 日期
	 * @return Date[] 返回一星期的日期(下标:Mon=0,Tue=1...Sun=6 Length==7)
	 *
	 */
	public static Date[] getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    	int weekday= weeks[calendar.get(Calendar.DAY_OF_WEEK)];//星期天为第一天转星期天为最后一天
    	//如果不是从一月一日算第一周 算法
    	calendar.add(Calendar.DATE, -(weekday-1));//周的第一天
        Date[] dates = new Date[7];
        for (int i = 0; i < 7; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
	/***
	 * 	public static Date[] getWeekDay(Date date)
	 * 获取对应Date值的整星期的时间（Date Arrays）以星期一为第一天
	 * @param date 日期
	 * @return Date[] 返回一星期的日期(下标:Mon=0,Tue=1...Sun=6 Length==7)
	 *
	 */
	public static String[] getWeekDayStr(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    	int weekday= weeks[calendar.get(Calendar.DAY_OF_WEEK)];//星期天为第一天转星期天为最后一天
    	//如果不是从一月一日算第一周 算法
    	calendar.add(Calendar.DATE, -(weekday-1));//周的第一天
        String[] dates = new String[7];
        for (int i = 0; i < 7; i++) {
            dates[i] =toStrYYYYMMDD(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
	
	/**
	 * 时间差值，以时间为分割单位 年月日时分秒
	 * 
	 * @param beforeDate 开始时间
	 * @param afterDate 结束时间
	 * @param simpleDateFormat
	 *            输出String类型时间格式
	 * @param field
	 *            =Calendar.YEAR年Calendar.MONTH月Calendar.DATE日Calendar.HOUR时分秒
	 * @param intervel
	 *            =间隔的差值 如（Calendar.YEAR intervel=1 则差值为一年2为两年）；
	 * @return Date d1=dateFormat("1999-12-30","yyyy-MM-dd"); Date
	 *         d2=dateFormat("2009-12-30","yyyy-MM-dd");
	 *         dateT(d1,d2,yyyyMM,Calendar
	 *         .YEAR,2)={199912,200112,200312,200512,200712,200912};
	 *         dateT(d1,d2,
	 *         yyyyMMdd,Calendar.YEAR,4)={1999-12-30,2003-12-30,2007-12-30};
	 *         dateT
	 *         (d1,d2,yyyyMMdd,Calendar.MOTHE,20)={1999-12-30,2001-08-30,2003
	 *         -04-30,2004-12-30,2006-08-30,2008-04-30,2009-12-30};
	 */
	public static String[] dateT(Date beforeDate, Date afterDate,
			SimpleDateFormat simpleDateFormat, int field, int intervel) {
		List<String> list = new ArrayList<String>();
		Calendar beforeCalendar = getCalendar(beforeDate);
		Calendar afterCalendar = getCalendar(afterDate);
		while (beforeCalendar.compareTo(afterCalendar) <= 0) {
			list.add(simpleDateFormat.format(beforeCalendar.getTime()));
			beforeCalendar.add(field, intervel);
		}
		return list.toArray(new String[0]);
	}
	/**
	 * 根据Date返回Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		Calendar c =Calendar.getInstance();
		c.setTime(date);
		return c;
	}
}
