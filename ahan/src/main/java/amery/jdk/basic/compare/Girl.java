package amery.jdk.basic.compare;

/**
 * @author ameryhan
 * @date 2019/9/4 17:24
 */
public class Girl implements Comparable<Object> {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Girl(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Girl [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int compareTo(Object o) {
        Girl g = (Girl) o;
        return this.age - g.getAge();
    }
}
