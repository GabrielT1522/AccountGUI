package DefaultFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener
{

    private DefaultFrame helpMenu;
    public MenuListener(DefaultFrame hm)
    {
        helpMenu = hm;
    }
    public void actionPerformed(ActionEvent evt)
    {
        String actionCommand = evt.getActionCommand();

        if(actionCommand.equals("Help")) {
            System.out.println("Help menu selected.");
            this.helpMenu.setVisible(true);
        }
        else if(actionCommand.equals("Exit")){
            System.exit(0);
        }
        else{
            System.out.println("ERROR: unknown action command.");
        }
    }
}
