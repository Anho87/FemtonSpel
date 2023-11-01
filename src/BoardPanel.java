import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class BoardPanel extends JPanel implements ActionListener {
    int rowLength = 4;
    int columLength = 4;
    int numberOfButtons = 0;
    ArrayList<JButton> buttonlist = new ArrayList<>();
    JButton[][] buttonArray = new JButton[rowLength][columLength];
    
    //adds 16 buttons to a list and makes the last one invisible and also shuffles the list
    public  ArrayList<JButton> createRandomButtonList() {
        ArrayList<JButton> temp = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            temp.add(new JButton(String.valueOf(i)));
        }
        temp.add(new JButton("16"));
        temp.get(15).setVisible(false);
        Collections.shuffle(temp);
        return temp;
    }

    BoardPanel() {
        //create a random button list
        buttonlist = createRandomButtonList();
        //create a 2dimensional button array
        createButtonArray();
        addBoardPanelSetup();
    }

    //create a board panel with custom input button list
    BoardPanel(ArrayList<JButton> customButtonList){
        this.buttonlist = new ArrayList<>(customButtonList);
        createButtonArray();
        addBoardPanelSetup();
    }
    //creates the 2dimensional button array
    public void createButtonArray() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columLength; j++) {
                buttonArray[i][j] = buttonlist.get(numberOfButtons);
                numberOfButtons++;
            }
        }
    }

    public void addBoardPanelSetup(){
        //adds actionlistener to buttons
        for (JButton i : buttonlist) {
            i.addActionListener(this);
        }
        //set layout
        setLayout(new GridLayout(4, 4));

        //display buttons on boardpanel and change the size
        for (JButton i : buttonlist) {
            i.setPreferredSize(new Dimension(60, 60));
            add(i);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //goes through the buttonarray to find the clicked button
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    moveTile(i, j);
                }
            }
        }
    }
    //checks if the button in the corresponding position is the invisible button
    public void moveTile(int a, int b) {
        if (moveRight(b)) {
            if (buttonArray[a][b + 1].getText().equals("16")) {
                exchangeTiles(a,b+1, a, b);
            }
        }
        if (moveLeft(b)) {
            if (buttonArray[a][b - 1].getText().equals("16")) {
                exchangeTiles(a, b-1, a, b);
            }
        }
        if (moveUp(a)) {
            if (buttonArray[a - 1][b].getText().equals("16")) {
                exchangeTiles(a-1, b, a, b);
            }
        }
        if (moveDown(a)) {
            if (buttonArray[a + 1][b].getText().equals("16")) {
                exchangeTiles(a+1, b, a, b);
            }
        }
        checkIfWin();
    }
    //replaces the texts on the buttons and changes visibility
    public void exchangeTiles(int row_emptyTile, int col_emptyTile, int row_selectedTile, int col_selectedTile){
        buttonArray[row_emptyTile][col_emptyTile].setText(buttonArray[row_selectedTile][col_selectedTile].getText());
        buttonArray[row_emptyTile][col_emptyTile].setVisible(true);
        buttonArray[row_selectedTile][col_selectedTile].setText("16");
        buttonArray[row_selectedTile][col_selectedTile].setVisible(false);
    }
    //check if you can move right or if it's out of bounds
    public boolean moveRight(int b) {
        return b != 3;
    }
    //check if you can move left or if it's out of bounds
    public boolean moveLeft(int b) {
        return b != 0;
    }
    //check if you can move up or if it's out of bounds
    public boolean moveUp(int a) {
        return a != 0;
    }
    //check if you can move down or if it's out of bounds
    public boolean moveDown(int a) {
        return a != 3;
    }

    //check if the buttons are in order and print "You win" msg
    public void checkIfWin() {
        int buttonCounter = 1;
        int correctButton = 0;
        int i;
        if (Integer.parseInt(buttonlist.get(0).getText())==16){
            i = 1;
        } else {
            i = 0;
        }
        for (int j = i; j < 15+i; j++) {
            if (Integer.parseInt(buttonlist.get(j).getText())==buttonCounter){
                correctButton++;
            }
            buttonCounter++;
        }
        if (correctButton == 15) {
            JOptionPane.showMessageDialog(null, "Du vann!");
        }
    }

    //shuffle the board panel to start a new game
    public JButton[][] shuffleGame(){
        int buttons = 0;
        JButton[][] buttonArray = new JButton[rowLength][columLength];
        Collections.shuffle(buttonlist);
        for (JButton button : buttonlist) {
            add(button);
        }
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columLength; j++) {
                buttonArray[i][j] = buttonlist.get(buttons);
                buttons++;
            }
        }
        return buttonArray;
    }

}
