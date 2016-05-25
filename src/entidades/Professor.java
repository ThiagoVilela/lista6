package entidades;

import java.util.ArrayList;

public abstract class Professor {
	//Atributos
	protected final long cpf;
	protected String nome;
	protected double salario;
	
	//Relações
	protected ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	//Construtores
	public Professor(long cpf, String nome, double salario){
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}
	
	public Professor(long cpf, String nome, double salario, ArrayList<Disciplina> disciplinas){
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
		this.disciplinas = disciplinas;
	}
	
	//Métodos
	/*@Override
	public String toString(){
		if (disciplinas.size() > 1) {
			StringBuilder resposta = new StringBuilder("\nProfessor: " + nome + "\nCPF: " + 
					cpf + "\nSalário: " + salario + "\nLeciona Disciplina: ");
			
			for (int i = 0; i < disciplinas.size(); i++) {
				resposta.append("\nNome: " + disciplinas.get(i).getNome());
			}
			
			return resposta.toString();
		} else{
			return "\nProfessor: " + nome + "\nCPF: " + cpf + "\nSalário: " + salario + 
					"\nLeciona Disciplina: " + disciplinas.get(0).getNome();
		}
	}*/
	@Override
	public String toString(){
		return this.cpf + ";" + this.nome + ";" + getSalario();
	}
	
	//Métodos Acessores;
	public long getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
