package PuntoVentas.model;

import java.time.LocalDate;

public class ClassReporte {
	private LocalDate date;
	private int cantidadArticulo;
	private String folioProducto;
	private String descripcion;
	private float precioCU;
	private float totalpago;
	private float iva;
	private float gananciaTotal;
	
	public ClassReporte(LocalDate date, int cantidadArticulo, String folioProducto, String descripcion, float precioCU,
			float totalpago, float iva, float gananciaTotal) {
		super();
		this.date = date;
		this.cantidadArticulo = cantidadArticulo;
		this.folioProducto = folioProducto;
		this.descripcion = descripcion;
		this.precioCU = precioCU;
		this.totalpago = totalpago;
		this.iva = iva;
		this.gananciaTotal = gananciaTotal;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getCantidadArticulo() {
		return cantidadArticulo;
	}

	public void setCantidadArticulo(int cantidadArticulo) {
		this.cantidadArticulo = cantidadArticulo;
	}

	public String getFolioProducto() {
		return folioProducto;
	}

	public void setFolioProducto(String folioProducto) {
		this.folioProducto = folioProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioCU() {
		return precioCU;
	}

	public void setPrecioCU(float precioCU) {
		this.precioCU = precioCU;
	}

	public float getTotalpago() {
		return totalpago;
	}

	public void setTotalpago(float totalpago) {
		this.totalpago = totalpago;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public float getGananciaTotal() {
		return gananciaTotal;
	}

	public void setGananciaTotal(float gananciaTotal) {
		this.gananciaTotal = gananciaTotal;
	}
	
	
}
