package ApolloStepDefinition;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ApolloDriverSetUp.DriverSetUp;
import ApolloPageFactory.BookLabPageFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookLabStepDefinition {
	static WebDriver driver;
	BookLabPageFactory run;

//------------------------------------Navigating to specific page--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: It is a mandatory step to navigate to the specific lab test page for all scenarios
	 */

	@Given("User is on the Apollo247 home page")
	public void user_is_on_the_apollo247_home_page() {
		driver = DriverSetUp.chromeDriver();
		//driver = DriverSetUp.edgeDriver();
		run = new BookLabPageFactory(driver);
	}

	@When("User navigates to the COVID-{int} RT-PCR test page")
	public void user_navigates_to_the_covid_rt_pcr_test_page(Integer int1) {
		run.rtPcrAtHome();
	}

	@When("User navigates to the lab-tests page")
	public void user_navigates_to_the_lab_tests_page() {
		run.BookLab();
	}

//------------------------------------Add COVID-19 RT-PCR Test to Cart--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: To check the Add to cart functionality
	 */

	@When("User clicks on the {string} option")
	public void user_clicks_on_the_option(String string) {
		run.addToCart();
	}

	@When("User clicks on {string}")
	public void user_clicks_on(String string) {
		run.proccedToCart();
	}

	@Then("User should see the test added to the cart")
	public void user_should_see_the_test_added_to_the_cart() {
		run.cancelLogin();
		run.cart();
		String msg = run.getmsg();
		Assert.assertTrue(msg.contains("(1 Item)"));
	}

//------------------------------------Search for specific Vaccine--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: To search for a specific vaccine
	 */

	@When("User clicks on the search bar")
	public void user_clicks_on_the_search_bar() {
		run.clickOnBar();
	}

	@When("^User enters (.*) into the search bar$")
	public void user_enters_search_query_into_the_search_bar(String search_query) {
		run.searchFor(search_query);
	}

	@When("User clicks on the search button")
	public void user_clicks_on_the_search_button() throws InterruptedException, AWTException {
		run.clickOnTest();
	}

	@Then("User should be directed to the relevant search results page")
	public void user_should_be_directed_to_the_relevant_search_results_page() {

		String thyroid = "https://www.apollo247.com/lab-tests/thyroid-profile-total-t3-t4-tsh?source=Partial_search";
		System.out.println(driver.getCurrentUrl());
		String url = run.url();
		Assert.assertEquals(url, thyroid);
		
	}

//------------------------------------Enter Location for COVID-19 RT-PCR Test--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: To specify the location for conducting the COVID-19 RT-PCR test
	 */

	@When("User clicks on the location bar")
	public void user_clicks_on_the_location_bar() {
		run.clickOnLocationBar();
	}

	@When("User enters the Location in the location field")
	public void user_enters_the_location_in_the_location_field(DataTable dataTable)
			throws AWTException, InterruptedException {
		List<String> locations = dataTable.asList(String.class);
		for (String location : locations) {
			if (location != null && !location.isEmpty()) {
				run.LocationData(location);
			}
		}
	}

	@When("User selects the suggested location")
	public void user_selects_the_suggested_location() throws AWTException, InterruptedException {
		run.enterTab();
	}

	@Then("User should see the selected location displayed in the location field")
	public void user_should_see_the_selected_location_displayed_in_the_location_field() throws InterruptedException {
		String msg = run.ConfirmLocation();
		Assert.assertTrue(msg.equals("Indore"));

	}

//------------------------------------Sort Full Body Checkup Tests by Price from Low to High--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: To sort Full Body Checkup Tests according to their price, arranging them from low to high
	 */

	@When("User clicks on the Full Body Checkup option")
	public void user_clicks_on_the_full_body_checkup_option() {
		run.clickOnFullBodyCheckup();
	}

	@When("User clicks on the {string} drop-down menu")
	public void user_clicks_on_the_drop_down_menu(String string) {
		run.clickOnSortBy();
	}

	@When("User selects {string}")
	public void user_selects(String string) {
		run.selectLowToHigh();
	}

	@Then("User should see the Full Body Checkup tests sorted by price from low to high")
	public void user_should_see_the_full_body_checkup_tests_sorted_by_price_from_low_to_high() {
		boolean price = run.result();
		Assert.assertEquals(true, price);
	}

//------------------------------------View RECOMMENDED BY Doctor Information--------------------------

	/*
	 * Created By: Yamini Bendale 
	 * Reviewed By: Akasha KC 
	 * Motive: To view information recommended by the doctor.
	 */

	@When("the User clicks on a doctor's name")
	public void the_user_clicks_on_a_doctor_s_name() {
		run.clickOnRecommended();
	}

	@Then("the User should be able to navigate to the particular doctor's information page")
	public void the_user_should_be_able_to_navigate_to_the_particular_doctor_s_information_page() {

		String msg = run.information();
		Assert.assertTrue(msg.contains(
				"Dr. Srikar Darisetty, a highly esteemed Pulmonologist and Respiratory Medicine specialist with 11 years of experience"));
	}

//--------------------------Taking screenshot for extent report----------------------------

	/*
	 * Created By: Yamini Bendale \
	 * Reviewed By: Akasha KC 
	 * Motive: It is mandatory step to taking screenshot for all scenarios
	 */

	@AfterStep
	public static void takeScreendown(Scenario scenario) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		final byte[] src = ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(src, "image/png", scenario.getName());
		
		//for failed condition
		
//		if (scenario.isFailed()) {
//			TakesScreenshot ts = (TakesScreenshot) driver;
//
//			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
//			scenario.attach(src, "image/png", "screenshot");
//		}

	}
}
