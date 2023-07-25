package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	private By sortSecondColumn = By.xpath("//th[2]");
	private By sortThirdColumn = By.xpath("//th[3]");
	private By columnHeaders = By.xpath("//th[@aria-sort]");
	private By buttonList = By.cssSelector("ul[aria-label='Pagination'] li");
	private By firstButton = By.cssSelector("li:nth-child(1)");
	private By previousButton = By.cssSelector("li:nth-child(2)");
	private By oneButton = By.cssSelector("li:nth-child(3)");
	private By twoButton = By.cssSelector("li:nth-child(4)");
	private By threeButton = By.cssSelector("li:nth-child(5)");
	private By fourButton = By.cssSelector("li:nth-child(6)");
	private By nextButton = By.cssSelector("li:nth-child(7)");
	private By lastButton = By.cssSelector("li:nth-child(8)");
	/*-------------------- METHODS ------------------------------ */
	//Method for explicit wait 
	public void explicitWaitCustomMethod(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	//Method to validate the page title
	public void validatePageTitle()
	{
		String expectedTitle = "GREENKART";
		explicitWaitCustomMethod(greenKartTitle);
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
	
	//Method to validate that the values in the any column are sorted after clicking the column header (using the above method) | Finds 3 elements
	//This method uses the "aria-sort" attribute of the header to check on the value | Expected: ascending or none
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
	
	//Method to get the navigation buttons from the page | Returns an array of strings | 8 buttons expected
	public List<Integer> getTruePositions()
	{
		List<WebElement> buttons = driver.findElements(buttonList);
        String[] attributes = new String[buttons.size()];

        for (int i = 0; i < buttons.size(); i++) {
            attributes[i] = buttons.get(i).findElement(By.tagName("a")).getAttribute("aria-disabled");
        }

        // Create an ArrayList to store the positions of "true" values
        List<Integer> truePositions = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            if ("true".equals(attributes[i])) {
                truePositions.add(i);
            }
        }
        return truePositions;
	}
	
	//Method to validate the positions in which the "true" string is on | The method getTruePositions() identifies them
	public void validateTruePositions(List<Integer> expectedPositions)
	{
		List<Integer> positions = this.getTruePositions();
		
		System.out.println("-*******************************************");
		System.out.println("SIZE POSITIONS METHOD: " +positions);
		
		for(int i = 0; i < positions.size(); i++)
		{
			System.out.println("ELEMENTS: " +positions.get(i));
		}
		Assert.assertEquals(positions, expectedPositions);
	}
	
	//Method to click on the desired pagination button | Parameter: string name of button to click on
	public void clickPaginationButton(String buttonName)
	{
		switch (buttonName) 
		{
		case "First":
			driver.findElement(firstButton).click();
			System.out.println("FIRST BUTTON CLICKED");
			break;
		case "Previous":
			driver.findElement(previousButton).click();
			System.out.println("PREVIOUS BUTTON CLICKED");
			break;
		case "1":
			driver.findElement(oneButton).click();
			System.out.println("ONE BUTTON CLICKED");
			break;
		case "2":
			driver.findElement(twoButton).click();
			System.out.println("TWO BUTTON CLICKED");
			break;
		case "3":
			driver.findElement(threeButton).click();
			System.out.println("THREE BUTTON CLICKED");
			break;
		case "4":
			driver.findElement(fourButton).click();
			System.out.println("FOUR BUTTON CLICKED");
			break;
		case "Next":
			driver.findElement(nextButton).click();
			System.out.println("NEXT BUTTON CLICKED");
			break;
		case "Last":
			driver.findElement(lastButton).click();
			System.out.println("LAST BUTTON CLICKED");
			break;
		default:
			break;
		}
	}
}

