package amery.memoryleak.example;

import javax.swing.*;
import java.awt.*;

public class MyDocWindow extends JFrame {
	
	public MyDocWindow(MyDocument document){
		super("Document Window");
		JTextArea textArea = new JTextArea(500,20);
		textArea.append(document.getMyText());
		
		JPanel panel = new JPanel();
		panel.add(textArea);
		
		JScrollPane jpanel = new JScrollPane();
		jpanel.setViewportView(panel);
		//should invoke the setSize method or the jscrollpanel 
		//will not be visible 
		jpanel.setSize(new Dimension(0,0));
		
		this.add(jpanel);
//		this.setContentPane(panel);

		this.pack();
	    this.setLocationRelativeTo(null);	
	    this.setSize(new Dimension(300,500));
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
