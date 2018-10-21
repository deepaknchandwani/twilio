package com.deepakchandwani.twilio;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
 

public   class JobScheduler extends TimerTask {
	
  public static void main(String... arguments) {
	  
	String to="+19173302704";
	String from="+19174634744";
	
    TimerTask job1 = new JobScheduler(); 
    
    TimerTask job2 = new JobScheduler(); 
    
    ((JobScheduler)job2).setIsLastJob(true);
    
    // perform the task once a day at 4 a.m., starting tomorrow morning
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(job1, runAJobAtAParticularTime(0, 19 ,47 , 10), frequecyPerDyInSeconds);
    //timer.scheduleAtFixedRate(fetchMail2, runAJobAtAParticularTime(0, 19 ,42 , 35), fONCE_PER_DAY);
 
  }

  
  private final static long frequecyPerDyInSeconds = 1000 * 60 * 60 * 24;

  private final static int atDay = 0;
  private final static int atHour = 18 ;
  private final static int atMinutes = 53;
  private final static int atSeconds = 0;
  
  private  boolean isLastJob;
  private  String to;
  private  String from;


  private static Date runAJobAtAParticularTime( int daysAfter , int hours , int minutes , int seconds ) {
    Calendar tomorrow = new GregorianCalendar();
    tomorrow.add(Calendar.DATE, daysAfter);
    Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
        tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE), hours,
        minutes ,seconds );
    return result.getTime();
  }
  
  public void setIsLastJob(boolean value) {
	  isLastJob=value;
  }
  @Override
  public void run() {
    System.out.println("Executing Job at " + new Date());
    try {
		VoiceCallFromGetResource.pushMessage(to, from);
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if (isLastJob)System.exit(0);
  }

public  boolean isLastJob() {
	return isLastJob;
}

public  void setLastJob(boolean isLastJob) {
	isLastJob = isLastJob;
}

public  String getTo() {
	return to;
}

public  void setTo(String to) {
	to = to;
}

public  String getFrom() {
	return from;
}

public  void setFrom(String from) {
	from = from;
}
  
  
 
}