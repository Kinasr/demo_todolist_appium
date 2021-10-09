package helpers;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.MyReport;

public class AppiumEventListener implements AppiumWebDriverEventListener {
    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        MyReport.startStep(webElement.toString(), this.getClass().getSimpleName(),
                "Setting the element value");
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        MyReport.updateStepToBePassed(webElement.toString());
    }

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
        MyReport.startStep(webDriver.toString(), this.getClass().getSimpleName(), "Accepting Alert");
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
        MyReport.updateStepToBePassed(webDriver.toString());
    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
        MyReport.startStep(webDriver.toString(), this.getClass().getSimpleName(), "Dismissing Alert");
    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
        MyReport.updateStepToBePassed(webDriver.toString());
    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        MyReport.startStep(webDriver.toString(), this.getClass().getSimpleName(), "Navigating to: " + s);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        MyReport.updateStepToBePassed(webDriver.toString());
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        MyReport.startStep(by.toString(), this.getClass().getSimpleName(), "Trying to find: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        MyReport.updateStepToBePassed(by.toString());
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        MyReport.startStep(webElement.toString(), this.getClass().getSimpleName(),
                "Clicking on the element");
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        MyReport.updateStepToBePassed(webElement.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        MyReport.startStep(webElement.toString(), this.getClass().getSimpleName(),
                "Getting text from: " + webElement.getText());
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        MyReport.updateStepToBePassed(webElement.toString());
    }
}
