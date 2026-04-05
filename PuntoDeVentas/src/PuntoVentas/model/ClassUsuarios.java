package PuntoVentas.model;

public class ClassUsuarios {
	private int id;
	private String tipoUsuario;
	private String apellidoP;
	private String nombreUsuario;
	private String coreeo;
	private String contrasena1;
	private String contrasena2;
	
	
	
	public ClassUsuarios(int id, String tipoUsuario, String apellidoP, String nombreUsuario, String coreeo) {
		
		this.id = id;
		this.tipoUsuario = tipoUsuario;
		this.apellidoP = apellidoP;
		this.nombreUsuario = nombreUsuario;
		this.coreeo = coreeo;
	}

	public ClassUsuarios(int id, String tipoUsuario,String nombreUsuario, String apellidoP,  String coreeo,
			String contrasena1, String contrasena2) {
		
		this.id = id;
		this.tipoUsuario = tipoUsuario;
		this.apellidoP = apellidoP;
		this.nombreUsuario = nombreUsuario;
		this.coreeo = coreeo;
		this.contrasena1 = contrasena1;
		this.contrasena2 = contrasena2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCoreeo() {
		return coreeo;
	}

	public void setCoreeo(String coreeo) {
		this.coreeo = coreeo;
	}

	public String getContrasena1() {
		return contrasena1;
	}

	public void setContrasena1(String contrasena1) {
		this.contrasena1 = contrasena1;
	}

	public String getContrasena2() {
		return contrasena2;
	}

	public void setContrasena2(String contrasena2) {
		this.contrasena2 = contrasena2;
	}
	
	
	
	
	
	
}
