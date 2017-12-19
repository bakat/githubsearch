package services.github;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.repositoriesSearch.Items;
import domain.repositoriesSearch.SearchRepositoriesResponse;

public class ProcessSearchResults {

	private GitHubApiSearch search;

	public ProcessSearchResults(GitHubApiSearch search) {
		this.search = search;
	}
	
	public String process(String repositoryName, String tagName)
			throws JsonMappingException, JsonGenerationException, IOException {
		if(!parametersAreValid(repositoryName, tagName)){
			return "* Please provide valid parameters for the repository search.";
		}
		
		GitHubApiService service = null;
		StringBuilder results = new StringBuilder();
		
		service = search.getRepositoryWithHighestNumberOfStars(repositoryName);
		
		SearchRepositoriesResponse repositoriesResponse = service.getSearchRepositoriesResponse();
		
		if(repositoriesResponse.getItems().size() > 0){
			Items firstRepo = repositoriesResponse.getItems().iterator().next();
			
			buildStrinResults(results,"* Repository containing the highest number of stars:");
			buildStrinResults(results,"  - Name: " + firstRepo.getName());
			buildStrinResults(results,"  - Number of Stars: " + firstRepo.getStargazersCount());
			
			service = search.getLatestReleaseForARepository(firstRepo.getOwner().getLogin(), repositoryName);
			
			if(service.getRawResponse().getResponseCode() == HttpsURLConnection.HTTP_OK){
				if((service.getSearchReleaseResponse()).getTagName().equals(tagName)){
					buildStrinResults(results,"* The tag name informed is related to the latest release.");
				} else{
					service = search.getReleaseByTagName(firstRepo.getOwner().getLogin(), repositoryName, tagName);
					
					if(service.getRawResponse().getResponseCode() == HttpsURLConnection.HTTP_OK){
						if((service.getSearchReleaseResponse()).getTagName().equals(tagName)){
							buildStrinResults(results,"* The tag name informed refers to an older release.");
						}
					} else{
						buildStrinResults(results,"* The tag name informed is not related to any release.");
					}
				}
			} else{
				buildStrinResults(results,"* This repository doesn't have a release published.");
			}
		} else{
			buildStrinResults(results,"* The search did not return any results.");
		}
		
		return results.toString();
	}
	
	private Boolean parametersAreValid(String repositoryName, String tagName) {
		String repo = repositoryName;
		String tag = tagName;
		
		return repo.replace(" ", "").length() > 0 && tag.replace(" ", "").length() > 0;
	}

	private void buildStrinResults(StringBuilder results, String newResult){
		results.append(newResult);
		results.append(System.getProperty("line.separator"));
	}
}
