import java.io.IOException;

import services.ServiceMessage;
import services.github.GitHubApiService;
import services.github.GitHubApiService.GitHubApiServicesAvailable;
import services.github.SearchParameters;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.repositoriesSearch.SearchRepositoriesResponse;


public class Testing {

	public static void main(String[] args) throws JsonMappingException, JsonGenerationException, IOException {
		SearchParameters parameters = new SearchParameters("TestNG", "stars", "desc", null, null);
		ServiceMessage message = new ServiceMessage(parameters, GitHubApiServicesAvailable.SEARCH_REPOSITORIES);
		
		GitHubApiService service = new GitHubApiService();
		service.setRequestMessage(message);
		service.sendRequest();
		System.out.println(service.getRawResponse().getResponseCode());
		SearchRepositoriesResponse repositoriesResponse = (SearchRepositoriesResponse) service.getParsedResponse();
		System.out.println(repositoriesResponse.getItems().iterator().next().getName());
	}
}
