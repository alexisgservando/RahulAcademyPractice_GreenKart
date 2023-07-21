package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class GreenKartPage 
{
	/*-------------------- VARIABLES ------------------------------ */
	private WebDriver driver;
	
	/*-------------------- CONSTRUCTOR ------------------------------ */
	public GreenKartPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	/*-------------------- LOCATORS ------------------------------ */
	private By greenKartTitle = By.cssSelector("div[class='brand greenLogo'] div[class='brand greenLogo']");
	private By pageSizeDropdown = By.cssSelector("select[id='page-menu']");
	private By searchField = By.cssSelector("#search-field");
	private By firstColumItems = By.xpath("//tbody/tr/td[1]");
	private By sortFirstColumn = By.xpath("//th[1]/span[2]");
	private By sortSecondColumn = By.xpath("//th[2]/span[2]");
	private By sortThirdColumn = By.xpath("//th[3]/span[2]");
	private By columnHeaders = By.xpath("//th[@aria-sort]");
	// private By buttonsList = By.cssSelector("ul[aria-label='Pagination'] li");
	
	/*-------------------- METHODS ------------------------------ */
	//Method to validate the page title
	public void validatePageTitle()
	{
		String expectedTitle = "GREENKART";
		String actualTitle = driver.findElement(greenKartTitle).getText();
		Assert.assertEquals(actualTitle, expectedTitle, "The title does not match");
	}
	
	//Method to select the number of items to be displayed on the web table | Options: 5, 10, 20
	public void selectNumberOfItemsInTable(int option)
	{
		Select pageSizeSelect = new Select(driver.findElement(pageSizeDropdown));
		
		switch(option) 
		{
		case 5:
			pageSizeSelect.selectByValue("5");
			System.out.println("SELECTED 5");
			break;
		case 10:
			pageSizeSelect.selectByValue("10");
			System.out.println("SELECTED 10");
			break;
		case 20:
			pageSizeSelect.selectByValue("20");
			System.out.println("SELECTED 20");
			break;
		default:
			break;
		}
	}
	
	//Method to count the number of items in the table | Use this method after selecting the page Size
	public void countItemsInTable(int numberItems) 
	{
		List<WebElement> rowItems = driver.findElements(firstColumItems);
		ArrayList<String> products = new ArrayList<>();
		
		for (WebElement row : rowItems) 
		{
			//System.out.println(row.getText());
			products.add(row.getText());
		}
		//System.out.println("ACTUAL SIZE: " +products.size());
		//System.out.println("EXPECTED SIZE: " +numberItems);
		Assert.assertEquals(products.size(), numberItems, "The number of items do not match, expected: " +numberItems);
	}
	
	//Method to search for a veggie/fruit and validate the displayed on in the table
	public void searchItem(String search)
	{
		driver.findElement(searchField).sendKeys(search);
	}
	
	//Method to click the column header and sort the elements in the table | Finds 3 elements | takes the column number as parameter
	public void clickSortColumn(int index)
	{
		switch (index) 
		{
		case 1:
			driver.findElement(sortFirstColumn).click();
			System.out.println("FIRST COLUMN SORTED");
			this.validateAscendingOrder(index);
			break;
		case 2: 
			driver.findElement(sortSecondColumn).click();
			System.out.println("SECOND COLUMN SORTED");
			this.validateAscendingOrder(index);
			break;
		case 3:
			driver.findElement(sortThirdColumn).click();
			System.out.println("THIRD COLUMN SORTED");
			this.validateAscendingOrder(index);
			break;
		default:
			break;
		}
	}
	
	//Method to validate that the values are sorted after clicking the column header (using the above method) | Finds 3 elements | takes the column number as parameter
	public void validateAscendingOrder(int index)
	{
		String expectedAriaSort = "ascending";
		List<WebElement> headers = driver.findElements(columnHeaders);
		String[] ariaSortValues = new String[headers.size()];
		
		for (int i = 0; i < headers.size(); i++) 
		{
			WebElement element = headers.get(i);
			ariaSortValues[i] = element.getAttribute("aria-sort");
		}
		for (String ariaSortValue : ariaSortValues) 
		{
            System.out.println("aria-sort value: " + ariaSortValue);
        }
		Assert.assertEquals(ariaSortValues[index-1], expectedAriaSort, "The text inside the aria-sort attribute does not match");
		System.out.println("PRINTING ARIA VALUE FINAL: --- "+ariaSortValues[index-1]);
	}
}

