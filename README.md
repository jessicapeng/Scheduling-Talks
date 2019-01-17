# Scheduling Talks

Task:

Imagine you are a conference organizer and you are tasked with scheduling the largest possible subset from a set of talks all in the same room. Each talk in the set has a given start time and end time. These times cannot change. No talks that have times that overlap can be scheduled in the same room. For the sake of this assignment assume that one talk can begin instantly upon completion of the previous talk. Consider the example below with three talks in the set:

Talks:

Fred Flinstone 9:00AM-11AM
Barney Rubble 10:30AM-4PM
Bam Bam Rubble 1PM-1:15PM
The optimal schedule (the one that schedules the most talks) in this example would be to schedule Fred's talk and Bam Bam's talk. Note:Optimal does NOT mean the room is used for the longest time, it means the greatest possible number of talks are scheduled.

This Assignment
Step 1: Come up with an algorithm that will lead to scheduling the greatest number of talks.
Step 2: Implement your algorithm in Java using the design outline below.

Included on Codio is a file containing a list of 50 talks along with their start and end times. I used a 24 hour time format to make this a little easier. Your application must read a file in this format and schedule the maximium number of talks that can be scheduled in a single room. To do this write the following two classes:

Talk.java- This class is used to model a talk and may be used to provide the title and/or speaker for a talk along with the start and stop times of the talk. This class should implement the Comparable interface.

Scheduler.java - This class will serve as a repository for static methods that you will use to schedule the talks.

|| Scheduling Talks ||

For the Talk class, I have a constructor that takes in 3 strings for its parameter: the speaker, start time, and stop time. Inside the constructor, I set all my instance variables for the talk class. It throws an IOException if any error occurs (this would mainly be a IOException if the user enters a number larger than 2400 or less than 0000 or an IllegalArgumentException for incorrect time format in the reading file). I had several getter and setter methods in the talk class. The getter method included getSpeaker, get startTime, get stopTime, get startValue, get stopValue, and getValue. The value stood for the integer value of the time. getValue is a special method that trims the String of the time and parses it into an integer value. A NumberFormatException is caught if the input for time is not an integer and cannot be converted to one. For the comparable of this class, since we are comparing based on talks that stop first, I coded an Override comparable that returns -1 if the talk stops first, -1 if the talk stops first and starts first, 0 if the talks stop and start at the same time, and 1 if the talk stops later. Finally, for the toString of this class, I returned the speaker, startTime, and endTime. For the times I took the substring and added a “:” along with a “-“ for design/formatting reading ease. 

The Scheduler class serves as a repository for static methods used to schedule talks. The methods in this class are: makeTalkList, sortTalksByStopTime,
getNoOverlap, and scheduleTalks. The first method is the “makeTalks” method which takes in the filename of the file that was entered in the terminal and reads the file and enters the contents of it into an array list. To do this, I first created a new array list, then a new file object with the file name. I used the scanner to scan through the file and caught the FileNotFoundException in case the file that was entered does not exist. Afterwards, I loop through the file and spilt each of the lines by spaces to get the speaker, startTime, and stopTime. I put this into an array and check if the length is 3. If it is not, I thrown an IllegalArgumentException because this means that they have more or less data than needed. Then, I use the 3 parameters to create a talk object with it.  I also catch NumberFormatException and IOExceptions in this method that come from creating the talk object. Finally, I return the array list. My next static method is the sortTalksByStopTime. This one is fairly simple—I first create a new array list and insert all the elements of the original array list of data into it. Then, I use Collections.sort() to sort by end time sigh I manipulated the comparable in Talk. Afterwards, I return  the sortedList. Another static method I have in my Scheduler class is the getNoOverlap method. This method takes in the sorted by stop time array list and goes from first to last, placing the talks into an array list, but with the condition of making sure these talks are compatible. This means that it checks to make sure that the start of the next talk has to be after the stop of the prior talk, then sorts it accordingly. My last static method in this class is scheduleTalks. In this method, I create a new array list, sort by stop time, and finally sort that list by getting no overlaps in order to receive the final maximized speaker list which I return. 

(By the way, when I wrote this readMe file, I was throwing a lot of different Exceptions. I realized that the tester only catches IOExceptions, so afterwards, I changed all my Exceptions to IOExceptions and printed out the errors instead. So, if there is any confusion in reading my readMe file because I talk about throwing illegalArgumentExceptions or other exceptions, that is the reason why). 
