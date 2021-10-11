package screens;

import helpers.MobActions;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HomeScreen {
    private final AppiumDriver driver;
    private final MobActions mobActions;

    private final By addTaskBtn = By.id("fab");
    private final By tasksList = By.cssSelector(".android.widget.RelativeLayout");


    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        mobActions = new MobActions(driver);
    }

    public AddTaskScreen goToAddTaskScreen() {
        mobActions
                .waitScreenToLoad(addTaskBtn)
                .tapOn(addTaskBtn);
        return new AddTaskScreen(driver);
    }

    @Deprecated
    public HomeScreen assertThatTaskWithIndexIsAdded(int index) {
        var task = By.cssSelector(".android.widget.RelativeLayout:nth-child(" + index + ")");
        assertTrue(mobActions.isElementDisplayed(task));

        return this;
    }

    public HomeScreen assertNumOfTasks(int numOfTasks) {
        assertEquals(mobActions.numOfElementsInList(tasksList) - 1, numOfTasks);

        return this;
    }
}
