package services.github.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = {"html:target/test-report"}, 
features = { "src/test/resources/services/github/features/ProcessSearchResults.feature"}, 
glue = {"services.github"},
tags = {})
public class Test extends AbstractTestNGCucumberTests{

}