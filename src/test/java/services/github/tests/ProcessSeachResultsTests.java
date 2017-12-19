package services.github.tests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import services.github.GitHubApiSearch;
import services.github.GitHubApiService;
import services.github.ProcessSearchResults;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.releaseSearch.SearchReleaseResponse;
import domain.repositoriesSearch.Items;
import domain.repositoriesSearch.Owner;
import domain.repositoriesSearch.SearchRepositoriesResponse;

public class ProcessSeachResultsTests {

	@Mock
	private GitHubApiSearch gitHubApiSeachMock;

	@Mock
	private GitHubApiService gitHubApiService;
	
	@Mock
	private HttpsURLConnection httpUrlConnection;

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCheckForInvalidParameters() throws JsonMappingException,
			JsonGenerationException, IOException {
		ProcessSearchResults results = new ProcessSearchResults(
				new GitHubApiSearch());
		Assert.assertEquals(results.process("", ""),
				"* Please provide valid parameters for the repository search.");
	}

	@Test
	public void shouldCheckForRepositoriesExistence()
			throws JsonMappingException, JsonGenerationException, IOException {
		ProcessSearchResults results = new ProcessSearchResults(
				gitHubApiSeachMock);

		SearchRepositoriesResponse response = new SearchRepositoriesResponse();
		response.setItems(new ArrayList<Items>());

		when(gitHubApiService.getSearchRepositoriesResponse()).thenReturn(response);
		when(
				gitHubApiSeachMock
						.getRepositoryWithHighestNumberOfStars(any(String.class)))
				.thenReturn(gitHubApiService);
		
		Assert.assertTrue(results.process("mockTest", "v1.0.0").contains(
				"* The search did not return any results."));
	}
	
	@Test
	public void shouldCheckForUnpublishedRepository()
			throws JsonMappingException, JsonGenerationException, IOException {
		ProcessSearchResults results = new ProcessSearchResults(
				gitHubApiSeachMock);
		Owner owner = new Owner();
		owner.setLogin("mockOwner");
		
		Items items = new Items();
		items.setName("mockTest");
		items.setOwner(owner);
		items.setStargazersCount(1);

		List<Items> itemsList = new ArrayList<Items>();
		itemsList.add(items);

		SearchRepositoriesResponse response = new SearchRepositoriesResponse();
		response.setItems(itemsList);

		when(gitHubApiService.getSearchRepositoriesResponse()).thenReturn(response);
		when(
				gitHubApiSeachMock
						.getRepositoryWithHighestNumberOfStars(any(String.class)))
				.thenReturn(gitHubApiService);
		
		when(httpUrlConnection.getResponseCode()).thenReturn(
				HttpsURLConnection.HTTP_NOT_FOUND);
		when(gitHubApiService.getRawResponse()).thenReturn(
				httpUrlConnection);
		when(
				gitHubApiSeachMock
						.getLatestReleaseForARepository(any(String.class), any(String.class)))
				.thenReturn(gitHubApiService);
		
		Assert.assertTrue(results.process("mockTest", "v1.0.0").contains(
				"* This repository doesn't have a release published."));
	}
	
	@Test
	public void shouldCheckForLatestReleaseFromTag()
			throws JsonMappingException, JsonGenerationException, IOException {
		ProcessSearchResults results = new ProcessSearchResults(
				gitHubApiSeachMock);
		Owner owner = new Owner();
		owner.setLogin("mockOwner");
		
		Items items = new Items();
		items.setName("mockTest");
		items.setOwner(owner);
		items.setStargazersCount(1);

		List<Items> itemsList = new ArrayList<Items>();
		itemsList.add(items);

		SearchRepositoriesResponse repositoriesResponse = new SearchRepositoriesResponse();
		repositoriesResponse.setItems(itemsList);

		when(gitHubApiService.getSearchRepositoriesResponse()).thenReturn(repositoriesResponse);
		when(
				gitHubApiSeachMock
						.getRepositoryWithHighestNumberOfStars(any(String.class)))
				.thenReturn(gitHubApiService);
		
		when(httpUrlConnection.getResponseCode()).thenReturn(
				HttpsURLConnection.HTTP_OK);
		when(gitHubApiService.getRawResponse()).thenReturn(
				httpUrlConnection);
		when(
				gitHubApiSeachMock
						.getLatestReleaseForARepository(any(String.class), any(String.class)))
				.thenReturn(gitHubApiService);
		
		SearchReleaseResponse releaseResponse = new SearchReleaseResponse();
		releaseResponse.setTagName("v1.0.0");
		
		when(gitHubApiService.getSearchReleaseResponse()).thenReturn(releaseResponse);
		
		Assert.assertTrue(results.process("mockTest", "v1.0.0").contains(
				"* The tag name informed is related to the latest release."));
	}
	
//	@Test
//	public void shouldCheckForOlderReleaseFromTag()
//			throws JsonMappingException, JsonGenerationException, IOException {
//		ProcessSearchResults results = new ProcessSearchResults(
//				gitHubApiSeachMock);
//		Owner owner = new Owner();
//		owner.setLogin("mockOwner");
//		
//		Items items = new Items();
//		items.setName("mockTest");
//		items.setOwner(owner);
//		items.setStargazersCount(1);
//
//		List<Items> itemsList = new ArrayList<Items>();
//		itemsList.add(items);
//
//		SearchRepositoriesResponse repositoriesResponse = new SearchRepositoriesResponse();
//		repositoriesResponse.setItems(itemsList);
//
//		when(gitHubApiService.getSearchRepositoriesResponse()).thenReturn(repositoriesResponse);
//		when(
//				gitHubApiSeachMock
//						.getRepositoryWithHighestNumberOfStars(any(String.class)))
//				.thenReturn(gitHubApiService);
//		
//		when(httpUrlConnection.getResponseCode()).thenReturn(
//				HttpsURLConnection.HTTP_OK);
//		when(gitHubApiService.getRawResponse()).thenReturn(
//				httpUrlConnection);
//		when(
//				gitHubApiSeachMock
//						.getLatestReleaseForARepository(any(String.class), any(String.class)))
//				.thenReturn(gitHubApiService);
//		
//		SearchReleaseResponse releaseResponse = new SearchReleaseResponse();
//		releaseResponse.setTagName("v1.0.0");
//		
//		when(gitHubApiService.getSearchReleaseResponse()).thenReturn(releaseResponse);
//		
//		Assert.assertTrue(results.process("mockTest", "v1.0.0").contains(
//				"* The tag name informed refers to an older release."));
//	}

}
