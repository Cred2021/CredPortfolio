package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class ProcessTrackingPage extends PageBase {
	
	//Declaration of variables
	@FindBy(xpath = "//button[normalize-space()='Create New Case']")
	public WebElement CreateCase;

	@FindBy(xpath = "//div[contains(@class,'p-3 d-flex flex-wrap case-tracking-loan-details-card-row')]//div")
	public List <WebElement> PtPageLoanDetailList;

	
	
	
	//constructor
	public ProcessTrackingPage(WebDriver Driver) {
		
		setWebDriver(Driver);
		
	}
	
	//Page level Functions
	public void processTrackingCheckPoint() {
		
		if(waitForElementsToAppearBoolean(PtPageLoanDetailList)) {
			
			
		}
		else {
			createCase();
		}
	
	}
	
	//Create case Function
	public void createCase() {
		
		CreateCase.clear();
		
	}
	
	
	

}
