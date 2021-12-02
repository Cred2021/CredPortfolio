package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	protected static WebDriver pbDriver;
	private WebDriverWait wait;
	private static final int iTimeOut = 20; //seconds
	private  static final int iPolling = 100; //milliseconds
	
	    
	@SuppressWarnings("deprecation")
	public void setWebDriver(WebDriver driver) {
		PageBase.pbDriver = driver;
		wait = new WebDriverWait(pbDriver, iTimeOut, iPolling);
	    PageFactory.initElements(new AjaxElementLocatorFactory(PageBase.pbDriver, iTimeOut), this);
	}
	
	protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
	
	
	
	protected Boolean waitForElementToAppearBoolean(WebElement element) {
       return  wait.until(ExpectedConditions.visibilityOf(element))!=null;
    }
	
	
	@SuppressWarnings("deprecation")
	public void waitDriver() throws Throwable {
		pbDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	public void waitForElementToClickable(WebElement element) throws Throwable{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
		public void moveToElement(WebElement element) {
			Actions actions = new Actions(pbDriver); 
			   actions.moveToElement(element); 
		}
		public void jsExecutorClickOn(WebElement element){
	        ((JavascriptExecutor) pbDriver).executeScript("arguments[0].click();",element);
	    }
		
		
		public void jsExecutorscrollIntoView(WebElement element){
	        ((JavascriptExecutor) pbDriver).executeScript("arguments[0].scrollIntoView();",element);
	    }
		
	
		
		public  void openTab() throws InterruptedException {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)pbDriver;
			jse.executeScript("window.open()");
			
		}
		
		public void sendKeysINTByJS( WebElement element, int attributeValue){
		    JavascriptExecutor js = ((JavascriptExecutor) pbDriver);
		    js.executeScript("arguments[0].setAttribute('value','"+attributeValue+"');", element);
		}
		
}
