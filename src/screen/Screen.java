package screen;

import java.awt.Color;
import javax.swing.*;
import listeners.MyActionListener;

public class Screen extends JFrame{
    
    public static JFrame mainFrame = new JFrame();
    public static JPanel mainPanel = new JPanel();
    
    public static MyActionListener myActionListener = new MyActionListener();

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;

    public static int getWIDTH() {return WIDTH;}
    public static int getHEIGHT() {return HEIGHT;}
    
    public Screen(){

        //inicia todos os panels
        SidePanel sidePanel = new SidePanel();
        
        // outros paineis não são adicionados diretamente no frame principal porque
        // ao serem chaveados requisitariam frame.setVisible(false), o que faria a tela
        // sumir e reaparecer, então, é melhor apenas panel.setVisible(false)
        mainPanel.add(SidePanel.getPanel());

        ComponentCreator.panelSetup(mainPanel, mainFrame, 0, 0, WIDTH, HEIGHT);
        ComponentCreator.panelEdit(mainPanel,true,Color.WHITE);
        
        ComponentCreator.frameSetup(mainFrame, true, JFrame.EXIT_ON_CLOSE, WIDTH, HEIGHT);
        ComponentCreator.frameEdit(mainFrame, "JavaCipherHub", null);
        
    }
    
}
