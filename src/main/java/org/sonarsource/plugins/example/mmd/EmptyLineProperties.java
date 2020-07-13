package org.sonarsource.plugins.example.mmd;

import org.sonar.api.config.PropertyDefinition;

import java.util.List;

import static java.util.Arrays.asList;

public class EmptyLineProperties{

    // This is the key showed in [configuration] tab
    public static final String EMPTYLINE_KEY = "sonar.mmd.emptyline.debugmode";

    // This CATEGORY tab appears at [Administration] - [Configuration]
    public static final String CATEGORY = "MMD Team";


    public EmptyLineProperties() {
    }

    public static List<PropertyDefinition> getProperties() {
        return asList(
                PropertyDefinition.builder(EMPTYLINE_KEY)
                        .name("Empty Line Debug Mode")
                        .description("Whether to print the value of empty lines when sensor is running")
                        .defaultValue(String.valueOf(false))
                        .category(CATEGORY)
                        .build());
    }

}
