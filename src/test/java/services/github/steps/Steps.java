package services.github.steps;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import services.github.GitHubApiSearch;
import services.github.GitHubApiService;
import services.github.ProcessSearchResults;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.repositoriesSearch.Items;
import domain.repositoriesSearch.SearchRepositoriesResponse;

public class Steps {

	private GitHubApiSearch search;
	private ProcessSearchResults searchResults;
	private String result;
	private String repo;
	private String tagName;

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

	public Steps(GitHubApiSearch search) {
		this.search = search;
	}

	@Given("^I search for repositories with the name \"([^\"]*)\" and a tag name \"([^\"]*)\"$")
	public void i_search_for_repositories_with_the_name_and_a_tag_name(
			String repo, String tagName) throws Throwable {
		this.repo = repo;
		this.tagName = tagName;

		if ((repo.replace(" ", "").length() > 0)
				&& (tagName.replace(" ", "").length() > 0)) {
			searchResults = new ProcessSearchResults(gitHubApiSeachMock);
		} else {
			searchResults = new ProcessSearchResults(search);
		}
	}
//
//	@When("^I submit the search$")
//	public void i_submit_the_search() throws Throwable {
//		if (repo.equals("mockNonexistentRepo")) {
//			SearchRepositoriesResponse response = new SearchRepositoriesResponse();
//			response.setItems(new ArrayList<Items>());
//
//			when(gitHubApiService.getParsedResponse()).thenReturn(response);
//			when(
//					gitHubApiSeachMock
//							.getRepositoryWithHighestNumberOfStars(any(String.class)))
//					.thenReturn(gitHubApiService);
//		}
//		if (repo.equals("mockUnpublishedRepo")) {
//			Items items = new Items();
//			items.setName(repo);
//			items.setStargazersCount(1);
//
//			List<Items> itemsList = new ArrayList<Items>();
//			itemsList.add(items);
//
//			SearchRepositoriesResponse response = new SearchRepositoriesResponse();
//			response.setItems(itemsList);
//
//			when(gitHubApiService.getParsedResponse()).thenReturn(response);
//			when(
//					gitHubApiSeachMock
//							.getRepositoryWithHighestNumberOfStars(any(String.class)))
//					.thenReturn(gitHubApiService);
//			when(httpUrlConnection.getResponseCode()).thenReturn(
//					HttpsURLConnection.HTTP_NOT_FOUND);
//			when(gitHubApiService.getRawResponse()).thenReturn(
//					httpUrlConnection);
//		}
//		
//		result = searchResults.process(repo, tagName);
//	}

	@Then("^the system should respond with the message \"([^\"]*)\"$")
	public void the_system_should_respond_with_the_message(String message)
			throws Throwable {
		Assert.assertTrue(result.contains(message));
	}
}