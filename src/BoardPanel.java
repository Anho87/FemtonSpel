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
        for (int i = 1; i < 16; i++) {
            temp.add(new JButton(String.valueOf(i)));
        }
        Collections.shuffle(temp);
        return new ArrayList<>(temp);
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
