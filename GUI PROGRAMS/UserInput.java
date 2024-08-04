
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInput {
    public static void main(String[] args){
        RadioDemo obj = new RadioDemo();
    }
}

class RadioDemo extends JFrame implements ActionListener{
    JTextField t1 = new JTextField(20);
    JButton b = new JButton("OK");
    JRadioButton r1 = new JRadioButton("Male");
    JRadioButton r2 = new JRadioButton("Female");
    JLabel l = new JLabel("Greeting");
    ButtonGroup bg = new ButtonGroup();
    JCheckBox c1 = new JCheckBox("Dancing");
    JCheckBox c2 = new JCheckBox("Singing");
   

    public RadioDemo(){
        bg.add(r1);
        bg.add(r2);

        add(t1);
        add(r1);
        add(r2);
        add(c1);
        add(c2);
        add(b);
        add(l);

        b.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        String name = t1.getText();
        if(r1.isSelected()){
            name = "Mr. " + name;
        }
        else{
            name = "Mrs. " + name;
        }
        if(c1.isSelected()){
            name = name + " Dancer";
        }
        if(c2.isSelected()){
            name = name + " Singer";
        }
        l.setText(name);
    }
}