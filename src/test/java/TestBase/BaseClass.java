package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	public Logger log;
	public Properties p;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException{
		
		
		log = LogManager.getLogger(this.getClass()); //passing the current testcase
		
		FileInputStream file= new FileInputStream(".//src/test/resources/config.properties");
		p= new Properties();
		p.load(file);
		
		// launching browser Remotely-------------------------------->>>>>>>>>
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// platform ( windows / linux / mac )
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS --------->>>>>");
				return;
			}
			
			// browser
			switch(br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:System.out.println("no Match Found");
			}
			
			String nodeURL = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
			
		}
		
		// launching browser locally-------------------------------->>>>>>>>>
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver= new EdgeDriver();
				break;
			default:System.out.println("no Match Found");
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get(p.getProperty("openURL"));
		
	}
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".jpg";
		File targetFile=new File(targetFilePath);
		
		FileUtils.copyFile(sourceFile, targetFile);
			
		return targetFilePath;

	}

}
