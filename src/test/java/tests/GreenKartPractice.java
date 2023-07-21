package tests;

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
		GreenKartPage gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Select page size = 10 and validate that the number of products is 10")
	public void TC02()
	{
		int noOfItems = 10;
		GreenKartPage gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Select page size = 20 and validate that the number of products is 20")
	public void TC03()
	{
		int noOfItems = 20;
		GreenKartPage gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Search for a veggie/fruit and validate only 1 element is displayed")
	public void TC04()
	{
		int noOfItems = 1;
		String item = "Orange";
		GreenKartPage gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.searchItem(item);
		gkPage.countItemsInTable(noOfItems);
	}
	
	@Test(description = "Keep 5 items in the table and sort the table | Sort by first column")
	public void TC05()
	{
		int columnNumber = 1; 
		int noOfItems = 5;
		GreenKartPage gkPage = new GreenKartPage(driver);
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
		GreenKartPage gkPage = new GreenKartPage(driver);
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
		GreenKartPage gkPage = new GreenKartPage(driver);
		gkPage.validatePageTitle();
		gkPage.selectNumberOfItemsInTable(noOfItems);
		gkPage.countItemsInTable(noOfItems);
		gkPage.clickSortColumn(columnNumber);
	}
	
	//SORT SECOND COLUMN (PRICES) - ASC
	//SORT THIRD COLUMN (DISCOUNT PRICE) - ASC
	//CLICK ON NEXT BUTTON ONE AT THE TIME AND PREVIOUS BUTTON ONE AT THE TIME 
	//CLICK ON THE LAST BUTTON
	
	@AfterTest
	public void teardown()
	{
		//driver.quit();
		//driver.close();
	}
}
