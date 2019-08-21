package amery.memoryleak.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyMainFrame extends JFrame {
    private ArrayList documents;
    private int nextDocNum;

    public MyMainFrame() {
        super("Document #from xx");

        documents = new ArrayList();

        JButton button = new JButton("Open Document");
        button.addActionListener(
                new ActionListener() {
                    //@Override
                    public void actionPerformed(ActionEvent e) {
                        openDocument();
                    }
                });
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(button);
        panel.setPreferredSize(new Dimension(300, 100));
//		this.add(panel);
        this.setContentPane(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void openDocument() {
        String documentName = "Document #" + nextDocNum;

        MyDocument document = new MyDocument(createMyText(), "Document");
        documents.add(document);

        MyDocWindow myDocWindow = new MyDocWindow(document);
        myDocWindow.show();

        //remove document to prevent the memory leak
//		documents.remove(document);

    }

    private String createMyText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 3000; i++) {
            text.append("Line ...... # ");
            text.append(i);
            text.append("\n");
        }
        return text.toString();
    }


}
