import java.util.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightBookTest {
	static WebDriver driver;
	WebElement element;
	WebDriverWait wait;
  @Test
  public void testForAOneWayJourney() throws InterruptedException {

      //setDriverPath();
	  /*---*/
	  ChromeOptions options=new ChromeOptions();
  	  options.addArguments("--disable-notifications");
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\Rayudu\\Desktop\\software testing\\drivers\\chromedriver.exe");
	  driver = new ChromeDriver(options);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
      driver.get("https://www.cleartrip.com/");
      //explicitly waiting until the element is visible on the webpage
  	  wait=new WebDriverWait(driver,20);
      element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Flights']")));
      boolean status=element.isDisplayed();
      //if element is visible proceeds 
      if(status) {
      driver.findElement(By.id("OneWay")).click();

      driver.findElement(By.id("FromTag")).clear();
      driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
     
      
      List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
      originOptions.get(0).click();

      driver.findElement(By.id("FromTag")).sendKeys(Keys.TAB);
      driver.findElement(By.xpath("//input[@id='ToTag' and @name='destination']")).sendKeys("Delhi");

      //select the first item from the destination auto complete list
      List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
      destinationOptions.get(0).click();

      driver.findElement(By.xpath("//td/a[text()=\"20\"]")).click();

      //all fields filled in. Now click on search
      driver.findElement(By.id("SearchBtn")).click();
      
      //verify that result appears for the provided journey search
      Assert.assertTrue(isElementPresent(By.className("searchSummary")));
      //close the browser
      endTest();
      }else
      {
      	System.out.println("Flights feature is not visible");
      }
  }

  private void endTest() {
      driver.quit();
  }

  private boolean isElementPresent(By by) {
      try {
          driver.findElement(by);
          return true;
      } catch (NoSuchElementException e) {
          return false;
      }
  }

}
