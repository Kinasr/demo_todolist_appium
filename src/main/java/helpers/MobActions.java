package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.MyLogger;

import java.util.Set;

public class MobActions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    private final TouchAction touchAction;

    public MobActions(AppiumDriver driver) {
        this.driver = driver;
        touchAction = new TouchAction(driver);
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

    public MobActions waitScreenToLoad(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return this;
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

    /**
     * To implement the scrolling
     * If startOffset > endOffset it will scroll down
     * If startOffset < endOffset it will scroll up
     * @param startOffset the start point should be 0 -> 1
     * @param endOffset the end point should be 0 -> 1
     * @return the current instance from this class
     */
    public MobActions scrollVertical(float startOffset, float endOffset) {
        validateOnOffset(startOffset, endOffset);

        Dimension dimension = driver.manage().window().getSize();
        int startPoint = (int) (dimension.getHeight() * startOffset);
        int endPoint = (int) (dimension.getHeight() * endOffset);

        touchAction
                .press(PointOption.point(0, startPoint))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();

        return this;
    }

    /**
     * To implement the scrolling
     * If startOffset > endOffset it will scroll right
     * If startOffset < endOffset it will scroll left
     * @param startOffset the start point should be 0 -> 1
     * @param endOffset the end point should be 0 -> 1
     * @return the current instance from this class
     */
    public MobActions scrollHorizontal(float startOffset, float endOffset) {
        validateOnOffset(startOffset, endOffset);

        Dimension dimension = driver.manage().window().getSize();
        int startPoint = (int) (dimension.getHeight() * startOffset);
        int endPoint = (int) (dimension.getHeight() * endOffset);

        touchAction
                .press(PointOption.point(startPoint, 0))
                .moveTo(PointOption.point(endPoint, 0))
                .release()
                .perform();

        return this;
    }

    private void validateOnOffset(float fOffset, float sOffset) {
        if (fOffset < 0 || fOffset > 1 || sOffset < 0 || sOffset > 1)
            MyLogger.severe(this.getClass().getSimpleName(),
                    "Invalid offset [" + fOffset + "or" + sOffset + "] offset should be between 0 and 1");
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

    public int numOfElementsInList(By by) {
        return wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by))
                .size();
    }

    public boolean isElementDisplayed(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public boolean isElementDisplayed(By by) {
        return isElementDisplayed((MobileElement) driver.findElement(by));
    }
}
