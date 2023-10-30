import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class FemtonSpel extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new FemtonSpel();
    }
    
    MenuPanel menuPanel = new MenuPanel();
    BoardPanel boardPanel = new BoardPanel();
    FemtonSpel(){
        setTitle("FemtonSpel");
        setLayout(new BorderLayout());
        
        add(menuPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        menuPanel.newGame.addActionListener(this);
        
        
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*for (JButton i : boardPanel.getButtonlist()){
            if (e.getSource()==i){
                boardPanel.updateBoardPanelAfterSelection(i.getText());
            }
        }*/
        
        
        if (e.getSource() == menuPanel.newGame){
            boardPanel.removeAll();
            boardPanel.buttonArray = boardPanel.shuffleGame();
            boardPanel.revalidate();
        }
    }
}
