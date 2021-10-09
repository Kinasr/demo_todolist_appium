package model;

import helpers.JsonReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Task(int index, String title, String note, List<String> tags, String newTag,
                   String startDate, String dueDate, String priority) {

    public static Task fromJson(JsonReader reader, String jsonPath) {
        var tags = new ArrayList<String>();
        var tagsObj = reader.get(jsonPath + ".tags.existed").toArray();
        if (tagsObj != null) {
            Arrays.stream(tagsObj).toList().forEach(
                    tag -> tags.add((String) tag)
            );
        }

        return new Task(
                reader.get(jsonPath + ".index").toInt(),
                reader.get(jsonPath + ".task-name").toString(),
                reader.get(jsonPath + ".task-note").toString(),
                tags,
                reader.get(jsonPath + ".tags.new").toString(),
                reader.get(jsonPath + ".start-date").toString(),
                reader.get(jsonPath + ".due-date").toString(),
                reader.get(jsonPath + ".priority").toString()
        );
    }
}
