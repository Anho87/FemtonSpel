import javax.swing.*;
import java.awt.*;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.random.RandomGenerator;

public class BoardPanel extends JPanel {

    ArrayList<JButton> buttonlist = new ArrayList<>();

    public ArrayList<JButton> createRandomButtonList(){
        ArrayList<JButton> temp = new ArrayList<>();
        temp.add(new JButton(" "));
        temp.get(0).setVisible(false);
        for (int i = 1; i < 16; i++) {
            temp.add(new JButton(String.valueOf(i)));
        }
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
        }
        throw new NullPointerException("Invalid button");
    }

    public boolean checkIfSelectedBtnIsNextToEmpty(String selectedButton){
        int indexOfSelectedButton = getIndexOfSelectedButton(selectedButton);
        //check button ABOVE
        if ((indexOfSelectedButton-4)>=0){
            if (buttonlist.get(indexOfSelectedButton-4).getText().equals(" ")){
                return true;
            }
        }
        //check button BELOW
        if ((indexOfSelectedButton+4)<=15){
            if (buttonlist.get(indexOfSelectedButton+4).getText().equals(" ")){
                return true;
            }
        }
        //check button on the LEFT
        if ((indexOfSelectedButton-1)>=0){
            if (buttonlist.get(indexOfSelectedButton-1).getText().equals(" ")){
                return true;
            }
        }
        //check button on the RIGHT
        if ((indexOfSelectedButton+1)>=0){
            if (buttonlist.get(indexOfSelectedButton+1).getText().equals(" ")){
                return true;
            }
        }

        return false;
    }

    //method to exchange texts of empty space buttons and selected button
    public void updateBoardPanelAfterSelection(String selectedButton){
        //identify the index of empty space and selected button in the arraylist
        int indexOfEmpty = getIndexOfSelectedButton(" ");
        int indexOfSelectedBtn = getIndexOfSelectedButton(selectedButton);
        //if the selected button is beside an empty space, exchange texts of the two buttons in the arraylist
        if (checkIfSelectedBtnIsNextToEmpty(selectedButton)){
            buttonlist.get(indexOfEmpty).setText(selectedButton);
            buttonlist.get(indexOfSelectedBtn).setText(" ");
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
