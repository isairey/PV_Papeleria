package PuntoVentas.view;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.StringUtils;

import PuntoVentas.model.ClassProductos;
import PuntoVentas.model.ConnectorMySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerReportes {
	@FXML
    private TableColumn<ClassProductos, String> cantidad;
	@FXML
    private TableColumn<ClassProductos, String> precioCU;
	@FXML
    private TableColumn<ClassProductos, String> cantidadProducto;
	@FXML
    private TableColumn<ClassProductos, String> folio;
	@FXML
    private TableColumn<ClassProductos, String> precio;
	@FXML
    private TableColumn<ClassProductos, String> nombreProduct;
    @FXML
    private TableView<ClassProductos> tablaReporte;
    @FXML
    ObservableList<ClassProductos> productList = FXCollections.observableArrayList();
	@FXML
	private Button Salir,buscarFolio;
	@FXML
	private TextField consola,folioReporte,ivaText,gananciaText,fechaText;

	public ObservableList<ClassProductos> getPersonList(){
		ObservableList<ClassProductos> productList = FXCollections.observableArrayList();
		Connection connection = ConnectorMySQL.getConnection();
		Statement st;
		ResultSet rs;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select * from datosproduct");
			ClassProductos Productos;
			while(rs.next()) {
				Productos = new ClassProductos(rs.getString("tipoProduct"),rs.getInt("CantidadProduct"),rs.getInt("IDtipoProduct"),rs.getString("nombre"),rs.getString("folioproduct"),rs.getFloat("precio"),rs.getString("nombreProveedor"));
				productList.add(Productos);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	

    

    
	public static String leerArchivo (String fileName)throws Exception {
		
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	    
	  }
	
	public static String leerArchivo2 (String data) {
		Pattern pattern2 = Pattern.compile("%(.*?)%", Pattern.DOTALL);
	    Matcher matcher2 = pattern2.matcher(data);
	    while (matcher2.find()) {
	        data=matcher2.group(1);
	    }
		return data;

	    
	  }
	
	@FXML
	public void ejecutar() throws Exception {
		tablaReporte.getItems().clear();
		productList.clear();
		Connection connection = ConnectorMySQL.getConnection();
		Statement st;
		ResultSet rs;
		String data=null;
		
		ArrayList<String> cola = new ArrayList<String>();
		
		String fecha = null;
		if(folioReporte.getText().isEmpty()) {
			consola.setText("Casilla de folio Vacia.");
		}
		else {
		
		String folioString= folioReporte.getText();
		try {
			data = leerArchivo("C:/Users/87257/Downloads/PuntoDeVentas(2)/TXTtickets/"+folioString+".txt"); 
		}
		catch(Exception e) {
			consola.setText("Casilla de folio no encontrada");
		}

		
	    Pattern pattern = Pattern.compile("<(.*?)>", Pattern.DOTALL);
	    Matcher matcher = pattern.matcher(data);
	    while (matcher.find()) {
	        cola.add(matcher.group(1));
	    }
	    
	    fecha=leerArchivo2(data);
	    
	    System.out.println(cola);
	    System.out.println(fecha);
	    
	    fechaText.setText(fecha);
	    
	    ClassProductos Productos;
	    for(int i=0;i<cola.size();i++) {
	    	String query = "SELECT * FROM datosproduct WHERE folioproduct = "+ cola.get(i);
	    	st = connection.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()) {
				Productos = new ClassProductos(rs.getString("tipoProduct"),rs.getInt("CantidadProduct"),rs.getInt("IDtipoProduct"),rs.getString("nombre"),rs.getString("folioproduct"),rs.getFloat("precio"),rs.getString("nombreProveedor"));
				productList.add(Productos);	
			}
			
			cantidadProducto.setCellValueFactory(new PropertyValueFactory<>("cantidadProduct"));
			nombreProduct.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));
			folio.setCellValueFactory(new PropertyValueFactory<>("folioProducto"));
			precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
			ObservableList<ClassProductos> list = productList;
			
			

			
	    	tablaReporte.getItems().setAll(list);

	    	
	    }
	    float preciosSinIVA = calcularPrecio();

		float iva=(float) (preciosSinIVA*(0.16));
		ivaText.setText(String.valueOf(iva));

		float preciosTotal = iva+preciosSinIVA;
		gananciaText.setText(String.valueOf(preciosTotal));
		}
	    
	}

	private float calcularPrecio() {
		float preciosSinIVA = 0;
		for (ClassProductos classProductos : productList) {
			preciosSinIVA += classProductos.getPrecio();
		}
		return preciosSinIVA;
	}
	
	public ClassProductos productoSeleccionado() {
		if (tablaReporte != null) {
		    List<ClassProductos> tabla = tablaReporte.getSelectionModel().getSelectedItems();
		    if (tabla.size() == 1) {
		        final ClassProductos seleccionada = tabla.get(0);
		        return seleccionada;
		    }
		}
		return null;
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
	
	
}
