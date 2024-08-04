import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;



public class Calculator {
    public static void main(String[] args){
        AddSub obj = new AddSub();
    }
}

class AddSub extends JFrame implements ActionListener {
    JTextField t1 = new JTextField(20);
    JTextField t2 = new JTextField(20);
    JButton b1 = new JButton("ADD");
    JButton b2 = new JButton("SUB");
    JLabel l = new JLabel("Result");
    public AddSub(){
        add(t1);
        add(t2);
        add(b1);
        add(b2);
        add(l);

        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE
        );
    }
    
    public void actionPerformed(ActionEvent ae){
        int num1 = Integer.parseInt(t1.getText());
        int num2 = Integer.parseInt(t2.getText());
        int value;

        if(ae.getSource() == b1)
        value = num1+num2;

        else
        value = num1-num2;

        l.setText(value + " ");

    }

}
