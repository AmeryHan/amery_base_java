package amery.jdk.lambda;

public class Test {

	
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		System.out.print("Helllo Lambda in actionPerformed");
		}
	});
		//下面是使用 Lambda 表达式后：
	button.addActionListener(
		//actionPerformed 有一个参数 e 传入，所以用 (ActionEvent e)
		(ActionEvent e)-> 
		System.out.print("Helllo Lambda in actionPerformed")
		);
}
