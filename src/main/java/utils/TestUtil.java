package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import base.PageBase;
import base.TestBase;


public class TestUtil extends TestBase{
	static String rootdir;
	public static String brow;
	public static String reportFolderPath = System.getProperty("user.dir") + "/target/TestReports/";
	public static String[][] reportLog;
	static Date date;
	Calendar calendar;
	public static Robot robj ;

	public static Robot robj;
	
	public static String getCurrentDate()
	{
		Date date = new Date();  
	    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate = dateformat.format(date); 
	    return strDate;
	}
	
	public static String getTimeStamp()
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String time = dateFormat.format(now);
		return time.replace("-", "");
		
	}
	
	
	//-------------------------------------------TimeStamp Function----------------------------------	
	public static String getAttendenceTime(int hour)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());              
		cal.add(Calendar.HOUR_OF_DAY, hour);  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(cal.getTime());
		return time;
	}
	
	
	//---------------------------------Function For Current Date---------------------------------		
	public static String getLeaveDate(int date)
	{
	    Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, date);
	    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate = dateformat.format( cal.getTime()); 
	    return strDate;
	}
	
	//---------------------------------------Select Item--------------------------------------------
	public static void selectItem(WebElement element,int LeaveFormat) {
		Select Leave = new Select(element);
		Leave.selectByIndex(LeaveFormat);
	}
	
	
	
	public static void selectItemByVisibleText(WebElement element,String visibleText) {
		Select list = new Select(element);
		list.selectByVisibleText(visibleText);
	}
	
	//------------------------get date by format---------------------------------------------------
	public static String getDatebyFormat(String dateFormat ,int addToDate)
	{
	    Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, addToDate);
	    SimpleDateFormat dateformat = new SimpleDateFormat(dateFormat);  
	    String strDate = dateformat.format( cal.getTime()); 
	    return strDate;
	}
	
	public static void mouseHover(WebElement element) throws Throwable {
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.moveToElement(element).perform();
	}
	
	
	public static String getScreenhot(String Status) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/TestsScreenshots/"+Status+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public static String createLogFile() {
		
		try {
		      File myObj = new File(System.getProperty("user.dir") + "/target/TestReports/loggerReport"+getTimeStamp()+".log");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        return myObj.getName();
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return null;
	}
	
	
	public static String getTime(int hour)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());              
		cal.add(Calendar.MINUTE, -hour);  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = dateFormat.format(cal.getTime());
		return time;
	}
	
	
	public static void selectBYList(List <WebElement> el, String Name) {
		
		
		
		int size = el.size();
		for(int i =0; i<=size; i++) {
			System.out.println(el.get(i).getText());
			if(el.get(i).getText().equalsIgnoreCase(Name)) {
			PageBase.clickOnElement(el.get(i));
				
				break;
			}
		}
		
		
	
	}
	
	
	public static String removeDayandDate(String datexpath) {
		Calendar calendar = Calendar.getInstance();
		 calendar = Calendar.getInstance();
		 date = calendar.getTime();
		 String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		 calendar = Calendar.getInstance();
		 SimpleDateFormat dateformat = new SimpleDateFormat("dd yyyy");  
		 String strDate = dateformat.format( calendar.getTime()); 

		String Day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		
		datexpath = datexpath.replace(Day, "");
		datexpath =	datexpath.replace(",", "");
		datexpath =	datexpath.replace("AM", "");
		datexpath =	datexpath.replace("PM", "");
		datexpath =	datexpath.replace(month, "");
		datexpath =	datexpath.replace(strDate, "");
		datexpath =	datexpath.trim();
		System.out.println(datexpath);
		return datexpath;
		
	}
	
	public static void uploadFile(String fileName) throws AWTException {
		robj = new Robot();
		//String fileName = System.getProperty("user.dir")+"/src/main/java/config/universal_Notice.docx";
		File file = new File(fileName);
		StringSelection StringSelection = new StringSelection(file.getAbsolutePath());
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection, null);

	    driver.switchTo().window(driver.getWindowHandle());

	   

	    //Open Goto window
	    robj.keyPress(KeyEvent.VK_META);
	    robj.keyPress(KeyEvent.VK_SHIFT);
	    robj.keyPress(KeyEvent.VK_G);
	    robj.keyRelease(KeyEvent.VK_META);
	    robj.keyRelease(KeyEvent.VK_SHIFT);
	    robj.keyRelease(KeyEvent.VK_G);

	    //Paste the clipboard value
	    robj.keyPress(KeyEvent.VK_META);
	    robj.keyPress(KeyEvent.VK_V);
	    robj.keyRelease(KeyEvent.VK_META);
	    robj.keyRelease(KeyEvent.VK_V);

	    //Press Enter key to close the Goto window and Upload window
	    robj.delay(1000);
	    robj.keyPress(KeyEvent.VK_ENTER);
	    robj.keyRelease(KeyEvent.VK_ENTER);
	    robj.delay(1000);
	    robj.keyPress(KeyEvent.VK_ENTER);
	    robj.keyRelease(KeyEvent.VK_ENTER);
		
		
		
		
	}
	
	
	public static void uploadFile(String filename) throws AWTException {
		robj = new Robot();
		//String fileName = System.getProperty("user.dir")+"/src/main/java/config/testing notice 2.docx";
		File file = new File(filename);
		StringSelection StringSelection = new StringSelection(file.getAbsolutePath());
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection, null);

	    driver.switchTo().window(driver.getWindowHandle());

	   

	    //Open Goto window
	    robj.keyPress(KeyEvent.VK_META);
	    robj.keyPress(KeyEvent.VK_SHIFT);
	    robj.keyPress(KeyEvent.VK_G);
	    robj.keyRelease(KeyEvent.VK_META);
	    robj.keyRelease(KeyEvent.VK_SHIFT);
	    robj.keyRelease(KeyEvent.VK_G);

	    //Paste the clipboard value
	    robj.keyPress(KeyEvent.VK_META);
	    robj.keyPress(KeyEvent.VK_V);
	    robj.keyRelease(KeyEvent.VK_META);
	    robj.keyRelease(KeyEvent.VK_V);

	    //Press Enter key to close the Goto window and Upload window
	    robj.delay(1000);
	    robj.keyPress(KeyEvent.VK_ENTER);
	    robj.keyRelease(KeyEvent.VK_ENTER);
	    robj.delay(1000);
	    robj.keyPress(KeyEvent.VK_ENTER);
	    robj.keyRelease(KeyEvent.VK_ENTER);
		
		
		
		
	}
	

}
	
