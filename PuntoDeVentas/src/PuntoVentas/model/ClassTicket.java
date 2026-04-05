package PuntoVentas.model;

import java.time.LocalDate;

public class ClassTicket {
	private int cantidadProducto;
	private String descripcionProduct;
	private float importe;
	private int totalArticulos;
	
	
	public ClassTicket(int cantidadProducto, String descripcionProduct, float importe, int totalArticulos) {
		
		this.cantidadProducto = cantidadProducto;
		this.descripcionProduct = descripcionProduct;
		this.importe = importe;
		this.totalArticulos = totalArticulos;
	}


	public int getCantidadProducto() {
		return cantidadProducto;
	}


	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}


	public String getDescripcionProduct() {
		return descripcionProduct;
	}


	public void setDescripcionProduct(String descripcionProduct) {
		this.descripcionProduct = descripcionProduct;
	}


	public float getImporte() {
		return importe;
	}


	public void setImporte(float importe) {
		this.importe = importe;
	}


	public int getTotalArticulos() {
		return totalArticulos;
	}


	public void setTotalArticulos(int totalArticulos) {
		this.totalArticulos = totalArticulos;
	}
	
	
	

	
	
}
