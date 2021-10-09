package tests;

import base_test.BaseTest;
import helpers.Constants;
import helpers.JsonReader;
import model.Task;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;

import java.util.Arrays;

public class AddTaskTest extends BaseTest {
    JsonReader jsonReader;

    @BeforeMethod
    @Override
    public void setUpMethod() {
        super.setUpMethod();
        jsonReader = new JsonReader(Constants.TEST_RESOURCES_PATH + "task_data");
    }

    @Test
    public void addTaskWithTitleOnly() {
        var task = Task.fromJson(jsonReader, "task-with-title-only");

        new HomeScreen(driver())
                .goToAddTaskScreen()
                .enterTaskName(task.title())
                .saveTask()
                .assertThatTaskWithIndexIsAdded(task.index());
    }

    @Test
    public void addTaskWithFullData() {
        var task = Task.fromJson(jsonReader, "task-with-full-data");

        new HomeScreen(driver())
                .goToAddTaskScreen()
                .enterTaskName(task.title())
                .enterTaskNote(task.note())
                .tapOnTagPicker()
                .selectTag(task.tags())
                .enterNewTag(task.newTag())
                .confirmSelectedTags()
                .tapOnStartDatePicker()
                .pickDate(task.startDate())
                .confirmDate()
                .tapOnDueDatePicker()
                .pickDate(task.dueDate())
                .confirmDate()
                .tapOnPriorityPicker()
                .pickPriority(task.priority())
                .confirmSelectedPriority()
                .saveTask()
                .assertThatTaskWithIndexIsAdded(task.index());

    }
}
