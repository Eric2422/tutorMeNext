// libraries

import GuiApplication.*;
//	Gui.setSplit()
//	TEXT_MODE
//	.closeGui()
//	Gui.text.clearText()
//	Gui.text.println()
//	Gui.text.pressEnter()
//	Gui.text.readString()
//	Gui.pause()

//	EasyReader()
//	.hasOpenErrorOccurred()
//	.close()

//	EasyWriter
//	.println()
//	.close()


public class MeNextProgram extends GuiApplication
{
	// final class properties
	
	private static final String PRINTER_NAME = "A:\\Printer.txt";
	private static final Time EARLIEST_TIME = new Time(14, 15);
	private static final Time LATEST_TIME = new Time(15, 30);
	private static final int PAUSE_LENGTH = 20;

	// level 0
	
	public static void main(String [] args)
	/*
		preconditions:	args, none.  Any file actually opened as a
			request file is formatted as prescribed.
		postconditions:  None.
	*/
	{
		Gui.setSplit(TEXT_MODE);
		Welcome();
		String fileName = GetFileName();
		EasyReader requestFile = new EasyReader(fileName);
		if(requestFile.hasOpenErrorOccurred())
			ErrorMessage("Could not open: \"" + fileName + "\"");
		else
		{
			ListQueue fileQueue = FillFileQueue(requestFile);
			requestFile.close();
			Teacher teacher = GetTeacherInfo();
			Time startTime = GetTime("start", EARLIEST_TIME, LATEST_TIME);
			Time endTime = GetTime("end", startTime, LATEST_TIME);
			Results results = new Results();
			EasyWriter printer = new EasyWriter(PRINTER_NAME);
			PrintHeading(teacher, startTime, endTime, printer);
			RunAndPrintSimulation(fileQueue, teacher, startTime, endTime, results, printer);
			PrintWrapUp(teacher, results, printer);
			printer.close();
		}
		Gui.closeGui();
		return;
	}
	
	
	// level 1
	
	private static void Welcome()
	/*
		preconditions:	None.
		postconditions:  None.
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Welcome to the \"MeNext\" Simulation");
		Gui.text.println("==================================");
		Gui.text.println();
		Gui.text.println("This program simulates the help");
		Gui.text.println("given to students after school.");
		Gui.text.pressEnter();
		return;
	}
			
	private static String GetFileName()
	/*
		preconditions:	None.
		postconditions:  Returns a possible file name.
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Get the name of the file of help requests");
		Gui.text.println("=========================================");
		Gui.text.println();
		return Gui.text.readString("Enter the name of the file:  ");
	}
	
	private static void ErrorMessage(String error)
	/*
		preconditions:	error has been filled.
		postconditions:  None.
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Error Message");
		Gui.text.println("=============");
		Gui.text.println();
		Gui.text.println(error);
		Gui.text.pressEnter();
		return;
	}
	
	private static ListQueue FillFileQueue(EasyReader requestFile)
	/*
		preconditions:	requestFile has been opened for reading and
			is foramtted properly.
		postconditions:  Returns a ListQueue that has been filled
			with help requests from the file.  The requestFile
			has been read to through the end.
			
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Reading help requests");
		Gui.text.println("=====================");
		Gui.text.println();
		Gui.text.print("reading");
		ListQueue fileQueue = new ListQueue();
		while(! requestFile.hasEOFBeenReached())
		{
			HelpRequest helpRequest = ReadHelpRequest(requestFile);
			fileQueue.enqueue(helpRequest);
			Gui.text.print(".");
		}
		Gui.text.println();
		Gui.text.pressEnter();
		return fileQueue;
	}
	
	private static Teacher GetTeacherInfo()
	/*
		preconditions:  None.
		postconditions:  A Teacher is returned with all fields filled
			with valid values.
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Enter teacher information");
		Gui.text.println("=========================");
		Gui.text.println();
		Teacher teacher = new Teacher();
		teacher.setName(Gui.text.readString("Enter the teacher\'s name:  "));
		String experience;
		do
			experience = Gui.text.readString("Enter experience:\n\t(\"first year\"|\"intermediate\"|\"experienced\"):  ");
		while(! Experience.LegalExperience(experience));
		teacher.setExperience(experience);
		return teacher;
	}

	private static Time GetTime(String whichTime,
								 Time earliestTime,
								 Time latestTime)
	/*
		preconditions:	whichTime is a prompt.  earliestTime <= latestTime.
		postconditions:  A Time is returned that is >= earliestTime and
			<= latestTime.  whichTime, earliestTime, and latestTime are not 
			changed.
	*/
	{
		Gui.text.clearText();
		Gui.text.println("Enter time");
		Gui.text.println("==========");
		Gui.text.println();
		Time time = new Time();
		do
		{
			String timeString;
			do
				timeString = Gui.text.readString("Enter " 
					+ whichTime + " time(" + earliestTime + ".." + latestTime + "):  ");
			while(! Time.LegalTimeString(timeString));
			time.setFromString(timeString);
		}
		while(time.compareTo(earliestTime) < 0 || time.compareTo(latestTime) > 0);
		return time;
	}

	private static void PrintHeading(Teacher teacher, 
									  Time startTime, 
									  Time endTime, 
									  EasyWriter printer)
	/*
		preconditions:	teacher, startTime, and endTime have been filled.
			printer is opened for writing.
		postconditions:  teacher, startTime, endTime are unchanged, the
			printer has had a heading sent.
	*/
	{
		printer.println("Simulation File");
		printer.println("===============");
		printer.println();
		printer.println();
		printer.println("Run Duration");
		printer.println("============");
		printer.println(startTime + " - " + endTime);
		printer.println();
		printer.println();
		printer.println("Teacher");
		printer.println("=======");
		printer.println();
		PrintTeacher(teacher, printer);
		printer.println();
		printer.println();
		return;
	}
	
	private static void RunAndPrintSimulation(ListQueue fileQueue, 
											   Teacher teacher, 
											   Time startTime, 
											   Time endTime, 
											   Results results, 
											   EasyWriter printer)
	/*
		preconditions:	fileQueue has been filled from a file
			of HelpRequests, teacher, startTime, and endTime have been fileed,
			results has been initialized, and printer is opened for output.
		postconditions:  The fileQueue has been processed through any
			HelpRequests with times <= endTime.  startTime and endTime have
			not been changed, results has been updated to reflect the processing
			as has the printer.
	*/
	{
		printer.println("Simulation Run");
		printer.println("==============");
		printer.println();
		Time currentTime = new Time(startTime);
		while(currentTime.compareTo(endTime) <= 0)
		{
			Gui.text.clearText();
			Gui.text.println("Simulation Run");
			Gui.text.println("==============");
			Gui.text.println();
			Gui.text.println("Time:  " + currentTime + "    ");
			Gui.pause(PAUSE_LENGTH);
			RunAndPrintOneMinute(currentTime, teacher, fileQueue, results, printer);
			currentTime = currentTime.nextMinute();
		}
		printer.println();
		printer.println();
		Gui.text.println("Simulation done...");
		Gui.text.pressEnter();
		return;
	}
	
	private static void PrintWrapUp(Teacher teacher, 
									 Results results, 
									 EasyWriter printer)
	/*
		preconditions:  teacher and results are in a condition 
			resulting from running the simulation.  printer
			is opened and ready for output.
		postconditions:teacher and results, none.  printer
			has had a summary printed to it.
	*/
	{
		printer.println("Teacher At End");
		printer.println("==============");
		printer.println();
		printer.println();
		PrintTeacher(teacher, printer);
		printer.println();
		printer.println();
		PrintResults(results, printer);		
		return;
	}


	// level 2
	
	private static HelpRequest ReadHelpRequest(EasyReader requestFile)
	/*
		preconditions:  requestFile is opened for reading and
			formatted properly.
		postconditions:  requestFile has been read past a HelpRequest.
			That HelpRequest is returned.
	*/
	{
		HelpRequest helpRequest = new HelpRequest();
		Time aTime = new Time();
		String timeString = requestFile.readLine();
		aTime.setFromString(timeString);
		helpRequest.setTime(aTime);
		helpRequest.setName(requestFile.readLine());
		helpRequest.setDemeanor(new Demeanor(requestFile.readLine()));
		helpRequest.setError(new Error(requestFile.readLine()));
		helpRequest.setMinutesWithHelp(requestFile.readInt());
		requestFile.readLine();
		helpRequest.setMinutesWithoutHelp(requestFile.readInt());
		requestFile.readLine();
		return helpRequest;
	}
	
	private static void PrintTeacher(Teacher teacher, 
									  EasyWriter printer)
	/*
		preconditions:  teacher has been filled.  printer is opened
			for output.
		postconditions:  teacher is unchanged.  The contents of teacher 
			have been sent to printer.
	*/
	{
		printer.println("Teacher:");
		printer.println("\t\"" + teacher.getName() + "\"");
		printer.println("\t" + teacher.getExperience());
		printer.print("Current ");
		PrintRequest(teacher.getCurrentRequest(), printer);
		printer.print("Teacher ");
		PrintStack(teacher.requestStack, printer);
		printer.print("Teacher ");
		PrintQueue(teacher.requestQueue, printer);
		return;
	}
	
	private static void RunAndPrintOneMinute(Time currentTime, 
											  Teacher teacher, 
											  ListQueue fileQueue, 
											  Results results,
											  EasyWriter printer)
	/*
		preconditions:  currentTime is the current time, teacher,
			fileQueue, and results reflect all previous processing, 
			printer is ready for output.
		postconditions:   currentTime, unchanged.  teacher,
			fileQueue, results, and printer have been updated to reflect
			one minute of processing, 
			printer is ready for output.
	*/
	{
		printer.println("Time:  " + currentTime + "    ");
		if(! fileQueue.isEmpty() 
		&&((HelpRequest) fileQueue.peekFront()).getTime().compareTo(currentTime) <= 0)
			while(! fileQueue.isEmpty() 
			&&((HelpRequest) fileQueue.peekFront()).getTime().compareTo(currentTime) <= 0)
			{
				HelpRequest request =(HelpRequest) fileQueue.dequeue();
				printer.print("\t" + teacher.getName() + "\" accepts request:  \"");
				printer.print(request.getName() + "\" @ " + request.getTime());
				printer.println(", " + request.getDemeanor());
				TeacherAcceptsRequest(request, teacher);
			}
		else
			Help(currentTime, teacher, results, printer);
		return;
	}

	private static void PrintResults(Results results, 
									  EasyWriter printer)
	/*
		preconditions:  results reflects all previous processing.  printer
			is ready for output.
		postconditions:  results is unchanged.  printer has had
			a summary sent to it.
	*/
	{
		printer.println("Simulation summary:");
		printer.println("===================");
		printer.println();
		printer.println("Number helped:  " + results.getNumberHelped());
		printer.println("Total wait time:  " + results.getTotalWaitTime());
		printer.print("Average wait time:  ");
		if(results.getNumberHelped() > 0)
			printer.println(((float) results.getTotalWaitTime() /(float) results.getNumberHelped()));
		else
			printer.println("0.0");
		return;
	}

	
	// level 3
	
	private static void	PrintRequest(HelpRequest request, 
									  EasyWriter printer)
	/*
		preconditions:  request is filled.  printer is ready for output.
		postconditions:  request is unchanged.  printer has had the request 
			sent to it.
	*/
	{
		printer.println("Request:  \"" + request.getName() + "\", @ " + request.getTime());
		printer.println("\tDemeanor:  " + request.getDemeanor());
		printer.println("\tError:  " + request.getError());
		printer.println("\tMinutes with help:  " + request.getMinutesWithHelp());
		printer.println("\tMinutes without help:  " + request.getMinutesWithoutHelp());
		return;
	}
	
	private static void	PrintStack(ArrayStack stack, 
									EasyWriter printer)
	/*
		preconditions:  stack is filled.  printer is ready for output.
		postconditions:  stack is unchanged!!!  printer has had the stack 
			sent to it.
	*/
	{
		printer.println("Request Stack:  ");
		ArrayStack tempStack = new ArrayStack();
		while(! stack.isEmpty())
		{
			HelpRequest request =(HelpRequest) stack.pop();
			tempStack.push(request);
			PrintRequest(request, printer);
		}
		while(! tempStack.isEmpty())
			stack.push(tempStack.pop());
		return;
	}
	
	private static void	PrintQueue(ListQueue queue, 
									EasyWriter printer)
	/*
		preconditions:  queue is filled.  printer is ready for output.
		postconditions:  queue is unchanged!!!  printer has had the queue 
			sent to it.
	*/
	{
		printer.println("Request Queue:  ");
		ListQueue tempQueue = new ListQueue();
		while(! queue.isEmpty())
		{
			HelpRequest request =(HelpRequest) queue.dequeue();
			tempQueue.enqueue(request);
			PrintRequest(request, printer);
		}
		while(! tempQueue.isEmpty())
			queue.enqueue(tempQueue.dequeue());
		return;
	}

	
	// level 4

	private static void TeacherAcceptsRequest(HelpRequest request, 
											   Teacher teacher)
	/*
		preconditions:  request is filled.  teacher reflects all
			previous processing.
		postconditions:  request is unchanged.  teacher has added
			the request to the stack or queue as appropriate.
	*/
	{
		if(teacher.getExperience().equals(Experience.FIRST_YEAR))
		{
			if(request.getDemeanor().equals(Demeanor.RUDE))
				teacher.requestStack.push(request);
			else if(request.getDemeanor().equals(Demeanor.POLITE))
				teacher.requestQueue.enqueue(request);
			else
				throw new IllegalStateException("TeacherAcceptsRequest(), illegal Demeanor."); 
		}
		else if(teacher.getExperience().equals(Experience.INTERMEDIATE))
			teacher.requestQueue.enqueue(request);
		else if(teacher.getExperience().equals(Experience.EXPERIENCED))
		{
			if(request.getDemeanor().equals(Demeanor.RUDE))
				teacher.requestQueue.enqueue(request);
			else if(request.getDemeanor().equals(Demeanor.POLITE))
			{
				if(request.getError().equals(Error.COMPILING)
				|| request.getError().equals(Error.LINKING))
					teacher.requestStack.push(request);
				else if(request.getError().equals(Error.RUNTIME))
					teacher.requestQueue.enqueue(request);
				else
					throw new IllegalStateException("TeacherAcceptsRequest(), illegal Error.");
			}
			else
				throw new IllegalStateException("TeacherAcceptsRequest(), illegal Demeanor.");
		}
		else
			throw new IllegalStateException("TeacherAcceptsRequest(), illegal Experience.");
		return;
	}
	
	private static void Help(Time currentTime, 
							  Teacher teacher, 
							  Results results, 
							  EasyWriter printer)
	/*
		preconditions:  currentTime is the current time, teacher, results, 
			and printer all reflect previous processing.
		postconditions:  currentTime is unchanged.  teacher, results, 
			and printer all reflect the process of Help.
	*/
	{
		if(! teacher.getCurrentRequest().getError().equals(Error.UNDEFINED))
		{
		
			HelpRequest helpRequest = teacher.getCurrentRequest();
			helpRequest.setMinutesWithHelp(helpRequest.getMinutesWithHelp() - 1);
			teacher.setCurrentRequest(helpRequest);
			if(teacher.getCurrentRequest().getMinutesWithHelp() == 0)
				printer.print("\tProblem fixed:  \"");
			else
				printer.print("\tHelping:  \"");
			printer.println(teacher.getCurrentRequest().getName() 
				+ "\"(" + teacher.getCurrentRequest().getMinutesWithHelp() + ")");
		}
		if(teacher.getCurrentRequest().getMinutesWithHelp() == 0)
			if(! teacher.requestStack.isEmpty())
			{
				teacher.setCurrentRequest((HelpRequest) teacher.requestStack.pop());
				results.setNumberHelped(results.getNumberHelped() + 1);
				results.setTotalWaitTime(results.getTotalWaitTime() 
					+ currentTime.inMinutes() - teacher.getCurrentRequest().getTime().inMinutes());
				printer.print("\tFrom stack:  \"" + teacher.getCurrentRequest().getName());
				printer.print("\" now current student(" 
					+ teacher.getCurrentRequest().getMinutesWithHelp() + "), ");
				printer.println(teacher.getCurrentRequest().getError());
			}
			else if(! teacher.requestQueue.isEmpty())
			{
				teacher.setCurrentRequest((HelpRequest) teacher.requestQueue.dequeue());
				results.setNumberHelped(results.getNumberHelped() + 1);
				results.setTotalWaitTime(results.getTotalWaitTime() 
					+ currentTime.inMinutes() - teacher.getCurrentRequest().getTime().inMinutes());
				printer.print("\tFrom queue:  \"" + teacher.getCurrentRequest().getName());
				printer.print("\" now current student(" 
					+ teacher.getCurrentRequest().getMinutesWithHelp() + "), ");
				printer.println(teacher.getCurrentRequest().getError());
			}
			else
			{
				teacher.setCurrentRequest(new HelpRequest());
				printer.println("\tNo one to help.");
			}
		return;
	}
}