package services.github;

public class SearchParameters {

	private String query;
	private String sort;
	private String order;
	private String owner;
	private String repo;
	private String tagName;
	private int numberOfResults;
	
	public SearchParameters(String query, String sort, String order, int numberOfResults) {
		this.query = query;
		this.sort = sort;
		this.order = order;
		this.numberOfResults = numberOfResults;
	}
	
	public SearchParameters(String owner, String repo) {
		this.owner = owner;
		this.repo = repo;
	}
	
	public SearchParameters(String owner, String repo, String tagName) {
		this.owner = owner;
		this.repo = repo;
		this.tagName = tagName;
	}
	
	public String getQuery() {
		return query;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public String getOwner() {
		return owner;
	}
	public String getRepo() {
		return repo;
	}
	public String getTagName() {
		return tagName;
	}
	public int getNumberOfResults() {
		return numberOfResults;
	}
}