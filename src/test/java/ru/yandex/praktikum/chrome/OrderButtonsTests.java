package ru.yandex.praktikum.chrome;

import PageObject.MainPageElements;
import PageObject.OrderFormElements;
import PageObject.TestFixtures;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static PageObject.Helpers.getCalendarDate;

@RunWith(Parameterized.class)
public class OrderButtonsTests extends TestFixtures {
    private MainPageElements mainPageElements = new MainPageElements(driver);
    private OrderFormElements orderFormElements = new OrderFormElements(driver);
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
}
