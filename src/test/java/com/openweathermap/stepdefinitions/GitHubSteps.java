package com.openweathermap.stepdefinitions;

import com.openweathermap.api.GitHubApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.List;

public class GitHubSteps {
    private static final Logger log = LoggerFactory.getLogger(GitHubSteps.class);
    private Response response;
    private int totalOpenIssues;
    GitHubApi gitHubApi;
    List<String> sortedRepos;

    @Given("I access to the Github API")
    public void i_access_to_the_github_api(){
        gitHubApi = new GitHubApi();
    }

    @When("I retrieve the repositories from the GitHub API")
    public void i_retieve_the_repositories_from_the_github_api(){
        response = gitHubApi.getallRepository();
    }

    @Then("It should be display the total number of open issues")
    public void it_should_be_display_the_total_number_of_open_issues(){
        totalOpenIssues = gitHubApi.getTotalOpenIssues(response);
        gitHubApi.verifyTotalOpenIssues(totalOpenIssues);
    }

    @Then("The repositories should be in descending order")
    public void the_repositories_should_be_in_decending_order(){
        List<JSONObject> sortedRepos = gitHubApi.getSortedRepositoriesByUpdateDate(response);
        boolean checksort = true;
        for (int i = 0; i < sortedRepos.size() - 1; i ++){
            String currentDate = sortedRepos.get(i).getString("updated_at");
            String nextDate = sortedRepos.get(i+1).getString("updated_at");
            if(currentDate.compareTo(nextDate) < 0){
                checksort = false;
                break;
            }
        }
        Assert.assertTrue(checksort, "Sort repositories incorrectly");
    }

    @Then("It should be display repository with the most wathchers")
    public void it_should_be_display_repository_with_the_most_watchers(){
        String repowithMostWatchers = gitHubApi.getMostWatchers(response);
        log.info(repowithMostWatchers);
    }

}
