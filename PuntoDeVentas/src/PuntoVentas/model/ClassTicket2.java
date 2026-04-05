package PuntoVentas.model;

import java.time.LocalDate;

public class ClassTicket2 {
	public class ticketExtras{
		private float subTotal;
		private float iva;
		private float total;
		private float precio1articulo;
		private LocalDate date;
		private String folioTicket;
		
		public ticketExtras(float subTotal, float iva, float total, float precio1articulo, LocalDate date,
				String folioTicket) {
			
			this.subTotal = subTotal;
			this.iva = iva;
			this.total = total;
			this.precio1articulo = precio1articulo;
			this.date = date;
			this.folioTicket = folioTicket;
		}

		public float getSubTotal() {
			return subTotal;
		}

		public void setSubTotal(float subTotal) {
			this.subTotal = subTotal;
		}

		public float getIva() {
			return iva;
		}

		public void setIva(float iva) {
			this.iva = iva;
		}

		public float getTotal() {
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}

		public float getPrecio1articulo() {
			return precio1articulo;
		}

		public void setPrecio1articulo(float precio1articulo) {
			this.precio1articulo = precio1articulo;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public String getFolioTicket() {
			return folioTicket;
		}

		public void setFolioTicket(String folioTicket) {
			this.folioTicket = folioTicket;
		}
	}
}
