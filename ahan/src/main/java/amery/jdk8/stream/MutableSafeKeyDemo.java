package amery.jdk8.stream;

import java.util.HashMap;
import java.util.Map;

public class MutableSafeKeyDemo {
	
	
	/**
	 * 如果可变对象在HashMap中被用作键，那就要小心在改变对象状态的时候，不要改变它的哈希值了。

在下面的Employee示例类中，哈希值是用实例变量id来计算的。一旦Employee的对象被创建，id的值就能再改变。只有name可以改变，但name不能用来计算哈希值。所以，一旦Employee对象被创建，它的哈希值不会改变。所以Employee在HashMap中用作Key是安全的。
	 * @param args
	 */

	public static void main(String[] args) {
        Employee emp = new Employee(2);
        emp.setName("Robin");
 
        // Put object in HashMap.
        Map<Employee, String> map = new HashMap<>();
        map.put(emp, "Showbasky");
 
        System.out.println(map.get(emp));
 
        // Change Employee name. Change in 'name' has no effect
        // on hash code.
        emp.setName("Lily");
        System.out.println(map.get(emp));
    }
	
	
	class Employee {
	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			//result = prime * result + getOuterType().hashCode();
			result = prime * result + id;
			//result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Employee other = (Employee) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		// It is specified while object creation.
	    // Cannot be changed once object is created. No setter for this field.
	    private int id;
	    private String name;
	 
	    public Employee(final int id) {
	        this.id = id;
	    }
	 
	    public final String getName() {
	        return name;
	    }
	 
	    public final void setName(final String name) {
	        this.name = name;
	    }
	 
	    public int getId() {
	        return id;
	    }

		private MutableSafeKeyDemo getOuterType() {
			return MutableSafeKeyDemo.this;
		}
	}
}
