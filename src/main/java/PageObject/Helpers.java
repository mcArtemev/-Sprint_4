package PageObject;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

public class Helpers { //Класс для вспомогательных инструментов не относящийхся к тестам
    //Получить дату через @Param кол-во дней
    public static String getCalendarDate(int amountOfSkipDays) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yy");
        Date dt = new Date();
        DateTime dtOrg = new DateTime(dt);
        DateTime dtPlusOne = dtOrg.plusDays(amountOfSkipDays);
        return dtPlusOne.toString(fmt);
    }
    public void scrollToTargetElement(WebDriver driver, By targetElement){
        WebElement element = driver.findElement(targetElement);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

    }
    public void waitElementDisplayed(WebDriver driver, int timeToWait, By targetElement){
        new WebDriverWait(driver, timeToWait).until(d -> (driver.findElement(targetElement).isDisplayed()));
    }
    public void waitTextToBePresentInElement(WebDriver driver, int timeToWait, By targetElement, String expectedText){
        new WebDriverWait(driver, timeToWait).until(ExpectedConditions.textToBePresentInElementLocated(targetElement, expectedText));
    }

}
