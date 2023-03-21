package com.fmzm.extentreporting.extentreport;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void verifyTitle() {
        acceptAll();
        String title = driver.getTitle().trim();
        String expTitle = "JetBrains: Essential tools for software developers and teams";
        assertEquals(title, expTitle, "Title mismatched");
    }

//    @Test
    public void search() {
        acceptAll();
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test='search-input']"));
        assertEquals(searchPageField.getAttribute("value"), "Selenium");
    }

//    @Test
    public void toolsMenu() {
        acceptAll();
        mainPage.toolsMenu.click();
        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
        assertTrue(menuPopup.isDisplayed());
    }

//    @Test
    public void navigationToAllTools() {

        acceptAll();
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();
        agreeToRecordInteractions();
        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals(driver.getTitle(), "All Developer Tools and Products by JetBrains");
    }

    private void acceptAll(){
        String xpath = "/html/body/div[2]/div[2]/div[2]/button[1]";
        driver.findElement(By.xpath(xpath)).click();
    }

    private void agreeToRecordInteractions(){
        String xpath = "/html/body/div[3]/div[1]/div[2]/div/div/div[2]/a[1]";
        driver.findElement(By.xpath(xpath)).click();
    }
}
