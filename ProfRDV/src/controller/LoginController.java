package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Eleve;
import modele.Prof;
import modele.User;


public class LoginController  implements Initializable {

     User user = main.Main.user;
    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private AnchorPane MainContext;
    @FXML
    private TextField login;
    @FXML
    private Button ok;
    @FXML
    private PasswordField password;



    @FXML
    public  void login(ActionEvent event) {
        String uname = login.getText();
        String pass = password.getText();

        if (uname.equals("") && pass.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Champs vides ");
            alert.showAndWait();
            login.setText("");
            password.setText("");
            login.requestFocus(); }
        
             try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
                pst = connection.prepareStatement("select * from users where Login=(?) and Password=(?)");
                pst.setString(1,uname);
                pst.setString(2,pass);

                rs = pst.executeQuery();

                if (rs.next()) {
                    if(rs.getString("Type").equals("Etudiant")){
                        this.user.setEleve(new Eleve(rs.getString("uId"),rs.getString("Nom"),rs.getString("Email")));
                        affiche_listrdvetudiant();
                    } else{
                        if(rs.getString("Type").equals("Professeur")){
                            this.user.setProf(new Prof(rs.getString("uId"),rs.getString("Nom"),rs.getString("Email"),null));
                            affiche_listrdvprof();

                        } else {

                            if(rs.getString("Type").equals("root")){
                                connection.close();
                                Stage stage = main.Main.getStage();
                                Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/AdminPagev2.fxml"));
                                stage.setScene(new Scene(fxmlLoader, 600, 500));
                            }
                        }
                    }
                }

                 else {
                    System.out.println("not exist");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Utilisateur non trouvé ");
                    alert.showAndWait();
                    login.setText("");
                    password.setText("");
                    login.requestFocus();
                } 
            }
            
            catch (Exception e){
                System.out.println(""+e.getMessage()); }
             
        }

        @FXML
        public void closeApplication(){
            Platform.exit();
        }
    
        @FXML
        public void seDeconnecter() throws IOException{
            Stage stage = main.Main.getStage();
            main.Main.user = new User();
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            stage.setScene(new Scene(fxmlLoader, 600, 500));
        }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void affiche_listrdvetudiant() throws SQLException, IOException{
        connection.close();
        Stage stage = main.Main.getStage();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/listerdv-2.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    public void affiche_listrdvprof() throws SQLException, IOException{
        connection.close();
        Stage stage = main.Main.getStage();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/listerdvProf.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

        

}

