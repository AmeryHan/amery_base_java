package amery.spring.batch;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class PartitionerTest implements Partitioner{
    
    private int itemCount;

    public Map<String, ExecutionContext> partition(int gridSize) {
	
	Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>(gridSize);
	
	for (int i = 0; i < itemCount ; i ++ )
	{
		ExecutionContext value = new ExecutionContext();
		value.putLong("number", i);
		result.put("partition" + i, value);
	}
	return result;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}
