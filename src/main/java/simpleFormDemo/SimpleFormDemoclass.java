package simpleFormDemo;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleFormDemoclass {

	private RemoteWebDriver driver;
	private String Status = "passed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "DhvanilJ" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "LT_u5clgIoT4DsZUFjotyPoxXBy4waMxmIvqw1pDnnMlBALoWE"
                : System.getenv("LT_ACCESS_KEY");
        String hub = "@hub.lambdatest.com/wd/hub";

//		DesiredCapabilities caps = new DesiredCapabilities();
//		// Configure your capabilities here
//		caps.setCapability("platform", "Windows 10");
//		caps.setCapability("browserName", "Chrome");
//		caps.setCapability("version", "103.0");
//		caps.setCapability("resolution", "1024x768");
//		caps.setCapability("build", "TestNG With Java");
//		caps.setCapability("name", m.getName() + this.getClass().getName());
//		caps.setCapability("plugin", "git-testng");
//
//		String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
//		caps.setCapability("tags", Tags);
//
//		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("103.0");

        // LambdaTest-specific options
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", authkey);
        ltOptions.put("resolution", "1024x768");
        ltOptions.put("build", "TestNG With Java");
        ltOptions.put("name", m.getName() + this.getClass().getName());
        ltOptions.put("plugin", "git-testng");
        ltOptions.put("tags", new String[]{"Feature", "Magicleap", "Severe"});

        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
    }

    @Test

    public void TestScenario1() throws InterruptedException {

        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement SimpleFormDemoLink = driver
                .findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo']"));
        SimpleFormDemoLink.click();

        String Expectedurl = driver.getCurrentUrl();
        String Actualurl = "simple-form-demo";

        if (Expectedurl.contains(Actualurl)) {
            System.out.println("URL matched");
        } else {
            System.out.println("URL does not matched!");
        }

        String message = "Welcome to LambdaTest.";
        WebElement mess_send = driver
                .findElement(By.xpath("//div/input[@class='border border-gray-550 w-full h-35 rounded px-10']"));
        Thread.sleep(1000);
        mess_send.sendKeys(message);

        Thread.sleep(1000);
        WebElement button = driver.findElement(By.id("showInput"));
        button.click();

        WebElement your_mess = driver.findElement(By.id("message"));
        String print_mess = your_mess.getText();

        if (print_mess.contains(message)) {
            System.out.println("Message is matched");
        } else {
            System.out.println("Message is not matched!");
        }

    }

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}

