package PuntoVentas.view;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import PuntoVentas.model.ClassProveedores;
import PuntoVentas.model.ClassProveedores;
import PuntoVentas.model.ConnectorMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerProveedores {
	private String query1 = "SELECT * FROM listaproveedores order by IDtipoProduct ASC ";
	@FXML
	private ComboBox<String> filtrado;
	@FXML
	private Button Regresar;
	@FXML
	private TextField CAJAnombrePovee;
	@FXML
	private TextField CAJAnombreProduct;
	@FXML
	private TextField CAJAfolioProduct;
	@FXML
	private TextField CAJAIDTipoProduct;
	
	@FXML
	private Button eliminar;
	@FXML
	private Button modificar;
	@FXML
    private TableView<ClassProveedores> tablaProvee;
	@FXML
    private TableColumn<ClassProveedores, String> ID;
	@FXML
    private TableColumn<ClassProveedores, String> nombre;
	@FXML
    private TableColumn<ClassProveedores, String> nomProduct;
	@FXML
    private TableColumn<ClassProveedores, String> folioProduct;
	@FXML
    ObservableList<ClassProveedores> proveedoresList = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		if(filtrado.getItems().isEmpty()) {
			filtrado.getItems().removeAll(filtrado.getItems());
			filtrado.getItems().addAll("ID", "Nombre de Proveedor", "Nombre de Producto","Folio");
			filtrado.getSelectionModel().select("ID");
    	}
		ID.setCellValueFactory(new PropertyValueFactory<>("tipoProductoID"));
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
		nomProduct.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
		folioProduct.setCellValueFactory(new PropertyValueFactory<>("folioProducto"));
		ObservableList<ClassProveedores> list = getPersonList();
		tablaProvee.getItems().setAll(list);
    	
    	final ObservableList<ClassProveedores> tablaProvees = tablaProvee.getSelectionModel().getSelectedItems();
	    tablaProvees.addListener(selectortablaProveedor);
    }
		
	public ObservableList<ClassProveedores> getPersonList(){
		ObservableList<ClassProveedores> proveedoresList = FXCollections.observableArrayList();
		Connection connection = ConnectorMySQL.getConnection();
		Statement st;
		ResultSet rs;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query1);
			ClassProveedores Proveedores;
			while(rs.next()) {
				Proveedores = new ClassProveedores(rs.getInt("IDtipoProduct"),rs.getString("nombreProveedor"),rs.getString("nombreProduct"),rs.getString("folioProduct"));
				proveedoresList.add(Proveedores);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return proveedoresList;
	}
	
	@FXML 
	private void filtrar(ActionEvent event) {
		String filtro = filtrado.getSelectionModel().getSelectedItem().toString();
		
		switch(filtro) {
		case "Folio":
			System.out.println(filtro);
			query1="SELECT * FROM listaproveedores order by folioProduct ASC ";
			initialize();
			break;
		case "Nombre de Proveedor":
			System.out.println(filtro);
			query1="SELECT * FROM listaproveedores order by nombreProveedor,IDtipoProduct ASC ";
			initialize();
			break;
		case "ID":
			System.out.println(filtro);
			query1="SELECT * FROM listaproveedores order by IDtipoProduct ASC ";
			initialize();
			break;
		case "Nombre de Producto":
			System.out.println(filtro);
			query1="SELECT * FROM listaproveedores order by nombreProduct ASC ";
			initialize();
			break;

		}
		
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
	
	
	
	private final ListChangeListener<ClassProveedores> selectortablaProveedor =
            new ListChangeListener<ClassProveedores>() {

				@Override
				public void onChanged(Change<? extends ClassProveedores> arg0) {
					ponerProveedorSeleccionada();
					
				}
            };
	
	private void ponerProveedorSeleccionada() {
        final ClassProveedores proveedor =  proveedorSeleccionado();
       	proveedoresList.indexOf(proveedor);	
        if (proveedoresList != null) {
        	CAJAIDTipoProduct.setText(Integer.toString(proveedor.getTipoProductoID()));
        	CAJAnombrePovee.setText(proveedor.getNombreProveedor());
        	CAJAnombreProduct.setText(proveedor.getNombreProducto());
        	CAJAfolioProduct.setText(proveedor.getFolioProducto());
//        	cajanombreProvee.setText(proveedor.getNombreproveedor());
//            cajaprecio.setText(Float.toString(proveedor.getPrecio()));   
        }
    }

	public ClassProveedores proveedorSeleccionado() {
	if (tablaProvee != null) {
	    List<ClassProveedores> tabla = tablaProvee.getSelectionModel().getSelectedItems();
	    if (tabla.size() == 1) {
	        final ClassProveedores seleccionada = tabla.get(0);
	        return seleccionada;
	    }
	}
	return null;
	}
	
	
	@FXML 
	private void modificar(ActionEvent event) {
		String query = "UPDATE listaproveedores SET IDtipoProduct="+CAJAIDTipoProduct.getText()+",nombreProveedor='"+CAJAnombrePovee.getText()+"',nombreProduct='"+CAJAnombreProduct.getText()+"',folioProduct='"+CAJAfolioProduct.getText()+"' WHERE IDtipoProduct = " + CAJAIDTipoProduct.getText();
		executeQuery(query);
		ObservableList<ClassProveedores> list = getPersonList();
		tablaProvee.getItems().setAll(list);
//		
//		 final ObservableList<ClassProveedores> tablaPersonas = tablaProvee.getSelectionModel().getSelectedItems();
//	     tablaPersonas.addListener(selectortablaProveedor);
		
    }
	
	@FXML
	private void eliminar(ActionEvent event) {
		String query = "DELETE FROM listaproveedores WHERE IDtipoProduct="+CAJAIDTipoProduct.getText()+"";
		executeQuery(query);
		ObservableList<ClassProveedores> list = getPersonList();
		tablaProvee.getItems().setAll(list);
		
		 final ObservableList<ClassProveedores> tablaPersonas = tablaProvee.getSelectionModel().getSelectedItems();
	        tablaPersonas.addListener(selectortablaProveedor);
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
	@FXML
	private Button Salir;
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
	
	
}
