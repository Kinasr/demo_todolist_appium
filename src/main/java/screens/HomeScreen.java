package screens;

import helpers.MobActions;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertTrue;

public class HomeScreen {
    private final AppiumDriver driver;
    private final MobActions mobActions;

    private final By addTaskBtn = By.id("fab");


    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        mobActions = new MobActions(driver);
    }

    public  AddTaskScreen goToAddTaskScreen() {
        mobActions
                .tapOn(addTaskBtn);
        return new AddTaskScreen(driver);
    }

    public HomeScreen assertThatTaskWithIndexIsAdded(int index) {
        var task = By.cssSelector(".android.widget.RelativeLayout:nth-child(" + index + ")");
        assertTrue(mobActions.isElementDisplayed(task));

        return this;
    }
}
