package helpers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapsInitializer {
    public static DesiredCapabilities getCapabilities(String fileName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        var jsonReader = new JsonReader(Constants.CAPS_ROOT_PATH + fileName);
        var jsonArray = jsonReader.get("caps").toArray();

        for (var i = 0; i < jsonArray.length; i++){
            var capsIndex = "caps[" + i + "]";
            caps.setCapability(jsonReader.get(capsIndex + ".key").toString(),
                    jsonReader.get(capsIndex + ".value").toString());
        }

        return caps;
    }
}
