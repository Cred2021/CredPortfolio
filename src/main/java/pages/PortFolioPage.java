package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class PortFolioPage extends PageBase {

	@FindBy(xpath = "//table[@class='capitalize admin_portfolio_table table']//tbody//tr[1]//td[1]//a")
	public WebElement credgenicsCompany;
	
	@FindBy(css = "#searchKeyword")
	public WebElement searchEnter;
	
	
	@FindBy(xpath = "//table[@class='capitalize admin_portfolio_table table']//tbody//tr[1]//td[1]//a//span[1]")
	public WebElement searchClicked;
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]")
	public WebElement portTable;
	
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[3]/div[1]/select[1]")
	public WebElement selectOption;
	
	@FindBy(xpath = "//input[@id='searchKeyword']")
	public WebElement searchType;
	
	public static WebElement eloan;
	public static String loanNumber;
	
	//input[@id='searchKeyword']
	
	
	
	

	public PortFolioPage(WebDriver driver) {
		setWebDriver(pbDriver);
	}
	
	public void clickOnCompany(String method,String companyName) {
		waitForElementToAppear(credgenicsCompany);
		if(method.equalsIgnoreCase("Click")) {
			jsExecutorClickOn(credgenicsCompany);
	
			
		}else if(method.equalsIgnoreCase("Search")) {
			jsExecutorClickOn(searchEnter);
			searchEnter.sendKeys(companyName,Keys.ENTER);
			WebElement dynamicel = pbDriver.findElement(By.xpath("//td[1]//span[contains(text(),'"+companyName+"')]"));
		
			moveToElement(dynamicel);
			jsExecutorClickOn(dynamicel);
			
		
			
		
		}
		
	}
	
	public boolean searchLoan(String loan) {
		TestUtil.selectItem(selectOption, 1);
		searchType.sendKeys(loan,Keys.ENTER);
		 eloan = pbDriver.findElement(By.xpath("//span[contains(text(),'"+loan+"')]"));
		//span[contains(text(),'"+loan+"')]
		if(waitForElementToAppearBoolean(eloan)!=null) {
			loanNumber = eloan.getText().toString();
			jsExecutorClickOn(eloan);
			
			System.out.println(loanNumber);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean portfolioActivity() {
		if(waitForElementToAppearBoolean(portTable)!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
}
