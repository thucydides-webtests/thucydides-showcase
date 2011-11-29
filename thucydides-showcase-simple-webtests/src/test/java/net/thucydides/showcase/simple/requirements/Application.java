package net.thucydides.showcase.simple.requirements;

import net.thucydides.core.annotations.Feature;

public class Application {
    @Feature
    public class Search {
        public class SearchForArtifactsByName {}
        public class AdvancedSearch {}
    }

    @Feature
    public class DisplayArtifacts {
        public class ViewArtifactDetails {}
    }
}