package PuntoVentas.model;

public class ClassProductos {
	private String tipoProducto;
	private int cantidadProduct;
	private int tipoProductoID;
	private String nomProducto;
	private String folioProducto;
	private float precio;
	private String nombreproveedor;
	public ClassProductos(String tipoProducto, int cantidadProduct, int tipoProductoID, String nomProducto,
			String folioProducto, float precio, String nombreproveedor) {
		
		this.tipoProducto = tipoProducto;
		this.cantidadProduct = cantidadProduct;
		this.tipoProductoID = tipoProductoID;
		this.nomProducto = nomProducto;
		this.folioProducto = folioProducto;
		this.precio = precio;
		this.nombreproveedor = nombreproveedor;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public int getCantidadProduct() {
		return cantidadProduct;
	}
	public void setCantidadProduct(int cantidadProduct) {
		this.cantidadProduct = cantidadProduct;
	}
	public int getTipoProductoID() {
		return tipoProductoID;
	}
	public void setTipoProductoID(int tipoProductoID) {
		this.tipoProductoID = tipoProductoID;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public String getFolioProducto() {
		return folioProducto;
	}
	public void setFolioProducto(String folioProducto) {
		this.folioProducto = folioProducto;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNombreproveedor() {
		return nombreproveedor;
	}
	public void setNombreproveedor(String nombreproveedor) {
		this.nombreproveedor = nombreproveedor;
	}
	
	
	
}
