package entidades;

import java.util.ArrayList;

public class Disciplina {
	//Atributos
	private final int codigo;
	private String nome;
	private int cargaHoraria;
	
	//Relações
	private ArrayList<Matricula> alunosCadastrados = new ArrayList<Matricula>();
	private Professor professor = null;
	
	//Construtores
	public Disciplina(int codigo, String nome, int cargaHoraria){
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}
	
	public Disciplina(int codigo, String nome, int cargaHoraria, Professor professor){
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.professor = professor;
	}
	
	public Disciplina(int codigo, String nome, int cargaHoraria, ArrayList<Matricula> alunosCadastrados, Professor professor){
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.alunosCadastrados = alunosCadastrados;
		this.professor = professor;
	}
	
	//Métodos
	/*@Override
	public String toString(){
		if (alunosCadastrados.size() > 0) {
			StringBuilder resposta = new StringBuilder("\nDisciplina :" + nome + "\nCódigo: " + codigo +
					"\nCarga Horária: " + cargaHoraria + /*"\nProfessor: " + professor.getNome() + "\nAlunos Cadastrados: ");
			
			for (int i = 0; i < alunosCadastrados.size(); i++) {
				resposta.append("\nNome: " + alunosCadastrados.get(i).getAluno().getNome() + 
						" Matrícula: " + alunosCadastrados.get(i).getAluno().getMatricula());
			}
			
			return resposta.toString();
		} else{
			return "\nDisciplina :" + nome + "\nCódigo: " + codigo +
					"\nCarga Horária: " + cargaHoraria + /*"\nProfessor: " + 
					professor.getNome() +  "\nSem alunos cadastrados.";
		}
	}*/
	@Override
	public String toString(){
		return this.codigo + ";" + this.nome + ";" + this.cargaHoraria + ";" + this.professor.getCpf();
	}
	
	//Métodos Acessores
	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public ArrayList<Matricula> getAlunosCadastrados() {
		return alunosCadastrados;
	}

	public void setAlunosCadastrados(ArrayList<Matricula> alunosCadastrados) {
		this.alunosCadastrados = alunosCadastrados;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
