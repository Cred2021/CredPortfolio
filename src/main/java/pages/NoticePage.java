package pages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class NoticePage extends PageBase {
	

	String noticevalue;
	
	@FindBy(xpath = "//button[normalize-space()='Add Notice Type']")
	
	WebElement addNoticeType;
	
	
	@FindBy(xpath = "//input[@name='case_type_new']")
	WebElement addNoticeValue;
	
	@FindBy(xpath = "//button[normalize-space()='Submit']")
	WebElement submitNoticeType;
	
	@FindBy(xpath = "//button[normalize-space()='Upload Draft']")
	WebElement uploadDraftButton;

	@FindBy(xpath = "//select[@name='case_type']")
	WebElement selectNoticeType;

	@FindBy(css = "#file")
	WebElement chooseDraft;
	
	@FindBy(xpath = "//input[@value='Upload File']")
	WebElement submitNoticeDraft;
	
	//Constructor
	public NoticePage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	//Create Notice Type
	public void setNoticeType(String  noticeType) {
		noticevalue=noticeType;
		jsExecutorClickOn(addNoticeType);
		//addNoticeType.click();
		
		addNoticeValue.sendKeys(noticeType);
		
		jsExecutorClickOn(submitNoticeType);
		//submitNoticeType.click();
	}
	
	//Upload New Draft
	public void uploadDraft() throws AWTException {
		jsExecutorClickOn(uploadDraftButton);
		
		noticevalue = noticevalue.toUpperCase();
	
		TestUtil.selectItemByVisibleText(selectNoticeType, noticevalue);

		 chooseDraft.sendKeys(System.getProperty("user.dir")+"/src/main/java/config/universal_Notice.docx");
		
	
		clickOnElement(submitNoticeDraft);
		
		submitNoticeDraft.sendKeys(Keys.ENTER);
	
		

		
	}
	
	
}
	
	

