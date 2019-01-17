/** 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description This is the Scheduler class for my code.
 * This class serves as a repository for static methods 
 * used to schedule talks. Some of the methods included 
 * in this class are makeTalkList, sortTalksByStopTime,
 * getNoOverlap, and scheduleTalks.
 * 
 */

import java.io.*;
import java.util.*;
public class Scheduler
{

	/**
	 * static method that reads the file, breaks down
	 * its contents and makes a list
	 * 
	 * @exception throws FileNotFoundException if the
	 * name of the file cannot be found in the files
	 * 
	 * @exception throws IllegalArgumentException if 
	 * there are more than or less than 3 components in 
	 * one line which is incorrect because it's supposed 
	 * to be 3: speaker, startTime, stopTime
	 * 
	 * @exception throws NumberFormatException if there
	 * was a problem converting the number of the time
	 * which means that the format was not an integer
	 * 
	 * @exception throws IOException if input incorrect
	 * this error message comes from the Talk class about
	 * the time being either greater than 2400 or less than
	 * 0000
	 * 
	 * @return stopTime: string of stop time
	 * 
	 */
	public static ArrayList<Talk> makeTalks(String filename)
			throws IOException
	{
		//create arraylist of talks
		ArrayList<Talk> talkList = new ArrayList<Talk>();

		//create the file from filename
		File inFile = new File(filename); 

		//declare scanner
		Scanner inputFile;

		try
		{
			//initiliaze scanner
			inputFile = new Scanner(inFile);
		}

		//if the filename is wrong there is no file
		//catch exception
		catch(FileNotFoundException e)
		{
			System.out.println("File not found exception:"
					+ " The file that you entered does not exist. "
					+ "Please check the name of your file.");
			throw new IOException();
		}
		
		if(!inputFile.hasNext())
		{
			System.out.println("Your file is empty.");
			throw new IOException();
		}
		
		//while loop to read in every line of file
		while (inputFile.hasNextLine())
		{
			//separate line into words
			//split at space and enter into array
			String in[] = inputFile.nextLine().split("\\s+");

			if (in.length != 3)
			{
				//throw new IllegalArgumentException("The data is in"
				//		+ " the wrong format." 
				//		+ " Make sure to input data in the"
				//		+ " file as 3 elements in a row: "
				//		+ "speaker, startTime, stopTime.");

				System.out.println("The data is in wrong format."
						+ " Make sure to input data in file as 3"
						+ " elements in a row: speaker,"
						+ " startTime, stopTime.");
				throw new IOException();

			}
			else
			{
				//if everything works, create talk object 
				//with the respective parameters
				Talk talks = new Talk(in[0],in[1],in[2]);

				//add the talk object to the arraylist
				talkList.add(talks);
			}
		}
		// close file
		inputFile.close();


		//return the arraylist of talks
		return talkList;
	}

	/**
	 * static method that sorts the talks by stop time
	 * 
	 * @return sortedList: sortedList by stop time
	 * 
	 */
	public static ArrayList<Talk> sortTalksByStopTime
	(ArrayList<Talk> talks)
	{
		//create new arraylist
		ArrayList<Talk> sortedList = new ArrayList<Talk>();

		//for loop to go through all talk messages and add to arraylist
		for (int i = 0; i < talks.size(); i++)
		{
			sortedList.add(talks.get(i));
		}

		//sort using collections because we changed comparable
		Collections.sort(sortedList);

		//return sortedlist by stop time
		return sortedList;
	}

	/**
	 * static method that creates the speaker list
	 * by going through the sortByStopTimeList and 
	 * inserts talks from the top and makes sure
	 * all talks are compatible and don't overlap
	 * 
	 * @return list: list of talks sorted by 
	 * stop time first and are all compatible 
	 * with no overlaps
	 * 
	 */
	private static ArrayList<Talk> getNoOverlap(ArrayList<Talk> talks)
	{

		//create new arraylist
		ArrayList<Talk> list = new ArrayList<Talk>();

		//start by adding the earliest stop time to arraylist
		list.add(talks.get(0));

		//this index serves us the talk that is added to arraylist
		int index = 0;

		// loop through the sorted by stop time list
		for (int i = 1; i < talks.size(); i++)
		{
			//if the stop time is before or equal to next start time
			if (list.get(index).getStopValue() <= 
					talks.get(i).getStartValue())
			{
				//then add to list
				list.add(talks.get(i));

				// increment the index
				index++;
			}
		}

		//return the new "compatible" list
		return list;
	}

	/**
	 * the main static method that schedules
	 * and maximizes the number of talks
	 * 
	 * @return list: list of maximum
	 * scheduled talks
	 * 
	 */
	public static ArrayList<Talk> scheduleTalks(ArrayList<Talk>
			makeTalkList)
	{
		//create new arraylist for sorted by stop time talks
		ArrayList<Talk> stopTimeTalks = new ArrayList<Talk>();

		//set arraylist as the original list sorted by stop time
		stopTimeTalks = sortTalksByStopTime(makeTalkList);

		//create new arraylist for sorted by stop time no overlap
		ArrayList<Talk> sortedNoOverlapList = new ArrayList<Talk>();

		//call getNoOverlap method and use the sorted by stop time 
		//to get the final list
		sortedNoOverlapList = getNoOverlap(stopTimeTalks);

		//return the final maximized speaker list
		return sortedNoOverlapList;

	}
}