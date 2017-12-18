package services.github;

public class SearchParameters {

	private String query;
	private String sort;
	private String order;
	private String owner;
	private String repo;
	
	public SearchParameters(String query, String sort, String order,
			String owner, String repo) {
		this.query = query;
		this.sort = sort;
		this.order = order;
		this.owner = owner;
		this.repo = repo;
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
	
}