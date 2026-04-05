package screen;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CenterPanel {

    private static final JPanel panel = new JPanel();
    private static JLabel title = new JLabel();

    // === INPUT TEXT / FILE ===
    private static JLabel textLabel = new JLabel();
    private static JTextArea textArea = new JTextArea();
    private static JScrollPane scrollPane;

    private static JLabel fileLabel = new JLabel();
    private static JTextField fileText = new JTextField();
    private static JButton fileButton = new JButton();

    // === OUTPUT ===
    private static JLabel outputLabel = new JLabel();
    private static JTextField outputFolder = new JTextField();
    private static JLabel outputName = new JLabel();
    private static JTextField outputType = new JTextField();
    private static JButton outputButton = new JButton();

    // === PARÂMETROS POR ALGORITMO (todos invisíveis no início) ===
    private static JLabel paramTitle = new JLabel();

    private static JLabel passwordLabel = new JLabel();
    private static JPasswordField passwordField = new JPasswordField();

    private static JLabel saltLabel = new JLabel();
    private static JTextField saltField = new JTextField();

    private static JLabel ivLabel = new JLabel();
    private static JTextField ivField = new JTextField();

    private static JLabel keyBase64Label = new JLabel();
    private static JTextField keyBase64Field = new JTextField();

    private static JLabel modeLabel = new JLabel();
    private static JComboBox<String> modeCombo = new JComboBox<>();

    private static JLabel paddingLabel = new JLabel();
    private static JComboBox<String> paddingCombo = new JComboBox<>();

    private static JLabel keySizeLabel = new JLabel();
    private static JComboBox<String> keySizeCombo = new JComboBox<>();

    private static JLabel iterationsLabel = new JLabel();
    private static JTextField iterationsField = new JTextField();

    private static JLabel publicKeyLabel = new JLabel();
    private static JTextField publicKeyField = new JTextField();

    private static JLabel privateKeyLabel = new JLabel();
    private static JTextField privateKeyField = new JTextField();

    // === BOTÃO PROCESSAR ===
    private static JButton processButton = new JButton();

    private static Font buttonFont = new Font("Arial", Font.PLAIN, 15);
    private static Font titleFont = new Font("Arial", Font.PLAIN, 31);
    private static Font labelFont = new Font("Arial", Font.PLAIN, 19);
    private static Font paramFont = new Font("Arial", Font.PLAIN, 16);

    private static Color backColor = new Color(23, 23, 45);
    private static Color foreColor = Color.WHITE;
    private static Color backColorDisabled = new Color(12, 12, 23);
    private static Color foreColorDisabled = Color.GRAY;
    private static Color backColorSelected = new Color(46, 46, 90);
    private static Color foreColorSelected = Color.WHITE;

    private static File inputFile;
    private static File outputFile;

    private static String currentAlgorithm = "";
    public static String getCurrentAlgorithm() { return currentAlgorithm; }

    // ====================== GETTERS ======================
    public static JPanel getPanel() { return panel; }
    public static JLabel getTitle() { return title; }
    public static JTextField getFileText() { return fileText; }
    public static JTextField getOutputType() { return outputType; }
    public static JButton getFileButton() { return fileButton; }
    public static JButton getOutputButton() { return outputButton; }
    public static JButton getProcessButton() { return processButton; }
    public static File getInputFile() { return inputFile; }
    public static File getOutputFile() { return outputFile; }
    public static JTextField getOutputFolder() { return outputFolder; }

    // Getters dos campos de parâmetros (usados depois para chamar as classes de criptografia)
    public static String getPassword() { return new String(passwordField.getPassword()); }
    public static String getSalt() { return saltField.getText(); }
    public static String getIvOrNonce() { return ivField.getText(); }
    public static String getKeyBase64() { return keyBase64Field.getText(); }
    public static String getMode() { return (String) modeCombo.getSelectedItem(); }
    public static String getPadding() { return (String) paddingCombo.getSelectedItem(); }
    public static String getKeySize() { return (String) keySizeCombo.getSelectedItem(); }
    public static int getIterations() {
        try { return Integer.parseInt(iterationsField.getText()); }
        catch (Exception e) { return 10000; }
    }
    public static String getPublicKeyBase64() { return publicKeyField.getText(); }
    public static String getPrivateKeyBase64() { return privateKeyField.getText(); }
    public static JTextArea getTextArea() { return textArea; }

    // ====================== SETTERS ======================
    public static void setInputFile(File f) { inputFile = f; }
    public static void setOutputFile(File f) { outputFile = f; }

    public CenterPanel() {
        int WIDTH = Screen.getWIDTH();
        int HEIGHT = Screen.getHEIGHT();

        panel.setBounds(WIDTH/5 + 1, 0, 4*WIDTH/5 - 1, HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(backColorDisabled);

        ComponentCreator.labelSetup(title, "", false, 0, 0, 4*WIDTH/5, HEIGHT/8, panel);
        ComponentCreator.labelEdit(title, titleFont, backColorDisabled, foreColor);
        title.setHorizontalAlignment(JLabel.CENTER);

        // === TEXT INPUT ===
        ComponentCreator.labelSetup(textLabel, "Digite seu texto", false, 10, 4*HEIGHT/25, 4*WIDTH/5-35, HEIGHT/20, panel);
        ComponentCreator.labelEdit(textLabel, labelFont, backColorDisabled, foreColor);
        textLabel.setVisible(false);

        textArea.setLineWrap(true); textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(new Color(30,30,60));
        textArea.setForeground(foreColor);
        textArea.setVisible(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 4*HEIGHT/25 + HEIGHT/20, 4*WIDTH/5 - 35, HEIGHT/4);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVisible(false);
        panel.add(scrollPane);

        // === FILE INPUT ===
        ComponentCreator.labelSetup(fileLabel, "Seu arquivo de entrada", false, 10, 4*HEIGHT/25, 4*WIDTH/5-35, HEIGHT/20, panel);
        ComponentCreator.labelEdit(fileLabel, labelFont, backColorDisabled, foreColor);
        fileLabel.setVisible(false);
        ComponentCreator.textfieldSetup(fileText, "", 10, 4*HEIGHT/25+HEIGHT/15, 4*WIDTH/5-35, HEIGHT/20, false, true, panel);
        ComponentCreator.textfieldEdit(fileText, labelFont, backColorSelected, foreColorDisabled);
        fileText.setVisible(false);
        ComponentCreator.buttonSetup(fileButton, "Selecionar arquivo", 10, 4*HEIGHT/25+HEIGHT/15+HEIGHT/15, 4*WIDTH/5-35, HEIGHT/20, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(fileButton, buttonFont, backColorSelected, foreColor);
        fileButton.setVisible(false);

        // === OUTPUT ===
        ComponentCreator.labelSetup(outputLabel, "Pasta de saída", false, 10, 27*HEIGHT/40, 4*WIDTH/5-35, HEIGHT/20, panel);
        ComponentCreator.labelEdit(outputLabel, labelFont, backColorDisabled, foreColor);
        outputLabel.setVisible(false);
        ComponentCreator.textfieldSetup(outputFolder, "", 10, 27*HEIGHT/40+HEIGHT/20, 4*WIDTH/5-35, HEIGHT/20, false, true, panel);
        ComponentCreator.textfieldEdit(outputFolder, labelFont, backColorSelected, foreColorDisabled);
        outputFolder.setVisible(false);
        ComponentCreator.labelSetup(outputName, "Nome do arquivo:", false, 10, 27*HEIGHT/40+2*HEIGHT/20, WIDTH/5, HEIGHT/20, panel);
        ComponentCreator.labelEdit(outputName, labelFont, backColorDisabled, foreColor);
        outputName.setVisible(false);
        ComponentCreator.textfieldSetup(outputType, ".enc", 10+WIDTH/5, 27*HEIGHT/40+2*HEIGHT/20, 3*WIDTH/5-35, HEIGHT/20, true, true, panel);
        ComponentCreator.textfieldEdit(outputType, labelFont, backColorSelected, foreColor);
        outputType.setVisible(false);
        ComponentCreator.buttonSetup(outputButton, "Selecionar pasta", 10+3*WIDTH/10, 27*HEIGHT/40+3*HEIGHT/20, WIDTH/5-35, HEIGHT/20, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(outputButton, buttonFont, backColorSelected, foreColor);
        outputButton.setVisible(false);

        // === PARÂMETROS DOS ALGORITMOS (4 POR LINHA - COMPACTADO) ===
        int paramStartY = (int)(46.0 * HEIGHT / 100);
        int rowHeight   = 42;                             // altura de cada linha
        int fullW       = 4 * WIDTH / 5 - 45;
        int colWidth    = (fullW - 60) / 4;               // largura aproximada para 4 colunas

        int col1 = 10;
        int col2 = col1 + colWidth + 15;
        int col3 = col2 + colWidth + 15;
        int col4 = col3 + colWidth + 15;

        // Título
        ComponentCreator.labelSetup(paramTitle, "", false, 10, paramStartY - 38, fullW, HEIGHT/20, panel);
        ComponentCreator.labelEdit(paramTitle, new Font("Arial", Font.BOLD, 20), backColorDisabled, foreColor);

        int y = paramStartY;

        // LINHA 1 (4 colunas)
        ComponentCreator.labelSetup(passwordLabel, "Password:", false, col1, y, 140, 30, panel);
        ComponentCreator.labelEdit(passwordLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.passwordfieldSetup(passwordField, col1 + 150, y, 30, colWidth - 140, true, panel);
        passwordField.setVisible(false);

        ComponentCreator.labelSetup(saltLabel, "Salt:", false, col2, y, 100, 30, panel);
        ComponentCreator.labelEdit(saltLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(saltField, "", col2 + 110, y, colWidth - 110, 30, true, true, panel);
        saltField.setVisible(false);

        ComponentCreator.labelSetup(keySizeLabel, "Key Size:", false, col3, y, 120, 30, panel);
        ComponentCreator.labelEdit(keySizeLabel, paramFont, backColorDisabled, foreColor);
        keySizeCombo.addItem("128"); keySizeCombo.addItem("192"); keySizeCombo.addItem("256");
        ComponentCreator.comboboxSetup(keySizeCombo, col3 + 130, y, colWidth - 130, 30, 5, 2, panel);
        keySizeCombo.setVisible(false);

        ComponentCreator.labelSetup(iterationsLabel, "Iterations:", false, col4, y, 110, 30, panel);
        ComponentCreator.labelEdit(iterationsLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(iterationsField, "10000", col4 + 120, y, colWidth - 120, 30, true, true, panel);
        iterationsField.setVisible(false);

        y += rowHeight;

        // LINHA 2 (4 colunas)
        ComponentCreator.labelSetup(ivLabel, "IV/Nonce:", false, col1, y, 140, 30, panel);
        ComponentCreator.labelEdit(ivLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(ivField, "", col1 + 150, y, colWidth - 140, 30, true, true, panel);
        ivField.setVisible(false);

        ComponentCreator.labelSetup(keyBase64Label, "Key (Base64):", false, col2, y, 140, 30, panel);
        ComponentCreator.labelEdit(keyBase64Label, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(keyBase64Field, "", col2 + 150, y, colWidth - 140, 30, true, true, panel);
        keyBase64Field.setVisible(false);

        ComponentCreator.labelSetup(modeLabel, "Mode:", false, col3, y, 100, 30, panel);
        ComponentCreator.labelEdit(modeLabel, paramFont, backColorDisabled, foreColor);
        modeCombo.addItem("CBC"); modeCombo.addItem("GCM"); modeCombo.addItem("CTR");
        ComponentCreator.comboboxSetup(modeCombo, col3 + 110, y, colWidth - 110, 30, 5, 0, panel);
        modeCombo.setVisible(false);

        ComponentCreator.labelSetup(paddingLabel, "Padding:", false, col4, y, 100, 30, panel);
        ComponentCreator.labelEdit(paddingLabel, paramFont, backColorDisabled, foreColor);
        paddingCombo.addItem("PKCS5Padding"); paddingCombo.addItem("NoPadding");
        ComponentCreator.comboboxSetup(paddingCombo, col4 + 110, y, colWidth - 110, 30, 5, 0, panel);
        paddingCombo.setVisible(false);

        y += rowHeight;

        // LINHA 3 (2 colunas - campos longos)
        ComponentCreator.labelSetup(publicKeyLabel, "Public Key (Base64):", false, col1, y, 200, 30, panel);
        ComponentCreator.labelEdit(publicKeyLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(publicKeyField, "", col1 + 210, y, fullW - 210, 30, true, true, panel);
        publicKeyField.setVisible(false);

        y += rowHeight;

        // LINHA 4 (2 colunas - campos longos)
        ComponentCreator.labelSetup(privateKeyLabel, "Private Key (Base64):", false, col1, y, 200, 30, panel);
        ComponentCreator.labelEdit(privateKeyLabel, paramFont, backColorDisabled, foreColor);
        ComponentCreator.textfieldSetup(privateKeyField, "", col1 + 210, y, fullW - 210, 30, true, true, panel);
        privateKeyField.setVisible(false);

        // === BOTÃO PROCESSAR ===
        ComponentCreator.buttonSetup(processButton, "PROCESSAR", 10, HEIGHT - 90, 4*WIDTH/5 - 40, 60, Screen.myActionListener, panel);
        ComponentCreator.buttonEdit(processButton, new Font("Arial", Font.BOLD, 20), new Color(0, 180, 0), Color.WHITE);
        processButton.setVisible(false);

        hideAllParams();
    }

    public static void toggleText(){
        fileLabel.setVisible(false);
        fileText.setVisible(false);
        fileButton.setVisible(false);
    
        textLabel.setVisible(true);
        textArea.setVisible(true);
        scrollPane.setVisible(true);

        outputLabel.setVisible(true);
        outputFolder.setVisible(true);
        outputName.setVisible(true);
        outputType.setVisible(true);
        outputButton.setVisible(true);

        hideAllParams();
    }

    public static void toggleFile(){
        textLabel.setVisible(false);
        textArea.setVisible(false);
        scrollPane.setVisible(false);

        fileLabel.setVisible(true);
        fileText.setVisible(true);
        fileButton.setVisible(true);

        outputLabel.setVisible(true);
        outputFolder.setVisible(true);
        outputName.setVisible(true);
        outputType.setVisible(true);
        outputButton.setVisible(true);

        hideAllParams();
    }

    public static void hideAll(){
        textLabel.setVisible(false);
        textArea.setVisible(false);
        scrollPane.setVisible(false);

        fileLabel.setVisible(false);
        fileText.setVisible(false);
        fileButton.setVisible(false);

        outputLabel.setVisible(false);
        outputFolder.setVisible(false);
        outputName.setVisible(false);
        outputType.setVisible(false);
        outputButton.setVisible(false);

        hideAllParams();
    }

    public static void hideAllParams() {
        paramTitle.setVisible(false);
        passwordLabel.setVisible(false); passwordField.setVisible(false);
        saltLabel.setVisible(false); saltField.setVisible(false);
        ivLabel.setVisible(false); ivField.setVisible(false);
        keyBase64Label.setVisible(false); keyBase64Field.setVisible(false);
        modeLabel.setVisible(false); modeCombo.setVisible(false);
        paddingLabel.setVisible(false); paddingCombo.setVisible(false);
        keySizeLabel.setVisible(false); keySizeCombo.setVisible(false);
        iterationsLabel.setVisible(false); iterationsField.setVisible(false);
        publicKeyLabel.setVisible(false); publicKeyField.setVisible(false);
        privateKeyLabel.setVisible(false); privateKeyField.setVisible(false);
        processButton.setVisible(false);
    }

    public static void showAESFields() {
        hideAllParams();
        paramTitle.setText("AES Parameters"); paramTitle.setVisible(true);
        passwordLabel.setVisible(true); passwordField.setVisible(true);
        saltLabel.setVisible(true); saltField.setVisible(true);
        ivLabel.setText("IV (Base64):"); ivLabel.setVisible(true); ivField.setVisible(true);
        keyBase64Label.setVisible(true); keyBase64Field.setVisible(true);
        modeLabel.setVisible(true); modeCombo.setVisible(true);
        paddingLabel.setVisible(true); paddingCombo.setVisible(true);
        keySizeLabel.setVisible(true); keySizeCombo.setVisible(true);
        iterationsLabel.setVisible(true); iterationsField.setVisible(true);
        processButton.setText("PROCESSAR AES"); processButton.setVisible(true);
    }

    public static void showChaCha20Fields() {
        hideAllParams();
        paramTitle.setText("ChaCha20 Parameters"); paramTitle.setVisible(true);
        passwordLabel.setVisible(true); passwordField.setVisible(true);
        saltLabel.setVisible(true); saltField.setVisible(true);
        ivLabel.setText("Nonce (Base64):"); ivLabel.setVisible(true); ivField.setVisible(true);
        keyBase64Label.setVisible(true); keyBase64Field.setVisible(true);
        keySizeLabel.setText("Key Size (bits):"); keySizeLabel.setVisible(true); keySizeCombo.setVisible(true);
        iterationsLabel.setVisible(true); iterationsField.setVisible(true);
        processButton.setText("PROCESSAR CHACHA20"); processButton.setVisible(true);
    }

    public static void showDESFields() {
        hideAllParams();
        currentAlgorithm = "DES";
        paramTitle.setText("DES Parameters"); 
        paramTitle.setVisible(true);

        passwordLabel.setVisible(true); 
        passwordField.setVisible(true);
        saltLabel.setVisible(true); 
        saltField.setVisible(true);
        ivLabel.setText("IV (Base64):"); 
        ivLabel.setVisible(true); 
        ivField.setVisible(true);
        keyBase64Label.setVisible(true); 
        keyBase64Field.setVisible(true);
        modeLabel.setVisible(true); 
        modeCombo.setVisible(true);
        paddingLabel.setVisible(true); 
        paddingCombo.setVisible(true);
        keySizeLabel.setVisible(true); 
        keySizeCombo.setVisible(true);
        iterationsLabel.setVisible(true); 
        iterationsField.setVisible(true);

        processButton.setText("PROCESSAR DES"); 
        processButton.setVisible(true);
    }

    public static void showRSAFields() {
        hideAllParams();
        currentAlgorithm = "RSA";
        paramTitle.setText("RSA Parameters (Chaves assimétricas)"); 
        paramTitle.setVisible(true);

        publicKeyLabel.setVisible(true); 
        publicKeyField.setVisible(true);
        privateKeyLabel.setVisible(true); 
        privateKeyField.setVisible(true);

        processButton.setText("PROCESSAR RSA"); 
        processButton.setVisible(true);
    }

    public static void showECCFields() {
        hideAllParams();
        currentAlgorithm = "ECC";
        paramTitle.setText("ECC Parameters (Assinatura ECDSA)"); 
        paramTitle.setVisible(true);

        privateKeyLabel.setVisible(true); 
        privateKeyField.setVisible(true);

        processButton.setText("PROCESSAR ECC (Assinatura)"); 
        processButton.setVisible(true);
    }

    public static void showSHA256Fields() {
        hideAllParams();
        currentAlgorithm = "SHA256";
        paramTitle.setText("SHA-256 Parameters (Hash)"); 
        paramTitle.setVisible(true);

        passwordLabel.setVisible(true); 
        passwordField.setVisible(true);
        saltLabel.setVisible(true); 
        saltField.setVisible(true);

        processButton.setText("PROCESSAR SHA-256"); 
        processButton.setVisible(true);
    }

    public static void showMD5Fields() {
        hideAllParams();
        currentAlgorithm = "MD5";
        paramTitle.setText("MD5 Parameters (Hash)"); 
        paramTitle.setVisible(true);

        passwordLabel.setVisible(true); 
        passwordField.setVisible(true);
        saltLabel.setVisible(true); 
        saltField.setVisible(true);

        processButton.setText("PROCESSAR MD5"); 
        processButton.setVisible(true);
    }

    public static void showKyberFields() {
        hideAllParams();
        currentAlgorithm = "KYBER";
        paramTitle.setText("Kyber Parameters (Post-quantum KEM)"); 
        paramTitle.setVisible(true);

        publicKeyLabel.setVisible(true); 
        publicKeyField.setVisible(true);
        privateKeyLabel.setVisible(true); 
        privateKeyField.setVisible(true);

        processButton.setText("PROCESSAR KYBER"); 
        processButton.setVisible(true);
    }

}
