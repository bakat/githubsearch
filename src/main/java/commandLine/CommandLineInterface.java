package commandLine;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import services.github.GitHubApiSearch;
import services.github.ProcessSearchResults;

public class CommandLineInterface {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws JsonMappingException, JsonGenerationException, IOException {
		String section = "=========================================================";
		Scanner scanner = new Scanner(System.in);

		System.out.println("");
		System.out.println(section);
		System.out.println("[SEARCH INPUT]");
		System.out.println(section);
		System.out.println("- Enter the repository name: ");
		String repositoryName = scanner.next();
		System.out.println("- Enter the release tag: ");
		String tagName = scanner.next();
		
		System.out.println("");
		System.out.println(section);
		System.out.println("[SEARCH RESULTS]");
		System.out.println(section);
		ProcessSearchResults results = new ProcessSearchResults(new GitHubApiSearch());
		System.out.println(results.process(repositoryName, tagName));
	}
}
