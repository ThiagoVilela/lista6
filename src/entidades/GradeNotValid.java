package entidades;

public class GradeNotValid extends Exception{
	int grade;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GradeNotValid(int grade){
		this.grade = grade;
	}
	
	
	@Override
	public String getMessage(){
		return "Erro: nota " + this.grade + " não é válida.";
	}
}
