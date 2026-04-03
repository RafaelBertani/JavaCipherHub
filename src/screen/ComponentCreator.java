package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import listeners.MyActionListener;

public class ComponentCreator {

    /*
     * Esta classe contém vários métodos para facilitar
     * a montagem de interfaces gráficas
    */

    public static void frameSetup(JFrame frameName, boolean setVisible, int setDefaultCloseOperation, int width, int height){
        frameName.setDefaultCloseOperation(setDefaultCloseOperation);
        frameName.setVisible(true);
        frameName.setSize(width,height);
    }

    public static void frameEdit(JFrame frameName, String title, Color back){
        frameName.setTitle(title);
        if(back!=null){frameName.getContentPane().setBackground(back);}
    }

    public static void panelSetup(JPanel panelName, JFrame frame, int x, int y, int width, int height){

        panelName.setBounds(x,y,width,height);
        panelName.setLayout(null);
        
        frame.add(panelName);
        
    }

    public static void panelOnPanelSetup(JPanel panelName, JPanel panel_of_panel, int x, int y, int width, int height){

        panelName.setBounds(x,y,width,height);
        panelName.setLayout(null);
        
        panel_of_panel.add(panelName);
        
    }

    public static void panelEdit(JPanel panelName, boolean transparent, Color back){
        if(transparent){panelName.setOpaque(false); panelName.setBackground(new Color(0,0,0,64));}
        else{panelName.setOpaque(true); panelName.setBackground(back);}
    }
    
    public static void labelSetup(JLabel label, String text_label, boolean borda, int x, int y, int width, int height, JPanel labelPanel){

        label.setText(text_label);
        label.setBounds(x, y, width, height);
        if(borda==false){label.setBorder(null);}
        else{label.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));}
        label.setHorizontalAlignment(JLabel.CENTER);
        
        labelPanel.add(label);

    }
    
    public static void labelEdit(JLabel label, Font f, Color back, Color fore){
        if(f!=null){label.setFont(f);}
        if(back!=null){label.setOpaque(true); label.setBackground(back);}
        if(fore!=null){label.setForeground(fore);}
    }

    public static void textfieldSetup(JTextField textfield, String text, int x, int y, int width, int height, boolean isEditable, boolean border, JPanel textfieldPanel){

        textfield.setBounds(x, y, width, height);
        textfield.setEditable(isEditable);
        textfield.setText(text);
        if(border==false){textfield.setBorder(null);}
        textfield.setHorizontalAlignment(JTextField.LEFT); //left

        textfieldPanel.add(textfield);

    }

    public static void textfieldEdit(JTextField textfield, Font f, Color back, Color fore){
        if(f!=null){textfield.setFont(f);}
        if(back!=null){textfield.setBackground(back);}
        if(fore!=null){textfield.setForeground(fore);}
    }

    public static void passwordfieldSetup(JPasswordField passwordfield, int x, int y, int height, int width, boolean border, JPanel passwordfieldPanel){

        passwordfield.setBounds(x, y, height, width);
        passwordfield.setEditable(true);
        passwordfield.setText("");
        if(border==false){passwordfield.setBorder(null);}
        passwordfield.setHorizontalAlignment(JTextField.LEFT);

        passwordfieldPanel.add(passwordfield);

    }
    
    public static void passwordfieldEdit(JTextField passwordfield, Font f, Color back, Color fore){
        if(f!=null){passwordfield.setFont(f);}
        if(back!=null){passwordfield.setBackground(back);}
        if(fore!=null){passwordfield.setForeground(fore);}
    }

    public static void buttonSetup(JButton button, String button_text, int x, int y, int width, int height, MyActionListener actionclass, JPanel panel_button){
        
        button.setText(button_text);
        button.addActionListener(actionclass);
        button.setBounds(x,y,width,height);

        panel_button.add(button);

    }

    public static void buttonEdit(JButton button, Font f, Color back, Color fore){
        if(f!=null){button.setFont(f);}
        if(back!=null){button.setBackground(back);}
        if(fore!=null){button.setForeground(fore);}
    }

    public static void comboboxSetup(JComboBox<String> combobox, int x, int y, int width, int height, int nOfLines, int initialItem, JPanel comboboxPanel){
    
        combobox.setMaximumRowCount(nOfLines);
        combobox.setEditable(false);
        combobox.setBounds(x,y,width,height);
        combobox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        combobox.setSelectedIndex(initialItem);
        
        //centralize
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        combobox.setRenderer(dlcr);
        
        comboboxPanel.add(combobox);
        
    }

    public static void comboboxEdit(JComboBox<String> combobox, Font f, Color back, Color fore){
        if(f!=null){combobox.setFont(f);}
        if(back!=null){combobox.setBackground(back);}
        if(fore!=null){combobox.setForeground(fore);}
    }
   
    public static void radiobuttonSetup(JRadioButton rb, String radiobuttonText, int x, int y, int width, int height, ButtonGroup bg, JPanel rbPanel){
        
        rb.setText(radiobuttonText);
        rb.setBounds(x,y,width,height);    
        
        rb.setActionCommand(rb.getText());
        
        bg.add(rb);

        rbPanel.add(rb);
    
    }

    public static void radiobuttonEdit(JRadioButton rb, Font f, Color back, Color fore){

        if(f!=null){rb.setFont(f);}
        if(back!=null){rb.setBackground(back);}
        if(fore!=null){rb.setForeground(fore);}

    }

    public static void imageSetupNoResize(JLabel label, String completePath, int x, int y, int width, int height, JPanel labelPanel) {

        label.setBounds(x, y, width, height);
        label.setIcon(new ImageIcon(completePath));

        labelPanel.add(label);

    }

    public static void imageSetup(JLabel label, String completePath, int x, int y, int width, int height, JPanel labelPanel) {
        label.setBounds(x, y, width, height);
        ImageIcon icon = new ImageIcon(completePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));

        labelPanel.add(label);
    }

    public static void dragAndDropSetup(JLabel label, String completePath, String labelTitle, int x, int y, int width, int height, MouseListener ml, JPanel labelPanel){

        label.setName(labelTitle);
        label.setBounds(x,y,width,height);
        label.setIcon(new ImageIcon(completePath));
        
        label.addMouseListener(ml);
        label.setTransferHandler(new TransferHandler("icon"));
        labelPanel.add(label);

    }

    public static void barSetup(JProgressBar bar, java.awt.Color back, java.awt.Color fore, int value, int minimun, int maximum, int x, int y, int width, int height, JPanel barPanel){
        bar.setBackground(back);
        bar.setForeground(fore);
        //bar.setOrientation(JProgressBar.CENTER);
        bar.setMinimum(minimun);
        bar.setMaximum(maximum);
        bar.setValue(value);
        bar.setBounds(x,y,width,height);
        barPanel.add(bar);
    }

    public static void checkboxSetup(JCheckBox checkbox, String text, int x, int y, int width, int height, boolean selected, JPanel checkboxPanel){
        checkbox.setText(text);
        checkbox.setBounds(x, y, width, height);
        checkbox.setSelected(selected);
        checkboxPanel.add(checkbox);
    }

    public static void checkboxEdit(JCheckBox checkbox, Font font, Color back, Color fore){
        if(font!=null){checkbox.setFont(font);}
        if(back!=null){checkbox.setBackground(back);}
        if(fore!=null){checkbox.setForeground(fore);}
    }

    public static void menubar_setup(JMenuBar nome_menubar, String[] principais, List<String[]> secundarios, List<String[]> terciario, MyActionListener actionclass, JFrame jframe_da_menubar){
        
        int contador = 0;
        int subitemEmenu = -1; //localização da linha no terceario com os subsubitens
        for(String item : principais){
            JMenu menu = new JMenu(item);
            for(String subitem : secundarios.get(contador)){    
                
                subitemEmenu = -1;
                // key1,value,value,value
                // key2,value,value,value
                for(int k=0;k<terciario.size();k++){if(terciario.get(k)[0].equals(subitem)){subitemEmenu=k;}}

                if(subitemEmenu!=-1){ //se item é menu e possui subitens
                    JMenu i = new JMenu(subitem); //item é um menu
                    for(int j=1;j<terciario.get(subitemEmenu).length;j++){ //percorre todos os subitens do menu que foi descoberto
                        JMenuItem subsubitem = new JMenuItem(terciario.get(subitemEmenu)[j]); //item do menu descoberto
                        i.add(subsubitem); //adiciona item no menu (ou em outras palavras, subitem no submenu)
                        subsubitem.addActionListener(actionclass); //actionlistener no subitem
                    }
                    menu.add(i); //menu principal recebe o menu (agora arrumado) descoberto
                }
                else{JMenuItem i = new JMenuItem(subitem);menu.add(i);i.addActionListener(actionclass);}
                subitemEmenu=-1;

            }
            nome_menubar.add(menu);
            contador++;
        }


        jframe_da_menubar.setJMenuBar(nome_menubar);

    }

    public static void errorMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static int optionsMessage(String message, String title, String options[]){
        return JOptionPane.showOptionDialog(null,message,title,JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,options,null);
    }

    public static void informationMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
