package screens;

import helpers.MobActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.List;

public class AddTaskScreen {
    private final AppiumDriver driver;
    private final MobActions mobActions;

    private final By backNavigator = MobileBy.AccessibilityId("Navigate up");
    private final By taskTitleTextField = By.id("editTextTitre");
    private final By taskNoteTextField = By.id("editTextNote");
    private final By taskTagPicker = By.id("editTextTag");
    private final By startDatePicker = By.id("buttonStartDate");
    private final By clearStartDatePickerBtn = By.id("imageButtonBackStartDate");
    private final By dueDataPicker = By.id("buttonDeadline");
    private final By clearDueDataPickerBtn = By.id("imageButtonBackDeadline");
    private final By priorityPicker = By.id("editTextPriority");
    private final By saveTaskBtn = By.id("action_save");


    public AddTaskScreen(AppiumDriver driver) {
        this.driver = driver;
        mobActions = new MobActions(driver);
    }

    public AddTaskScreen enterTaskName(String taskName) {
        mobActions
                .hideKeyboard()
                .sendTextTo(taskTitleTextField, taskName);
        return this;
    }

    public AddTaskScreen enterTaskNote(String note) {
        mobActions.sendTextTo(taskNoteTextField, note);
        return this;
    }

    public TagPickerPopup tapOnTagPicker() {
        mobActions
                .tapOn(taskTagPicker);
        return new TagPickerPopup();
    }

    public DatePickerPopup tapOnStartDatePicker() {
        mobActions.tapOn(startDatePicker);

        return new DatePickerPopup();
    }

    public DatePickerPopup tapOnDueDatePicker() {
        mobActions.tapOn(dueDataPicker);

        return new DatePickerPopup();
    }

    public PriorityPickerPopup tapOnPriorityPicker() {
        mobActions.tapOn(priorityPicker);

        return new PriorityPickerPopup();
    }

    public HomeScreen saveTask() {
        mobActions
                .tapOn(saveTaskBtn);
        return new HomeScreen(driver);
    }

    public AddTaskScreen scrollVertical(float startOffset, float endOffset) {
        mobActions.scrollVertical(startOffset, endOffset);
        return this;
    }

    public class TagPickerPopup {
        private final By newTagTextField = By.cssSelector("[text='New tag']");


        public TagPickerPopup selectTag(List<String> tagNames) {
            tagNames.forEach(tag -> mobActions.tapOn(By.cssSelector("[text='" + tag + "']")));
            return this;
        }

        public TagPickerPopup enterNewTag(String tagName) {
            mobActions
                    .waitScreenToLoad(newTagTextField)
                    .sendTextTo(newTagTextField, tagName);
            return this;
        }

        public AddTaskScreen confirmSelectedTags() {
            mobActions.acceptAlert();
            return new AddTaskScreen(driver);
        }

        public AddTaskScreen cancelSelectedTags() {
            mobActions.dismissAlert();
            return new AddTaskScreen(driver);
        }
    }

    public class DatePickerPopup {
        // 28 October 2021
        public DatePickerPopup pickDate(String date) {
            mobActions.
                    waitForAlert()
                    .tapOn(MobileBy.AccessibilityId(date));
            return this;
        }

        public AddTaskScreen confirmDate() {
            mobActions.acceptAlert();
            return new AddTaskScreen(driver);
        }

        public AddTaskScreen cancelDate() {
            mobActions.dismissAlert();
            return new AddTaskScreen(driver);
        }
    }

    public class PriorityPickerPopup {
        public PriorityPickerPopup pickPriority(String priority) {
            mobActions.tapOn(By.cssSelector("[text='" + priority + "']"));
            return this;
        }

        public AddTaskScreen confirmSelectedPriority() {
            mobActions.acceptAlert();
            return new AddTaskScreen(driver);
        }

        public AddTaskScreen cancelSelectedPriority() {
            mobActions.dismissAlert();
            return new AddTaskScreen(driver);
        }
    }
}
