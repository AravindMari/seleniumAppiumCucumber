package runnerClass.sampleNativeApp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:output/cucumber/cucumber.json", "html:output/cucumber/cucumber.html"},
        tags = "@device1",
        features = "src/test/resources/featureFile",
        glue = {"stepDefinition", "Hooks"})

public class cucumberRunner {

}

