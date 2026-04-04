package screen;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterPanel {
    
    private static final JPanel panel = new JPanel();
    private static JLabel title = new JLabel();

    private static Font buttonFont = new Font("Arial",Font.PLAIN,15);
    private static Font titleFont = new Font("Arial",Font.PLAIN,31);
    private static Font labelFont = new Font("Arial",Font.PLAIN,17);
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
        
    }

}
