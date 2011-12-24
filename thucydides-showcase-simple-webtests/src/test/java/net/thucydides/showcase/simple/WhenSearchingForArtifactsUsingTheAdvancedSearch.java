package net.thucydides.showcase.simple;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import net.thucydides.showcase.simple.requirements.Application;
import net.thucydides.showcase.simple.steps.DeveloperSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.matchers.BeanMatchers.each;
import static net.thucydides.core.matchers.BeanMatchers.the;
import static net.thucydides.core.matchers.BeanMatchers.the_count;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@RunWith(ThucydidesRunner.class)
@Story(Application.Search.SearchForArtifactsByName.class)
public class WhenSearchingForArtifactsUsingTheAdvancedSearch {

    @Managed
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://search.maven.org")
    public Pages pages;

    @Steps
    public DeveloperSteps developer;

    @Test
    public void should_search_for_artifacts_by_group_id() {
        developer.opens_the_advanced_search_page();
        developer.searches_by_group("net.thucydides");
        developer.should_see_artifacts_where(the("GroupId", is("net.thucydides")),
                                             the_count(greaterThanOrEqualTo(10)));

    }

    @Test
    public void should_search_for_artifacts_by_artifact_id() {
        developer.opens_the_advanced_search_page();
        developer.searches_by_artifact("thucydides-core");
        developer.should_see_artifacts_where(the("ArtifactId", is("thucydides-core")), 
                                             the_count(is(1)));

    }

}
