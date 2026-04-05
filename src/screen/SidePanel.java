package screen;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel {
    
    private static final JPanel panel = new JPanel();

    private static JLabel option = new JLabel();
    private static JButton encrypt = new JButton();
    private static JButton decrypt = new JButton();
    private static JLabel input = new JLabel();
    private static JButton textInput = new JButton();
    private static JButton fileInput = new JButton();
    private static JLabel algorithm = new JLabel();
    private static JButton aesButton = new JButton();
    private static JButton chacha20Button = new JButton();
    private static JButton desButton = new JButton();
    private static JButton rsaButton = new JButton();
    private static JButton eccButton = new JButton();
    private static JButton sha256Button = new JButton();
    private static JButton md5Button = new JButton();
    private static JButton kyberButton = new JButton();

    private static Font buttonFont = new Font("Arial",Font.PLAIN,15);
    private static Font labelFont = new Font("Arial",Font.PLAIN,17);
    private static Color backColor = new Color(23,23,45);
    private static Color foreColor = Color.WHITE;
    private static Color backColorDisabled = new Color(12,12,23);
    private static Color foreColorDisabled = Color.GRAY;
    private static Color backColorSelected = new Color(46,46,90);
    private static Color foreColorSelected = Color.WHITE;

    private static ArrayList<JButton> buttonList = new ArrayList<>(){{
            add(SidePanel.getEncryptButton());
            add(SidePanel.getDecryptButton());
            add(SidePanel.getTextInputButton());
            add(SidePanel.getFileInputButton());
            add(SidePanel.getAesButton());
            add(SidePanel.getChacha20Button());
            add(SidePanel.getDesButton());
            add(SidePanel.getRsaButton());
            add(SidePanel.getEccButton());
            add(SidePanel.getSha256Button());
            add(SidePanel.getMd5Button());
            add(SidePanel.getKyberButton());
    }};

    //GETTERS
    public static JPanel getPanel() {return panel;}
    public static JButton getEncryptButton() {return encrypt;}
    public static JButton getDecryptButton() {return decrypt;}
    public static JButton getTextInputButton() {return textInput;}
    public static JButton getFileInputButton() {return fileInput;}
    public static JButton getAesButton() {return aesButton;}
    public static JButton getChacha20Button() {return chacha20Button;}
    public static JButton getDesButton() {return desButton;}
    public static JButton getRsaButton() {return rsaButton;}
    public static JButton getEccButton() {return eccButton;}
    public static JButton getSha256Button() {return sha256Button;}
    public static JButton getMd5Button() {return md5Button;}
    public static JButton getKyberButton() {return kyberButton;}
    public static Color getBackColor() { return backColor; }
    public static Color getForeColor() { return foreColor; }
    public static Color getBackColorDisabled() { return backColorDisabled; }
    public static Color getForeColorDisabled() { return foreColorDisabled; }
    public static Color getBackColorSelected() { return backColorSelected; }
    public static Color getForeColorSelected() { return foreColorSelected; }
    public static ArrayList<JButton> getButtonList() {return buttonList;}

    public SidePanel(){

        int WIDTH = Screen.getWIDTH();
        int HEIGHT = Screen.getHEIGHT();

        int BUTTONWIDTH = WIDTH/5;
        int BUTTONHEIGHT = HEIGHT/25;

        panel.setBounds(0,0,WIDTH/5,HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(backColorDisabled);

        //ENCRIPT/DECRYPT
        ComponentCreator.labelSetup(option, "Select your option:", false, 0, 4*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, panel);
        ComponentCreator.labelEdit(option, labelFont, null, foreColor);
        ComponentCreator.buttonSetup(encrypt, "Encrypt", 0, 5*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(encrypt, buttonFont, backColor, foreColor);
        encrypt.setFocusable(false);
        ComponentCreator.buttonSetup(decrypt, "Decrypt", 0, 6*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(decrypt, buttonFont, backColor, foreColor);
        decrypt.setFocusable(false);

        //INPUT
        ComponentCreator.labelSetup(input, "Select the input format:", false, 0, 8*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, panel);
        ComponentCreator.labelEdit(input, labelFont, null, foreColor);
        ComponentCreator.buttonSetup(textInput, "Text Input", 0, 9*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(textInput, buttonFont, backColor, foreColor);
        textInput.setFocusable(false);
        ComponentCreator.buttonSetup(fileInput, "File Input", 0, 10*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(fileInput, buttonFont, backColor, foreColor);
        fileInput.setFocusable(false);

        //ALGORITHM
        ComponentCreator.labelSetup(algorithm, "Select the algorithm:", false, 0, 12*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, panel);
        ComponentCreator.labelEdit(algorithm, labelFont, null, foreColor);
        ComponentCreator.buttonSetup(aesButton, "AES", 0, 13*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(aesButton, buttonFont, backColor, foreColor);
        aesButton.setFocusable(false);
        ComponentCreator.buttonSetup(chacha20Button, "ChaCha20", 0, 14*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(chacha20Button, buttonFont, backColor, foreColor);
        chacha20Button.setFocusable(false);
        ComponentCreator.buttonSetup(desButton, "DES", 0, 15*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(desButton, buttonFont, backColor, foreColor);
        desButton.setFocusable(false);
        ComponentCreator.buttonSetup(rsaButton, "RSA", 0, 16*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(rsaButton, buttonFont, backColor, foreColor);
        rsaButton.setFocusable(false);
        ComponentCreator.buttonSetup(eccButton, "ECC", 0, 17*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(eccButton, buttonFont, backColor, foreColor);
        eccButton.setFocusable(false);
        ComponentCreator.buttonSetup(sha256Button, "SHA-256", 0, 18*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(sha256Button, buttonFont, backColor, foreColor);
        sha256Button.setFocusable(false);
        ComponentCreator.buttonSetup(md5Button, "MD5", 0, 19*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(md5Button, buttonFont, backColor, foreColor);
        md5Button.setFocusable(false);        
        ComponentCreator.buttonSetup(kyberButton, "Kyber", 0, 20*BUTTONHEIGHT, BUTTONWIDTH, BUTTONHEIGHT, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(kyberButton, buttonFont, backColor, foreColor);
        kyberButton.setFocusable(false);
        
    }

    public static void toggleEncript() {

        encrypt.setForeground(foreColorSelected);
        encrypt.setBackground(backColorSelected);
        decrypt.setForeground(foreColor);
        decrypt.setBackground(backColor);

        deselectAlgorithmButtons();

        sha256Button.setBackground(backColor);
        sha256Button.setForeground(foreColor);
        sha256Button.setEnabled(true);
        md5Button.setBackground(backColor);
        md5Button.setForeground(foreColor);
        md5Button.setEnabled(true);

        CenterPanel.getTitle().setText("");
        CenterPanel.hideAll();
    
    }

    public static void toggleDecript() {
    
        decrypt.setForeground(foreColorSelected);
        decrypt.setBackground(backColorSelected);
        encrypt.setForeground(foreColor);
        encrypt.setBackground(backColor);

        deselectAlgorithmButtons();
    
        sha256Button.setBackground(backColorDisabled);
        sha256Button.setForeground(foreColorDisabled);
        sha256Button.setEnabled(false);
        md5Button.setBackground(backColorDisabled);
        md5Button.setForeground(foreColorDisabled);
        md5Button.setEnabled(false);

        CenterPanel.getTitle().setText("");
        CenterPanel.hideAll();
    
    }

    public static void toggleText() {
        textInput.setForeground(foreColorSelected);
        textInput.setBackground(backColorSelected);
        fileInput.setForeground(foreColor);
        fileInput.setBackground(backColor);

        deselectAlgorithmButtons();

        CenterPanel.getTitle().setText("");
        CenterPanel.hideAll();
    }

    public static void toggleFile() {
        fileInput.setForeground(foreColorSelected);
        fileInput.setBackground(backColorSelected);
        textInput.setForeground(foreColor);
        textInput.setBackground(backColor);

        deselectAlgorithmButtons();

        CenterPanel.getTitle().setText("");
        CenterPanel.hideAll();
    }

    public static void deselectAlgorithmButtons() {
        
        // deselect all
        aesButton.setBackground(backColor);
        aesButton.setForeground(foreColor);
        chacha20Button.setBackground(backColor);
        chacha20Button.setForeground(foreColor);
        desButton.setBackground(backColor);
        desButton.setForeground(foreColor);
        rsaButton.setBackground(backColor);
        rsaButton.setForeground(foreColor);
        eccButton.setBackground(backColor);
        eccButton.setForeground(foreColor);
        sha256Button.setBackground(backColor);
        sha256Button.setForeground(foreColor);
        md5Button.setBackground(backColor);
        md5Button.setForeground(foreColor);
        kyberButton.setBackground(backColor);
        kyberButton.setForeground(foreColor);

    }

    public static void selectAlgorithmButton(JButton button) {
        // select
        button.setBackground(backColorSelected);
        button.setForeground(foreColorSelected);
    }

}
