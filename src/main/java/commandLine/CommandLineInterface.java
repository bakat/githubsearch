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
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the repository name: ");
		String repositoryName = scanner.next();
		System.out.println("Enter the release tag: ");
		String tagName = scanner.next();
		
		ProcessSearchResults results = new ProcessSearchResults(new GitHubApiSearch());
		System.out.println(results.process(repositoryName, tagName));
	}
}
