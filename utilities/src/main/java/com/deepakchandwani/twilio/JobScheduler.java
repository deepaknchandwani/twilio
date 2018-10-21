package com.deepakchandwani.twilio;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
 

public   class JobScheduler extends TimerTask {
	
  public static void main(String... arguments) {
	  
	  
    TimerTask fetchMail = new JobScheduler(); 
    
    TimerTask fetchMail2 = new JobScheduler(); 
    
    ((JobScheduler)fetchMail2).setIsLastJob(true);
    
    // perform the task once a day at 4 a.m., starting tomorrow morning
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(fetchMail, runAJobAtAParticularTime(0, 19 ,47 , 10), fONCE_PER_DAY);
    //timer.scheduleAtFixedRate(fetchMail2, runAJobAtAParticularTime(0, 19 ,42 , 35), fONCE_PER_DAY);
 
  }
  
  private boolean isLastJob;
  
  public void setIsLastJob(boolean value) {
	  isLastJob=value;
  }
  @Override
  public void run() {
    System.out.println("doing");
    try {
		VoiceCallFromGetResource.pushMessage();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if (isLastJob)System.exit(0);
  }
  private final static long fONCE_PER_DAY = 1000 * 60 * 60 * 24;

  private final static int fONE_DAY = 0;
  private final static int fFOUR_AM = 18 ;
  private final static int fZERO_MINUTES = 53;

  private static Date runAJobAtAParticularTime( int daysAfter , int hours , int minutes , int seconds ) {
    Calendar tomorrow = new GregorianCalendar();
    tomorrow.add(Calendar.DATE, daysAfter);
    Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
        tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE), hours,
        minutes ,seconds );
    return result.getTime();
  }
}