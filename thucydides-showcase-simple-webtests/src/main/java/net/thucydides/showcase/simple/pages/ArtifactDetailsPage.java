package net.thucydides.showcase.simple.pages;

import net.thucydides.core.matchers.PropertyMatcher;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static net.thucydides.core.pages.components.HtmlTable.filterRows;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

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


