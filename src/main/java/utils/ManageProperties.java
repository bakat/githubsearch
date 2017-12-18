package utils;

import java.util.Properties;

public class ManageProperties {

	private static final String PROPERTIES_FILE = 
			"config/app.properties";
	
	private String gitHubApiHost;
	
	private void loadProperties() {
		Properties testProperties = new Properties();

		try {
			testProperties.load(getClass().getClassLoader().getResourceAsStream(
					PROPERTIES_FILE));
			gitHubApiHost = testProperties.getProperty("GITHUB_API_HOST");
		} catch (Exception ex) {
			System.out.println("Could not load properties file. \r\n");
			ex.printStackTrace();
		}
	}
	
	public String getGitHubHost(){
		if(gitHubApiHost == null){
			loadProperties();
		}
		return gitHubApiHost; 
	}
}
