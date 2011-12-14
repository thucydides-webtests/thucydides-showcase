package net.thucydides.showcase.simple.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArtifactDetailsPage extends PageObject {

    WebElement groupid;
    WebElement artifactid;
    WebElement versionid;

	public ArtifactDetailsPage(WebDriver driver) {
		super(driver);
	}

    public String getGroupId() {
        return element(groupid).getTextValue();
    }

    public String getArtifactId() {
        return element(artifactid).getTextValue();
    }

    public String getVersionId() {
        return element(versionid).getTextValue();
    }
}


