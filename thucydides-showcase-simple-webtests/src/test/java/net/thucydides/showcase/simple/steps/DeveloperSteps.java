package net.thucydides.showcase.simple.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.matchers.PropertyMatcher;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.showcase.simple.pages.ArtifactDetailsPage;
import net.thucydides.showcase.simple.pages.SearchPage;
import net.thucydides.showcase.simple.pages.SearchResultsPage;

import static net.thucydides.core.matchers.PropertyMatcher.shouldMatch;

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
    public void should_see_artifacts_where(PropertyMatcher... matchers) {
        shouldMatch(onSearchResultsPage().getSearchResults(), matchers);
    }

    @Step
    public void should_see_error_message(String expectedMessage) {
        onSearchResultsPage().resultTable().shouldContainText(expectedMessage);
    }

    @Step
    public void open_artifact_where(PropertyMatcher... matchers) {
        onSearchResultsPage().clickOnFirstRowMatching(matchers);
    }

    @Step
    public void should_see_artifact_details_where(PropertyMatcher... matchers) {
        shouldMatch(onArtifactDetailsPage(), matchers);
    }

    private SearchPage onSearchPage() {
        return getPages().get(SearchPage.class);
    }

    private SearchResultsPage onSearchResultsPage() {
        return getPages().get(SearchResultsPage.class);
    }

    private ArtifactDetailsPage onArtifactDetailsPage() {
        return getPages().get(ArtifactDetailsPage.class);
    }
}
