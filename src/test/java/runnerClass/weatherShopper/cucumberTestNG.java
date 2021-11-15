package runnerClass.weatherShopper;

import extendedReporter.cucumberHTMLReporter;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        plugin = {"pretty", "json:output/cucumber/cucumber.json", "html:output/cucumber/cucumber.html"},
        tags = "@sample",
        features = "src/test/resources/featureFile",
        glue = {"stepDefinition", "Hooks"})



public class cucumberTestNG extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public static void runAfterAllExecution(){
        System.out.println("Generating Cucumber Extended Reports");
        cucumberHTMLReporter.main(null);
    }

}

