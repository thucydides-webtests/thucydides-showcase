package net.thucydides.showcase.simple

import net.thucydides.showcase.simple.requirements.Application.DisplayArtifacts
import net.thucydides.showcase.simple.steps.DeveloperSteps
import static net.thucydides.core.matchers.PropertyMatcher.the
import static org.hamcrest.Matchers.is

using "thucydides"

//thucydides.uses_default_base_url "http://search.maven.org"
thucydides.tests_story DisplayArtifacts.ViewArtifactDetails
thucydides.uses_steps_from DeveloperSteps

scenario "A developer views the details of for an existing Maven artifact", {
    given "the developer is on the Maven Central search page", {
        developer.opens_the_search_page()
    }
    and "the user searches for artifacts with the name 'thucydides'", {
        developer.searches_for 'Thucydides'

    }
    and "the user clicks on the first Thucydides artifact", {
        developer.open_artifact_where(the("ArtifactId", is("thucydides")),
                                      the("GroupId", is("net.thucydides")));
    }
    then "the site should display details about the Thucydides artifact", {
        developer.should_see_artifact_details_where(the("artifactId", is("thucydides")),
                                                    the("groupId", is("net.thucydides")));

    }
}

scenario "The developer views a sample pom.xml dependency for the artifact", {
    given "the developer is on the Maven Central search page"
    and "the user searches for artifacts with the name 'thucydides'"
    and "the user clicks on the first Thucydides artifact"
    then "the site should display a sample XML dependency for this artifact"
}