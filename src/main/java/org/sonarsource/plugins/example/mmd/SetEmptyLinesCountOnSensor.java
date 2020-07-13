package org.sonarsource.plugins.example.mmd;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.plugins.example.settings.HelloWorldProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.sonarsource.plugins.example.mmd.EmptyLineMetrics.EMPTY_LINES_COUNT;

public class SetEmptyLinesCountOnSensor implements Sensor {
    @Override
    public void describe(SensorDescriptor sensorDescriptor) {
        sensorDescriptor.name("Count and set the number of empty lines of a file");
    }

    @Override
    public void execute(SensorContext sensorContext) {
        FileSystem fs = sensorContext.fileSystem();
        // only "main" files, but not "tests"
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
            int empty_lines = 0;

            try {
                InputStream input = file.inputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

                String line = null;
                String regexBlankLine = "\\s*"; // empty line regex

                while (null != (line = bufferedReader.readLine())) {
                    if (line.matches(regexBlankLine)) {
                        empty_lines++;
                    }
                }
                bufferedReader.close();
                input.close();
            } catch (IOException e) {
                Loggers.get(getClass()).info("InputStream error on file: " + file.filename());
            }


            sensorContext.<Integer>newMeasure().
                    forMetric(EMPTY_LINES_COUNT).
                    on(file).
                    withValue(empty_lines).
                    save();

//            if (sensorContext.config().getBoolean(EmptyLineProperties.EMPTYLINE_KEY).get()) {
            // print log only if property is set to true
            Loggers.get(getClass()).info(empty_lines + " in " + file.filename());
//            }
        }
    }
}
