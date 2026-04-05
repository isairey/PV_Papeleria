package PuntoVentas.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PuntoVentas.model.ClassProductos;
import PuntoVentas.model.ConnectorMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerREGISTROproveedores {
	@FXML
	private Button Salir;
	@FXML
	private Button Guardar;
	@FXML
	private TextField CAJAnombreProvee;
	@FXML
	private TextField CAJAnombreProduct;
	@FXML
	private TextField CAJAfolioproduct;

	
	
	
	@FXML
	public void regresarLogin() {
		try {
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasLISTADO.fxml"));
			Scene scene = new Scene (root2);
			Stage primaryLayout = new Stage();
			primaryLayout.setScene(scene);
			primaryLayout.setTitle("FXMLPuntoVentasLOGIN");
			primaryLayout.show();
			Stage nuevaEscena =(Stage) this.Salir.getScene().getWindow();
			nuevaEscena.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private Button Regresar;
	@FXML
	public void cargarListado() {
		try {
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("FXMLPuntoVentasLISTADO.fxml"));
			Scene scene = new Scene (root2);
			Stage primaryLayout = new Stage();
			primaryLayout.setScene(scene);
			primaryLayout.setTitle("FXMLPuntoVentasLISTADO");
			primaryLayout.show();
			Stage nuevaEscena =(Stage) this.Regresar.getScene().getWindow();
			nuevaEscena.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	public void agregar()throws SQLException{
		Connection cn = ConnectorMySQL.getConnection();
        try {
        	System.out.println("HOLI agregar proveedores");
            String sSQLL = "INSERT INTO listaproveedores (nombreProveedor,nombreProduct,folioProduct) VALUES(?,?,?)";
            PreparedStatement stt = cn.prepareStatement(sSQLL);
            stt.setString(1,(CAJAnombreProvee.getText()));
            stt.setString(2,CAJAnombreProduct.getText());
            stt.setString(3,CAJAfolioproduct.getText());
//            stt.setFloat(4,Float.parseFloat(cajaPrecio.getText()));
//            stt.setString(5,cajanombreProveedor.getText());
            stt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        CAJAnombreProvee.clear();
        CAJAnombreProduct.clear();
        CAJAfolioproduct.clear();
//        cajaPrecio.clear();
//        cajanombreProveedor.clear();
    }

}
