package net.thucydides.showcase.simple.pages;

import ch.lambdaj.function.convert.Converter;
import net.thucydides.core.matchers.BeanMatcher;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.convert;
import static net.thucydides.core.pages.components.HtmlTable.filterRows;
import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class SearchResultsPage extends PageObject {

    @FindBy(xpath="//table[@id='resultTable']")
    WebElement resultTable;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

    public List<Map<Object, String>> getSearchResults() {
        return rowsFrom(resultTable);
    }
    
    public class Artifact {
        private final String groupId;
        private final String artifactId;
        private final String latestVersion;

        public Artifact(String groupId, String artifactId, String latestVersion) {
            this.groupId = groupId;
            this.artifactId = artifactId;
            this.latestVersion = latestVersion;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public String getLatestVersion() {
            return latestVersion;
        }
    }

    public List<Artifact> getResults() {
        List<WebElement> rows = resultTable.findElements(By.xpath(".//tr[td]"));
        return convert(rows, toArtifacts());
    }

    private Converter<WebElement, Artifact> toArtifacts() {
        return new Converter<WebElement, Artifact>() {
            public Artifact convert(WebElement row) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                String groupId = cells.get(0).getText();
                String artifactId = cells.get(1).getText();
                String latestVersion = cells.get(2).getText();
                return new Artifact(groupId, artifactId, latestVersion);
            }
        };
    }

    public WebElementFacade resultTable() {
        return element(resultTable);
    }

    public void clickOnFirstRowMatching(BeanMatcher... matchers) {
        List<WebElement> matchingRows = filterRows(resultTable, matchers);
        WebElement targetRow = matchingRows.get(0);
        WebElement detailsLink = targetRow.findElement(By.xpath(".//a[contains(@href,'artifactdetails')]"));
        detailsLink.click();
    }
}


