import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class BoardPanel extends JPanel {

    ArrayList<JButton> buttonlist = new ArrayList<>();

    public ArrayList<JButton> createRandomButtonList(){
        ArrayList<JButton> temp = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            temp.add(new JButton(String.valueOf(i)));
        }
        temp.add(new JButton("16"));
        temp.get(15).setVisible(false);
        Collections.shuffle(temp);
        return new ArrayList<>(temp);
    }

    //find index of the selected button in the random array list
    public int getIndexOfSelectedButton(String selectedButton){
        int count = 0;
        for (JButton i : buttonlist){
            if (i.getText().equals(selectedButton)){
                return count;
            }
            count++;
        }
        throw new NullPointerException("Invalid button");
    }

    public boolean checkIfSelectedBtnIsNextToEmpty(String selectedButton){
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
        if (indexOfSelectedButton%4 != 0){
            if ((indexOfSelectedButton-1)>=0){
                if (buttonlist.get(indexOfSelectedButton-1).getText().equals("16")){
                    return true;
                }
            }
        }
        //check button on the RIGHT
        if (indexOfSelectedButton%4 != 3){
            if ((indexOfSelectedButton+1)<=15){
                if (buttonlist.get(indexOfSelectedButton+1).getText().equals("16")){
                    return true;
                }
            }
        }

        return false;
    }

    //method to exchange texts of empty space buttons and selected button
    public void updateBoardPanelAfterSelection(String selectedButton){
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
    }
    public void checkIfWin() {
        int buttonCounter = 1;
        int correctButton = 0;
        for (JButton jButton : buttonlist) {
            if (Integer.parseInt(jButton.getText()) == buttonCounter) {
                buttonCounter++;
                correctButton++;
            }
        }
        if (correctButton == 16) {
            JOptionPane.showMessageDialog(null, "Du vann!");
        }
    }

    public ArrayList<JButton> getButtonlist() {
        return buttonlist;
    }

    public void setButtonlist(ArrayList<JButton> buttonlist) {
        this.buttonlist = buttonlist;
    }

    BoardPanel(){
        //create a random button list
        buttonlist = createRandomButtonList();

        setLayout(new GridLayout(4,4));

        //display buttons on boardpanel
        for (JButton i : buttonlist){
            add(i);
        }
    }
}
