package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screen.SidePanel;

public class MyActionListener implements ActionListener {

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
        else if(e.getSource()==SidePanel.getAesButton()){ //SIDEPANEL: AES BUTTON

        }
        else if(e.getSource()==SidePanel.getChacha20Button()){ //SIDEPANEL: CHACHA20 BUTTON

        }
        else if(e.getSource()==SidePanel.getDesButton()){ //SIDEPANEL: DES BUTTON

        }
        else if(e.getSource()==SidePanel.getRsaButton()){ //SIDEPANEL: RSA BUTTON

        }
        else if(e.getSource()==SidePanel.getEccButton()){ //SIDEPANEL: ECC BUTTON

        }
        else if(e.getSource()==SidePanel.getSha256Button()){ //SIDEPANEL: SHA-256 BUTTON

        }
        else if(e.getSource()==SidePanel.getMd5Button()){ //SIDEPANEL: MD5 BUTTON

        }
        else if(e.getSource()==SidePanel.getKyberButton()){ //SIDEPANEL: KYBER BUTTON

        }

    }
    
}
