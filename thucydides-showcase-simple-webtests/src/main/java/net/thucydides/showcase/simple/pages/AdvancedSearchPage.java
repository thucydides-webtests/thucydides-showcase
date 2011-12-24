package net.thucydides.showcase.simple.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://search.maven.org/")
public class AdvancedSearchPage extends PageObject {

    private WebElement groupId;
    private WebElement artifactId;
    private String value;
    private WebElement version;
    private WebElement packaging;
    private WebElement classifier;

    private WebElement gavSearchButton;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
    }

    public void setGroupId(String value) {
        element(groupId).type(value);
    }

    public void setArtifactId(String value) {
        element(artifactId).type(value);
    }

    public void setVersion(String value) {
        element(version).type(value);
    }

    public void setPackaging(String value) {
        element(packaging).type(value);
    }

    public void setClassifier(String value) {
        element(classifier).type(value);
    }


    public void startSearch() {
        element(gavSearchButton).click();
    }
}
