package amery.logic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by ahan on 28/02/2017.
 */
@Slf4j
public class LogicTest {

    @Test
    public void test1() {
        Long vendorItemId = null;
        boolean invalidEnhancement = false;
        if (vendorItemId == null || vendorItemId == 0 && !invalidEnhancement) {
            log.debug("A");
        }
        vendorItemId = null;
        invalidEnhancement = true;
        if (vendorItemId == null || vendorItemId == 0 && !invalidEnhancement) {
            log.debug("B");
        }
        vendorItemId = null;
        invalidEnhancement = true;
        if ((vendorItemId == null || vendorItemId == 0) && !invalidEnhancement) {
            log.debug("B");
        }
        vendorItemId = 0L;
        invalidEnhancement = true;
        if (vendorItemId == null || vendorItemId == 0 && !invalidEnhancement) {
            log.debug("B");
        }
        vendorItemId = 0L;
        invalidEnhancement = true;
        if ((vendorItemId == null || vendorItemId == 0) && !invalidEnhancement) {
            log.debug("B");
        }

    }

    @Test
    public void test2() {
        Long itemId = null;
        itemId = (null != itemId && 0 != itemId) ? itemId : null;
        log.debug("A");

        itemId = 0L;
        itemId = (null != itemId && 0 != itemId) ? itemId : null;
        log.debug("A");
    }
}
