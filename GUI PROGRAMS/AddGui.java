import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGui{
    public static void main(String[] args){
     
    Addition obj = new Addition();

    }
}

class Addition extends JFrame implements ActionListener{

    JTextField t1 = new JTextField(20);
    JTextField t2 = new JTextField(20);
    JButton b = new JButton("OK");
     
    

    JLabel l = new JLabel("Result");

    public Addition(){
        setLayout(new FlowLayout());
        add(t1);
        add(t2);
        add(b);
        add(l);
        b.addActionListener(this); //action listener is an interface
        //OR  ActionListener al = new ActionListener;
        //b.addActionListener(al);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae){
        int num1 = Integer.parseInt(t1.getText());
        int num2 = Integer.parseInt(t2.getText());
        int value = num1 + num2;
        l.setText(value + " ");
         
    }
}