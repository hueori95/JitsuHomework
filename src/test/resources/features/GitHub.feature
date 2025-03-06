Feature: Github API Test

  Scenario: Get total open issues across all repositories
    Given I access to the Github API
    When I retrieve the repositories from the GitHub API
    Then It should be display the total number of open issues

  Scenario: Sort the repositories by data updated in descending order
    Given I access to the Github API
    When I retrieve the repositories from the GitHub API
    Then The repositories should be in descending order

  Scenario: get the most watchers of github API
    Given I access to the Github API
    When I retrieve the repositories from the GitHub API
    Then It should be display repository with the most wathchers
