import java.io.IOException;

import services.github.GitHubApiSearch;
import services.github.ProcessSearchResults;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class Testing {

	public static void main(String[] args) throws JsonMappingException, JsonGenerationException, IOException {
		//Parte 1
		String repositoryName = "bonfire";
		String tagName = "v0.0.1";
		
		ProcessSearchResults results = new ProcessSearchResults(new GitHubApiSearch());
		System.out.println(results.process(repositoryName, tagName));
	}
}