package net.thucydides.showcase.simple

import net.thucydides.showcase.simple.requirements.Application.Search.SearchForArtifactsByName
import net.thucydides.showcase.simple.steps.DeveloperSteps
import static net.thucydides.core.matchers.BeanMatchers.the
import static net.thucydides.core.matchers.BeanMatchers.the_count
import static net.thucydides.core.matchers.BeanMatchers.each
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.greaterThanOrEqualTo
import net.thucydides.core.matchers.BeanMatcher

using "thucydides"

thucydides.uses_default_base_url "http://search.maven.org"
thucydides.tests_story SearchForArtifactsByName
thucydides.uses_steps_from DeveloperSteps

scenario "A developer looks for a Thucydides Maven artifact by name and group id", {
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

scenario "A developer wants to see all the Thucydides artifacts", {
    given "the developer is on the Maven Central search page", {
        developer.opens_the_search_page()
    }
    when "the user searches for artifacts with the name 'thucydides'", {
        developer.searches_for 'Thucydides'
    }
    then "at least 10 artifacts should appear in the results list", {
        developer.should_see_artifacts_where(the("GroupId", is("net.thucydides")),
                                             each("ArtifactId").isDifferent())
                                             the_count(is(greaterThanOrEqualTo(10)))
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

