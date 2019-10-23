package Bean;





public class BeanFormulario {

	private String nombreApellido;
	private String correo;
	private String fechaNacimiento;
	private String sexo;
	private String hobbies;
	private String distrito;
	private String CasoPrueba;
	//

	public BeanFormulario() {
		super();
	}
	
	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getCasoPrueba() {
		return CasoPrueba;
	}

	public void setCasoPrueba(String casoPrueba) {
		CasoPrueba = casoPrueba;
	}

}
