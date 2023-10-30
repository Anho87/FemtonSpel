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
   
    public ArrayList<JButton> getButtonlist() {
        return buttonlist;
    }

    public void setButtonlist(ArrayList<JButton> buttonlist) {
        this.buttonlist = new ArrayList<>(buttonlist);
    }
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

    BoardPanel() {
        //create a random button list
        buttonlist = createRandomButtonList();
        //create a 2dimensional button array
        createButtonArray();
        addBoardPanelSetup();
    }

    BoardPanel(ArrayList<JButton> customButtonList){
        this.buttonlist = new ArrayList<>(customButtonList);
        createButtonArray();
        addBoardPanelSetup();
    }

    public void addBoardPanelSetup(){
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    moveTile(i, j);
                }
            }
        }
    }

    public void createButtonArray() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columLength; j++) {
                buttonArray[i][j] = buttonlist.get(numberOfButtons);
                numberOfButtons++;
            }
        }
    }

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
        int i;
        int k;
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

}
