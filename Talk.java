import java.io.IOException;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

/** 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description This is the Talk class for my scheduling
 * code. The talk class represents the Talk object which contains
 * the speaker name, startTime, and stopTime. It is used in
 * conjunction with the Scheduler class in order to schedule
 * the talks.
 *
 */

public class Talk implements Comparable<Talk>
{

	//private instance variables: speaker name, 
	//start & end time (string & int)

	private String speaker;

	private String startTime, stopTime;

	private int startValue, stopValue;

	/**
	 * Constructor for Talk Object
	 * @param  s: speaker name, start: startTime, stop: stopTime
	 */
	public Talk(String s, String start, String stop) throws IOException
	{
		setSpeaker(s);

		setStartTime(start);

		setStopTime(stop);

		setStartValue(start);

		setStopValue(stop);
	}

	/**
	 * getter method for speaker name
	 * @return  speaker: speaker name
	 */
	public String getSpeaker()
	{
		return speaker;
	}

	/**
	 * sets name of speaker
	 * @param  h: copy of hand that player has
	 * @return  payout: int # of the value of hand
	 */	private void setSpeaker(String sp)
	 {
		 this.speaker = sp;
	 }

	 /**
	  * method that converts string to values
	  * @exception catches numberFormatException 
	  * if the String format is incorrect
	  * @return value: String converted to integer
	  */
	 public int getValue(String t) throws IOException
	 {
		 t.trim();
		 int value = 0;
		 try {
			 value = Integer.parseInt(t);
		 }
		 catch(NumberFormatException e){
			 System.out.println("The format of"
					 + " the hours of the speaker are not numbers. "
					 + "Please enter numbers.");;
					 throw new IOException();
		 }
		 return value;
	 }

	 /**
	  * getter method for start time
	  * @return startValue: integer value of start time
	  */
	 public int getStartValue()
	 {
		 return startValue;
	 }

	 /**
	  * sets starting time int value 
	  * @exception throws IOException if the
	  *  time format is incorrect
	  */
	 private void setStartValue(String start) throws IOException
	 {
		 int startVal = getValue(start);
		 if (startVal > 2400 || startVal < 0)
		 {
			 System.out.println("Please enter a reasonable time. "
					 + "The time you entered either exceeds 2400 "
					 + "or is less than 0000.");
			 throw new IOException();

		 }
		 startValue = startVal;
	 }

	 /**
	  * getter method for stop time
	  * @return startValue: integer value of stop time
	  */
	 public int getStopValue()
	 {
		 return stopValue;
	 }

	 /**
	  * sets stopping time int value 
	  * @exception throws IOException if the
	  *  time format is incorrect
	  */
	 private void setStopValue(String stop) throws IOException
	 {
		 int stopVal = getValue(stop);
		 if (stopVal > 2400 || stopVal < 0)
		 {
			 throw new IOException("Please enter a reasonable time. "
					 + "The time you entered either exceeds "
					 + "2400 or is less than 0000.");

		 }
		 stopValue = getValue(stop);
	 }

	 /**
	  * getter method for start time
	  * @return startTime: string of start time
	  */
	 public String getStartTime()
	 {
		 return startTime;
	 }

	 /**
	  * sets the start time
	  */
	 private void setStartTime(String start)
	 {
		 this.startTime = start;
	 }

	 /**
	  * getter method for stop time
	  * @return stopTime: string of stop time
	  */
	 public String getStopTime()
	 {
		 return stopTime;
	 }

	 /**
	  * sets the stop time
	  */
	 private void setStopTime(String stop)
	 {
		 this.stopTime = stop;
	 }

	 public String toString()
	 {
		 String result = "";

		 result += getSpeaker() + "\t" +
				 startTime.substring(0, 2) + ":" 
				 + startTime.substring(2) + 
				 " - " + stopTime.substring(0, 2) +
				 ":" + stopTime.substring(2);

		 return result;
	 }

	 /**
	  * Override comparable so we can use Collections.sort()
	  * Compares talks and ranks based on which one ends first
	  * The one that ends first is "smaller" than one that ends later
	  * 
	  * @return int of -1, 0, 1 for less than, equal, or greater than
	  */
	 @Override
	 public int compareTo(Talk anotherTalk)
	 {
		 if (Integer.parseInt(this.getStopTime()) ==
				 Integer.parseInt(anotherTalk.getStopTime()))
		 {
			 if (Integer.parseInt(this.getStartTime()) >
			 Integer.parseInt(anotherTalk.getStartTime()))
			 {
				 return 1;
			 }
			 else if (Integer.parseInt(this.getStartTime()) <
					 Integer.parseInt(anotherTalk.getStartTime()))
			 {
				 return -1;
			 }
			 else
			 {
				 return 0;
			 }
		 }
		 else if (Integer.parseInt(this.getStopTime()) >
		 Integer.parseInt(anotherTalk.getStopTime()))
		 {
			 return 1;
		 }
		 else
		 {
			 return -1;
		 }
	 }
}