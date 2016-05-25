package entidades;

import java.util.ArrayList;

public class Doutor extends Professor{
	//Atributos
	private static double bonusSalarial = 1.3;
	private String tituloTese;
	
	//Construtores
	public Doutor(long cpf, String nome, double salario, String tituloTese) {
		super(cpf, nome, salario);
		this.tituloTese = tituloTese;
	}
	
	public Doutor(long cpf, String nome, double salario, ArrayList<Disciplina> disciplinas, String tituloTese) {
		super(cpf, nome, salario, disciplinas);
		this.tituloTese = tituloTese;
	}
	
	//Métodos
	/*@Override
	public String toString(){
		if (disciplinas.size() > 1) {
			StringBuilder resposta = new StringBuilder("\nProfessor: " + nome + "\nCPF: " + 
					cpf + "\nSalário: " + this.getSalario() + "\nTitulo Dissertacao: "+ tituloTese + "\nLeciona Disciplina: ");
			
			for (int i = 0; i < disciplinas.size(); i++) {
				resposta.append("\nNome: " + disciplinas.get(i).getNome());
			}
			return resposta.toString();
		} else{
			return "\nProfessor: " + nome + "\nCPF: " + cpf + "\nSalário: " + this.getSalario() + 
					"\nTitulo Tese: "+ tituloTese + "\nLeciona Disciplina: " + disciplinas.get(0).getNome();
		}
	}*/
	@Override
	public String toString(){
		return this.cpf + ";" + this.nome + ";" + getSalario() + ";" + this.tituloTese;
	}
	
	//Métodos Acessores
	public static double getBonusSalarial() {
		return bonusSalarial;
	}

	public static void setBonusSalarial(double bonusSalarial) {
		Doutor.bonusSalarial = bonusSalarial;
	}

	public String getTituloTese() {
		return tituloTese;
	}

	public void setTituloTese(String tituloTese) {
		this.tituloTese = tituloTese;
	}
	
	@Override
	public double getSalario(){
		return this.salario * bonusSalarial;
	}
}
