/*
 * Example Plugin for SonarQube
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.plugins.example;

import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonarsource.plugins.example.hooks.DisplayIssuesInScanner;
import org.sonarsource.plugins.example.hooks.DisplayQualityGateStatus;
import org.sonarsource.plugins.example.languages.FooLanguage;
import org.sonarsource.plugins.example.languages.FooQualityProfile;
import org.sonarsource.plugins.example.measures.ComputeSizeAverage;
import org.sonarsource.plugins.example.measures.ComputeSizeRating;
import org.sonarsource.plugins.example.measures.ExampleMetrics;
import org.sonarsource.plugins.example.measures.SetSizeOnFilesSensor;
import org.sonarsource.plugins.example.mmd.ComputeEmptyLinesTotal;
import org.sonarsource.plugins.example.mmd.EmptyLineMetrics;
import org.sonarsource.plugins.example.mmd.EmptyLineProperties;
import org.sonarsource.plugins.example.mmd.SetEmptyLinesCountOnSensor;
import org.sonarsource.plugins.example.rules.CreateIssuesOnJavaFilesSensor;
import org.sonarsource.plugins.example.rules.FooLintIssuesLoaderSensor;
import org.sonarsource.plugins.example.rules.FooLintRulesDefinition;
import org.sonarsource.plugins.example.rules.JavaRulesDefinition;
import org.sonarsource.plugins.example.settings.FooLanguageProperties;
import org.sonarsource.plugins.example.settings.HelloWorldProperties;
import org.sonarsource.plugins.example.settings.SayHelloFromScanner;
import org.sonarsource.plugins.example.web.MyPluginPageDefinition;

import static java.util.Arrays.asList;

/**
 * This class is the entry point for all extensions. It is referenced in pom.xml.
 */
public class ExamplePlugin implements Plugin {

  @Override
  public void define(Context context) {
    // tutorial on hooks
    // http://docs.sonarqube.org/display/DEV/Adding+Hooks
    context.addExtensions(DisplayIssuesInScanner.class, DisplayQualityGateStatus.class);

    // tutorial on languages
    context.addExtensions(FooLanguage.class, FooQualityProfile.class);
    context.addExtension(FooLanguageProperties.getProperties());

    // tutorial on measures
    context
      .addExtensions(ExampleMetrics.class, SetSizeOnFilesSensor.class, ComputeSizeAverage.class, ComputeSizeRating.class);

    // tutorial on rules
    context.addExtensions(JavaRulesDefinition.class, CreateIssuesOnJavaFilesSensor.class);
    context.addExtensions(FooLintRulesDefinition.class, FooLintIssuesLoaderSensor.class);

    // tutorial on settings
    // Abe's note: here adds a property to context, which can seen and changed at sonar server GUI.
    // When a sensor is running, it will retrieve the property value from context. If the value is true, the sensor
    // will 'say hello'
    context
      .addExtensions(HelloWorldProperties.getProperties())
      .addExtension(SayHelloFromScanner.class);

    // tutorial on web extensions
    // Abe's note:
    // The pages are registered in src/main/java/org/sonarsource/plugins/example/web/MyPluginPageDefinition.java,
    // and their respective front-end source code is located in src/main/js/.
    //
    // This example plugin uses Webpack for building the final JavaScript. Whatever build system you choose to use,
    // the final result MUST adhere to the following rules:
    //
    // # 1 entry file per extension page.
    // # The name of each entry file must correspond to the page_id of the registered page
    //   (see src/main/java/org/sonarsource/plugins/example/web/MyPluginPageDefinition.java and compare with the entry points in conf/webpack/webpack.config.js).
    // # Each entry file must be located in the resulting JAR's   static/   folder.
    context.addExtension(MyPluginPageDefinition.class);

    // Abe's note: a remarkable thing is that here a FLUENT BUILDER is used
    context.addExtensions(asList(
      PropertyDefinition.builder("sonar.foo.file.suffixes")
        .name("Suffixes FooLint")
        .description("Suffixes supported by FooLint")
        .category("FooLint")
        .defaultValue("")
        .build()));



    context.addExtension(EmptyLineProperties.getProperties());
    context.addExtensions(EmptyLineMetrics.class, SetEmptyLinesCountOnSensor.class, ComputeEmptyLinesTotal.class);

    context.addExtensions(asList(
            PropertyDefinition.builder("sonar.mmd.config.arg1")
                    .name("mmd arg 1")
                    .description("desc of mmd arg 1")
                    .category("MMD")
                    .defaultValue("")
                    .build()));

  }
}
