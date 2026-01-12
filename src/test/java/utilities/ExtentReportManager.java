package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

// We implement the ITestListener interface so this class listens to test events (start, success, failure, etc.)
public class ExtentReportManager implements ITestListener {

    // We create an object for ExtentSparkReporter, which handles the UI/HTML generation of the Extent report file.
    public ExtentSparkReporter sparkReporter;
    
    // We create an object for ExtentReports, the main engine used to manage report data and attach reporters.
    public ExtentReports extent;
    
    // We create an object for ExtentTest, representing a single test case within the Extent report file.
    public ExtentTest test;

    // A string variable to store the generated report name so it can be accessed globally in this class.
    String repName;

    public void onStart(ITestContext testContext) {

        // We create a SimpleDateFormat object and format the current Date to generate a timestamp.
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
        // We dynamically create an Extent report file name using the timestamp to ensure uniqueness.
        repName = "Test-Report-" + timeStamp + ".html";
        
        // We initialize the SparkReporter with the specific file path where the Extent report file will be saved.
        sparkReporter = new ExtentSparkReporter("./reports/" + repName);

        // We set the title that appears in the browser tab for the Extent report file.
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        
        // We set the name of the report displayed in the header of the Extent report file.
        sparkReporter.config().setReportName("Opencart Functional Testing");
        
        // We set the visual theme of the Extent report file to DARK.
        sparkReporter.config().setTheme(Theme.DARK);

        // We initialize the main ExtentReports engine.
        extent = new ExtentReports();
        
        // We attach the SparkReporter configuration to the main Extent engine.
        extent.attachReporter(sparkReporter);

        // We add system information (Application name) to the Extent report file summary.
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        
        // We capture and display the system user name of the person running the test in the Extent report file.
        extent.setSystemInfo("Tester/User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // We capture the "os" parameter from the XML file to show which OS was used.
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        // We capture the "br" parameter from the XML file.
        String br = testContext.getCurrentXmlTest().getParameter("br");
        extent.setSystemInfo("Browser", br);

        // We check for any included groups in the XML and list them in the Extent report file if they exist.
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }

    }

    public void onTestSuccess(ITestResult result) {

        // We create a new test entry in the Extent report file using the class name.
        test = extent.createTest(result.getTestClass().getName());
        
        // We assign the test category based on the groups defined in the test method.
        test.assignCategory(result.getMethod().getGroups());
        
        // We log a generic PASS status message in the Extent report file.
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        // We create a new test entry for the failed test in the Extent report file.
        test = extent.createTest(result.getTestClass().getName());
        
        // We assign the category to the failed test.
        test.assignCategory(result.getMethod().getGroups());

        // We log the FAIL status and the test name in the Extent report file.
        test.log(Status.FAIL, result.getName() + " got failed");
        
        // We log the exception message to understand why the test failed.
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            // We call the captureScreen method from BaseClass to take a screenshot. We can store this action in as String bcz the method captureScreen() returns the path of the img file  
            String imgPath = new BaseClass().captureScreen(result.getName()); //"result.getName()" will be the name of the screenshot img
            
            // We attach the captured screenshot file to the Extent report file entry so the developer can see the failure visually.
            test.addScreenCaptureFromPath(imgPath); //imgPath is a String returned by captureScreen() method in BaseClass. 

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        // We create a new test entry for the skipped test in the Extent report file.
        test = extent.createTest(result.getTestClass().getName());
        
        // We assign the category to the skipped test.
        test.assignCategory(result.getMethod().getGroups());

        // We log the SKIP status in the Extent report file.
        test.log(Status.SKIP, result.getName()+" got skipped");
        
        // We log the reason for skipping in the Extent report file.
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        // We write all collected logs and info to the actual Extent report file.
        extent.flush();

        // We define the full path to the generated Extent report file.
        String pathOfExtentReport =  System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            // We automatically open the Extent report file in the desktop browser.
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     /*
        try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465); 

            email.setAuthenticator(new DefaultAuthenticator("demoqa769@gmail.com", "rlnryhymlddkgbad"));
            email.setSSLOnConnect(true);
            email.setFrom("demoqa769@gmail.com"); //Sender
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report...");
            email.addTo("abhishek.mali1402@gmail.com"); //Receiver
            email.attach(url, "extent report", "please check report...");
            email.send(); // send the email

        } catch(Exception e)
        {
            e.printStackTrace();
        }         
         	*/ 
    }
}