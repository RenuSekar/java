import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class HelloGui {
    public static void main(String[] args){
        Abc abc = new Abc();

    
    }
}

class Abc extends JFrame{  //card layout only one is visible,thats why Renu is not visible when you run this.
    public Abc(){
        setLayout(new FlowLayout());//FlowLayout ,GridLayout, NullLayout -> here we use FlowLayout...
        JLabel a = new JLabel("RENU");
        JLabel b = new JLabel("Welcome");
        add(a);
        add(b);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}