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
public class WhenSearchingForArtifactsUsingMultipleCriteriaInTheAdvancedSearch {

    @Managed
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://search.maven.org")
    public Pages pages;

    @Steps
    public DeveloperSteps developer;

    @Test
    public void should_search_for_artifacts_by_group_id_and_version() {
        developer.opens_the_advanced_search_page();
        developer.searches_by_group_and_version("net.thucydides","0.6.0");
        developer.should_see_artifacts_where(the("GroupId", is("net.thucydides")),
                                             the("Version", is("0.6.0")));

    }

}
