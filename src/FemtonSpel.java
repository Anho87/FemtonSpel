import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FemtonSpel extends JFrame {
    static boolean isTest = true;
    JPanel menuPanel = new JPanel();
    BoardPanel boardPanel = isTest ? new BoardPanel(isTestMode()) : new BoardPanel();

    JButton newGame = new JButton("New Game");
    JButton exitButton = new JButton("Exit");

    //custom button array list for test mode
    public static ArrayList<JButton> isTestMode(){
        //Case 1: space at TOP-LEFT corner
        ArrayList<JButton> buttonListForTest = new ArrayList<>(Arrays.asList(
                new JButton("1"), new JButton("5"), new JButton("2"), new JButton("3"),
                new JButton("4"), new JButton("16"), new JButton("6"), new JButton("7"),
                new JButton("8"), new JButton("9"), new JButton("10"), new JButton("11"),
                new JButton("12"), new JButton("13"), new JButton("14"), new JButton("15")
        ));
        buttonListForTest.get(5).setVisible(false); //button of value "16" => empty button

//        //Case 2: space at BOTTOM-RIGHT corner
//        ArrayList<JButton> buttonListForTest = new ArrayList<>(Arrays.asList(
//                new JButton("1"), new JButton("2"), new JButton("3"), new JButton("4"),
//                new JButton("5"), new JButton("6"), new JButton("7"), new JButton("8"),
//                new JButton("9"), new JButton("10"), new JButton("16"), new JButton("12"),
//                new JButton("13"), new JButton("14"), new JButton("11"), new JButton("15")
//        ));
//        buttonListForTest.get(10).setVisible(false); //button of value "16" => empty button

        return buttonListForTest;
    }

    public static void main(String[] args) {
        FemtonSpel f = new FemtonSpel();
    }

    FemtonSpel(){
        setTitle("FemtonSpel");
        setLayout(new BorderLayout());

        add(boardPanel, BorderLayout.CENTER);
        
        add(menuPanel, BorderLayout.NORTH);
        menuPanel.add(newGame);
        menuPanel.add(exitButton);
        newGame.addActionListener(e -> {
            boardPanel.removeAll();
            boardPanel.buttonArray = boardPanel.shuffleGame();
            boardPanel.revalidate();
        });
        exitButton.addActionListener(e -> {System.exit(0);});

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
