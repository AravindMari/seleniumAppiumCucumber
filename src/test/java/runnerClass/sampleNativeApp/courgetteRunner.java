package runnerClass.sampleNativeApp;

import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = false,
        rerunAttempts = 1,
        showTestOutput = true,
        reportTargetDir = "output",
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/featureFile",
                glue = { "stepDefinition", "Hooks"},
                tags = {"@device1"},
                plugin = {
                        "pretty",
                        "json:output/cucumber/cucumber.json",
                        "html:output/cucumber/cucumber.html"}
        ))
public class courgetteRunner {
}


