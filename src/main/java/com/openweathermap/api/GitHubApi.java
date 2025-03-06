package com.openweathermap.api;

import com.openweathermap.util.RequestsUtility;
import io.restassured.response.Response;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class GitHubApi {
    private static final Logger log = LoggerFactory.getLogger(GitHubApi.class);

    public GitHubApi() {
    }

    public Response getallRepository(){
        Response response = RequestsUtility.get("SeleniumHQ/repos", new HashMap<>(), new HashMap<>());
        RequestsUtility.assert_status_code(response, 200);
        return response;
    }

    public List<JSONObject> getSortedRepositoriesByUpdateDate(Response response){
        JSONArray repos = new JSONArray(response.getBody().asString());
        List<JSONObject> repoList = new ArrayList<>();
        for (int i = 0; i < repos.length(); i++) {
            repoList.add(repos.getJSONObject(i));
        }

        repoList.sort((repo1, repo2) -> repo2.getString("updated_at").compareTo(repo1.getString("updated_at")));
        return repoList;

    }

    public int getTotalOpenIssues(Response response){
        List<Map<String, Object>> repositories = response.jsonPath().getList("$");
        int totalOpenIssues = repositories.stream().mapToInt(repo -> (int) repo.get("open_issues_count"))
                .sum();
        log.info("-------------------");
        log.info(String.valueOf(totalOpenIssues));
        return totalOpenIssues;
    }

    public String getMostWatchers(Response response){
        JSONArray repos = new JSONArray(response.getBody().asString());
        String repoWithMostWatchers = "";
        int maxWatchers = 0;
        for (int i = 0; i < repos.length(); i++) {
            JSONObject repo = repos.getJSONObject(i);
            int watchers = repo.getInt("watchers_count");
            if (watchers > maxWatchers) {
                maxWatchers = watchers;
                repoWithMostWatchers = repo.getString("name");
            }
        }
        log.info("-------------------");
        log.info(String.valueOf(maxWatchers));
        return repoWithMostWatchers;

    }

    public void verifyTotalOpenIssues(int count){
        Assert.assertTrue(count >= 0);
    }


}
