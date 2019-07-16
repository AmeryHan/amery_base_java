package batch.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author ameryhan
 */
public class OnJobNameCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnJobName.class.getName());
        String jobName = String.valueOf(annotationAttributes.get("jobName"));
        String javaPropertyValue = System.getProperty("jobName");
        return jobName.equals(javaPropertyValue);
    }

}
