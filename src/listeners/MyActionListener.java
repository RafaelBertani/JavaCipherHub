package listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import screen.CenterPanel;
import screen.ComponentCreator;
import screen.SidePanel;

public class MyActionListener implements ActionListener {



    public static boolean checkSelection() {
        
        boolean check = true;

        Color selected = SidePanel.getBackColorSelected();

        if( 
            SidePanel.getEncryptButton().getBackground()!=selected &&
            SidePanel.getDecryptButton().getBackground()!=selected
        ) {
            ComponentCreator.errorMessage("You need to select if you want do encrypt or decrypt", "Error");
            check = false;
        }
        else if( 
            SidePanel.getTextInputButton().getBackground()!=selected &&
            SidePanel.getFileInputButton().getBackground()!=selected
        ) {
            ComponentCreator.errorMessage("You need to select the input origin", "Error");
            check = false;
        }

        return check;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();

        if(source==SidePanel.getEncryptButton()){ //SIDEPANEL: ENCRYPT BUTTON
            SidePanel.toggleEncript();
        }
        else if(source==SidePanel.getDecryptButton()){ //SIDEPANEL: DECRYPT BUTTON
            SidePanel.toggleDecript();
        }
        else if(source==SidePanel.getTextInputButton()){ //SIDEPANEL: TEXT INPUT BUTTON
            SidePanel.toggleText();
        }
        else if(source==SidePanel.getFileInputButton()){ //SIDEPANEL: FILE INPUT BUTTON
            SidePanel.toggleFile();
        }
        else if(source==CenterPanel.getFileButton()){ //CENTERPANEL: SELECT FILE BUTTON
            
            try {
                CenterPanel.setInputFile(ComponentCreator.filechooserSetup("Select your input file"));
            } catch (Exception exc) {
                ComponentCreator.errorMessage("Failed to load input file", "Error");
            }

        }
        else if(source==CenterPanel.getOutputButton()){ //CENTERPANEL: SELECT OUTPUT BUTTON
        
            try {
                CenterPanel.setOutputFile(ComponentCreator.folderchooserSetup("Select your output folder"));
            } catch (Exception exc) {
                ComponentCreator.errorMessage("Failed to read output folder", "Error");
            }

        }
        else if( checkSelection() ){ // SIDEPANEL: ALGORITHMS BUTTONS

            String op1 = (SidePanel.getEncryptButton().getBackground()==SidePanel.getBackColorSelected())?"Encrypt":"Decrypt";
            String op2 = (SidePanel.getTextInputButton().getBackground()==SidePanel.getBackColorSelected())?"Text Input":"File Input";
         
            JButton button = (JButton) e.getSource();
            SidePanel.deselectAlgorithmButtons();
            SidePanel.selectAlgorithmButton(button);

            if(op2.equals("Text Input")){CenterPanel.toggleText();
            }else{CenterPanel.toggleFile();}

            
            if(button==SidePanel.getAesButton()){ //SIDEPANEL: AES BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - AES");
            }
            else if(button==SidePanel.getChacha20Button()){ //SIDEPANEL: CHACHA20 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - CHACHA20");
            }
            else if(button==SidePanel.getDesButton()){ //SIDEPANEL: DES BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - DES");
            }
            else if(button==SidePanel.getRsaButton()){ //SIDEPANEL: RSA BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - RSA");
            }
            else if(button==SidePanel.getEccButton()){ //SIDEPANEL: ECC BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - ECC");
            }
            else if(button==SidePanel.getSha256Button()){ //SIDEPANEL: SHA-256 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - SHA-256");
            }
            else if(button==SidePanel.getMd5Button()){ //SIDEPANEL: MD5 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - MD5");
            }
            else if(button==SidePanel.getKyberButton()){ //SIDEPANEL: KYBER BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - KYBER");
            }

        }

    }
    
}
