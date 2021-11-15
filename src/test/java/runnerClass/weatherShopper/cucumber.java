package runnerClass.weatherShopper;

import extendedReporter.cucumberHTMLReporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:output/cucumber/cucumber.json", "html:output/cucumber/cucumber.html"},
        tags = "@WSsanity",
        features = "src/test/resources/featureFile/weatherShopper",
        glue = {"stepDefinition", "Hooks"})

public class cucumber {

    @AfterClass
    public static void runAfterAllExecution(){
        System.out.println("Generating Cucumber Extended Reports");
        cucumberHTMLReporter.main(null);
    }

}

