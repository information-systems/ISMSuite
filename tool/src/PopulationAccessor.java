import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.Transition;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class PopulationAccessor {

	public static void updatePopulation(String transitionName, String fileLocation, Multimap<String,String> population, List<String> entityList) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		
		switch(transitionName)
		{
		case "Student.Start":
			population.put(entityList.get(0), "person(" + "s" + entityList.get(0) + ")");	
			break;
			
		// If transition is Student.Register : 
		case "Student.Register":
			
			// Get the student and track as a comma seperated string.
			String P = "s" + entityList.get(0);
			String PxT = "s" + entityList.get(0) + ",s" + entityList.get(1);
			String P_T = "s" + entityList.get(0) + "_s" + entityList.get(1);
			// Update the population Multimap
			population.put(PxT, "registers(" + PxT + ")");
			
			// Update the population file
			addToPopulation("fof(" + P  + ", axiom, (person(" + P + "))).", fileLocation);
			addToPopulation("fof(" + P_T + ", axiom, (registers(" + PxT + "))).", fileLocation);
			break;
		
		// If transition is Student.Accept_student: 
		case "Student.Accept_Student": 
			// Get the student and track as a comma seperated string.
			String PxT_AcceptS = "s" + entityList.get(1) + ",s" + entityList.get(2);
			String P_T_AcceptS = "s" + entityList.get(1) + "_s" + entityList.get(2);
			// Get the student, track and administrator as a comma seperated string.
			String PxTxA_AcceptS = "s" + entityList.get(1) + ",s" + entityList.get(2) + ",s" + entityList.get(0);
			String P_T_A_AcceptS = "s" + entityList.get(1) + "_s" + entityList.get(2) + "_s" + entityList.get(0);
			
			
			// Update the population Multimap
			population.put(P_T_A_AcceptS,"accepts(s" + PxTxA_AcceptS + ",s" + System.currentTimeMillis() + ")");
			population.remove(P_T_AcceptS, "registers(s" + PxT_AcceptS + ")");
			population.remove(P_T_AcceptS, "enrols(s" + PxT_AcceptS + ")");
			population.put(P_T_AcceptS, "enrols(s" + PxT_AcceptS + ")");
			
			//Update the population file
			addToPopulation("fof(" + P_T_A_AcceptS + ", axiom, (accepts(" + PxT_AcceptS + ",s" + dateFormat.format(date) + "))).", fileLocation);
			removeFromPopulation("registers(" + PxT_AcceptS + ")",fileLocation);
			removeFromPopulation("enrols(" + PxT_AcceptS + ")",fileLocation);
			addToPopulation("fof(" + P_T_AcceptS + ", axiom, (enrols(" + PxT_AcceptS + "))).", fileLocation);
			break;
		
		
		// If transition is Student.Reject_student: 
		case "Student.Reject_Student":
			// Get the student and track as a comma seperated string.
			String PxT_RejectS = "s" + entityList.get(1) + ",s" + entityList.get(2);
			String P_T_RejectS = "s" + entityList.get(1) + "_s" + entityList.get(2);
			// Get the student, track and administrator as a comma seperated string.
			String PxTxA_RejectS = "s" + entityList.get(1) + ",s" + entityList.get(2) + ",s" + entityList.get(0);
			String P_T_A_RejectS = "s" + entityList.get(1) + "_s" + entityList.get(2) + "_s" + entityList.get(0);
			
			// Update the population Multimap
			//population.remove(P_T_RejectS,"registers(s" + PxT_RejectS + ")");
			//population.put(P_T_A_RejectS, "rejects(s" + PxTxA_RejectS + "," + System.currentTimeMillis() + ")");
			
			//Update the population file
			removeFromPopulation("registers(" + PxT_RejectS + ")", fileLocation);
			addToPopulation("fof(" + P_T_A_RejectS + ", axiom, (rejects(" + PxTxA_RejectS + ",s" + dateFormat.format(date) + "))).", fileLocation);
	        break;
	    
	    
	    // If transition is Student.Create_Studyplan, create a studyplan for each student.
		case "Student.Create_Studyplan":
			String SPxSxT_CreateSP = "s" + Integer.parseInt(entityList.get(0)) + 1 + ",s" + entityList.get(1) + ",s" + entityList.get(2);
			String SP_S_T_CreateSP = "s" + Integer.parseInt(entityList.get(0)) + 1 + "_s" + entityList.get(1) + "_s" + entityList.get(2);
			
			String SP_CreateSP = "s" + Integer.toString(Integer.parseInt(entityList.get(0)) + 1);
			
			
			addToPopulation("fof(" + SP_S_T_CreateSP + ", axiom, (isOf(" + SPxSxT_CreateSP +"))).", fileLocation);
			addToPopulation("fof(" + SP_CreateSP + ", axiom, (contains(" + SP_CreateSP + "))).", fileLocation);
			break;
		
		case "Student.Reject_Plan":
			// remove ( "isOf(sp,p,t)" ); -> Sounds illogical
			
			String SP_RejectSP = "s" + entityList.get(1);
				
			updateStudyplan("contains(" + SP_RejectSP + ",","fof(" + SP_RejectSP + ", axiom, (contains(" + SP_RejectSP + "))).", fileLocation);
			
			break;
			
		case "Student.Accept_plan":
			String SPxM_AcceptP = "s" + entityList.get(1) + ",s" + entityList.get(0);
			String SP_M_AcceptP = "s" + entityList.get(1) + "_s" + entityList.get(0);
			addToPopulation("fof(" + SP_M_AcceptP + ", axiom, (contains(" + SPxM_AcceptP + "))).", fileLocation);
			break;
		 
		case "Student.Add_Course":
			
			// To be implemented later.
			break;
		
		
		case "Student.Remove_Course":
			// To be implemented later.
			break;
			
		case "Student.Register_Course":
			String PxTxC_RegisterC = "s" + entityList.get(2) + ",s" + entityList.get(3) + ",s" + entityList.get(0);
			String P_T_C_RegisterC = "s" + entityList.get(2) + "_s" + entityList.get(3) + "_s" + entityList.get(0);
			addToPopulation("fof(" + P_T_C_RegisterC + ", axiom, (enrolsInto(" + PxTxC_RegisterC + "))).", fileLocation);
			
			break;
		
		// Remove the enrolment, if the student Unregisters from a course.
		case "Student.Unregister":
			String PxTxC_Unregister = "s" + entityList.get(1) + ",s" + entityList.get(2) + ",s" + entityList.get(0);
			String P_T_C_Unregister = "s" + entityList.get(1) + "_s" + entityList.get(2) + "_s" + entityList.get(0);
			
			removeFromPopulation("enrolsInto(" + PxTxC_Unregister + ")", fileLocation);
			break;
		
		case "Student.Fail_Course":
			String PxTxC_FailC = "s" + entityList.get(1) + ",s" + entityList.get(3) + ",s" + entityList.get(0);
			String PxTxCxL_FailC = "s" + entityList.get(2) + ",s" + entityList.get(3) + ",s" + entityList.get(0) + ",s" + entityList.get(1);
			String P_T_C_L_FailC = "s" + entityList.get(2) + "_s" + entityList.get(3) + "_s" + entityList.get(0) + "_s" + entityList.get(1);
			
			removeFromPopulation("enrolsInto(" + PxTxC_FailC + ")", fileLocation);
			addToPopulation("fof(" + P_T_C_L_FailC + ", axiom, (fails(" + PxTxCxL_FailC + "))).", fileLocation);
			break;	
		
		
		case "Student.Pass_Course":
			String PxTxC_PassC = "s" + entityList.get(1) + ",s" + entityList.get(3) + ",s" + entityList.get(0);
			String PxTxCxL_PassC = "s" + entityList.get(2) + ",s" + entityList.get(3) + ",s" + entityList.get(0) + ",s" + entityList.get(1);
			String P_T_C_L_PassC = "s" + entityList.get(2) + "_s" + entityList.get(3) + "_s" + entityList.get(0) + "_s" + entityList.get(1);
			
			removeFromPopulation("enrolsInto(" + PxTxC_PassC + ")", fileLocation);
			addToPopulation("fof(" + P_T_C_L_PassC + ", axiom, (passes(" + PxTxCxL_PassC + "))).", fileLocation);
			break;	
		
		case "Student.Register_Exam":
			String PxT_RegisterE = "s" + entityList.get(1) + ",s" + entityList.get(2);
			String P_T_RegisterE = "s" + entityList.get(1) + "_s" + entityList.get(2);
			
			addToPopulation("fof(" + P_T_RegisterE + ", axiom, (registeredExam(" + PxT_RegisterE + "))).", fileLocation);
			break;
		
		
		// If the transition is Student.Reject_Exam, remove the exam statement from the population.
		case "Student.Reject_Exam":
			String SP_RejectE = "s" + entityList.get(1);
			
			updateStudyplan("contains(" + SP_RejectE,"fof(" + SP_RejectE + ", axiom, (contains(" + SP_RejectE + "))).", fileLocation);	
			// remove ( "isOf(sp,p,t)" );	-> Deleting whole studyplan is not logical.
			break;
		
		case "Student.Accept_Exam":
			String PxTxM_AcceptE = "s" + entityList.get(2) + ",s" + entityList.get(3) + ",s" + entityList.get(0);
			String P_T_M_AcceptE = "s" + entityList.get(2) + "_s" + entityList.get(3) + "_s" + entityList.get(0);
			
			addToPopulation("fof(" + P_T_M_AcceptE + ", axiom, (approvesExamOf(" + PxTxM_AcceptE + "))).", fileLocation);
			break;
			
		case "Student.Award_Exam":
			String PxT_AwardE = "s" + entityList.get(0) + "_s" + entityList.get(1);
			String P_T_AwardE = "s" + entityList.get(0) + "_s" + entityList.get(1);
			
			addToPopulation("fof(" + P_T_AwardE + ", axiom, (awardedExam(" + PxT_AwardE + "))).", fileLocation);
			break;
		}	
		
	}
	
	// Return the courses as a list, needs a string as input which consists of all the courses seperated by a comma.
	public static List<String> getCoursesStudyplan(String studyplan)
	{
		List<String> followingCoursesList = new ArrayList<String>();
		try {
			if (studyplan.isEmpty() == false)
			followingCoursesList = new ArrayList<String>(Arrays.asList(studyplan.split(",")));
		return followingCoursesList;
		}
		catch (Exception e) {
			System.out.println(studyplan);
			System.out.println(followingCoursesList);
			System.out.println("An error occured when getting the courses from the studyplan.");;
		}
		throw new ArithmeticException("An error occured when getting the courses from the studyplan.");
	}
	
	// Get the entities from the specific type, e.g. student(Rick) returns Rick.
	public static List<String> getTypeEntities(String filelocation, String type) throws Exception
	{
		List<String> returnList = new ArrayList<String>();
		// Read the file.
		try (BufferedReader reader = new BufferedReader(new FileReader(filelocation)))
		{
			String newline;
			// Read the file line by line.
			while ((newline = reader.readLine()) != null) {
				// Create a start and end index to get the specific entities.
				int startIndexSubstring = newline.indexOf(type);
				int endIndexSubstring = newline.indexOf(")");
				
				//If the start and index are not -1, i.e. they do exist. Then get the entities.
				if (startIndexSubstring != -1 && endIndexSubstring != -1)
				{
				startIndexSubstring += type.length() + 1;
				returnList.add(newline.substring(startIndexSubstring, endIndexSubstring ));				
				}
			}
			reader.close();
		}
		return returnList;		
	}
	
	// Add the statement to the population.
	public static void addToPopulation(String statement, String fileLocation) throws Exception
	{
		FileWriter fw = new FileWriter(fileLocation, true);
		fw.write(statement + "\r\n");
		fw.close();		
	}
		
	
	// Remove the lines that contain the statement from the population.
	public static void removeFromPopulation(String statement, String fileLocation) throws Exception
	{
		File file = new File(fileLocation);
		List<String> content_of_file = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
		
		for (int l = 0; l < content_of_file.size(); l++)
		{
			if (content_of_file.get(l).contains(statement))
				content_of_file.remove(l);
		}
		Files.write(file.toPath(),content_of_file, StandardCharsets.UTF_8);
	}
	
	// Methode kan beter als mee wordt gegeven wie de persoon moet zijn.
	public static boolean validate(String populationFileLocation, String constraintsFileLocation) throws Exception
	{		
		// Create a list that will contain all the created constraints.
		List<String> constraintList = new ArrayList<String>();
			
		// get the mentioned file (i.e. file within the same directory). And use this to get the absoluteFile
		File populationAbsoluteFile = new File(populationFileLocation);
		
		// read everyline from the constraints file.
		try (BufferedReader reader = new BufferedReader(new FileReader(constraintsFileLocation)))
		{		
			String newline;
			// Read the file line by line.
			while ((newline = reader.readLine()) != null) 		
				constraintList.add(newline);
			
			reader.close();			
		}
		
		// Replace all the different tags within the constraints file.
		for (int i = 0; i < Tag2Replace.values().length; i++ )
			constraintList = PopulationAccessor.replaceTags(constraintList, Tag2Replace.values()[i], populationFileLocation);
		
		File tempConstraintDirectory = new File("temporaryConstraintFiles");
			
		for (String Constraint : constraintList)
		{
			if (Constraint.isEmpty())
				continue;
				
			// Create temporary file.
			File constraintTempFile = File.createTempFile("constraintFile", ".p",tempConstraintDirectory);
				
			// If the file exists and it is possible to write in file, start writing
			if (constraintTempFile.exists() && constraintTempFile.canWrite())
			{
				PrintWriter writer = new PrintWriter(constraintTempFile.getCanonicalPath(), "UTF-8");
				writer.println("include(\'" + populationAbsoluteFile.getAbsolutePath() + "\').");
				writer.println(Constraint);
				writer.close();
			}
			else
				throw new ArithmeticException("It was not possible to create a temporary constraint file and write to it.");
				
			// Check if constraint can be proven.
			// If valid, continue to the next constraint. Else return false.
			if (CPNAccessor.CheckValid(constraintTempFile.getPath()))
			{ 
				constraintTempFile.delete();
				continue;
			}
			// If not valid, delete the file and return that the transition is not valid.
			else
			{
				constraintTempFile.delete();
				return false;
			}
		}
		return true;	
	}
	
	private static void updateStudyplan(String line_to_replace, String new_line, String fileLocation) throws Exception
	{
		File file = new File(fileLocation);
		List<String> content_of_file = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
		
		for (int l = 0; l < content_of_file.size(); l++)
		{
			if (content_of_file.get(l).contains(line_to_replace))
				{ 
				  content_of_file.set(l, new_line);
				  break;
				}
		}
		Files.write(file.toPath(),content_of_file, StandardCharsets.UTF_8);
	}
	
	private enum Tag2Replace { 
		person, studyplan, course, track,
		enrols, administrator, manager, lecturer,
		registers, rejects, accepts, isof,
		contains, approves, enrolsInto, fails,
		passes, registeredExam, validates, approvesExamOf,
		awardedExam
		};
	
	// Return a List<String> with the updated constraints
	private static List<String> replaceTags(List<String> constraintList , Tag2Replace tag, String populationFileLocation) throws Exception
	{
		// Create the variables required.
		String tag2Replace = new String();
		List<String> IDs2Replace = new ArrayList<String>();
		List<String> tempConstraintList = new ArrayList<String>();
		
		// Get the tag to replace and the IDs to replace the tag with.
		tag2Replace = tag.toString().substring(0, 1).toUpperCase() + tag.toString().substring(1)  + "2Replace";
		IDs2Replace = getTypeEntities(populationFileLocation,tag.toString());
		
		// Replace all the replace-tags
		for (String constraint : constraintList)
			{
				if (constraint.contains(tag2Replace))
					{
						// Replace all the person replace-tags
						for (String ID : IDs2Replace)
						{
							tempConstraintList.add(constraint.replaceAll(tag2Replace,ID));
						}
					}
				else
					tempConstraintList.add(constraint);
			}
					
		return tempConstraintList;				
	}
	
}
