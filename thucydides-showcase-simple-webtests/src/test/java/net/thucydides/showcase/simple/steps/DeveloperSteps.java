package net.thucydides.showcase.simple.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.matchers.BeanFieldMatcher;
import net.thucydides.core.matchers.BeanMatcher;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.showcase.simple.pages.AdvancedSearchPage;
import net.thucydides.showcase.simple.pages.ArtifactDetailsPage;
import net.thucydides.showcase.simple.pages.SearchPage;
import net.thucydides.showcase.simple.pages.SearchResultsPage;

import static net.thucydides.core.matchers.BeanMatcherAsserts.shouldMatch;


public class DeveloperSteps extends ScenarioSteps {

    public DeveloperSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void opens_the_search_page() {
        onSearchPage().open();
    }

    @Step
    public DeveloperSteps searches_for(String search_terms) {
        onSearchPage().enter_search_terms(search_terms);
        onSearchPage().starts_search();
        return this;
    }

    @Step
    public void should_see_artifacts_where(BeanMatcher... matchers) {
        shouldMatch(onSearchResultsPage().getSearchResults(), matchers);
    }


    @Step
    public void should_see_artifact_objects_where(BeanMatcher... matchers) {
        shouldMatch(onSearchResultsPage().getResults(), matchers);
    }


    @Step
    public void should_see_error_message(String expectedMessage) {
        onSearchResultsPage().resultTable().shouldContainText(expectedMessage);
    }

    @Step
    public void open_artifact_where(BeanMatcher... matchers) {
        onSearchResultsPage().clickOnFirstRowMatching(matchers);
    }

    @Step
    public void should_see_artifact_details_where(BeanMatcher... matchers) {
        shouldMatch(onArtifactDetailsPage(), matchers);
    }

    public void opens_the_advanced_search_page() {
        onSearchPage().open();
        onSearchPage().clickOnAdvancedSearch();
    }

    public void searches_by_group(String group) {
        onAdvancedSearchPage().setGroupId(group);
        onAdvancedSearchPage().startSearch();
    }

    public void searches_by_artifact(String artifact) {
        onAdvancedSearchPage().setArtifactId(artifact);
        onAdvancedSearchPage().startSearch();
    }

    public void searches_by_group_and_version(String group, String version) {
        onAdvancedSearchPage().setGroupId(group);
        onAdvancedSearchPage().setVersion(version);
        onAdvancedSearchPage().startSearch();
    }

    private SearchPage onSearchPage() {
        return getPages().get(SearchPage.class);
    }

    private AdvancedSearchPage onAdvancedSearchPage() {
        return getPages().get(AdvancedSearchPage.class);
    }

    private SearchResultsPage onSearchResultsPage() {
        return getPages().get(SearchResultsPage.class);
    }

    private ArtifactDetailsPage onArtifactDetailsPage() {
        return getPages().get(ArtifactDetailsPage.class);
    }
}
