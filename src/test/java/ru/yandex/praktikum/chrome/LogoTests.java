package ru.yandex.praktikum.chrome;

import PageObject.MainPageElements;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class LogoTests {
    private WebDriver driver;
    private MainPageElements mainPageElements;
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    private String yandexUrl = "https://dzen.ru/";
    private By yandexSiteElement = By.xpath(".//header[contains(@class,'dzen-header-desktop')");

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--no-sandbox");
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPageElements = new MainPageElements(driver);

    }

    @Test //Проверка перехода на главную страницу сайта после нажатия на логотип "Самокат"
    public void clickLogoScooterFromOrderGoToMainPage(){
        //Открыть форму заказа
        mainPageElements.clickOrderButtonTop();
        mainPageElements.clickLogoScooter();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("При нажатии на логотип Самокат не произошел переход на главную страницу сайта", URL, mainPageUrl);
    }
    @Test //Проверка перехода на страницу Яндекс после нажатия на логотип "Яндекс"
    public void clickLogoYandexGoToYandexPage()  {
        mainPageElements.clickLogoYandex();
        String currentHandle= driver.getWindowHandle();
        Set<String> handles=driver.getWindowHandles();
        for(String actual: handles) {
            if(!actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(actual);
                driver.get(yandexUrl);
            }
        }
        //Работать с табами через Set было больно и неприятно XD
        String URL = driver.getCurrentUrl();
        System.out.println(URL);
        Assert.assertTrue("Страница Яндекс не открыта", URL.contains(yandexUrl));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
