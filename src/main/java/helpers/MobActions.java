package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class MobActions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public MobActions(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Constants.WAIT_IN_SECONDS);
    }

    public MobActions tapOn(MobileElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        return this;
    }

    public MobActions tapOn(By by) {
        return tapOn((MobileElement) driver.findElement(by));
    }

    public MobActions sendTextTo(MobileElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public MobActions sendTextTo(By by, String text) {
        return sendTextTo(
                (MobileElement) driver.findElement(by),
                text
        );
    }

    public boolean isElementDisplayed(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
    public boolean isElementDisplayed(By by) {
        return isElementDisplayed((MobileElement) driver.findElement(by));
    }

    public MobActions switchToWebView() {
        Set<String> availableContexts = driver.getContextHandles();
        for (String context : availableContexts) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }
        return this;
    }

    public MobActions switchToNativeApp() {
        driver.context("NATIVE_APP");
        return this;
    }

    public MobActions waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        return this;
    }

    public MobActions acceptAlert() {
        waitForAlert();
        driver.switchTo().alert().accept();

        return this;
    }

    public MobActions dismissAlert() {
        waitForAlert();
        driver.switchTo().alert().dismiss();

        return this;
    }

    public MobActions sendTextToAlert(String text) {
        waitForAlert();
        driver.switchTo().alert().sendKeys(text);

        return this;
    }

    public MobActions hideKeyboard() {
        driver.hideKeyboard();
        return this;
    }
}
