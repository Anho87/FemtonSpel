import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener{
    
    JButton newGame = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    MenuPanel(){
        add(newGame);
        add(exitButton);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
