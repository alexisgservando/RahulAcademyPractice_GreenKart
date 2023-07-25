package tests;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GreenKartPage;

public class GreenKartPractice 
{
	//Variables
	private WebDriver driver;
	GreenKartPage gkPage;
		
	@BeforeTest
	public void setUp()
	{
		//Initializing the webdriver
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();
	}
	
	@Test(description = "Select page size = 5 and validate that the number of products is 5")
	public void TC01()
	{
		int noOfItems = 5;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Select page size = 10 and validate that the number of products is 10")
	public void TC02()
	{
		int noOfItems = 10;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Select page size = 20 and validate that the number of products is 20")
	public void TC03()
	{
		int noOfItems = 20;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(19);							//At the time of this script, there were 19 elements in total; update this value in the future
	}
	
	@Test(description = "Search for a veggie/fruit and validate only 1 element is displayed")
	public void TC04()
	{
		int noOfItems = 1;
		String item = "Orange";
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.searchItem(item);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Keep 5 items in the table and sort the table | Sort by first column")
	public void TC05()
	{
		int columnNumber = 1; 
		int noOfItems = 5;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
		gkPage.clickSortColumn(columnNumber);
	}
	
	@Test(description = "Keep 10 items in the table and sort the table | Sort by first column")
	public void TC06()
	{
		int columnNumber = 1; 
		int noOfItems = 10;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
		gkPage.clickSortColumn(columnNumber);
	}
	
	@Test(description = "Keep 20 items in the table and sort the table | Sort by first column")
	public void TC07()
	{
		int columnNumber = 1; 
		int noOfItems = 20;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(19);							//At the time of this script, there were 19 elements in total; update this value in the future
		gkPage.clickSortColumn(columnNumber);
	}
	
	@Test(description = "Keep 10 items in the table and sort the table | Sort by second column")
	public void TC08()
	{
		int columnNumber = 2; 
		int noOfItems = 10;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
		gkPage.clickSortColumn(columnNumber);
	}
	
	@Test(description = "Keep 10 items in the table and sort the table | Sort by third column")
	public void TC09()
	{
		int columnNumber = 3; 
		int noOfItems = 10;
		gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
		gkPage.clickSortColumn(columnNumber);
	}
	
	@Test(dependsOnMethods = "TC01", description = "Keep 5 items in the table | Click pagination button: 1 | Validate that the first 2 buttons are disabled")
	public void TC10()
	{
		String buttonName = "1";
		List<Integer> expectedPositions = Arrays.asList(0, 1);
		gkPage.clickPaginationButton(buttonName);
		gkPage.validateTruePositions(expectedPositions);
	}
	
	@Test(dependsOnMethods = "TC01", description = "Keep 5 items in the table | Click pagination button: Last | Validate the last 2 buttons are disabled")
	public void TC11()
	{
		String buttonName = "Last";
		List<Integer> expectedPositions = Arrays.asList(5, 6);
		gkPage.clickPaginationButton(buttonName);
		gkPage.validateTruePositions(expectedPositions);
	}
	
	@Test(dependsOnMethods = "TC01", description = "Keep 5 items in the table | Click pagination button: Next | Validate the last 2 buttons are disabled after moving through the buttons")
	public void TC12()
	{
		String buttonName = "Next";
		List<Integer> expectedPositions = Arrays.asList(5, 6);
		gkPage.clickPaginationButton(buttonName);
		gkPage.clickPaginationButton(buttonName);
		gkPage.clickPaginationButton(buttonName);
		gkPage.validateTruePositions(expectedPositions);
	}
	
	@Test(dependsOnMethods = "TC01", description = "Keep 5 items in the table | Click pagination button: Last | Validate the first 2 buttons are disabled after moving backwards through the buttons")
	public void TC13()
	{
		String buttonName1 = "Last";
		String buttonName2 = "Previous";
		List<Integer> expectedPositions = Arrays.asList(0, 1);
		gkPage.clickPaginationButton(buttonName1);
		gkPage.clickPaginationButton(buttonName2);
		gkPage.clickPaginationButton(buttonName2);
		gkPage.clickPaginationButton(buttonName2);
		gkPage.validateTruePositions(expectedPositions);
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
		//driver.close();
	}
}
