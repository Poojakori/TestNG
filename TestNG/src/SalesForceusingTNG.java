import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesForceusingTNG extends ReusableMethods{

    @BeforeMethod
    public static void InitializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ekpoo\\\\Desktop\\\\TekArch\\\\Selenium\\\\DriverLinks\\\\chromedriver_win32\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
    }

    public static void login() throws InterruptedException, IOException {
      //  String[][] data = readXlData("C:\\Users\\ekpoo\\Desktop\\TekArch\\Selenium\\Inputfiles\\TC1_Login.xls", "Sheet1");
        Thread.sleep(10000);

        WebElement uName = driver.findElement(By.xpath("//input[@id='username']"));
        enterText(uName, "vedu@acc.com", "UserName");
        //logger.log(LogStatus.INFO, "Username is entered successfully");

        WebElement pWord = driver.findElement(By.xpath("//input[@id='password']"));
        enterText(pWord, "Pooh@123", "Password");
        //logger.log(LogStatus.INFO, "Password is entered successfully");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='Login']"));
        clickElement(loginBtn, " Login Button");
        //logger.log(LogStatus.INFO, "Login Button Clicked successfully");
        Thread.sleep(10000);
    }

    @Test (priority = 0)
    public static void TC1_Login() throws InterruptedException, IOException {
       // logger = reports.startTest("TC1_Login");
        //logger.log(LogStatus.INFO, " Test case Started");
        login();

        Boolean checkUserNav = driver.findElement(By.xpath("//span[@id='userNavLabel']")).isDisplayed();
        Assert.assertTrue(checkUserNav);
        //checkPresence(checkUserNav, "Login Successful");
    }

    @AfterMethod
    public static void ExitDriver() throws IOException {
        reports.endTest(logger);
        logOut();
        driver.close();
    }
}
