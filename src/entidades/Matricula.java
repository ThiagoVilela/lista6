package entidades;

public class Matricula {
	//Atributos
	private int pontuacao;
	
	//Relações
	private Aluno aluno;
	private Disciplina disciplina;

	private Integer integer;
	
	//Construtores
	public Matricula(){}
	
	public Matricula(Aluno aluno, Disciplina disciplina){
		integer = (Integer) null;
		this.pontuacao = integer;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}
	
	public Matricula(int pontuacao, Aluno aluno, Disciplina disciplina){
		this.pontuacao = pontuacao;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}
	
	//Métodos
	/*@Override
	public String toString(){
		return "\nMatricula do aluno: " + aluno.getNome() + "\nNúmero de Matrícula: " + aluno.getMatricula() +
				"\nDiscplina: " + disciplina.getNome() + "\nCódigo: " + disciplina.getCodigo();
	}*/
	@Override
	public String toString(){
		return this.disciplina.getCodigo() + ";" + this.aluno.getMatricula() + ";" + this.pontuacao;
	}

	//Métodos Acessores
	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		try{
			if (pontuacao >= 0 && pontuacao <= 100) {
				this.pontuacao = pontuacao;
				if (pontuacao < 60) {
					System.out.println("REPROVADO");
				} else if (pontuacao >= 60) {
					System.out.println("APROVADO");
				}
			} else {
				throw new GradeNotValid(pontuacao);
			}
		}catch(GradeNotValid e){
			System.out.println(e.getMessage());
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public static void main(String[] args) {
		Matricula matricula = new Matricula();
		matricula.setPontuacao(1000);
	}
}
