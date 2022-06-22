import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataRecuperoCredenziali {

    private static TextField emailText;
    private static Button recuperaCredenziali_Button;
    private static String mail = "";
    private static String password;

    public SchermataRecuperoCredenziali() throws FileNotFoundException {
        JPanel fullView = new JPanel(new BorderLayout(0,200));
        JPanel northView = new JPanel(new BorderLayout());
        JPanel sudView = new JPanel(new BorderLayout());

        //PARTE NORD DELLA SCHERMATA
        Image logo = new Image("logo.png",100,100);
        Button GoBack = new Button("SchermataRegistrazione","Torna Indietro",150,50);
        GoBack.changeFontButton("Arial",1,15);
        GoBack.createListenerButtonGoBackAutenticazione();
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(GoBack);
        northView.add(logo,BorderLayout.WEST);
        northView.add(buttonsPanel,BorderLayout.EAST);
        //FINE PARTE NORD DELLA SCHERMATA


        //PARTE CENTRALE DELLA SCHERMATA
        JPanel boxCenterPanel = new JPanel();
        boxCenterPanel.setLayout(new BoxLayout(boxCenterPanel, BoxLayout.PAGE_AXIS));


        Image utenteImage = new Image("utente.png",100,100);
        utenteImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel logoPanel = new JPanel(new FlowLayout());
        logoPanel.add(utenteImage);
        boxCenterPanel.add(logoPanel);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        emailText = new TextField(30, "Mail", 150, 30);
        emailText.setPreferredSize(new Dimension(150,30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(emailText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));

        recuperaCredenziali_Button = new Button("RECUPERA CREDENZIALI", 300,50);
        recuperaCredenziali_Button.changeFontButton("Arial",1,20);
        createListenerButtonRecuperaCredenziali();
        JPanel buttonRecupera_Panel = new JPanel(new FlowLayout());
        buttonRecupera_Panel.add(recuperaCredenziali_Button);
        boxCenterPanel.add(buttonRecupera_Panel);

        JPanel boxFlowLayout = new JPanel(new FlowLayout());
        boxFlowLayout.add(boxCenterPanel);
        sudView.add(boxFlowLayout,BorderLayout.CENTER);
        //FINE PARTE CENTRALE DELLA SCHERMATA


        fullView.add(northView,BorderLayout.NORTH);
        fullView.add(sudView,BorderLayout.CENTER);
        Main.schermataRecuperoCredenzialiPanel.add(fullView,BorderLayout.CENTER);
    }

    public void createListenerButtonRecuperaCredenziali(){
        recuperaCredenziali_Button.addActionListener(e -> {
            ResultSet resultSet = null;
            try {
                resultSet = Main.dbms_Azienda.getData("SELECT email,password from dbms_azienda.utente WHERE email = '" + SchermataRecuperoCredenziali.emailText.getText() + "';");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }

            try {
                if(resultSet.next() != false) {
                    mail = null;
                    try {
                        resultSet.first();
                        mail = resultSet.getString(1);
                        password = resultSet.getString(2);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if(emailText.getText().equals(mail)){
                Frame frame = new Frame();
                Email em = new Email(mail,password);
                JOptionPane.showMessageDialog(frame, "TI ABBIAMO INVIATO UNA EMAIL CONTENENTE" +
                        "\nLE TUE CREDENZIALI ALLA MAIL FORNITA");
            }
            else{
                Frame frame = new Frame();
                JOptionPane.showMessageDialog(frame, "NON ESISTE UN ACCOUNT CON QUESTA MAIL");
            }

        });
    }

}
