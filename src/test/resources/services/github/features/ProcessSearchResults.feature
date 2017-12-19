Feature: Github search processing

@NegativeScenario
Scenario: Search for a repository with invalid parameters
	Given I search for repositories with the name "" and a tag name ""
	When I submit the search
	Then the system should respond with the message "* Please provide valid parameters for the repository search."
	
@PositiveScenario
Scenario: Search for a repository that doesn't exist
	Given I search for repositories with the name "mockNonexistentRepo" and a tag name "v1.0.0"
	When I submit the search
	Then the system should respond with the message "* The search did not return any results."
	
@PositiveScenario
Scenario: Search for a repository that doesn't have a published release
	Given I search for repositories with the name "mockUnpublishedRepo" and a tag name "v1.0.0"
	When I submit the search
	Then the system should respond with the message "* This repository doesn't have a release published."