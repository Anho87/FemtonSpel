//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MenuPanel extends JPanel{
//
//    BoardPanel boardPanel = new BoardPanel();
//    JButton newGame = new JButton("New Game");
//    JButton exitButton = new JButton("Exit");
//    MenuPanel(){
//        add(newGame);
//        add(exitButton);
//        newGame.addActionListener(new startNewGame());
//        exitButton.addActionListener(new exitGame());
//    }
//
//    class startNewGame implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == newGame){
//                boardPanel.removeAll();
//                boardPanel.buttonArray = boardPanel.shuffleGame();
//                boardPanel.revalidate();
//            }
//
//        }
//    }
//
//    class exitGame implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource() == exitButton){
//                System.exit(0);
//            }
//        }
//    }
//}
