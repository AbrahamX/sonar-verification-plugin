package org.sonarsource.plugins.example.mmd;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.example.mmd.EmptyLineMetrics.EMPTY_LINES_COUNT;

public class ComputeEmptyLinesTotal implements MeasureComputer {
    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext context) {
        return context.newDefinitionBuilder()
                .setOutputMetrics(EMPTY_LINES_COUNT.key())
                .build();
    }

    @Override
    public void compute(MeasureComputerContext context) {
        if (context.getComponent().getType() != Component.Type.FILE) {
            int sum = 0;
            for (Measure child : context.getChildrenMeasures(EMPTY_LINES_COUNT.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(EMPTY_LINES_COUNT.key(), sum);
        }
    }
}
