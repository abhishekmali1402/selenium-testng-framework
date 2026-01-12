package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver; 
	public Logger logger; 
	public Properties p; 
	
	@BeforeClass (groups={"Master", "Sanity", "Regression", "DataDriven"} )
	@Parameters({"br", "os"})
	public void setup(String br, String os) throws IOException {
		
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties"); 
		p=new Properties(); 
		p.load(file); 
		
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br) {
		case "chrome":this.driver= new ChromeDriver();break; 
		case "edge":this.driver= new EdgeDriver();break; 
		case "firefox":this.driver= new FirefoxDriver();break; 
		default: System.out.println("**invaild browser name**");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize(); 
	}
	
	@AfterClass (groups={"Master", "Sanity", "Regression", "DataDriven"} )
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString(){
		String randomString = RandomStringUtils.randomAlphabetic(3); 
		return randomString; 
	}
	public String randomNumber(){
		String randomNumber = RandomStringUtils.randomNumeric(5); 
		return randomNumber; 
	}
	public String randomAlphaNumeric(){
		String randomAlphaNumberic = RandomStringUtils.randomAlphanumeric(10); 
		return randomAlphaNumberic; 
	}
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath; 
	}
	
	
	
}
