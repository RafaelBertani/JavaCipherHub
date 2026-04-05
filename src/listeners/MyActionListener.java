package listeners;

import algorithms.AESCrypto;
import algorithms.ChaCha20Crypto;
import algorithms.DESCrypto;
import algorithms.ECCrypto;
import algorithms.MD5Crypto;
import algorithms.RSACrypto;
import algorithms.SHA256Crypto;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import screen.CenterPanel;
import screen.ComponentCreator;
import screen.SidePanel;

public class MyActionListener implements ActionListener {

    public static boolean checkSelection() {
        boolean check = true;
        Color selected = SidePanel.getBackColorSelected();

        if (SidePanel.getEncryptButton().getBackground() != selected &&
            SidePanel.getDecryptButton().getBackground() != selected) {
            ComponentCreator.errorMessage("Você precisa selecionar se quer ENCRIPTAR ou DECRIPTAR", "Erro");
            check = false;
        }
        else if (SidePanel.getTextInputButton().getBackground() != selected &&
                 SidePanel.getFileInputButton().getBackground() != selected) {
            ComponentCreator.errorMessage("Você precisa selecionar a origem da entrada (Texto ou Arquivo)", "Erro");
            check = false;
        }
        return check;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();

        // ==================== BOTÕES DO SIDEPANEL ====================
        if (source == SidePanel.getEncryptButton()) {
            SidePanel.toggleEncript();
        }
        else if (source == SidePanel.getDecryptButton()) {
            SidePanel.toggleDecript();
        }
        else if (source == SidePanel.getTextInputButton()) {
            SidePanel.toggleText();
        }
        else if (source == SidePanel.getFileInputButton()) {
            SidePanel.toggleFile();
        }

        // ==================== BOTÕES DO CENTERPANEL ====================
        else if (source == CenterPanel.getFileButton()) { // SELECT FILE
            try {
                CenterPanel.setInputFile(ComponentCreator.filechooserSetup("Selecione o arquivo de entrada"));
                if (CenterPanel.getInputFile() != null) {
                    CenterPanel.getFileText().setText(CenterPanel.getInputFile().getAbsolutePath());
                }
            } catch (Exception exc) {
                ComponentCreator.errorMessage("Falha ao carregar o arquivo de entrada", "Erro");
            }
        }
        else if (source == CenterPanel.getOutputButton()) { // SELECT OUTPUT FOLDER
            try {
                CenterPanel.setOutputFile(ComponentCreator.folderchooserSetup("Selecione a pasta de saída"));
                if (CenterPanel.getOutputFile() != null) {
                    CenterPanel.getOutputFolder().setText(CenterPanel.getOutputFile().getAbsolutePath());
                }
            } catch (Exception exc) {
                ComponentCreator.errorMessage("Falha ao selecionar a pasta de saída", "Erro");
            }
        }

        // ==================== BOTÃO PROCESSAR ====================
        else if (source == CenterPanel.getProcessButton()) {
            processCryptoOperation();
        }

        // ==================== BOTÕES DOS ALGORITMOS ====================
        else if (checkSelection()) {
            String op1 = (SidePanel.getEncryptButton().getBackground() == SidePanel.getBackColorSelected()) ? "Encrypt" : "Decrypt";
            String op2 = (SidePanel.getTextInputButton().getBackground() == SidePanel.getBackColorSelected()) ? "Text Input" : "File Input";

            JButton button = (JButton) e.getSource();
            SidePanel.deselectAlgorithmButtons();
            SidePanel.selectAlgorithmButton(button);

            if (op2.equals("Text Input")) {
                CenterPanel.toggleText();
            } else {
                CenterPanel.toggleFile();
            }

            if (button == SidePanel.getAesButton()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - AES");
                CenterPanel.showAESFields();
            }
            else if (button == SidePanel.getChacha20Button()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - CHACHA20");
                CenterPanel.showChaCha20Fields();   // método que você já tinha
            }
            else if (button == SidePanel.getDesButton()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - DES");
                CenterPanel.showDESFields();
            }
            else if (button == SidePanel.getRsaButton()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - RSA");
                CenterPanel.showRSAFields();
            }
            else if (button == SidePanel.getEccButton()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - ECC");
                CenterPanel.showECCFields();
            }
            else if (button == SidePanel.getSha256Button()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - SHA-256");
                CenterPanel.showSHA256Fields();
            }
            else if (button == SidePanel.getMd5Button()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - MD5");
                CenterPanel.showMD5Fields();
            }
            else if (button == SidePanel.getKyberButton()) {
                CenterPanel.getTitle().setText(op1 + " - " + op2 + " - KYBER");
                CenterPanel.showKyberFields();
            }
        }
    }

    // ==================== LÓGICA COMPLETA DE PROCESSAMENTO ====================
    private void processCryptoOperation() {
        if (CenterPanel.getOutputFile() == null) {
            ComponentCreator.errorMessage("Selecione a pasta de saída primeiro!", "Erro");
            return;
        }

        String outputFolderPath = CenterPanel.getOutputFile().getAbsolutePath();
        String filename = CenterPanel.getOutputType().getText().trim();
        if (filename.isEmpty()) filename = "output.enc";
        String fullOutputPath = outputFolderPath + File.separator + filename;

        boolean isEncrypt = SidePanel.getEncryptButton().getBackground() == SidePanel.getBackColorSelected();
        boolean isTextInput = SidePanel.getTextInputButton().getBackground() == SidePanel.getBackColorSelected();

        String algo = CenterPanel.getCurrentAlgorithm();

        try {
            switch (algo) {
                case "AES":
                    if (isTextInput) {
                        String data = CenterPanel.getTextArea().getText();
                        if (isEncrypt) AESCrypto.encryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else AESCrypto.decryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    } else {
                        String inputPath = CenterPanel.getInputFile().getAbsolutePath();
                        if (isEncrypt) AESCrypto.encryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else AESCrypto.decryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    }
                    ComponentCreator.informationMessage("AES concluído com sucesso!\nArquivo salvo em:\n" + fullOutputPath, "Sucesso");
                    break;

                case "CHACHA20":
                    // mesma lógica do AES (ChaCha20Crypto já existe)
                    if (isTextInput) {
                        String data = CenterPanel.getTextArea().getText();
                        if (isEncrypt) ChaCha20Crypto.encryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else ChaCha20Crypto.decryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    } else {
                        String inputPath = CenterPanel.getInputFile().getAbsolutePath();
                        if (isEncrypt) ChaCha20Crypto.encryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else ChaCha20Crypto.decryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    }
                    ComponentCreator.informationMessage("ChaCha20 concluído com sucesso!", "Sucesso");
                    break;

                case "DES":
                    // mesma lógica do AES (DESCrypto)
                    if (isTextInput) {
                        String data = CenterPanel.getTextArea().getText();
                        if (isEncrypt) DESCrypto.encryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else DESCrypto.decryptFromString(data, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    } else {
                        String inputPath = CenterPanel.getInputFile().getAbsolutePath();
                        if (isEncrypt) DESCrypto.encryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                        else DESCrypto.decryptFromFile(inputPath, fullOutputPath, CenterPanel.getPassword(), CenterPanel.getSalt(), CenterPanel.getIvOrNonce(), CenterPanel.getKeyBase64(), CenterPanel.getMode(), CenterPanel.getPadding(), Integer.parseInt(CenterPanel.getKeySize()), CenterPanel.getIterations());
                    }
                    ComponentCreator.informationMessage("DES concluído com sucesso!", "Sucesso");
                    break;

                case "RSA":
                    if (isTextInput) {
                        String data = CenterPanel.getTextArea().getText();
                        if (isEncrypt) RSACrypto.encryptFromString(data, fullOutputPath, CenterPanel.getPublicKeyBase64(), "PKCS1Padding");
                        else RSACrypto.decryptFromString(data, fullOutputPath, CenterPanel.getPrivateKeyBase64(), "PKCS1Padding");
                    } else {
                        String inputPath = CenterPanel.getInputFile().getAbsolutePath();
                        if (isEncrypt) RSACrypto.encryptFromFile(inputPath, fullOutputPath, CenterPanel.getPublicKeyBase64(), "PKCS1Padding");
                        else RSACrypto.decryptFromFile(inputPath, fullOutputPath, CenterPanel.getPrivateKeyBase64(), "PKCS1Padding");
                    }
                    ComponentCreator.informationMessage("RSA concluído com sucesso!", "Sucesso");
                    break;

                case "ECC":
                    if (!isEncrypt) {
                        ComponentCreator.errorMessage("Verificação de assinatura ECC ainda não implementada", "Aviso");
                        return;
                    }
                    if (isTextInput) {
                        ECCrypto.signFromString(CenterPanel.getTextArea().getText(), fullOutputPath, CenterPanel.getPrivateKeyBase64());
                    } else {
                        ECCrypto.signFromFile(CenterPanel.getInputFile().getAbsolutePath(), fullOutputPath, CenterPanel.getPrivateKeyBase64());
                    }
                    ComponentCreator.informationMessage("Assinatura ECC gerada com sucesso!", "Sucesso");
                    break;

                case "SHA256":
                    if (isTextInput) SHA256Crypto.hashFromString(CenterPanel.getTextArea().getText(), fullOutputPath, CenterPanel.getSalt(), CenterPanel.getPassword());
                    else SHA256Crypto.hashFromFile(CenterPanel.getInputFile().getAbsolutePath(), fullOutputPath, CenterPanel.getSalt(), CenterPanel.getPassword());
                    ComponentCreator.informationMessage("Hash SHA-256 gerado com sucesso!", "Sucesso");
                    break;

                case "MD5":
                    if (isTextInput) MD5Crypto.hashFromString(CenterPanel.getTextArea().getText(), fullOutputPath, CenterPanel.getSalt(), CenterPanel.getPassword());
                    else MD5Crypto.hashFromFile(CenterPanel.getInputFile().getAbsolutePath(), fullOutputPath, CenterPanel.getSalt(), CenterPanel.getPassword());
                    ComponentCreator.informationMessage("Hash MD5 gerado com sucesso!", "Sucesso");
                    break;

                case "KYBER":
                    ComponentCreator.informationMessage("Kyber ainda não implementado completamente.\nUse BouncyCastle + KyberCrypto quando estiver pronto.", "Aviso");
                    return;

                default:
                    ComponentCreator.errorMessage("Algoritmo não reconhecido", "Erro");
                    return;
            }

        } catch (Exception ex) {
            ComponentCreator.errorMessage("Erro durante a operação:\n" + ex.getMessage(), "Erro de Criptografia");
        }
    }
}