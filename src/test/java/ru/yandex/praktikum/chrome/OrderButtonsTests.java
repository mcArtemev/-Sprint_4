package ru.yandex.praktikum.chrome;

import PageObject.MainPageElements;
import PageObject.OrderFormElements;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static PageObject.Helpers.getCalendarDate;

@RunWith(Parameterized.class)
public class OrderButtonsTests {
    private WebDriver driver;
    private MainPageElements mainPageElements;
    private OrderFormElements orderFormElements;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phoneNumber;
    private String orderDate;
    private String rentDate;
    private String scooterColor;
    private String commentForCourier;
    private boolean isDisplayed;

    public OrderButtonsTests(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber,
            String orderDate,
            String rentDate,
            String scooterColor,
            String commentForCourier,
            boolean isDisplayed){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentDate = rentDate;
        this.scooterColor = scooterColor;
        this.commentForCourier = commentForCourier;
        this.isDisplayed = isDisplayed;
    }

    @Parameterized.Parameters(name = "CHROME. Форма заказа заполнена корректно для {0} {1}. Заказ оформлен? {9}")
    public static Object[][] expectedHeaderAndText() {
        return new Object[][] {
                {"Иван","Иванов","Ленина,3","Ясенево","89999999999",getCalendarDate(1),"сутки","чёрный жемчуг", "Быстрее", true},
                {"Канеки","Кен","Аогири,13","Трубная","+79999999999",getCalendarDate(2),"четверо суток","серая безысходность","1000-7", true},
                {"Сидор","Сидоров","Улочная,55","Лубянка","29999999999",getCalendarDate(3),"семеро суток","оба","", true},
        };
    }

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPageElements = new MainPageElements(driver);
        orderFormElements = new OrderFormElements(driver);
    }

    @Test()
    public void orderScooterFromNavButtonSuccess(){
        mainPageElements.clickOrderButtonTop();
        orderFormElements.waitForOrderForm();
        orderFormElements.completeOrderForm1(
                name,
                surname,
                address,
                metroStation,
                phoneNumber);
        orderFormElements.clickNextButton();
        orderFormElements.completeOrderForm2(orderDate,
                rentDate,
                scooterColor,
                commentForCourier);
        orderFormElements.clickOrderButton();
        orderFormElements.waitForOrderConfirmationModal();
        orderFormElements.clickAgreeConfirmationButton();
        orderFormElements.waitForSuccessOrderModal();
        orderFormElements.waitForSuccessOrderModal();
        Assert.assertTrue("Модальное окно \"Заказ оформлен\" не отображается",driver.findElement(orderFormElements.getSuccessOrderModal()).isDisplayed()==isDisplayed);
    }

    @Test()
    public void orderScooterFromMiddleButtonSuccess(){
        mainPageElements.scrollToOrderButtonMiddle();
        mainPageElements.clickOrderButtonMiddle();
        orderFormElements.waitForOrderForm();
        orderFormElements.completeOrderForm1(
                name,
                surname,
                address,
                metroStation,
                phoneNumber);
        orderFormElements.clickNextButton();
        orderFormElements.completeOrderForm2(orderDate,
                rentDate,
                scooterColor,
                commentForCourier);
        orderFormElements.clickOrderButton();
        orderFormElements.waitForOrderConfirmationModal();
        orderFormElements.clickAgreeConfirmationButton();
        orderFormElements.waitForSuccessOrderModal();
        orderFormElements.waitForSuccessOrderModal();
        Assert.assertTrue("Модальное окно \"Заказ оформлен\" не отображается",driver.findElement(orderFormElements.getSuccessOrderModal()).isDisplayed()==isDisplayed);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
