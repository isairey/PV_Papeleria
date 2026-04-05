package PuntoVentas.model;

public class ClassRegistro {
	private int IDusuario;
	private String nombreUsuario;
	private String ApellidoP;
	private String correo;
	private String contrasena;
	private String comprobarContrasena;
	private String tipoUsuario;
	public ClassRegistro(int iDusuario, String nombreUsuario, String apellidoP, String correo, String contrasena,
			String comprobarContrasena, String tipoUsuario) {
		
		IDusuario = iDusuario;
		this.nombreUsuario = nombreUsuario;
		ApellidoP = apellidoP;
		this.correo = correo;
		this.contrasena = contrasena;
		this.comprobarContrasena = comprobarContrasena;
		this.tipoUsuario = tipoUsuario;
	}
	
	public int getIDusuario() {
		return IDusuario;
	}
	public void setIDusuario(int iDusuario) {
		IDusuario = iDusuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoP() {
		return ApellidoP;
	}
	public void setApellidoP(String apellidoP) {
		ApellidoP = apellidoP;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getComprobarContrasena() {
		return comprobarContrasena;
	}
	public void setComprobarContrasena(String comprobarContrasena) {
		this.comprobarContrasena = comprobarContrasena;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	
	
}
