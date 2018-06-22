//AbdulTest
package candidateTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import candidates.Candidates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CandidateStep {

	//An ArrayList to keep all Candidates Information
	ArrayList<Candidates> wholeCandidateInfo = new ArrayList<Candidates>();
	
	//Counter to keep the number of candidates that passed all requirements
    private int cnt=0;  
    
    
	@Given("^All information about the candidate$")
	public void All_information_about_the_candidate() throws Throwable {
		
		//Candidate Info As String to pass it to a Array
		String[] candidate_info  = null;
		
		//Excel Path is given to Java
		File candidate_excel = new File ("C:\\Users\\...\\Desktop\\testCandidate\\CandidateTest.xlsx");
		
		//To Get Information inside Excel as Bytes
		FileInputStream fis = new FileInputStream (candidate_excel);
		
		//To Connect Excel
		XSSFWorkbook wb = new XSSFWorkbook (fis);
		
		//To go to Sheet, 0 for Sheet1
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		//Row Count of Excel
		int rowcount = sheet1.getLastRowNum();
		
		//ArrayList to keep Candidate info
		ArrayList<String> Candidates = new ArrayList<String>();
		
		//Column Number is entered manually since getting it is complicated
		int columncnt = 4;
		
		//Nested For Loop to get Candidate information and pass it to candidate list

		for (int i = 1; i < rowcount+1; i++) {
			
			Candidates candidate = new Candidates();
			String data = "";
			
			for (int j = 0; j < columncnt; j++) {
			
			  data = data + sheet1.getRow(i).getCell(j).toString()+" ";
			
			}
			//Adds information to candidates list
			Candidates.add(data);
			
			//Splits Information of Candidate 
			candidate_info = data.split(" ");
			
			candidate.setName(candidate_info[0]);
			candidate.setScore(Float.parseFloat(candidate_info[1]));
			candidate.setHeight(Float.parseFloat(candidate_info[2]));
			candidate.setWeight(Float.parseFloat(candidate_info[3]));
			
			//Adds Candidate Info to WholeCandidateInfo List
			wholeCandidateInfo.add(candidate);
		}
		
		//Print on Console to see output
		System.out.println("Name\tWeight\tHeight\tScore");
		for(int i=0;i<wholeCandidateInfo.size();i++) {
			
			System.out.println(wholeCandidateInfo.get(i).getName()+"\t"+wholeCandidateInfo.get(i).getWeight()+"\t"+wholeCandidateInfo.get(i).getHeight()+"\t"+wholeCandidateInfo.get(i).getScore()+" ");
			
		}
		
		//To Close Workbook
		wb.close();
	
			
	}

	
	@When("^Candidate information Tested$")
	public void Candidate_information_Tested() throws Throwable {
		//For loop to count number of candidates that passed exam
		for(int i=0;i<wholeCandidateInfo.size();i++) {
			
			if(wholeCandidateInfo.get(i).getScore() >= 60  && wholeCandidateInfo.get(i).getHeight()>=150 && wholeCandidateInfo.get(i).getWeight()>=80){
				
				cnt++;
				
			}
		}
		
		
	}

	@Then("^Candidate should Pass the Exam$")
	public void Candidate_should_Pass_the_Exam() throws Throwable {
		
		//Print total number of candidates that passed all the requirements
		System.out.println("Number of cadidates passed all requirements are:  "+cnt);

	}

}