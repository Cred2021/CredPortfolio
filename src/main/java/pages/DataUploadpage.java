package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;

public class DataUploadpage extends PageBase {
	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr")
	public List <WebElement> DetailsTable;
	
	int tableSize;
	
	@FindBy(xpath = "//a[contains(text(),'Loan Details')]")
	public WebElement loanDetailsTab;

	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//th")
	public List <WebElement> leftDetailsTable;
	
	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//td")
	public List <WebElement> rightDetailsTable;
	

	//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//th
	
	@FindBy(xpath = "//a[contains(text(),'Applicant Details')]")
	public WebElement applicantDetailsTap;
	
	
	//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr
	
	public DataUploadpage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	
	public void switchToTab(String tabName) {
		
		switch(tabName) {
		  case "Applicant Details":
			  jsExecutorClickOn(applicantDetailsTap);
		  
		    break;
		  case "Loan Details":
			  jsExecutorClickOn(loanDetailsTab);
		   
		    break;

		  default:
		    
		}
		
	}
	
	
	public void validateData(String Tab1) throws Throwable {
		
		if(Tab1.contains("Loan Details")) {
			
			tableSize = DetailsTable.size();
			
			
			
			for(int i =0;i<=tableSize;i++) {
			String col=	ExcelLibraries.getDataUploadCol(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
			
			
				if(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase().contains(col)) {
					System.out.println(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					String Value=	ExcelLibraries.getDataUploadValue(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					
					if(!rightDetailsTable.get(i).getText().isBlank() || !rightDetailsTable.get(i).getText().isEmpty()) {
						
						
						if(rightDetailsTable.get(i+1).getText().contains(Value)) {
							System.out.println("There is Data");
						
					}
					else {
						System.out.println(leftDetailsTable.get(i).getText().toString() + "Is Empty");
					}
					}
					
				
					
			}
			
			
		}
			
			
			
		}else if(Tab1.contains("Applicant Details")) {
			switchToTab("Applicant Details");
			
			tableSize = DetailsTable.size();
			
			
			
			for(int i =0;i<=tableSize;i++) {
			String col=	ExcelLibraries.getDataUploadCol(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
			
			
				if(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase().contains(col)) {
					String Value=	ExcelLibraries.getDataUploadValue(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					if(!rightDetailsTable.get(i).getText().isBlank() || !rightDetailsTable.get(i).getText().isEmpty()) {
						
						
						if(rightDetailsTable.get(i).getText().contains(Value)) {
							System.out.println("There is Data");
						
					}
					else {
						System.out.println(leftDetailsTable.get(i).getText().toString() + "Is Empty");
					}
					}
				
				
			}
			
			
		}
		
	}
	
	}

}
