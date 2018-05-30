package com.nazdaq.ejms.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class TestMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, -7);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date remDate = cal.getTime();
		System.out.println(df.format(remDate));*/
//		Set<String> zones = ZoneId.getAvailableZoneIds();
//		System.out.println("Default Zone:"+ZoneId.systemDefault());
//		System.out.println("Number of available time zones is: " + zones.size());
//		zones.forEach(System.out::println);
		
        ZoneId singaporeZone = ZoneId.of("Europe/London");
        ZonedDateTime dateTimeInSingapore = ZonedDateTime.of(
        LocalDateTime.of(2016, Month.JANUARY, 1, 6, 0), singaporeZone);
        ZoneId aucklandZone = ZoneId.of("Asia/Dhaka");
        ZonedDateTime sameDateTimeInAuckland =
                    dateTimeInSingapore.withZoneSameInstant(aucklandZone);
        Duration timeDifference = Duration.between(
                                dateTimeInSingapore.toLocalTime(),
                                sameDateTimeInAuckland.toLocalTime());
        System.out.printf("Time difference between %s and %s zones is %d hours",
                    singaporeZone, aucklandZone, timeDifference.toHours());
		
	}

}
