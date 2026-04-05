package PuntoVentas.view;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import PuntoVentas.model.ClassUsuarios;
import PuntoVentas.model.ClassUsuarios;
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

public class ControllerUsuarios implements Initializable {
	private String query1 = "SELECT * FROM listausuarios order by ID ASC ";
	@FXML
	private ComboBox<String> comboFiltrado;
	@FXML
	private Button Salir;
	@FXML
	private Button editar;
	@FXML
	private Button eliminar;
	@FXML
	private TextField CAJAid;
	@FXML
	private TextField CAJAapellidoPater;
	@FXML
	private TextField CAJAnombres;
	@FXML
	private TextField CAJAtipoUsuario;
	@FXML
	private TextField CAJAcorreo;
	@FXML
    private TableColumn<ClassUsuarios, String> ID;
	@FXML
    private TableColumn<ClassUsuarios, String> apellidoPaterno;
	@FXML
    private TableColumn<ClassUsuarios, String> Nombres;
	@FXML
    private TableColumn<ClassUsuarios, String> tipoUsuario;
	@FXML
    private TableColumn<ClassUsuarios, String> correos;
	@FXML
    private TableView<ClassUsuarios> tablaUsuario;
	@FXML
    ObservableList<ClassUsuarios> UsuariosList = FXCollections.observableArrayList();
	
	
	
	 public void initialize(URL url, ResourceBundle rb) {
		 if(comboFiltrado.getItems().isEmpty()) {
	    		comboFiltrado.getItems().removeAll(comboFiltrado.getItems());
	    		comboFiltrado.getItems().addAll("Por ID", "Por Nombre", "Por Apellido","Tipo de Usuario","Por Correo");
	    		comboFiltrado.getSelectionModel().select("Por ID");
	    	}
		 	ID.setCellValueFactory(new PropertyValueFactory<>("id"));
		 	apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
		 	Nombres.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
		 	tipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
		 	correos.setCellValueFactory(new PropertyValueFactory<>("coreeo"));
		 	
			ObservableList<ClassUsuarios> list = getPersonList();
			
			tablaUsuario.getItems().setAll(list);
	    	
	    	final ObservableList<ClassUsuarios> tablaUsuarioo = tablaUsuario.getSelectionModel().getSelectedItems();
		    tablaUsuarioo.addListener(selectortablaUsuarioos);
	    }
	 
		public ObservableList<ClassUsuarios> getPersonList(){
			ObservableList<ClassUsuarios> UsuariosList = FXCollections.observableArrayList();
			Connection connection = ConnectorMySQL.getConnection();
			
			Statement st;
			ResultSet rs;
			
			try {
				st = connection.createStatement();
				rs = st.executeQuery(query1);
				ClassUsuarios Usuarioss;
				while(rs.next()) {
					Usuarioss = new ClassUsuarios(rs.getInt("ID"),rs.getString("tipoUsuario"),rs.getString("apellidoPaterno"),rs.getString("nombre"),rs.getString("Correo"));
					UsuariosList.add(Usuarioss);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return UsuariosList;
		}
		
		@FXML 
		private void filtrado(ActionEvent event) {
			String filtro = comboFiltrado.getSelectionModel().getSelectedItem().toString();

			switch(filtro) {
			case "Por ID":
				System.out.println(filtro);
				query1="SELECT * FROM listausuarios order by ID ASC ";
				initialize(null, null);
				break;
			case "Por Nombre":
				System.out.println(filtro);
				query1="SELECT * FROM listausuarios order by nombre ASC ";
				initialize(null, null);
				break;
			case "Por Apellido":
				System.out.println(filtro);
				query1="SELECT * FROM listausuarios order by apellidoPaterno ASC ";
				initialize(null, null);
				break;
			case "Tipo de Usuario":
				System.out.println(filtro);
				query1="SELECT * FROM listausuarios order by tipoUsuario, ID ASC ";
				initialize(null, null);
				break;
			case "Por Correo":
				System.out.println(filtro);
				query1="SELECT * FROM listausuarios order by Correo, ID ASC ";
				initialize(null, null);
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
		
		
		
		private final ListChangeListener<ClassUsuarios> selectortablaUsuarioos =
	            new ListChangeListener<ClassUsuarios>() {

					@Override
					public void onChanged(Change<? extends ClassUsuarios> arg0) {
						ponerUsuariosseleccionada();
						
					}
	            };
		
		private void ponerUsuariosseleccionada() {
	        final ClassUsuarios usuario =  Usuariosseleccionado();
	        UsuariosList.indexOf(usuario);
	        if (UsuariosList != null) {
	        	CAJAid.setText(Integer.toString(usuario.getId()));
	        	CAJAapellidoPater.setText(usuario.getApellidoP());
	        	CAJAnombres.setText(usuario.getNombreUsuario());
	        	CAJAtipoUsuario.setText(usuario.getTipoUsuario());
	        	CAJAcorreo.setText(usuario.getCoreeo());
	            
	        }
	    }

		public ClassUsuarios Usuariosseleccionado() {
		if (tablaUsuario != null) {
		    List<ClassUsuarios> tabla = tablaUsuario.getSelectionModel().getSelectedItems();
		    if (tabla.size() == 1) {
		        final ClassUsuarios seleccionada = tabla.get(0);
		        return seleccionada;
		    }
		}
		return null;
		}
		
		
		@FXML 
		private void modificar(ActionEvent event) {
			String query = "UPDATE listausuarios SET ID='"+CAJAid.getText()+"',tipoUsuario='"+CAJAtipoUsuario.getText()+"',apellidoPaterno='"+CAJAapellidoPater.getText()+"',nombre='"+CAJAnombres.getText()+"',Correo='"+CAJAcorreo.getText()+"' WHERE ID='"+CAJAid.getText()+"'";
			executeQuery(query);
			ObservableList<ClassUsuarios> list = getPersonList();
			tablaUsuario.getItems().setAll(list);
			
			 final ObservableList<ClassUsuarios> tablaPersonas = tablaUsuario.getSelectionModel().getSelectedItems();
		     tablaPersonas.addListener(selectortablaUsuarioos);
			
	    }
		
		@FXML
		private void eliminar(ActionEvent event) {
			String query = "DELETE FROM listausuarios WHERE ID="+CAJAid.getText()+"";
			executeQuery(query);
			ObservableList<ClassUsuarios> list = getPersonList();
			tablaUsuario.getItems().setAll(list);
			
			 final ObservableList<ClassUsuarios> tablaPersonas = tablaUsuario.getSelectionModel().getSelectedItems();
		        tablaPersonas.addListener(selectortablaUsuarioos);
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
