package swagbase_pkg;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import swagpage_pkg.SwagLogin_page;


public class Swagbase_class {
		
		public static WebDriver driver;
		public SwagLogin_page obj;
		
		public static ExtentSparkReporter reporter;
		public static ExtentReports extent;
		public static ExtentTest test;
		
		
		@BeforeTest
		public void setup()
		{
			reporter = new ExtentSparkReporter("./Reports/myreport1.html");  // create a report
			reporter.config().setDocumentTitle("Automation Report");
			reporter.config().setReportName("Functional Test");
			reporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
			extent.setSystemInfo("Hostname", "localhost");
			extent.setSystemInfo("OS", "Windows11");
			extent.setSystemInfo("Tester Name", "Athira");
			extent.setSystemInfo("Browser Name", "Chrome");
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");	
		}
		
		
		@BeforeMethod
		public void mthd(Method method)
		{
			obj = new SwagLogin_page(driver);
		
		}
		
		@AfterMethod
		public void after_method(ITestResult result) throws Exception
		{
			if(result.getStatus()== ITestResult.FAILURE)
			{
				test.log(Status.FAIL,"Test Case Failed is --> "+result.getName());
				test.log(Status.FAIL,"Test Case Failed is --> "+result.getThrowable());	
				String screenshot_path = screenshotMethod(driver,result.getName());
				test.addScreenCaptureFromPath(screenshot_path);
				
			}
			else if(result.getStatus()==ITestResult.SUCCESS)
			{
				test.pass("Test Passed --> "+result.getName());
			}
			else if(result.getStatus()==ITestResult.SKIP)
			{
				test.skip("Test Skipped ! --> "+result.getName());
			}
		}
		
		private String screenshotMethod(WebDriver driver, String name) throws Exception
		{
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir")+"./screenshot/"+name+".png";
			// String destination = "./screenshot/"+name+".png";
			FileHandler.copy(src, new File(destination));
			
			return destination;
		}


		@AfterTest
		public void endReport()
		{
			extent.flush();
			driver.quit();
		}
	}


