package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlLogin {

    public static void setListeners(){
        ActionListener AC_Login = createListenerButtonLogin();
        SchermataLogin.buttonLogin.addListener(AC_Login);

        ItemListener IL_MostraPassword = createItemListenerMostraPassword();
        SchermataLogin.mostraPassword.addItemListener(IL_MostraPassword);

        ActionListener AC_GoBack = createActionListenerButtonGoBack();
        SchermataLogin.buttonTornaIndietro.addListener(AC_GoBack);
    }

    public static ActionListener createActionListenerButtonGoBack(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MOSTRA LA SCHERMATA PRECEDENTE
                Main.mainFrame.setVisible(true);
                Main.cardLayout.show(Main.mainPanel, "SchermataAutenticazione");
            }
        };
        return AC;
    }

    public static ItemListener createItemListenerMostraPassword(){
        ItemListener itemlistener = new ItemListener() {public void itemStateChanged(ItemEvent itemE) {
            int st = itemE.getStateChange();
            if (st == ItemEvent.SELECTED) {
                SchermataLogin.passwordField.setEchoChar((char) 0);
            }
            if (st == ItemEvent.DESELECTED) {
                SchermataLogin.passwordField.setEchoChar('*');
            }
        }};

        return itemlistener;
    }

    private static String getEmail_View(){
        return SchermataLogin.emailField.getText();
    }

    public static ActionListener createListenerButtonLogin(){
        ActionListener AC_Login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PRENDE I DATI DALLA SCHERMATA
                String email_Insert = getEmail_View();

                //PRENDE I DATI DAL DATABASE
                ResultSet queryResult1 = null;
                ResultSet queryResult2 = null;
                try {
                    queryResult1 = Main.dbms_Azienda.getData("SELECT email, mansione from dbms_azienda.utente WHERE email = '" + email_Insert + "';");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    if(queryResult1.next() != false){
                        SchermataLogin.email = null;
                        try {
                            queryResult1.first();
                            SchermataLogin.email = queryResult1.getString(1);
                            SchermataLogin.mansione = queryResult1.getString(2);
                            //nomeFarmacia = queryResult1.getString(2);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            queryResult2 = Main.dbms_Azienda.getData("SELECT dbms_azienda.utente.Password FROM dbms_azienda.utente WHERE dbms_azienda.utente.Email = '" + SchermataLogin.email + "' AND dbms_azienda.utente.Password = '" + SchermataLogin.passwordField.getText() + "';");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            ex.printStackTrace();
                        }
                        if(queryResult2.next() != false){
                            String password = null;
                            try {
                                queryResult2.first();
                                password = queryResult2.getString(1);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            ResultSet result = null;
                            if(SchermataLogin.mansione.equals("Farmacista")){
                                try {
                                    result = Main.dbms_Azienda.getData("SELECT nome_farmacia FROM dbms_azienda.farmacista WHERE Email = '" + SchermataLogin.email + "' AND password = '" + password + "';");
                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    ex.printStackTrace();
                                }

                                try {
                                    result.first();
                                    SchermataLogin.nomeFarmacia = result.getString(1);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }


                            //SE COMBACIA CAMBIA SCHERMATA
                            try {
                                Main.dbms_Azienda.setData("UPDATE `dbms_azienda`.`utente` SET `Stato` = '1' WHERE (`Email` = '"+SchermataLogin.email+"');;");
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                ex.printStackTrace();
                            }
                            Main.cardLayout.show(Main.mainPanel, "Schermata" + SchermataLogin.mansione);
                            SchermataLogin.emailField.setText("Mail");
                            SchermataLogin.passwordField.setText("Password");

                            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
                            //tornare indietro tramite apposito bottone
                            Button.lastView = "SchermataLogin";
                        } else {
                            JOptionPane.showMessageDialog(Main.schermataLoginPanel, "Password errata");
                        }
                    } else {
                        JOptionPane.showMessageDialog(Main.schermataLoginPanel, "Non esiste alcun account associato a questa Email");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        } ;

        return AC_Login;
    }
}
