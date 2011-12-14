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

import static net.thucydides.core.matchers.BeanMatchers.the;
import static org.hamcrest.Matchers.is;

@RunWith(ThucydidesRunner.class)
@Story(Application.DisplayArtifacts.ViewArtifactDetails.class)
public class WhenViewingArtifactDetails {

    @Managed
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://search.maven.org")
    public Pages pages;

    @Steps
    public DeveloperSteps developer;

    @Test
    public void clicking_on_artifact_should_display_details_page() {
        developer.opens_the_search_page();
        developer.searches_for("Thucydides");
        developer.open_artifact_where(the("ArtifactId", is("thucydides")),
                                      the("GroupId", is("net.thucydides")));

        developer.should_see_artifact_details_where(the("artifactId", is("thucydides")),
                                                    the("groupId", is("net.thucydides")));
    }
}
