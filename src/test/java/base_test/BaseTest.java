package base_test;

import helpers.AppiumEventListener;
import helpers.CapsInitializer;
import helpers.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.MyLogger;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private AppiumDriver driver;

    @BeforeMethod
    public void setUpMethod() {
        URL url = null;
        try {
            url  = new URL(Constants.APPIUM_SERVER_URL);
        } catch (MalformedURLException e) {
            MyLogger.severe(this.getClass().getSimpleName(), "Can not connect with the Appium server");
            e.printStackTrace();
        }
        driver = new AppiumDriver(
                url,
                CapsInitializer.getCapabilities("android_emulator_capabilities")
        );
        // to register the event listener
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new AppiumEventListener());
    }

    @AfterMethod
    public void tearMethodDown() {
        driver.quit();
    }

    public AppiumDriver driver() {
        return driver;
    }
}
