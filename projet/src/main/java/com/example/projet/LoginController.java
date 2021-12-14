import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginController   {

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
    protected void login() {

        String uname = login.getText();
        String pass = password.getText();

        if (uname.equals("") && pass.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Champs vides ");
            alert.showAndWait();
            login.setText("");
            password.setText("");
            login.requestFocus();
        }

             try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection( "jdbc:sqlite:data.db" );
                pst = connection.prepareStatement("select * from users where login=(?) and password=(?)");
                pst.setString(1,uname);
                pst.setString(2,pass);

                rs = pst.executeQuery();

                if (rs.next()) {

                    System.out.println("found it");
                    /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Utilisateur trouvé! ");
                    alert.showAndWait(); */
                    AnchorPane pane;
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("listerdv.fxml"));
                pane = fxmlLoader.load();
                MainContext.getChildren().setAll(pane);


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
                System.out.println(""+e.getMessage());
            } 
        }


}
