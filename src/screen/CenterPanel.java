package screen;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CenterPanel {
    
    private static final JPanel panel = new JPanel();
    private static JLabel title = new JLabel();
    private static JLabel textLabel = new JLabel();
    private static JTextArea textArea = new JTextArea();
    private static JScrollPane scrollPane;
    private static JLabel fileLabel = new JLabel();
    private static JTextField fileText = new JTextField();
    private static JButton fileButton = new JButton();

    private static Font buttonFont = new Font("Arial",Font.PLAIN,15);
    private static Font titleFont = new Font("Arial",Font.PLAIN,31);
    private static Font labelFont = new Font("Arial",Font.PLAIN,19);
    private static Color backColor = new Color(23,23,45);
    private static Color foreColor = Color.WHITE;
    private static Color backColorDisabled = new Color(12,12,23);
    private static Color foreColorDisabled = Color.GRAY;
    private static Color backColorSelected = new Color(46,46,90);
    private static Color foreColorSelected = Color.WHITE;

    //GETTERS
    public static JPanel getPanel() {return panel;}
    public static JLabel getTitle() {return title;}
    public static Color getBackColor() { return backColor; }
    public static Color getForeColor() { return foreColor; }
    public static Color getBackColorDisabled() { return backColorDisabled; }
    public static Color getForeColorDisabled() { return foreColorDisabled; }
    public static Color getBackColorSelected() { return backColorSelected; }
    public static Color getForeColorSelected() { return foreColorSelected; }

    public CenterPanel(){

        int WIDTH = Screen.getWIDTH();
        int HEIGHT = Screen.getHEIGHT();

        panel.setBounds(WIDTH/5+1,0,4*WIDTH/5-1,HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(backColorDisabled);

        ComponentCreator.labelSetup(title, "", false, 0, 0, 4*WIDTH/5, HEIGHT/8, panel);
        ComponentCreator.labelEdit(title, titleFont, backColorDisabled, foreColor);
        title.setHorizontalAlignment(JLabel.CENTER);


        ComponentCreator.labelSetup(textLabel, "Type your input", false, 10, 4*HEIGHT/25, 4*WIDTH/5-35, HEIGHT/20, panel);
        ComponentCreator.labelEdit(textLabel, labelFont, backColorDisabled, foreColor);
        textLabel.setVisible(false);
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(new Color(30, 30, 60));
        textArea.setForeground(foreColor);
        //textArea.setCaretColor(foreColor);
        textArea.setVisible(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 4*HEIGHT/25 + HEIGHT/20, 4*WIDTH/5 - 35, HEIGHT/4);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollPane.getVerticalScrollBar().setBackground(backColorDisabled);
        // scrollPane.getVerticalScrollBar().setForeground(foreColor);
        scrollPane.setVisible(false);

        panel.add(scrollPane);
        

        ComponentCreator.labelSetup(fileLabel, "Your input file", false, 10, 4*HEIGHT/25, 4*WIDTH/5-35, HEIGHT/20, panel);
        ComponentCreator.labelEdit(fileLabel, labelFont, backColorDisabled, foreColor);
        fileLabel.setHorizontalAlignment(JLabel.CENTER);
        fileLabel.setVisible(false);
        ComponentCreator.textfieldSetup(fileText, "", 10, 4*HEIGHT/25+HEIGHT/15, 4*WIDTH/5-35, HEIGHT/20, false, true, panel);
        ComponentCreator.textfieldEdit(fileText, labelFont, backColorSelected, foreColorDisabled);
        fileText.setVisible(false);
        ComponentCreator.buttonSetup(fileButton, "Select file", 10, 4*HEIGHT/25+HEIGHT/15+HEIGHT/15, 4*WIDTH/5-35, HEIGHT/20, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(fileButton, buttonFont, backColor, foreColor);
        fileButton.setVisible(false);

    }

    public static void toggleText(){
        fileLabel.setVisible(false);
        fileText.setVisible(false);
        fileButton.setVisible(false);
    
        textLabel.setVisible(true);
        textArea.setVisible(true);
        scrollPane.setVisible(true);
    }

    public static void toggleFile(){
        textLabel.setVisible(false);
        textArea.setVisible(false);
        scrollPane.setVisible(false);

        fileLabel.setVisible(true);
        fileText.setVisible(true);
        fileButton.setVisible(true);
    }

    public static void hideAll(){
        textLabel.setVisible(false);
        textArea.setVisible(false);
        scrollPane.setVisible(false);

        fileLabel.setVisible(false);
        fileText.setVisible(false);
        fileButton.setVisible(false);
    }

}
