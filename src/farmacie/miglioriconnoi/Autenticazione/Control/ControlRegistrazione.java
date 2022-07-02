package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataRegistrazione;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.AlertMessage;
import farmacie.miglioriconnoi.Common.ItemListeners;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlRegistrazione {
    public static void setListeners(){
        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataAutenticazione");
        SchermataRegistrazione.GoBack.addListener(AC_GoBack);

        ItemListener IT_mostraPassword = ItemListeners.createItemListenerMostraPasswordAndConfirmPassword(SchermataRegistrazione.passwordText,SchermataRegistrazione.confirmPasswordText);
        SchermataRegistrazione.mostraPassword.addItemListener(IT_mostraPassword);

        ActionListener AC_Registrati = ActionListeners.createListenerButtonRegistrati();
        SchermataRegistrazione.registrazioneButton.addListener(AC_Registrati);
    }

    public static void registraUtente(){
        //CONTROLLA SE LA MANSIONE È STATA SELEZIONATA O NO
        if(!SchermataRegistrazione.mansioneSelected){
            SchermataRegistrazione.campoErrato.setText("");
            SchermataRegistrazione.campoErrato.setText("NON È STATA SELEZIONATA NESSUNA MANSIONE");
        }
        else{
            //PRENDE I DATI DAI CAMPI DI TESTO E CONTROLLA CHE SIANO IDONEI E CHE LE PASSWORD COINCIDANO
            if(!SchermataRegistrazione.passwordText.getText().equals(SchermataRegistrazione.confirmPasswordText.getText())) {
                SchermataRegistrazione.campoErrato.setText("");
                SchermataRegistrazione.campoErrato.setText("LE PASSWORD NON COINCIDONO");
            }
            else
            {
                SchermataRegistrazione.emailMatcher = SchermataRegistrazione.emailPattern.matcher(SchermataRegistrazione.emailText.getText());
                SchermataRegistrazione.passwordMatcher = SchermataRegistrazione.passwordPattern.matcher(SchermataRegistrazione.passwordText.getText());
                //SE IL CAMPO EMAIL NON È IDONEO MOSTRA LA SCRITTA IN ROSSO
                if(!SchermataRegistrazione.emailMatcher.matches()){
                    SchermataRegistrazione.campoErrato.setText("");
                    SchermataRegistrazione.campoErrato.setText("L'EMAIL DEVE ESSERE COMPOSTA DA UN NOME UTENTE CHE CONTIENE SOLO NUMERI, CARATTERI E I CARATTERISPECIALI ' . ',' - ',' _ ', " + System.lineSeparator() + "SEGUITI DA UNA CHIOCCIOLA E CON UN DOMINIO DI ALMENO 4 LETTERE DEL TIPO: 'example.com' o 'example.com'");
                }
                else{
                    //SE IL CAMPO PASSWORD NON È IDONEO MOSTRA LA SCRITTA IN ROSSO
                    if(!SchermataRegistrazione.passwordMatcher.matches()){
                        SchermataRegistrazione.campoErrato.setText("");
                        SchermataRegistrazione.campoErrato.setText("LA PASSWORD DEVE ESSERE COMPOSTA DA ALMENO 6 CARATTERI DI CUI ALMENO UNA LETTERA MAIUSOLA, UNA LETTERA MINUSCOLA," + System.lineSeparator() + "E UN CARATTERE SPECIALE TRA I SEGUENTI: ' . ',' @ ',' # ',' $ ',' % ',' ^ ',' & ',' + ',' = ', NON SONO AMMESSI SPAZI");
                    }
                    else{
                        //SE I CAMPI EMAIL E PASSWORD SONO IDONEI CERCA NEL DATABASE SE ESISTE GIÀ UN UTENTE CON LA STESSA MAIL
                        //PRENDE LA MAIL DAL DATABASE, SE È NULL L'UTENTE NON SI È ANCORA REGISTRATO
                        SchermataRegistrazione.campoErrato.setText("");
                        ResultSet mailResultSet = null;
                        try {
                            mailResultSet = Main.dbms_Azienda.getData("SELECT email from dbms_azienda.utente WHERE email = '" + SchermataRegistrazione.emailText.getText() + "';");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                 SQLException ex) {
                            ex.printStackTrace();
                        }

                        try {
                            if (mailResultSet.next() != false) {
                                try {
                                    mailResultSet.first();
                                    SchermataRegistrazione.mail = mailResultSet.getString(1);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


                        //FINE RICERCA NEL DATABASE
                        if(SchermataRegistrazione.emailText.getText().equals(SchermataRegistrazione.mail)){
                            //SE L'UTENTE È GIÀ REGISTRATO ALLORA MOSTRA UN ALLERT
                            JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE GIÀ REGISTRATO");
                        }
                        else{
                            //SE L'UTENTE NON È REGISTRATO LO REGISTRA
                            if(SchermataRegistrazione.mansioneString.equals("Farmacista"))
                            {
                                AlertMessage datiAggiuntivi = new AlertMessage(SchermataRegistrazione.emailText.getText(), SchermataRegistrazione.passwordText.getText());
                                SchermataRegistrazione.emailText.setText("Mail");
                                SchermataRegistrazione.passwordText.setText("Password");
                                SchermataRegistrazione.confirmPasswordText.setText("Password");
                            }
                            else if(SchermataRegistrazione.mansioneString.equals("Corriere")){
                                try {
                                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Stato`) VALUES ('" + SchermataRegistrazione.emailText.getText() + "', '" + SchermataRegistrazione.passwordText.getText() + "', '"+ SchermataRegistrazione.mansioneString +"', '0');");
                                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`corriere` (`Email`, `Password`, `Mansione`) VALUES ('" + SchermataRegistrazione.emailText.getText() + "', '" + SchermataRegistrazione.passwordText.getText() + "', '"+ SchermataRegistrazione.mansioneString +"');");
                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE REGISTRATO CON SUCCESSO");
                                SchermataRegistrazione.emailText.setText("Mail");
                                SchermataRegistrazione.passwordText.setText("Password");
                                SchermataRegistrazione.confirmPasswordText.setText("Password");
                                Main.cardLayout.show(Main.mainPanel, "SchermataAutenticazione");
                            }
                            else {
                                try {
                                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Stato`) VALUES ('" + SchermataRegistrazione.emailText.getText() + "', '" + SchermataRegistrazione.passwordText.getText() + "', '"+ SchermataRegistrazione.mansioneString +"', '0');");
                                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`impiegato_azienda` (`Email`, `Password`, `Mansione`) VALUES ('" + SchermataRegistrazione.emailText.getText() + "', '" + SchermataRegistrazione.passwordText.getText() + "', '"+ SchermataRegistrazione.mansioneString +"');");
                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE REGISTRATO CON SUCCESSO");
                                SchermataRegistrazione.emailText.setText("Mail");
                                SchermataRegistrazione.passwordText.setText("Password");
                                SchermataRegistrazione.confirmPasswordText.setText("Password");
                                Main.cardLayout.show(Main.mainPanel, "SchermataAutenticazione");
                            }
                        }
                    }
                }
            }
        }
    }
}
