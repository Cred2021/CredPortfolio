package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;  
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelLibraries;
import utils.ExtentReport;
import utils.TestUtil;
import utils.WebEventListener;



public class TestBase {
	protected static WebDriver driver;
	protected static PageBase basePage;
	public static Properties prop;
	public boolean checkBlnMethod;
	public boolean testStatus;
	static Logger LOGGER =    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	boolean append = true;
    static FileHandler handler;
    public static String logFolderLocation = System.getProperty("user.dir") + "/target/TestReports/";
    public static boolean checkFun;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeSuite
	public void extentFileCreation() {
		//System.out.println();
		System.out.println(TestUtil.getTime(30));	
	
		ExtentReport.createReportFile(prop.getProperty("reportTitle"));
	}
	
	@Parameters("browser")
	@BeforeTest
    public void launchApplication(String brow) throws Throwable {
	     
		
		
		ExcelLibraries.createExcel(getClass().getSimpleName());
		
		ExtentReport.updateReportName(getClass().getSimpleName());
		
	
		
		testStatus = Boolean.valueOf(ExcelLibraries.getTestColValue("Status"));
		
		if(!testStatus) {
			ExtentReport.skipReport();
			throw new SkipException(getClass().getSimpleName()+" has been skipped");
		}
		
		handler = new FileHandler(logFolderLocation+TestUtil.createLogFile(), append);
		
		setDriverProperty(brow);
		
		driver.get(prop.getProperty("AppUrl"));

		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
	}
	
	@BeforeMethod
	public  void getFunctionName(Method  arg0) {
		ExtentReport.createParentNode(arg0.getName());
		
	}
	
	
	public static void log(String data) {
		
		LOGGER.info(data);
		Reporter.log(data);
		LOGGER.addHandler(handler);
	}
	
    @AfterTest
    public static void tearDown() throws Throwable {
    
    	ExcelLibraries.setEndTime();
    	ExcelLibraries.resetCount();
        driver.close();
        driver.quit();
    }
    
    private static void setDriverProperty(String Brow) throws Throwable{
    
		
    	if(Brow.contains("Chrome") || Brow.equalsIgnoreCase("chrome")) {
    		WebDriverManager.chromedriver().setup();
    		driver = new ChromeDriver(); 
  
    		//driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
    		
    	}
    	else if (Brow.contains("Firefox") || Brow.equalsIgnoreCase("Firefox")) 
    		{
    		WebDriverManager.firefoxdriver().setup();
    		driver = new FirefoxDriver(); 
    	}
    	
    	driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	
    	
    }
    
    
public void reporting(String desc,String exp,String actual,String status) throws Throwable {
		
		if(status.equalsIgnoreCase("PASS")) {
			ExtentReport.Report("Pass", desc, actual, exp);
		}else {
			ExtentReport.Report("Fail", desc, actual, exp);
		}
	}
	
    
    
}  
