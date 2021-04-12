import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReusableMethods {
    protected static WebDriver driver;
    static ExtentReports reports;
    static ExtentTest logger;
    static String reportFolder = "C:\\Users\\ekpoo\\Desktop\\TekArch\\Selenium\\Reports\\";





    public static void enterText(WebElement ele, String txtValue, String objectName) throws IOException {

        if (ele.isDisplayed()) {
            ele.sendKeys(txtValue);
            System.out.println(txtValue + "has been successfully Entered  into " + objectName);
            logger.log(LogStatus.PASS, objectName + " has been successfully Entered." +
                    logger.addScreenCapture(takeScreenshot()));
        } else {
            System.out.println(objectName + " is not displayed on the web page.");
        }
    }


    public static void switchFrame(WebElement ele, String objectName) {
        if (ele.isDisplayed()) {
            driver.switchTo().frame(ele);
            System.out.println(objectName + "has been successfully switched. ");
        } else {
            System.out.println(objectName + " is not displayed on the web page.");
        }

    }


    public static void clickElement(WebElement ele, String ObjectName) throws IOException {
        if (ele.isDisplayed()) {
            ele.click();
            ;
            System.out.println(ObjectName + " has been successfully clicked");


        } else {
            System.out.println(ObjectName + " is not displayed in the web page.");
            logger.log(LogStatus.ERROR, ObjectName + " is not displayed in the web page." +
                    logger.addScreenCapture(takeScreenshot()));
        }
    }

    public static String[][] readXlData(String path, String sheetName) throws IOException {
        FileInputStream fs = new FileInputStream(new File(path));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() + 1;
        int colCount = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int cellType = sheet.getRow(i).getCell(j).getCellType();
                if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                    int val = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
                    data[i][j] = String.valueOf(val);
                } else
                    data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }
        return (data);
    }


    public static void checkPresence(Boolean ele, String str) throws IOException {
        if (ele == true)
            logger.log(LogStatus.PASS, str + "is Present");
        else {
            logger.log(LogStatus.FAIL, str + "is not present");
            logger.log(LogStatus.ERROR, str + " is not displayed in the web page." +
                    logger.addScreenCapture(takeScreenshot()));
        }
    }

    public static void InitializeReport() {
        reports = new ExtentReports(reportFolder + new SimpleDateFormat("'SalesForceReport_'YYYYMMddHHmm'.html'").
                format(new Date()));

    }

    public static String takeScreenshot() throws IOException {
        TakesScreenshot srcShot = ((TakesScreenshot) driver);
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        String imagePath = reportFolder + "ScreenShots\\" + new SimpleDateFormat("'Image_'YYYYMMddHHmm'.png'").format(new Date());
        File destFile = new File(imagePath);
        FileUtils.copyFile(srcFile, destFile);
        return imagePath;
    }
    public static void logOut() throws IOException {
        WebElement selectMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
        clickElement(selectMenu, " Menu Clicked");
        logger.log(LogStatus.INFO, "select Menu Clicked successfully");
        WebElement logOut = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        clickElement(logOut, " Logout Button");
        logger.log(LogStatus.INFO, "Logout Button Clicked successfully");
    }


}













