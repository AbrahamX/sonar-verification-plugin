package org.sonarsource.plugins.example.mmd;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.List;

import static java.util.Arrays.asList;

public class EmptyLineMetrics implements Metrics {

    public static final Metric<Integer> EMPTY_LINES_COUNT = new Metric.Builder("empty_lines_count", "Empty lines count", Metric.ValueType.INT)
            .setDescription("Count of empty lines")
            .setDirection(Metric.DIRECTION_NONE)  // The metric direction has no meaning
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_SIZE) // This metric has sth to do with size
            .create();

    @Override
    public List<Metric> getMetrics() {
        return asList(EMPTY_LINES_COUNT);
    }
}
