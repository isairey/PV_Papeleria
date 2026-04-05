package PuntoVentas.model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClassProveedores {
	private int tipoProductoID;
	private String nombreProveedor;
	private String nombreProducto;
	private String folioProducto;
	

	
	
	public ClassProveedores(int tipoProductoID, String nombreProveedor, String nombreProducto,
			String folioProducto) {
		
		this.tipoProductoID = tipoProductoID;
		this.nombreProveedor = nombreProveedor;
		this.nombreProducto = nombreProducto;
		this.folioProducto = folioProducto;
	}

	public int getTipoProductoID() {
		return tipoProductoID;
	}

	public void setTipoProductoID(int tipoProductoID) {
		this.tipoProductoID = tipoProductoID;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getFolioProducto() {
		return folioProducto;
	}

	public void setFolioProducto(String folioProducto) {
		this.folioProducto = folioProducto;
	}
	
	
	
	
}
