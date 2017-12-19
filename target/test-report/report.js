$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/services/github/features/ProcessSearchResults.feature");
formatter.feature({
  "line": 1,
  "name": "Github search processing",
  "description": "",
  "id": "github-search-processing",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Search for a repository with invalid parameters",
  "description": "",
  "id": "github-search-processing;search-for-a-repository-with-invalid-parameters",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@NegativeScenario"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I search for repositories with the name \"\" and a tag name \"\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I submit the search",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "the system should respond with the message \"* Please provide valid parameters for the repository search.\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 41
    },
    {
      "val": "",
      "offset": 59
    }
  ],
  "location": "Steps.i_search_for_repositories_with_the_name_and_a_tag_name(String,String)"
});
formatter.result({
  "duration": 184755788,
  "status": "passed"
});
formatter.match({
  "location": "Steps.i_submit_the_search()"
});
formatter.result({
  "duration": 135292,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "* Please provide valid parameters for the repository search.",
      "offset": 44
    }
  ],
  "location": "Steps.the_system_should_respond_with_the_message(String)"
});
formatter.result({
  "duration": 1971904,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Search for a repository that doesn\u0027t exist",
  "description": "",
  "id": "github-search-processing;search-for-a-repository-that-doesn\u0027t-exist",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 9,
      "name": "@PositiveScenario"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "I search for repositories with the name \"mockNonexistentRepo\" and a tag name \"v1.0.0\"",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I submit the search",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "the system should respond with the message \"* The search did not return any results.\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "mockNonexistentRepo",
      "offset": 41
    },
    {
      "val": "v1.0.0",
      "offset": 78
    }
  ],
  "location": "Steps.i_search_for_repositories_with_the_name_and_a_tag_name(String,String)"
});
formatter.result({
  "duration": 303957,
  "status": "passed"
});
formatter.match({
  "location": "Steps.i_submit_the_search()"
});
formatter.result({
  "duration": 970978,
  "error_message": "java.lang.NullPointerException\n\tat services.github.steps.Steps.i_submit_the_search(Steps.java:71)\n\tat ✽.When I submit the search(src/test/resources/services/github/features/ProcessSearchResults.feature:12)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "* The search did not return any results.",
      "offset": 44
    }
  ],
  "location": "Steps.the_system_should_respond_with_the_message(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "line": 16,
  "name": "Search for a repository that doesn\u0027t have a published release",
  "description": "",
  "id": "github-search-processing;search-for-a-repository-that-doesn\u0027t-have-a-published-release",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 15,
      "name": "@PositiveScenario"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "I search for repositories with the name \"mockUnpublishedRepo\" and a tag name \"v1.0.0\"",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I submit the search",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "the system should respond with the message \"* This repository doesn\u0027t have a release published.\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "mockUnpublishedRepo",
      "offset": 41
    },
    {
      "val": "v1.0.0",
      "offset": 78
    }
  ],
  "location": "Steps.i_search_for_repositories_with_the_name_and_a_tag_name(String,String)"
});
formatter.result({
  "duration": 291390,
  "status": "passed"
});
formatter.match({
  "location": "Steps.i_submit_the_search()"
});
formatter.result({
  "duration": 741847,
  "error_message": "java.lang.NullPointerException\n\tat services.github.steps.Steps.i_submit_the_search(Steps.java:88)\n\tat ✽.When I submit the search(src/test/resources/services/github/features/ProcessSearchResults.feature:18)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "* This repository doesn\u0027t have a release published.",
      "offset": 44
    }
  ],
  "location": "Steps.the_system_should_respond_with_the_message(String)"
});
formatter.result({
  "status": "skipped"
});
});