package DefaultFrame;

import javax.swing.*;

public class DefaultFrame extends JFrame
{
    public DefaultFrame()
    {
        // Create menu bar, menus and menu items
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        menubar.add(fileMenu);
        menubar.add(helpMenu);
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem searchItem = new JMenuItem("Search");
        //fileMenu.add(loadItem);
        //fileMenu.add(saveAsItem);
        //fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        //fileMenu.add(searchItem);
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);
        // Create a listener and add it to the menu items
        MenuListener menuList = new MenuListener(this);
        exitItem.addActionListener(menuList);
        helpItem.addActionListener(menuList);

        //this.setResizable(false);
        this.setSize(450,350);
        this.setLocation(450,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Makes the frame visible.
    public void showIt(){
        this.setVisible(true);
    }
    // Makes the frame visible and sets the title text.
    public void showIt(String title){
        this.setTitle(title);
        this.setVisible(true);
    }
    // Makes the frame visible and sets the title text // and the position of the window.
    public void showIt(String title,int x, int y){
        this.setTitle(title);
        this.setLocation(x,y);
        this.setVisible(true);
    }
    // Makes the frame invisible.
    public void hideIt(){
        this.setVisible(false);
    }
}