package com.xc.microservice.validate.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DateUtil
{

  public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static String DATETIMEMS_FORMAT = "yyyyMMddHHmmssS";

  private static ReentrantLock lock = new ReentrantLock();

  
 
  public static String formatDate(Date d)
  {
    return new SimpleDateFormat(DATETIME_FORMAT).format(d);
  }

  public static String getGenerateCode(Date d)
  {
	lock.lock();
    String generateCode = new SimpleDateFormat(DATETIMEMS_FORMAT).format(d);
    lock.unlock();
    return generateCode;
  }

  public static Date parseDate(String d)
  {
    try
    {
      return new SimpleDateFormat(DATETIME_FORMAT).parse(d);
    } catch (Exception e) {
    }
    return null;
  }

  public static String formatSub(long parame){
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String date = sdf.format(new Date(parame*1000L));
	  return date;
  }

}