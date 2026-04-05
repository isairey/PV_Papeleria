package PuntoVentas.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import PuntoVentas.model.ConnectorMySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerRegistro {
	
	@FXML
	private Button Cancelar;
	@FXML
	private TextField CAJAnombre;
	@FXML
	private TextField CAJAapellidoP;
	@FXML
	private TextField CAJAcorreo;
	@FXML
	private TextField CAJAcontra;
	@FXML
	private TextField CAJAcontra2;
	@FXML
	private ComboBox<String> tipoUsuario;
	
	@FXML
	public void initialize() {
	    tipoUsuario.getItems().removeAll(tipoUsuario.getItems());
	    tipoUsuario.getItems().addAll("Dueño", "Administrador", "Usuario");
	    tipoUsuario.getSelectionModel().select("Usuario");

	}
	
	@FXML
	public void regresarLogin() {
		try {
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasLOGIN.fxml"));
			Scene scene = new Scene (root2);
			Stage primaryLayout = new Stage();
			primaryLayout.setScene(scene);
			primaryLayout.setTitle("FXMLPuntoVentasLOGIN");
			primaryLayout.show();
			Stage nuevaEscena =(Stage) this.Cancelar.getScene().getWindow();
			nuevaEscena.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void agregar()throws SQLException{
		Connection cn = ConnectorMySQL.getConnection();
        
        	try {
            	System.out.println("HOLI registro usuario");

                String sSQLL = "INSERT INTO listausuarios (tipoUsuario,nombre,apellidoPaterno,Correo,contrasena,contrasena2) VALUES(?,?,?,?,?,?)";
                PreparedStatement stt = cn.prepareStatement(sSQLL);
                
                stt.setString(1,(tipoUsuario.getSelectionModel().getSelectedItem().toString()));
                stt.setString(2,(CAJAnombre.getText()));
                stt.setString(3,(CAJAapellidoP.getText()));
                stt.setString(4,(CAJAcorreo.getText()));
                stt.setString(5,(CAJAcontra.getText()));
                stt.setString(6,(CAJAcontra2.getText()));
                stt.execute();
                
                
            }catch (SQLException e){
                e.printStackTrace();
            }
            CAJAnombre.clear();
            CAJAapellidoP.clear();
            CAJAcorreo.clear();
            CAJAcontra.clear();
            CAJAcontra2.clear();
        
}

    
	


	
	@FXML
	private Button Aceptar;
	@FXML
	public void cargarListado() throws SQLException {
		boolean nombre = CAJAnombre.getText().isEmpty();
		boolean apellido = CAJAapellidoP.getText().isEmpty();
		boolean pass = CAJAcontra.getText().isEmpty();
		boolean pass2 = CAJAcontra2.getText().isEmpty();
		Connection cn = ConnectorMySQL.getConnection();
		if(nombre == false || apellido == false || pass == false || pass2 == false ) 
		{
			try {
				
				String sSQLL2 = "INSERT INTO login (NombreUsuario,contrasenia,tipoUsuario) VALUES(?,?,?)";
	            PreparedStatement stt = cn.prepareStatement(sSQLL2);
	            stt.setString(1,(CAJAnombre.getText()));
	            stt.setString(2,(CAJAcontra.getText()));
	            stt.setString(3,(tipoUsuario.getSelectionModel().getSelectedItem().toString()));
	            stt.execute();
	            System.out.println("Registro 2");
	            agregar();
//		        try {
//		        	System.out.println("HOLI registro usuario");
	//
//		            String sSQLL = "INSERT INTO listausuarios (apellidoPaterno,nombre,Correo,contrasena,contrasena2) VALUES(?,?,?,?,?)";
//		            PreparedStatement stt = cn.prepareStatement(sSQLL);
//		            stt.setString(1,(CAJAnombre.getText()));
//		            stt.setString(2,(CAJAapellidoP.getText()));
//		            stt.setString(3,(CAJAcorreo.getText()));
//		            stt.setString(4,(CAJAcontra.getText()));
//		            stt.setString(5,(CAJAcontra2.getText()));
//		            stt.execute();
//		        }catch (SQLException e){
//		            e.printStackTrace();
//		        }
//		        CAJAnombre.clear();
//		        CAJAapellidoP.clear();
//		        CAJAcorreo.clear();
//		        CAJAcontra.clear();
//		        CAJAcontra2.clear();
				
				
				////////////////////////
				AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasLOGIN.fxml"));
				Scene scene = new Scene (root2);
				Stage primaryLayout = new Stage();
				primaryLayout.setScene(scene);
				primaryLayout.setTitle("FXMLPuntoVentasLOGIN");
				primaryLayout.show();
				Stage nuevaEscena =(Stage) this.Aceptar.getScene().getWindow();
				nuevaEscena.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Llenar los espacios en blanco.");
		}
		
	}
	

	
}
