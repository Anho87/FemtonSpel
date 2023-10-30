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
    ArrayList<JButton> buttonList = new ArrayList<>();
    JButton[][] buttonArray = new JButton[rowLength][columLength];

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

    public void createButtonArray() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columLength; j++) {
                buttonArray[i][j] = buttonList.get(numberOfButtons);
                numberOfButtons++;
            }
        }
    }


    //find index of the selected button in the random array list
   /* public int getIndexOfSelectedButton(String selectedButton){
        int count = 0;
        for (JButton i : buttonlist){
            if (i.getText().equals(selectedButton)){
                return count;
            }
            count++;
        }
        throw new NullPointerException("Invalid button");
    }*/

   /* public boolean checkIfSelectedBtnIsNextToEmpty(String selectedButton){
        int indexOfSelectedButton = getIndexOfSelectedButton(selectedButton);
        //check button ABOVE
        if ((indexOfSelectedButton-4)>=0){
            if (buttonlist.get(indexOfSelectedButton-4).getText().equals("16")){
                return true;
            }
        }
        //check button BELOW
        if ((indexOfSelectedButton+4)<=15){
            if (buttonlist.get(indexOfSelectedButton+4).getText().equals("16")){
                return true;
            }
        }
        //check button on the LEFT
        if ((indexOfSelectedButton-1)>=0){
            if (buttonlist.get(indexOfSelectedButton-1).getText().equals("16")){
                return true;
            }
        }
        //check button on the RIGHT
        if ((indexOfSelectedButton+1)<=15){
            if (buttonlist.get(indexOfSelectedButton+1).getText().equals("16")){
                return true;
            }
        }

        return false;
    }*/

    //method to exchange texts of empty space buttons and selected button
   /* public void updateBoardPanelAfterSelection(String selectedButton){
        //identify the index of empty space and selected button in the arraylist
        int indexOfEmpty = getIndexOfSelectedButton("16");
        int indexOfSelectedBtn = getIndexOfSelectedButton(selectedButton);
        //if the selected button is beside an empty space, exchange texts of the two buttons in the arraylist
        if (checkIfSelectedBtnIsNextToEmpty(selectedButton)){
            buttonlist.get(indexOfEmpty).setText(selectedButton);
            buttonlist.get(indexOfEmpty).setVisible(true);
            buttonlist.get(indexOfSelectedBtn).setText("16");
            buttonlist.get(indexOfSelectedBtn).setVisible(false);
            checkIfWin();
            
        }
    }*/
    public void moveTile(int a, int b) {
        if (moveRight(b)) {
            if (buttonArray[a][b + 1].getText().equals("16")) {
                buttonArray[a][b + 1].setText(buttonArray[a][b].getText());
                buttonArray[a][b + 1].setVisible(true);
                buttonArray[a][b].setText("16");
                buttonArray[a][b].setVisible(false);
            }
        }
        if (moveLeft(b)) {
            if (buttonArray[a][b - 1].getText().equals("16")) {
                buttonArray[a][b - 1].setText(buttonArray[a][b].getText());
                buttonArray[a][b - 1].setVisible(true);
                buttonArray[a][b].setText("16");
                buttonArray[a][b].setVisible(false);
            }
        }
        if (moveUp(a)) {
            if (buttonArray[a - 1][b].getText().equals("16")) {
                buttonArray[a - 1][b].setText(buttonArray[a][b].getText());
                buttonArray[a - 1][b].setVisible(true);
                buttonArray[a][b].setText("16");
                buttonArray[a][b].setVisible(false);
            }
        }
        if (moveDown(a)) {
            if (buttonArray[a + 1][b].getText().equals("16")) {
                buttonArray[a + 1][b].setText(buttonArray[a][b].getText());
                buttonArray[a + 1][b].setVisible(true);
                buttonArray[a][b].setText("16");
                buttonArray[a][b].setVisible(false);
            }
        }
        checkIfWin();
    }

    public void checkIfWin() {
        int buttonCounter = 1;
        int correctButton = 0;
        for (JButton jButton : buttonList) {
            if (Integer.parseInt(jButton.getText()) == buttonCounter) {
                buttonCounter++;
                correctButton++;
            }
        }
        if (correctButton == 16) {
            JOptionPane.showMessageDialog(null, "Du vann!");
        }
    }

    public boolean moveRight(int b) {
        return b != 3;
    }

    public boolean moveLeft(int b) {
        return b != 0;
    }

    public boolean moveUp(int a) {
        return a != 0;
    }

    public boolean moveDown(int a) {
        return a != 3;
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(ArrayList<JButton> buttonList) {
        this.buttonList = buttonList;
    }
    public JButton[][] shuffleGame(){
        int buttons = 0;
        JButton[][] buttonArray = new JButton[rowLength][columLength];
        Collections.shuffle(buttonList);
        for (JButton button : buttonList) {
           add(button);
        }
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columLength; j++) {
                buttonArray[i][j] = buttonList.get(buttons);
                buttons++;
            }
        }
        return buttonArray;
    }

    BoardPanel() {
        //create a random button list
        buttonList = createRandomButtonList();
        //create a 2dimensional button array
        createButtonArray();

        for (JButton i : buttonList) {
            i.addActionListener(this);
        }
        //set layout
        setLayout(new GridLayout(4, 4));

        //display buttons on boardpanel and change the size
        for (JButton i : buttonList) {
            i.setPreferredSize(new Dimension(60, 60));
            add(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonArray[0][0]) {
            moveTile(0, 0);
        }
        if (e.getSource() == buttonArray[0][1]) {
            moveTile(0, 1);
        }
        if (e.getSource() == buttonArray[0][2]) {
            moveTile(0, 2);
        }
        if (e.getSource() == buttonArray[0][3]) {
            moveTile(0, 3);
        }
        if (e.getSource() == buttonArray[1][0]) {
            moveTile(1, 0);
        }
        if (e.getSource() == buttonArray[1][1]) {
            moveTile(1, 1);
        }
        if (e.getSource() == buttonArray[1][2]) {
            moveTile(1, 2);
        }
        if (e.getSource() == buttonArray[1][3]) {
            moveTile(1, 3);
        }
        if (e.getSource() == buttonArray[2][0]) {
            moveTile(2, 0);
        }
        if (e.getSource() == buttonArray[2][1]) {
            moveTile(2, 1);
        }
        if (e.getSource() == buttonArray[2][2]) {
            moveTile(2, 2);
        }
        if (e.getSource() == buttonArray[2][3]) {
            moveTile(2, 3);
        }
        if (e.getSource() == buttonArray[3][0]) {
            moveTile(3, 0);
        }
        if (e.getSource() == buttonArray[3][1]) {
            moveTile(3, 1);
        }
        if (e.getSource() == buttonArray[3][2]) {
            moveTile(3, 2);
        }
        if (e.getSource() == buttonArray[3][3]) {
            moveTile(3, 3);
        }
    }
}
