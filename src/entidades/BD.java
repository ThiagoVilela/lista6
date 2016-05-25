package entidades;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BD {
	//Atributos
		private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		private ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
		private ArrayList<Professor> professores = new ArrayList<Professor>();
		
		//Métodos
		public StringBuilder gerarBackup(){
		//Inicio header
		StringBuilder cabecalho = new StringBuilder("\n\nBackup realizado em ");
		
		Calendar dataAtual = Calendar.getInstance();
		cabecalho.append(dataAtual.get(Calendar.DAY_OF_MONTH) + " de ");
		
		switch (dataAtual.get(Calendar.MONTH)) {
			case 1: cabecalho.append("Janeiro de ");break;
			case 2: cabecalho.append("Fevereiro de ");break;
			case 3: cabecalho.append("Março de ");break;
			case 4: cabecalho.append("Abril de ");break;
			case 5: cabecalho.append("Maio de ");break;
			case 6: cabecalho.append("Junho de ");break;
			case 7: cabecalho.append("Julho de ");break;
			case 8: cabecalho.append("Agosto de ");break;
			case 9: cabecalho.append("Setembro de ");break;
			case 10: cabecalho.append("Outubro de ");break;
			case 11: cabecalho.append("Novembro de ");break;
			case 12: cabecalho.append("Dezembro de ");break;
			default: cabecalho.append("Erro mês de ");break;
		}
		
		cabecalho.append(dataAtual.get(Calendar.YEAR) + " às ");
		
		//Utilizar o a classe DateFormat para hora (ela só aceita parâmetros tipo Date)
		Date data = dataAtual.getTime();
		DateFormat horaAtual = DateFormat.getTimeInstance();
		cabecalho.append(horaAtual.format(data));
		//Fim header
		
		//Inicio Aluno
		if(alunos.size() > 0){
			cabecalho.append("\n\nAlunos");
			
			for (int i = 0; i < alunos.size(); i++) {
				cabecalho.append("\n" + this.alunos.get(i).toString());
			}
			
		} else{
			cabecalho.append("\n\nNenhum aluno cadastrado.\n");
		}
		//Fim Aluno
		//Inicio Professor
		if(professores.size() > 0){
			cabecalho.append("\n\nProfessores");
			
			for (int i = 0; i < professores.size(); i++) {
				cabecalho.append("\n" + this.professores.get(i).toString());
			}
			
		} else{
			cabecalho.append("\nNenhum professor cadastrado.\n");
		}
		//Fim Professor
		//Inicio Disciplinas		
		if(disciplinas.size() > 0){
			cabecalho.append("\n\nDisciplinas");
			
			for (int i = 0; i < disciplinas.size(); i++) {
				cabecalho.append("\n" + this.disciplinas.get(i).toString());
			}
			
		} else{
			cabecalho.append("\n\nNenhuma disciplina cadastrada.\n");
		}
		//Fim disciplinas
		//Inicio Matriculas
		if(matriculas.size() > 0){
			cabecalho.append("\n\nMatriculas");
			
			for (int i = 0; i < matriculas.size(); i++) {
				cabecalho.append("\n" + this.matriculas.get(i).toString());
			}
			
		} else{
			cabecalho.append("\n\nNenhuma matricula cadastrada.\n");
		}
		//Fim Matriculas
		
		return cabecalho;
	}

	//Métodos Acessores
	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ArrayList<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(ArrayList<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}
}
