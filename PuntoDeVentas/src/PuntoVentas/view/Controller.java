package PuntoVentas.view;

import java.io.IOException;
import PuntoVentas.model.ClassLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import PuntoVentas.Main;
import PuntoVentas.model.ClassProductos;
import PuntoVentas.model.ConnectorMySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

	public static String nombre = null;
	
	public static String tipoCuenta = null;
	@FXML
	AnchorPane anchorOpcion;
	@FXML
	private Button entrar;
	@FXML
	private Button Guardar;
	@FXML
	private TextField CAJAusuario,consola;
	@FXML
	private PasswordField CAJAcontrasenia;
	
	@FXML
	private ComboBox<String> tipoUsuario;
	
	@FXML
	public void initialize() {
	    tipoUsuario.getItems().removeAll(tipoUsuario.getItems());
	    tipoUsuario.getItems().addAll("Dueño", "Administrador", "Usuario");
	    tipoUsuario.getSelectionModel().select("Usuario");
	}
	
	
	@FXML
	public void guardar() throws SQLException{
		Connection cn = ConnectorMySQL.getConnection();
        try {
        	System.out.println("HOLI cargar inicio secion");
            String sSQLL = "INSERT INTO login (NombreUsuario,Contrasenia) VALUES(?,?)";
            PreparedStatement stt = cn.prepareStatement(sSQLL);
            stt.setString(1,(CAJAusuario.getText()));
            stt.setString(2,CAJAcontrasenia.getText());
            
            
            stt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        CAJAusuario.clear();
        CAJAcontrasenia.clear();
	}

	
	public void cargarListado() throws SQLException   {
		
		String bandera=tipoUsuario.getSelectionModel().getSelectedItem().toString();
		boolean bandera2=true;
		String user="vacio",pass="vacio",tipo="vacio";
		String caja1=CAJAusuario.getText();
		String caja2=CAJAcontrasenia.getText();
		Connection connection = ConnectorMySQL.getConnection();
		String query = "SELECT * FROM login where NombreUsuario='"+caja1+"' and Contrasenia='"+caja2+"'";
		Statement st;
		ResultSet rs;
		st = connection.createStatement();
		rs = st.executeQuery(query);
		while(rs.next()) {
			
			user = rs.getString("NombreUsuario");
			nombre=rs.getString("NombreUsuario");
			
			pass = rs.getString("contrasenia");

			tipo = rs.getString("tipoUsuario");
			
			switch(tipo) {

				case "Administrador":
					if(bandera.equals("Administrador")||bandera.equals("Usuario")) {
						
						System.out.println("pasa");
						
					}
					else
					{
						System.out.println("no pasa");
						bandera2=false;
					}
					break;
					
				case "Usuario":
					if(bandera.equals("Usuario")) {
						
						System.out.println("pasa");
						
					}
					else
					{
						System.out.println("no pasa");
						bandera2=false;
					}
					break;
					
			}
		}
		if(caja1.equals(user)&&caja2.equals(pass))
		{
			if(bandera2==false)
			{
				System.out.println("tipo de cuenta inaccesible");
				consola.setText("tipo de cuenta inaccesible");
			}
			else
			try {
				
				////////////////////////////////////////////////////////////////////
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("FXMLPuntoVentasLISTADO.fxml"));
				Parent TableViewParent=loader.load();
				
				Scene scene = new Scene (TableViewParent);
				ControllerListado controller = loader.getController();
				controller.initData(nombre,bandera);
				
				
				Stage primaryLayout = new Stage();
				primaryLayout.setScene(scene);
				primaryLayout.setTitle("FXMLPuntoVentasLISTADO");
				primaryLayout.show();
				Stage nuevaEscena =(Stage) this.entrar.getScene().getWindow();
				nuevaEscena.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Usuario o Contraseña incorrectos");
			consola.setText("Usuario o Contraseña incorrectos");
		}
		
		
	}
	
	@FXML
	private Button Registrarse;

	
	@FXML
	public void cargarRegistro() throws SQLException {

		try {
			
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasREGISTRO.fxml"));
			
			Scene scene = new Scene (root2);
			Stage primaryLayout = new Stage();
			primaryLayout.setScene(scene);
			primaryLayout.setTitle("FXMLPuntoVentasREGISTRO");
			primaryLayout.show();
			Stage nuevaEscena =(Stage) this.Registrarse.getScene().getWindow();
			nuevaEscena.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@FXML
	private Button Regresar;
	@FXML
	public void cargarOPCIONBASEDATOS() throws SQLException {
		try {
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasOPCIONBasedatos.fxml"));
			Scene scene = new Scene (root2);
			Stage primaryLayout = new Stage();
			primaryLayout.setScene(scene);
			primaryLayout.setTitle("FXMLPuntoVentasOPCIONBasedatos");
			primaryLayout.show();
			Stage nuevaEscena =(Stage) this.Regresar.getScene().getWindow();
			nuevaEscena.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
