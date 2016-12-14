package amery;

import java.math.BigDecimal;

public class TestNullIf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigDecimal a = null;
		//BigDecimal a = new BigDecimal(4.000);
		BigDecimal b = new BigDecimal(0.000);
		if(null == a && b.doubleValue() == 0) {
		}else {
			System.out.println("-----------------"+b);
		}
	}

}
