package ApolloPageFactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookLabPageFactory {
	static WebDriver driver;
	String parentWindowHandle;
	WebDriverWait wait;
	JavascriptExecutor js;
	Robot robot;

//------------------------------------Some common methods--------------------------

	public BookLabPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Driver Method");
	}

	public void windowHandle() {
		parentWindowHandle = driver.getWindowHandle();

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

//------------------------------------Navigating to specific page--------------------------

	@FindBy(xpath = "//a[contains(@href, 'covid-19-rt-pcr-with-home-collection') and @target='_blank']")
	WebElement rtPcrAtHome;

	public void rtPcrAtHome() {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", rtPcrAtHome);
		rtPcrAtHome.click();
		System.out.println("rtPcrAtHome Method");
	}

//------------------------------------Add COVID-19 RT-PCR Test to Cart--------------------------

	@FindBy(xpath = "//span[text()=\"Add To Cart\"]")
	WebElement addToCart;

	@FindBy(xpath = "//span[text()=\"Proceed to cart\"]")
	WebElement proceedToCart;

	@FindBy(xpath = "//span[@aria-label=\"close button\"]")
	WebElement cancelLogin;

	@FindBy(className = "icon-ic_cart")
	WebElement cart;

	@FindBy(xpath = "//span[text()=\"(1 Item)\"]")
	WebElement getmsg;

	public void addToCart() {

		windowHandle();
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
		addToCart.click();
		System.out.println("AddToCart Method");
	}

	public void proccedToCart() {
		proceedToCart.click();
		System.out.println("ProccedToCart Method");
	}

	public void cancelLogin() {
		cancelLogin.click();
		System.out.println("cancelLogin Method");
	}

	public void cart() {
		cart.click();
		System.out.println("Cart Method");
	}

	public String getmsg() {
		String msg = getmsg.getText();
		System.out.println("getmsg Method");
		return msg;
	}

//------------------------------------Search for specific Vaccine--------------------------

	@FindBy(css = "input[placeholder='Search for lab tests']")
	WebElement searchBar;

	@FindBy(xpath = "//h6[text()=\"Thyroid Profile (Total T3, Total T4, TSH)\"]")
	WebElement thyroid;

	public void clickOnBar() {
		windowHandle();
		searchBar.click();
		System.out.println("clickOnBar Method");
	}

	public void searchFor(String search_query) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(searchBar));
		searchBar.sendKeys(search_query);
		System.out.println("SearchFor Method");
	}

	public void clickOnTest() throws InterruptedException, AWTException {

		robot = new Robot();
		int x = 350;
		int y = 250;
		robot.mouseMove(x, y); // location x & y login
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		System.out.println("clickOnTest Method  1");
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		System.out.println("clickOnTest Method  2");

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(thyroid)).click();
	}
	public String url() {
		System.out.println(driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

//------------------------------------Enter Location for COVID-19 RT-PCR Test--------------------------

	@FindBy(className = "TestsPincodeSearch_selectedLocation__DbGmF")
	WebElement location1;

	@FindBy(xpath = "//input[@placeholder=\"Search for street name, pincode…\"]")
	WebElement enterTab;

	@FindBy(xpath = "//div[text()=\"Indore\"]")
	WebElement indore;

	public void clickOnLocationBar() {
		windowHandle();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(location1)).click();;
		//location1.click();
		System.out.println("clickOnLocationBar Method");
	}

	public void LocationData(String location) throws InterruptedException, AWTException {

		enterTab.click();
		enterTab.clear();
		enterTab.sendKeys(location);
		System.out.println("LocationData Method");
	}

	public void enterTab() throws AWTException, InterruptedException {

		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("Entertab Method");
	}

	public String ConfirmLocation() throws InterruptedException {
	
		Thread.sleep(1000);
		String msg = indore.getText();
		System.out.println(msg);
		return msg;
	}

//------------------------------------Sort Full Body Checkup Tests by Price from Low to High--------------------------

	@FindBy(xpath = "//a[@title=\"Book Lab Tests at Home\"]")
	WebElement BookLab;

	@FindBy(xpath = "//p[text()=\"Full Body Checkup\"]")
	WebElement FullBody;

	@FindBy(id = "headlessui-listbox-button-1")
	WebElement SortBy;

	@FindBy(xpath = "//span[text()=\"Price: Low to High\"]")
	WebElement LowToHigh;

	@FindBy(xpath = "//span[text()= \"499\"]")
	WebElement firstPrice;

	@FindBy(xpath = "//span[text()= \"1499\"]")
	WebElement secondPrice;

	public void BookLab() {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", BookLab);
		BookLab.click();
		System.out.println("BookLab Method");
	}

	public void clickOnFullBodyCheckup() {
		windowHandle();
		FullBody.click();
		System.out.println("clickOnFullBodyCheckup Method");
	}

	public void clickOnSortBy() {
		SortBy.click();
		System.out.println("clickOnSortBy Method");
	}

	public void selectLowToHigh() {
		LowToHigh.click();
		System.out.println("selectLowToHigh Method");
	}

	public boolean result() {

		boolean flag = false;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(firstPrice));
		wait.until(ExpectedConditions.visibilityOf(secondPrice));
		String firstValue = firstPrice.getText();
		String secondValue = secondPrice.getText();
		System.out.println(firstValue + "	price values	" + secondValue);

		double first = Double.parseDouble(firstValue.replace("₹", ""));
		double second = Double.parseDouble(secondValue.replace("₹", ""));

		System.out.println(flag);
		if (first < second) {
			flag = true;
		} else {
			flag = false;
		}

		System.out.println("result Method");
		System.out.println(flag);
		return flag;
	}

//------------------------------------View RECOMMENDED BY Doctor Information--------------------------

	@FindBy(xpath = "//span[text()=\"RECOMMENDED BY:\"]")
	WebElement doctore;

	@FindBy(xpath = "//p[contains(text(), \"Dr. Srikar Darisetty, a highly esteemed Pulmonologist and Respiratory Medicine specialist with 11 years of experience\")]")
	WebElement info;

	public void clickOnRecommended() {
		windowHandle();
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1200)");
		doctore.click();
		System.out.println("clickOnRecommended Method");
	}

	public String information() {

		js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,200)");
		System.out.println(info.getText());
		String msg = info.getText();
		System.out.println(msg);
		return msg;
	}

}
