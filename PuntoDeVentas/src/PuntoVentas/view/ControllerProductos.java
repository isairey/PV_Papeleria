package PuntoVentas.view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import PuntoVentas.model.ConnectorMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import PuntoVentas.model.ClassProductos;

public class ControllerProductos implements Initializable{
	public String query1="select * from datosproduct order by IDtipoProduct ASC";
	@FXML
	private ComboBox<String> comboProductoFiltrado;
	@FXML
	private Button Regresar;
	@FXML
	private Button buttoneliminar;
	@FXML
	private Button buttoneditar;
	@FXML
	private Button buttonAgregar;
	@FXML
	private TextField cajatipoProduct;
	@FXML
	private TextField cajaIDtipoProduct;
	@FXML
	private TextField cajanombreProduct;
	@FXML
	private TextField cajafolioProduct;
	@FXML
	private TextField cajaprecio;
	@FXML
	private TextField cajanombreProvee;
	@FXML
	private TextField CAJAnPRODUCT;
	@FXML
    private TableColumn<ClassProductos, String> tipoProduct;
	@FXML
    private TableColumn<ClassProductos, String> CantidadProducto;
	@FXML
    private TableColumn<ClassProductos, String> idTipoProduct;
	@FXML
    private TableColumn<ClassProductos, String> nombreProduct;
	@FXML
    private TableColumn<ClassProductos, String> folio;
	@FXML
    private TableColumn<ClassProductos, String> precio;
	@FXML
    private TableColumn<ClassProductos, String> nombreProvee;
    @FXML
    private TableView<ClassProductos> tablaProduct;
    @FXML
    ObservableList<ClassProductos> productList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    	
    	if(comboProductoFiltrado.getItems().isEmpty()) {
    		comboProductoFiltrado.getItems().removeAll(comboProductoFiltrado.getItems());
    	    comboProductoFiltrado.getItems().addAll("Tipo de Producto", "ID", "Nombre","Folio","Nombre del Proveedor");
    	    comboProductoFiltrado.getSelectionModel().select("ID");
    	}
	    
    	tipoProduct.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    	CantidadProducto.setCellValueFactory(new PropertyValueFactory<>("cantidadProduct"));
		idTipoProduct.setCellValueFactory(new PropertyValueFactory<>("tipoProductoID"));
		nombreProduct.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));
		folio.setCellValueFactory(new PropertyValueFactory<>("folioProducto"));
		precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
		nombreProvee.setCellValueFactory(new PropertyValueFactory<>("nombreproveedor"));
		ObservableList<ClassProductos> list = getPersonList();
    	tablaProduct.getItems().setAll(list);
    	
    	final ObservableList<ClassProductos> tablaProducto = tablaProduct.getSelectionModel().getSelectedItems();
	    tablaProducto.addListener(selectorTablaProductos);
    }
		
	public ObservableList<ClassProductos> getPersonList(){
		ObservableList<ClassProductos> productList = FXCollections.observableArrayList();
		Connection connection = ConnectorMySQL.getConnection();
		Statement st;
		ResultSet rs;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query1);
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
	
	
	
    public void executeQuery(String query) {
		Connection conn = ConnectorMySQL.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private final ListChangeListener<ClassProductos> selectorTablaProductos =
            new ListChangeListener<ClassProductos>() {

				@Override
				public void onChanged(Change<? extends ClassProductos> arg0) {
					ponerProductoSeleccionada();
					
				}
            };
	
	private void ponerProductoSeleccionada() {
        final ClassProductos producto =  productoSeleccionado();
        productList.indexOf(producto);
        if (productList != null) {
        	cajatipoProduct.setText(producto.getTipoProducto());
        	CAJAnPRODUCT.setText(Integer.toString(producto.getCantidadProduct()));
        	cajaIDtipoProduct.setText(Integer.toString(producto.getTipoProductoID()));
        	cajanombreProduct.setText(producto.getNomProducto());
        	cajafolioProduct.setText(producto.getFolioProducto());
        	cajanombreProvee.setText(producto.getNombreproveedor());
            cajaprecio.setText(Float.toString(producto.getPrecio()));   
        }
    }

	public ClassProductos productoSeleccionado() {
	if (tablaProduct != null) {
	    List<ClassProductos> tabla = tablaProduct.getSelectionModel().getSelectedItems();
	    if (tabla.size() == 1) {
	        final ClassProductos seleccionada = tabla.get(0);
	        return seleccionada;
	    }
	}
	return null;
	}
	
	@FXML 
	private void filtrar(ActionEvent event) {
		String filtro = comboProductoFiltrado.getSelectionModel().getSelectedItem().toString();
		
		switch(filtro) {
		case "Folio":
			System.out.println(filtro);
			query1="select * from datosproduct order by folioproduct ASC";
			initialize(null, null);
			break;
		case "Tipo de Producto":
			System.out.println(filtro);
			query1="select * from datosproduct order by tipoProduct ASC";
			initialize(null, null);
			break;
		case "ID":
			System.out.println(filtro);
			query1="select * from datosproduct order by IDtipoProduct ASC";
			initialize(null, null);
			break;
		case "Nombre":
			System.out.println(filtro);
			query1="select * from datosproduct order by nombre ASC";
			initialize(null, null);
			break;
		case "Nombre del Proveedor":
			System.out.println(filtro);
			query1="select * from datosproduct order by nombreProveedor ASC";
			initialize(null, null);
			break;

		}
		
	}
    
	
	@FXML 
	private void modificar(ActionEvent event) {
		String query = "UPDATE datosproduct SET tipoProduct='"+cajatipoProduct.getText()+"',CantidadProducto='"+CAJAnPRODUCT.getText()+"',nombre='"+cajanombreProduct.getText()+"',folioproduct='"+cajafolioProduct.getText()+"',precio='"+cajaprecio.getText()+"',nombreProveedor='"+cajanombreProvee.getText()+"' WHERE IDtipoProduct='"+cajaIDtipoProduct.getText()+"'";
		executeQuery(query);
		ObservableList<ClassProductos> list = getPersonList();
		tablaProduct.getItems().setAll(list);
		
		 final ObservableList<ClassProductos> tablaPersonas = tablaProduct.getSelectionModel().getSelectedItems();
	     tablaPersonas.addListener(selectorTablaProductos);
		
    }
	
	@FXML
	private void eliminar(ActionEvent event) {
		String query = "DELETE FROM datosproduct WHERE IDtipoProduct="+cajaIDtipoProduct.getText()+"";
		executeQuery(query);
		ObservableList<ClassProductos> list = getPersonList();
		tablaProduct.getItems().setAll(list);
		
		 final ObservableList<ClassProductos> tablaPersonas = tablaProduct.getSelectionModel().getSelectedItems();
	        tablaPersonas.addListener(selectorTablaProductos);
    }
	
	@FXML
	private Button Salir;
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
