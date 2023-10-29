import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BoardPanel extends JPanel {
    ArrayList<JButton> buttonlist;

    public ArrayList<JButton> createRandomButtonList(){
        ArrayList<JButton> temp = new ArrayList<>();
        temp.add(new JButton(" "));
        for (int i = 1; i < 16; i++) {
            buttonlist.add(new JButton(String.valueOf(i)));
        }
        Collections.shuffle(temp);
        return temp;
    }

    BoardPanel(){
        buttonlist = createRandomButtonList();

        setLayout(new GridLayout(4,4));

    }
}
