import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class LoginController  implements Initializable {

     User user = Login.user;
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
                connection = DriverManager.getConnection( "jdbc:sqlite:data-2.db" );
                pst = connection.prepareStatement("select * from users where Login=(?) and Password=(?)");
                pst.setString(1,uname);
                pst.setString(2,pass);

                rs = pst.executeQuery();

                 if (rs.next()) {

                    if(rs.getString("Type").equals("Etudiant")){
                        this.user.setEleve(new Eleve(rs.getString("uId"),rs.getString("Nom"),rs.getString("Email")));
                        affiche_listrdvetudiant();
                    } 
                    else{
                    if(rs.getString("Type").equals("Professeur")){
                        this.user.setProf(new Prof(rs.getString("uId"),rs.getString("Nom"),rs.getString("Email"),null));
                        affiche_listrdvetudiant();

                    } else {

                            if(rs.getString("Type").equals("root")){
                                connection.close();
                                AnchorPane pane;
                                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AdminPagev2.fxml"));
                                pane = fxmlLoader.load();
                                MainContext.getChildren().setAll(pane);
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        System.out.println(user.getTest());
    }



    public void affiche_listrdvetudiant() throws SQLException, IOException{
                connection.close();
                AnchorPane pane;
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("listerdv-2.fxml"));
                pane = fxmlLoader.load();
                MainContext.getChildren().setAll(pane);
    }


        

}

