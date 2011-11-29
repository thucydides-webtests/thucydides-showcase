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

public class SearchResultsPage extends PageObject {

    WebElement resultTable;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

    public List<Map<String, String>> getSearchResults() {
        return rowsFrom(resultTable);
    }

    public WebElementFacade resultTable() {
        return element(resultTable);
    }

    public void clickOnFirstRowMatching(PropertyMatcher... matchers) {
        List<WebElement> matchingRows = filterRows(resultTable, matchers);
        WebElement targetRow = matchingRows.get(0);
        WebElement detailsLink = targetRow.findElement(By.xpath(".//a[contains(@href,'artifactdetails')]"));
        detailsLink.click();
    }
}


