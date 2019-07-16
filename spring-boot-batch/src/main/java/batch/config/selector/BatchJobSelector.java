package batch.config.selector;

import batch.config.SpringBatchChunkConfig;
import batch.config.SpringBatchPartitionerConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

/**
 * author
 */
public class BatchJobSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Class<?> annotationType = EnableFkbBatchProcessing.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(annotationType.getName(), false));
        Assert.notNull(attributes, String.format("@%s is not present on importing class '%s' as expected, ", annotationType.getSimpleName(), importingClassMetadata.getClassName()));

        if (!attributes.containsKey("jobName")) {
            return new String[0];
        }
        if (attributes.getString("jobName").equals("taskletJob")) {
            return new String[] {SpringBatchPartitionerConfig.class.getName()};
        }
        if (attributes.getString("jobName").equals("chunkJob-reader-processor-writer")) {
            return new String[] {SpringBatchChunkConfig.class.getName()};
        }
        return new String[0];
    }
}
