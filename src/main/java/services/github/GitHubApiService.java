package services.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import services.PojoMapper;
import services.ServiceHandler;
import services.ServiceMessage;
import utils.ManageProperties;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.releaseSearch.SearchReleaseResponse;
import domain.repositoriesSearch.SearchRepositoriesResponse;

public class GitHubApiService extends ServiceHandler {

	private HttpsURLConnection response;
	private GitHubApiServicesAvailable serviceName;
	private SearchParameters searchParameters;

	@Override
	protected String getUri() {
		ManageProperties properties = new ManageProperties();
		String host = properties.getGitHubHost();

		if (isASearchForLastestReleaseRequest(serviceName)) {
			return String.format("%srepos/%s/%s/releases/latest", host,
					searchParameters.getOwner(), searchParameters.getRepo());
		} else if (isASearchForRepositoriesRequest(serviceName)) {
			return String.format(
					"%ssearch/repositories?q=%s&sort=%s&order=%s&per_page=%d",
					host, searchParameters.getQuery(),
					searchParameters.getSort(), searchParameters.getOrder(),
					searchParameters.getNumberOfResults());
		} else if (isASearchForAReleaseTag(serviceName)) {
			return String.format("%srepos/%s/%s/releases/tags/%s", host,
					searchParameters.getOwner(), searchParameters.getRepo(),
					searchParameters.getTagName());
		}
		return null;
	}

	@Override
	public HttpsURLConnection getRawResponse() {
		return response;
	}
	
	public SearchRepositoriesResponse getSearchRepositoriesResponse(){
		StringBuilder responseBuilder = null;
		
		try {
			responseBuilder = readInputStream();

			return (SearchRepositoriesResponse) PojoMapper.fromJson(responseBuilder.toString(),
						SearchRepositoriesResponse.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public SearchReleaseResponse getSearchReleaseResponse(){
		StringBuilder responseBuilder = null;
		
		try {
			responseBuilder = readInputStream();

			return (SearchReleaseResponse) PojoMapper.fromJson(responseBuilder.toString(),
					SearchReleaseResponse.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private StringBuilder readInputStream() throws IOException {
		BufferedReader bufferedReader;
		StringBuilder responseBuilder;
		bufferedReader = new BufferedReader(new InputStreamReader(
				response.getInputStream()));
		responseBuilder = new StringBuilder();
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			responseBuilder.append(line);
		}
		return responseBuilder;
	}

	@Override
	public void setRequestMessage(ServiceMessage message) {
		serviceName = (GitHubApiServicesAvailable) message.getServiceName();
		searchParameters = (SearchParameters) message.getMessagesURIParams();
	}

	@Override
	public void sendRequest() throws JsonMappingException,
			JsonGenerationException, IOException {
		response = performGetRequest(getUri(), null);
	}

	private boolean isASearchForRepositoriesRequest(
			GitHubApiServicesAvailable servicesAvailable) {
		return servicesAvailable
				.equals(GitHubApiServicesAvailable.SEARCH_REPOSITORIES);
	}

	private boolean isASearchForLastestReleaseRequest(
			GitHubApiServicesAvailable servicesAvailable) {
		return servicesAvailable
				.equals(GitHubApiServicesAvailable.GET_LATEST_RELEASE);
	}

	private boolean isASearchForAReleaseTag(
			GitHubApiServicesAvailable servicesAvailable) {
		return servicesAvailable
				.equals(GitHubApiServicesAvailable.GET_RELEASE_BY_TAG_NAME);
	}

	public enum GitHubApiServicesAvailable {
		SEARCH_REPOSITORIES, GET_LATEST_RELEASE, GET_RELEASE_BY_TAG_NAME
	}

}
