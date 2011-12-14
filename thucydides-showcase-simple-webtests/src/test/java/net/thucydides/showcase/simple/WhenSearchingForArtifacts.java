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

import static net.thucydides.core.matchers.BeanMatchers.the_count;
import static net.thucydides.core.matchers.BeanMatchers.each;
import static net.thucydides.core.matchers.BeanMatchers.the;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@RunWith(ThucydidesRunner.class)
@Story(Application.Search.SearchForArtifactsByName.class)
public class WhenSearchingForArtifacts {

    @Managed
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://search.maven.org")
    public Pages pages;

    @Steps
    public DeveloperSteps developer;

    @Test
    public void should_search_for_artifacts_by_name() {
        developer.opens_the_search_page();
        developer.searches_for("Thucydides");
        developer.should_see_artifacts_where(the("ArtifactId", is("thucydides")),
                                             the("GroupId", is("net.thucydides")));

    }

    @Test
    public void should_search_for_artifact_objects_by_name() {
        developer.opens_the_search_page();
        developer.searches_for("Thucydides");
        developer.should_see_artifact_objects_where(the("artifactId", is("thucydides")),
                the("groupId", is("net.thucydides")));

    }

    @Test
    public void should_find_the_right_number_of_artifacts() {
        developer.opens_the_search_page();
        developer.searches_for("Thucydides");
        developer.should_see_artifacts_where(the("GroupId", startsWith("net.thucydides")),
                each("ArtifactId").isDifferent(),
                the_count(is(greaterThanOrEqualTo(16))));

    }
}
