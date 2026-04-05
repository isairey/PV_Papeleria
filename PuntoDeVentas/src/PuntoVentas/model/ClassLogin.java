package PuntoVentas.model;

public class ClassLogin {
	private String nombreUsuario;
	private String contrasena;
	private String tipoUsuario;
	public ClassLogin(String nombreUsuario, String contrasena, String tipoUsuario) {
		
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	
	
	
}
