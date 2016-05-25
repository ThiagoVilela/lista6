package entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Aluno {
	//Atributos
	private final int matricula;
	private String nome;
	private Date dataNascimento;
	
	//Relação
	private ArrayList<Matricula> disciplinasCadastradas = new ArrayList<Matricula>();
	
	//Construtores
	public Aluno(int matricula, String nome, Date dataNascimento){
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Aluno(int matricula, String nome, Date dataNascimento, ArrayList<Matricula> disciplinasCadastradas){
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.disciplinasCadastradas = disciplinasCadastradas;
	}
	
	//Métodos
	/*@Override
	public String toString(){
		if( disciplinasCadastradas.size() > 0 ){
			StringBuilder resposta = new StringBuilder("\nAluno(a): " + nome + "\nMatrícula: " + matricula);
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataNascimentoFormatada = formatador.format(this.dataNascimento);
			
			resposta.append("\nData de Nascimento: " + dataNascimentoFormatada + "\nDisciplinas Cadastradas: ");
			
			for (int i = 0; i < disciplinasCadastradas.size(); i++) {
				resposta.append( "\n" + disciplinasCadastradas.get(i).getDisciplina().getNome() );
			}
			
			return resposta.toString();
			
		} else{
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataNascimentoFormatada = formatador.format(this.dataNascimento);
			
			return "\nAluno(a): " + nome + "\nMatrícula: " + matricula +
					"\nData de Nascimento: " + dataNascimentoFormatada + "\nSem disciplinas cadastradas.";
		}
	}*/
	@Override
	public String toString(){
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataNascimentoFormatada = formatador.format(this.dataNascimento);
		
			return this.matricula + ";" + this.nome + ";" + dataNascimentoFormatada;
	}
		
	//Métodos Acessores
	public int getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Matricula> getDisciplinasCadastradas() {
		return disciplinasCadastradas;
	}

	public void setDisciplinasCadastradas(ArrayList<Matricula> disciplinasCadastradas) {
		this.disciplinasCadastradas = disciplinasCadastradas;
	}
}
