package ApolloRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "resource\\Features", glue = "ApolloStepDefinition",
tags = "@BookLab",
		plugin = { "pretty", "html:target/HTML Reports/BookLabCucumberReport.html",
				"json:target/JSON Reports/BookLabJsonReport.json", "junit:target/JUNIT Reports/BookLabJunitReport.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestRunner extends AbstractTestNGCucumberTests {

}
