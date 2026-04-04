package listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        if(e.getSource()==SidePanel.getEncryptButton()){ //SIDEPANEL: ENCRYPT BUTTON
            SidePanel.toggleEncript();
        }
        else if(e.getSource()==SidePanel.getDecryptButton()){ //SIDEPANEL: DECRYPT BUTTON
            SidePanel.toggleDecript();
        }
        else if(e.getSource()==SidePanel.getTextInputButton()){ //SIDEPANEL: TEXT INPUT BUTTON
            SidePanel.toggleText();
        }
        else if(e.getSource()==SidePanel.getFileInputButton()){ //SIDEPANEL: FILE INPUT BUTTON
            SidePanel.toggleFile();
        }
        else if( checkSelection() ){

            String op1 = (SidePanel.getEncryptButton().getBackground()==SidePanel.getBackColorSelected())?"Encrypt":"Decrypt";
            String op2 = (SidePanel.getTextInputButton().getBackground()==SidePanel.getBackColorSelected())?"Text Input":"File Input";
         
            if(e.getSource()==SidePanel.getAesButton()){ //SIDEPANEL: AES BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - AES");
            }
            else if(e.getSource()==SidePanel.getChacha20Button()){ //SIDEPANEL: CHACHA20 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - CHACHA20");
            }
            else if(e.getSource()==SidePanel.getDesButton()){ //SIDEPANEL: DES BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - DES");
            }
            else if(e.getSource()==SidePanel.getRsaButton()){ //SIDEPANEL: RSA BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - RSA");
            }
            else if(e.getSource()==SidePanel.getEccButton()){ //SIDEPANEL: ECC BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - ECC");
            }
            else if(e.getSource()==SidePanel.getSha256Button()){ //SIDEPANEL: SHA-256 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - SHA-256");
            }
            else if(e.getSource()==SidePanel.getMd5Button()){ //SIDEPANEL: MD5 BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - MD5");
            }
            else if(e.getSource()==SidePanel.getKyberButton()){ //SIDEPANEL: KYBER BUTTON
                CenterPanel.getTitle().setText(op1+" - "+op2+" - KYBER");
            }

        }

    }
    
}
