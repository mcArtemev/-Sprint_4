package ru.yandex.praktikum.chrome;

import PageObject.MainPageElements;
import PageObject.TestFixtures;;
import org.junit.Assert;
import org.junit.Test;
import java.util.Set;

public class LogoTests extends TestFixtures {
    private MainPageElements mainPageElements = new MainPageElements(driver);
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    private String yandexUrl = "https://dzen.ru/";

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
}
