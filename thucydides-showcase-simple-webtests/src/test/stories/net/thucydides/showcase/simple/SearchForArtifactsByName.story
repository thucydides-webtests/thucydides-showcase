package net.thucydides.showcase.simple

import net.thucydides.showcase.simple.requirements.Application.Search.SearchForArtifactsByName
import net.thucydides.showcase.simple.steps.DeveloperSteps
import static net.thucydides.core.matchers.PropertyMatcher.the
import static org.hamcrest.Matchers.is

using "thucydides"

thucydides.uses_default_base_url "http://search.maven.org"
thucydides.tests_story SearchForArtifactsByName
thucydides.uses_steps_from DeveloperSteps

scenario "A developer looks for an existing Maven artifact by name and group id", {
    given "the developer is on the Maven Central search page", {
        developer.opens_the_search_page()
    }
    when "the user searches for artifacts with the name 'thucydides'", {
        developer.searches_for 'Thucydides'
    }
    then "the thucyidides core artifact should appear in the results list", {
        developer.should_see_artifacts_where( the("ArtifactId", is("thucydides")),
                                              the("GroupId", is("net.thucydides")))
    }
}

scenario "A developer looks for an inexistant Maven artifact by name", {
    given "the developer is on the Maven Central search page", {
        developer.opens_the_search_page()
    }
    when "the user searches for artifacts with the name 'thucydides'", {
        developer.searches_for 'does-not-exist'
    }
    then "the thucyidides core artifact should appear in the results list", {
        developer.should_see_error_message "No records found"
    }
}
