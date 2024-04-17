package ApolloDriverSetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetUp {
	public static WebDriver driver;
	public static ChromeOptions chromeOption;
	public static EdgeOptions edgeOption;

	public static WebDriver chromeDriver() {
		WebDriverManager.chromedriver().setup();
		chromeOption = new ChromeOptions();
		chromeOption.addArguments("--start-maximized");
		chromeOption.addArguments("disable-notifications");
		driver = new ChromeDriver(chromeOption);
		driver.get("https://www.apollo247.com/");
		return driver;
	}

	public static WebDriver edgeDriver() {
		WebDriverManager.edgedriver().setup();
		edgeOption = new EdgeOptions();
		edgeOption.addArguments("--start-maximized");
		edgeOption.addArguments("disable-notifications");
		driver = new EdgeDriver(edgeOption);
		driver.get("https://www.apollo247.com/");
		return driver;
	}

	public static void teardown() {
		driver.close();
	}
}
