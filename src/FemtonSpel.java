import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FemtonSpel extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new FemtonSpel();
    }
    FemtonSpel(){
        setTitle("FemtonSpel");
        setLayout(new BorderLayout());
        
        
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
