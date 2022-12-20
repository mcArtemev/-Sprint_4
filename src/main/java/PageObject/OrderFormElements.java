package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class OrderFormElements extends TestFixtures{
    //Форма заказа
    private By orderForm = By.xpath(".//div[contains(@class, 'Order_Content')]");
    //Инпут "Имя"
    private By nameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //Инпут "Фамилия"
    private By surnameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //Инпут "Адрес"
    private By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //Выпадающий список "Станция метро"
    private By metroDropdownList = By.className("select-search");
    //Инпут "Телефон"
    private By phoneInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[contains(text(),'Далее')]");
    //Поле "Когда привезти самокат"
    private By orderTimeDatepicker = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    //Поле "Срок аренды"
    private By rentTimeField = By.className("Dropdown-control");
    //Выпадающий список суток аренды
    private By rentDropdownList = By.xpath(".//div[@class='Dropdown-menu']/div[contains(@class,'Dropdown-option')]");
    //Чекбоксы "Цвет самоката"
    private By blackScooterCheckbox = By.id("black");
    private By greyScooterCheckbox = By.id("grey");
    //Поле "Комментарий для курьера"
    private By commentForCourierField = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    //Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[contains(text(), 'Заказать') and contains(@class, 'Middle')]");
    //Модальное окно "Подтверждение заказа"
    private By orderConfirmationModal = By.xpath(".//div[contains(text(), 'Хотите оформить заказ?')]");
    //Кнопка "Да" модального окна "Подтверждение заказа"
    private By agreeConfirmationButton = By.xpath(".//button[contains(text(), 'Да')]");
    //Модальное окно "Заказ оформлен"
    private By successOrderModal = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    //Геттер для модального окна "Заказ оформлен"
    public By getSuccessOrderModal(){
        return successOrderModal;
    }

    public OrderFormElements(WebDriver driver){
        this.driver = driver;
    }

    //Ожидание получения формы заказа
    public void waitForOrderForm(){
        helpers.waitElementDisplayed(driver, 10, orderForm);
    }

    //Заполнить поле "Имя"
    public void setName(String name){
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    //Заполнить поле "Фамилия"
    public void setSurname(String surname){
        driver.findElement(surnameInput).clear();
        driver.findElement(surnameInput).sendKeys(surname);
    }

    //Заполнить поле "Адрес: куда привезти заказ"
    public void setAddress(String address){
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }

    //Выбрать элемент из выпадающего списка "Станция метро"
    public void setMetro(String metroStation){
        driver.findElement(metroDropdownList).click();
        new Actions(driver)
                .sendKeys(metroStation)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    //Заполнить поле "Телефон: на него позвонит курьер"
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phoneNumber);
    }

    //Нажать кн "Далее"
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    //Заполнить поле "Когда привезти самокат"
    public void setOrderDate(String orderDate){
        driver.findElement(orderTimeDatepicker).clear();
        driver.findElement(orderTimeDatepicker).sendKeys(orderDate, Keys.ENTER);
    }

    //Заполнить поле "Срок аренды"
    public void setRentDate(String rentDate){
        driver.findElement(rentTimeField).click();
        List<WebElement> list = driver.findElements(rentDropdownList);
        for(WebElement element : list){
            if (element.getText().equals(rentDate)){
               element.click();
               return;
            }
        }
    }

    //Выбрать чекбокс "Цвет самоката"
    public void setScooterColor(String choose){
        switch (choose) {
            case "чёрный жемчуг":
                driver.findElement(blackScooterCheckbox).click();
                break;
            case "серая безысходность":
                driver.findElement(greyScooterCheckbox).click();
                break;
            case "оба":
                driver.findElement(blackScooterCheckbox).click();
                driver.findElement(greyScooterCheckbox).click();
                break;
            default:
                System.out.println("Такого самоката нет");
        }
    }

    //Заполнить поле "Комментарий для курьера"
    public void setCommentForCourier(String commentForCourier){
        driver.findElement(commentForCourierField).clear();
        driver.findElement(commentForCourierField).sendKeys(commentForCourier);
    }

    //Нажать кн "Заказать"
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    //Нажать кн "Да" в окне подтверждения
    public void clickAgreeConfirmationButton(){
        driver.findElement(agreeConfirmationButton).click();
    }

    //Ожидание появления окна подтверждения заказа
    public void waitForOrderConfirmationModal(){
        helpers.waitElementDisplayed(driver, 5, orderConfirmationModal);
    }

    //Ожидание появления окна об успешном заказе
    public void waitForSuccessOrderModal(){
        helpers.waitElementDisplayed(driver, 5, successOrderModal);
    }

    //Шаги заполнения формы "Для кого самокат"
    public void completeOrderForm1(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber)
    {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metroStation);
        setPhoneNumber(phoneNumber);
    }
    //Шаги заполнения формы "Про аренду"
    public void completeOrderForm2(
            String orderDate,
            String rentDate,
            String scooterColor,
            String commentForCourier)
    {
        setOrderDate(orderDate);
        setRentDate(rentDate);
        setScooterColor(scooterColor);
        setCommentForCourier(commentForCourier);
    }

}
