package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/report.html"}, tags = "@ao",
                    features="src/test/features/", glue = "step_definitions.ao")
public class AOCucumberTest {
}