# GLOBOFORCE - Technical assessment

Author: Bernardo Vieira Carneiro

### Description

The project ***githubsearch*** contains a simple application that communicates with the [GITHUB API] (https://developer.github.com/) and searches for information on it's repositories.

Internally you can find the project organized as follows:
- `src/main/java`: contains the classes that were built to communicate with the Github Api;
- `src/main/resources`: contains a properties file within which the Github's Api host is configured;
- `src/test/java`: contains a class with some unit tests

### How to run the tests

- Simply run the class ProcessSeachResultsTests.java.

### How to run the application

- From a command line tool, access the project "githubsearch" and then execute the following command: ***mvn clean compile exec:java -Dexec.mainClass="commandLine.CommandLineInterface"***
