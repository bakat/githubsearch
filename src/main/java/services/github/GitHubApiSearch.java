package services.github;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import services.ServiceMessage;
import services.github.GitHubApiService.GitHubApiServicesAvailable;

public class GitHubApiSearch {

	public GitHubApiService getRepositoryWithHighestNumberOfStars(
			String repoName) throws JsonMappingException, JsonGenerationException, IOException{
		GitHubApiService service = searchWithParameters(new SearchParameters(repoName, "stars", "desc", 1), GitHubApiServicesAvailable.SEARCH_REPOSITORIES);
		
		return service;
	}

	private GitHubApiService searchWithParameters(SearchParameters searchParameters, GitHubApiServicesAvailable gitHubApiServicesAvailable)
			throws JsonMappingException, JsonGenerationException, IOException {
		ServiceMessage message = new ServiceMessage(searchParameters, gitHubApiServicesAvailable);
		
		GitHubApiService service = new GitHubApiService();
		service.setRequestMessage(message);
		service.sendRequest();
		return service;
	}
	
	public GitHubApiService getLatestReleaseForARepository(String owner, String repo) throws JsonMappingException, JsonGenerationException, IOException{
		GitHubApiService service = searchWithParameters(new SearchParameters(owner, repo), GitHubApiServicesAvailable.GET_LATEST_RELEASE);
		return service;
	}
	
	public GitHubApiService getReleaseByTagName(String owner, String repo, String tagName) throws JsonMappingException, JsonGenerationException, IOException{
		GitHubApiService service = searchWithParameters(new SearchParameters(owner, repo, tagName), GitHubApiServicesAvailable.GET_RELEASE_BY_TAG_NAME);
		return service;
	}
}