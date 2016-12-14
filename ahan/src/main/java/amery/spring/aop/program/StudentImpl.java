package amery.spring.aop.program;

public class StudentImpl implements IStudent {

	public void addStudent(String name) {
		System.out.println( " 欢迎  " + name + "  你加入Spring家庭! " );

	}

}
