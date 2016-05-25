package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Aplicacao {
	//Atributos
	private BD bd = new BD();
	
	//Métodos EX 09
	//------------------------------------------------Aluno---------------------------------------------------------------------
	//Método Inclui Aluno-------------------------------------------------------------------------------------------------------
	public void incluirAluno(){	
		System.out.println("\nIncluir aluno selecionado\n");
		this.bd.getAlunos().add(this.montaAluno());
		//System.out.println(this.bd.getAlunos().get(0));
		System.out.println("Aluno adicionado com sucesso.");
	}
	
	//Métodos Inclui Aluno - Monta o objeto a ser adicionado
	public Aluno montaAluno(){
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Digite o número de matrícula: ");
		int matricula = in.nextInt();
		in.nextLine();
		
		System.out.println("Digite o nome do aluno: ");
		String nome = in.nextLine();
		
		Date dataNascimento = this.montaData();
		
		in.close();
		
		Aluno aluno = new Aluno(matricula, nome, dataNascimento);
		System.out.println("Sucesso criando aluno!");
		return aluno;
	}
	
	//Método Inclui Aluno - Converte a data formatada pro tipo genérico
	public Date montaData(){
		String dataFinal = recebeData();
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			Date date = data.parse(dataFinal);
			return date;
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	//Método Inclui Aluno - Recebe a data digitada pelo usuário
	public String recebeData(){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Digite o dia do nascimento: ");
		int dia = in.nextInt();
		
		System.out.println("Digite o mês do nascimento: ");
		int mes = in.nextInt();
		
		System.out.println("Digite o ano do nascimento: ");
		int ano = in.nextInt();
		in.nextLine();
		
		String dataMontada = dia + "/" + mes + "/" + ano;
		
		in.close();
		return dataMontada;	
	}

	//Método Lista Aluno--------------------------------------------------------------------------------------------------------
	public void listarAlunos(){
		StringBuilder resposta = new StringBuilder("\nListar alunos selecionado\n");
		
		boolean temAlunos = false;
		for (int i = 0; i < this.bd.getAlunos().size(); i++) {
			temAlunos = true;
			resposta.append("\n" + this.bd.getAlunos().get(i).toString());
		}
		
		if (!temAlunos) {
			resposta.append("\nNenhum aluno cadastrado.");
		}
		
		System.out.println(resposta);
	}
	
	//Método Excluir Aluno------------------------------------------------------------------------------------------------------
	public void excluirAluno(){
		System.out.println("\nExcluir aluno selecionado.\n");
		
		Scanner in = new Scanner(System.in);
		//in.nextLine();
		System.out.println("Digite a matrícula do aluno desejado: ");
		int matriculaDesejada = in.nextInt();
		in.nextLine();
		in.close();
		
		boolean encontrou = false;
		for (int i = 0; i < this.bd.getAlunos().size() && encontrou == false; i++) {
			if (matriculaDesejada == this.bd.getAlunos().get(i).getMatricula()) {
				encontrou = true;

				if (this.bd.getAlunos().get(i).getDisciplinasCadastradas().size() > 0) { //Caso tenha disciplinas cadastradas
					
					/*for (int j = 0; j < this.bd.getAlunos().get(i).getDisciplinasCadastradas().size(); j++) {  //Percorre a lista de disciplinas cadastradas em ALUNO
						
						for (int j2 = 0; j2 < this.bd.getDisciplinas().size(); j2++) { //Percorre a lista de matriculas cadastradas em DISCIPLINA
							
							if(this.bd.getAlunos().get(i).getDisciplinasCadastradas().get(j).getDisciplina().getCodigo() //Se a disciplina encontrada for a presente na matricula
									== this.bd.getDisciplinas().get(j2).getCodigo()){
								
								this.bd.getDisciplinas().get(j2).getAlunosCadastrados().remove(this.bd.getAlunos().get(i).getDisciplinasCadastradas().get(j)); //Remove a matricula da lista em DISCIPLINA
								
							}
					
						}
						
					}
					
					for (int j = 0; j < this.bd.getAlunos().get(i).getDisciplinasCadastradas().size(); j++) { //Percorre a lista de disciplinas cadastradas em MATRICULA
						
						if (this.bd.getAlunos().get(i).getMatricula() == this.bd.getMatriculas().get(j).getAluno().getMatricula()) { //Se a matricula do meu aluno estiver presente em alguma Matricula
							
							this.bd.getMatriculas().remove(this.bd.getMatriculas().get(j)); //Apaga a matricula do Banco
							
						}
						
					}*/
					Aluno aluno = this.bd.getAlunos().get(i);
					
					if (this.excluiAlunoDisciplina(aluno.getDisciplinasCadastradas())) {
						System.out.println("\nExclusão da Lista - Disciplinas. ");
					} else {
						System.out.println("\n[ERRO]Exclusão da Lista - Disciplinas. ");
					}
					
					if (this.excluiAlunoMatricula(aluno)) {
						System.out.println("\nExclusão da Lista - Matrículas.  ");
					} else {
						System.out.println("\n[ERRO]Exclusão da Lista - Matrículas. ");
					}
					
					this.bd.getAlunos().remove(this.bd.getAlunos().get(i));//Apaga o aluno do banco
					
					
				} else if(this.bd.getAlunos().get(i).getDisciplinasCadastradas().size() == 0){ //Caso não tenha disciplinas cadastradas é apagado
					this.bd.getAlunos().remove(this.bd.getAlunos().get(i));
				}
			}
		}
		
		if (encontrou) {
			System.out.println("Aluno encontrado.");
		}
	
	}
	
	//Método Excluir Aluno - Exclui as matriculas do banco de DISCIPLINAS
	public boolean excluiAlunoDisciplina(ArrayList<Matricula> disciplinasCadastradas){
		
		boolean excluiu = false;
		
		for (int i = 0; i < disciplinasCadastradas.size(); i++) {  //Percorre a lista de disciplinas cadastradas em ALUNO
			
			for (int j = 0; j < this.bd.getDisciplinas().size(); j++) { //Percorre a lista de matriculas cadastradas em DISCIPLINA
				
				if(disciplinasCadastradas.get(i).getDisciplina().getCodigo() == this.bd.getDisciplinas().get(j).getCodigo()){ //Se a disciplina encontrada for a presente na matricula
					
					this.bd.getDisciplinas().get(j).getAlunosCadastrados().remove(disciplinasCadastradas.get(i)); //Remove a matricula da lista em DISCIPLINA
					excluiu = true;
					
				}
		
			}
				
		}
		
		return excluiu;
	}
	
	//Método Excluir Aluno - Excluir as matriculas do banco MATRICULAS
	public boolean excluiAlunoMatricula(Aluno aluno){
		
		boolean excluiu = false;
		
		for (int i = 0; i < aluno.getDisciplinasCadastradas().size(); i++) { //Percorre a lista de disciplinas cadastradas em MATRICULA
			
			if (aluno.getMatricula() == this.bd.getMatriculas().get(i).getAluno().getMatricula()) { //Se a matricula do meu aluno estiver presente em alguma Matricula
				
				this.bd.getMatriculas().remove(this.bd.getMatriculas().get(i)); //Apaga a matricula do Banco
				excluiu = true;
				
			}
		
		}
		
		return excluiu;
	}
	
	//--------------------------------------------------Professor---------------------------------------------------------------
	//Método Incluir Professor--------------------------------------------------------------------------------------------------
	public void incluirProfessor(){
		System.out.println("Incluir Professor selecionado\n");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o nome do professor: ");
		String nome = in.nextLine();
		
		System.out.println("Digite o CPF do professor: ");
		long cpf = in.nextLong();
		in.nextLine();
		
		System.out.println("Digite o salário do professor: ");
		double salario = in.nextDouble();
		in.nextLine();
		
		System.out.println("O professor é Mestre ou Doutor?");
		String qualificacao = in.next();
		
		in.close();
		
		if (qualificacao.equals("Doutor") || qualificacao.equals("doutor")) {
			
			Professor professor = new Doutor(cpf,nome,salario,qualificacao);
			this.bd.getProfessores().add(professor);
			System.out.println("Professor Doutor adicionado com sucesso!");
			
		} else if (qualificacao.equals("Mestre") || qualificacao.equals("mestre")) {
			
			Professor professor = new Mestre(cpf,nome,salario,qualificacao);
			this.bd.getProfessores().add(professor);
			System.out.println("Professor Mestre adicionado com sucesso!");
			
		} else {
			
			System.out.println("Erro ao indentificar o tipo de professor, insira novamente.");
			
		}
	
	}
	
	//Método Listar Professores--------------------------------------------------------------------------------------------------
	public void listarProfessores(){
		System.out.println("Listando Professores selecionado.\n");
		
		for (int i = 0; i < this.bd.getProfessores().size(); i++) {
			System.out.println(this.bd.getProfessores().get(i) + "\n");
		}
		
		if (this.bd.getProfessores().size() == 0){
			System.out.println("Nenhum professor cadastrado.");
		}
	}
	
	//Método Excluir Professor---------------------------------------------------------------------------------------------------
	public void excluirProfessor(){
		System.out.println("Excluir Professor selecionado.\n");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o CPF do professor: ");
		long cpfDesejado = in.nextLong();
		in.nextLine();
		
		in.close();
		
		boolean encontrou = false;
		for (int i = 0; i < this.bd.getProfessores().size() && encontrou == false; i++) {
			if( cpfDesejado == this.bd.getProfessores().get(i).getCpf() ){
				System.out.println("Professor encontrado.\n");
				encontrou = true;
				
				if (this.bd.getDisciplinas().size() > 0){  //Caso lecione alguma disciplina
					excluirProfessorDisciplina(cpfDesejado);
				} 
				
				this.bd.getProfessores().remove(this.bd.getProfessores().get(i));
				System.out.println("Remoção realizada com sucesso.");
			}
		}
		
		if (!encontrou) {
			System.out.println("Professor não encontrado.");
		}
		
	}
	
	//Método Excluir Professor - Encontra e seta nulo nas disciplinas
	public boolean excluirProfessorDisciplina(long cpf){
		boolean excluiu = false;
		
		for (int i = 0; i < this.bd.getDisciplinas().size(); i++) {
			if (cpf == this.bd.getDisciplinas().get(i).getProfessor().getCpf()) { //Caso o professor lecione esta disciplina
				this.bd.getDisciplinas().get(i).setProfessor(null); //Altera o professor para nulo, deixando sem professor
				excluiu = true;
				System.out.println("Professor excluído da disciplina.");
			}
		}
		
		if (!excluiu) {
			System.out.println("Professor não encontrado em disciplinas.");
		}
		
		return excluiu;
	}
	
	//---------------------------------------------------Disciplina--------------------------------------------------------------
	//Método Incluir Disciplina--------------------------------------------------------------------------------------------------
	public void incluirDisciplina(){
		System.out.println("Incluir disciplina selecionado.\n");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o código da disciplina: ");
		int codigo = in.nextInt();
		in.nextLine();
		
		System.out.println("Digite o nome da disciplina: ");
		String nome = in.nextLine();
		
		System.out.println("Digite a carga horária da disciplina: ");
		int cargaHoraria = in.nextInt();
		in.nextLine();
		
		System.out.println("Digite o CPF de um professor cadastrado que irá lecionar a disciplina: ");
		long cpfDesejado = in.nextLong();
		in.nextLine();
		
		in.close();
		
		Professor professor = encontraProfessor(cpfDesejado); //Encontra o professor no BD
		
		Disciplina disciplina = new Disciplina(codigo,nome,cargaHoraria,professor); //Monta um objeto Disciplina com o conteúdo digitado
		
		this.bd.getDisciplinas().add(disciplina); //Adiciona no BD
		System.out.println("Inclusão realizada com sucesso!");
	}
	
	//Método Incluir Disciplina - Acha o Professor para add
	public Professor encontraProfessor(long cpf){
		
		boolean encontrou = false;
		for (int i = 0; i < this.bd.getProfessores().size(); i++) {
			
			if (cpf == this.bd.getProfessores().get(i).getCpf()) {
				
				encontrou = true;
				return this.bd.getProfessores().get(i);
				
			}
			
		}
		
		if (!encontrou) {
			System.out.println("Professor não encontrado. Disciplina ficará sem professor até segunda ordem.");
		}
		
		return null;
	}
	
	//Método Listar Disciplinas--------------------------------------------------------------------------------------------------
	public void listarDisciplinas(){
		System.out.println("Listar Disciplinas selecionado.\n");
		
		for (int i = 0; i < this.bd.getDisciplinas().size(); i++) {
			System.out.println(this.bd.getDisciplinas().get(i) + "\n");
		}
		
		if(this.bd.getDisciplinas().size() == 0){
			System.out.println("Nenhuma disciplina cadastrada.");
		}
	}
	
	//Método Excluir Disciplinas-------------------------------------------------------------------------------------------------
	public void excluirDisciplinas(){
		System.out.println("Excluir disciplinas selecionado.");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o código da disciplina que deseja excluir: ");
		int codigoDesejado = in.nextInt();
		in.nextLine();
		
		in.close();
		
		for (int i = 0; i < this.bd.getDisciplinas().size(); i++) {
		
			if (codigoDesejado == this.bd.getDisciplinas().get(i).getCodigo()) {
				
				this.excluirDisciplinasMatriculas(codigoDesejado); //Remove do BD de matrículas
				
				this.bd.getDisciplinas().remove(this.bd.getDisciplinas().get(i)); //Remove do BD de disciplinas
				
			}
			
		}
	}
	
	//Método Excluir Disciplinas - Procura e exclui no BD de Matrículas
	public void excluirDisciplinasMatriculas(int codigo){
		
		for (int i = 0; i < this.bd.getMatriculas().size(); i++) {
			
			if (codigo == this.bd.getMatriculas().get(i).getDisciplina().getCodigo()) {
				
				this.excluirDisciplinasAlunos(this.bd.getMatriculas().get(i)); //Remove do BD de alunos
				
				this.bd.getMatriculas().remove(this.bd.getMatriculas().get(i));
				
			}
			
		}
		
	}
	
	//Método Excluir Disciplinas - Procura e exclui no BD de Alunos
	public void excluirDisciplinasAlunos(Matricula matricula){
		
		for (int i = 0; i < this.bd.getAlunos().size(); i++) {
			
			for (int j = 0; j < this.bd.getAlunos().get(i).getDisciplinasCadastradas().size(); j++) { //Verifica todos os alunos que tem aquela matricula
				
				if (matricula.getAluno().getMatricula() == this.bd.getAlunos().get(i).getDisciplinasCadastradas().get(j).getAluno().getMatricula()) { //Verifica as Disciplinas Cadastradas(Matriculas) de cada aluno
					
					this.bd.getAlunos().get(i).getDisciplinasCadastradas().remove(matricula);
					return;
		
				}
				
			}
			
		}
		
	}
	
	//---------------------------------------------------Matrículas--------------------------------------------------------------
	//Método Matricular Aluno
	public void matricularAluno(){
		System.out.println("Matricular aluno selecionado.\n");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite a matrícula do aluno desejado para cadastrar: ");
		int matriculaAluno = in.nextInt();
		in.nextLine();
		
		System.out.println("Digite o código da disciplina desejada para cadastrar: ");
		int codigoDisciplina = in.nextInt();
		in.nextLine();
		
		in.close();
		
		if(procuraAluno(matriculaAluno) != null && procuraDisciplina(codigoDisciplina) != null){
			
			Matricula matricula = new Matricula(procuraAluno(matriculaAluno),procuraDisciplina(codigoDisciplina));
			this.bd.getMatriculas().add(matricula);
			System.out.println("Matrícula adicionada com sucesso!");
			
		}
		
	}
	
	//Método matricular Aluno - acha aluno
	public Aluno procuraAluno(int matricula){
		
		for (int i = 0; i < this.bd.getAlunos().size(); i++) {
			
			if (matricula == this.bd.getAlunos().get(i).getMatricula()) {
				System.out.println("Aluno encontrado com sucesso!");
				return this.bd.getAlunos().get(i);
			}
			
		}
		
		System.out.println("Aluno não encontrado.");
		return null;
		
	}
	
	//Método matricula aluno - acha disciplina
	public Disciplina procuraDisciplina(int codigo){
		
		for (int i = 0; i < this.bd.getDisciplinas().size(); i++) {
			
			if (codigo == this.bd.getDisciplinas().get(i).getCodigo()) {
				
				if (this.bd.getDisciplinas().get(i).getProfessor() == null) {
					System.out.println("Disciplina encontrada, porém não possui professor.");
					return null;
				} else{
					System.out.println("Disciplina encontrada.");
					return this.bd.getDisciplinas().get(i);
				}
				
			}
			
		}
		
		System.out.println("Disciplina não encontrada.");
		return null;
	}
	
	//-----------------------------------------------------Notas-----------------------------------------------------------------
	//Método Inserir Nota--------------------------------------------------------------------------------------------------------
	public void inserirNota(){
		
		System.out.println("Inserir nota selecionado.");
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite a matrícula do aluno que deseja inserir a nota: ");
		int matriculaDesejada = in.nextInt();
		in.nextLine();
		
		System.out.println("Digite o código da disciplina que deseja inserir a nota: ");
		int codigoDesejado = in.nextInt();
		in.nextLine();
		
		if (procuraAluno(matriculaDesejada) != null && procuraDisciplina(codigoDesejado) != null) {
			
			System.out.println("Digite a nota que deseja alterar: ");
			int nota = in.nextInt();
			in.nextLine();
			
			editaMatricula(nota,procuraAluno(matriculaDesejada),procuraDisciplina(codigoDesejado));
		}
		
		in.close();
	}
	
	//Método Inserir nota - Edita Matricula
	public void editaMatricula(int nota, Aluno aluno, Disciplina disciplina){
		
		for (int i = 0; i < this.bd.getMatriculas().size(); i++) {
		
			if (aluno.getMatricula() == this.bd.getMatriculas().get(i).getAluno().getMatricula() && 
				disciplina.getCodigo() == this.bd.getMatriculas().get(i).getDisciplina().getCodigo()) {
				
				this.bd.getMatriculas().get(i).setPontuacao(nota); //Altera a nota no BD Matriculas
				
				editarMatriculaAluno(nota, aluno, disciplina); //Altera a nota no BD Alunos
				editarMatriculaDisciplina(nota, aluno, disciplina); //Altera a nota no BD Disciplinas
				
				System.out.println("Alterações realizadas com sucesso.");
				return;
				
			}
			
		}
		
	}
	
	//Método Inserir nota - Editar Matricula no BD Aluno
	public void editarMatriculaAluno(int nota, Aluno aluno, Disciplina disciplina){
		
		for (int i = 0; i < this.bd.getAlunos().size(); i++) {
			
			if (aluno.getMatricula() == this.bd.getAlunos().get(i).getMatricula()) {
				
				for (int j = 0; j < this.bd.getAlunos().get(i).getDisciplinasCadastradas().size(); j++) {
					
					if (disciplina.getCodigo() == this.bd.getAlunos().get(i).getDisciplinasCadastradas().get(j).getDisciplina().getCodigo()) {
						
						this.bd.getAlunos().get(i).getDisciplinasCadastradas().get(j).setPontuacao(nota);
						System.out.println("Pontuacao no BD de aluno alterada com sucesso.");
						return;
						
					}
					
				}
				
			}
			
		}
		
		System.out.println("Nenhum aluno está cadastrado nesta disciplina.");
		return;
	}
	
	//Método Inserir nota - Editar Matricula no BD Disciplina
	public void editarMatriculaDisciplina(int nota, Aluno aluno, Disciplina disciplina){
		
		for (int i = 0; i < this.bd.getDisciplinas().size(); i++) {
			
			if (disciplina.getCodigo() == this.bd.getDisciplinas().get(i).getCodigo()) {
				
				for (int j = 0; j < this.bd.getDisciplinas().get(i).getAlunosCadastrados().size(); j++) {
					
					if (aluno.getMatricula() == this.bd.getDisciplinas().get(i).getAlunosCadastrados().get(j).getAluno().getMatricula()) {
						
						this.bd.getDisciplinas().get(i).getAlunosCadastrados().get(j).setPontuacao(nota);
						System.out.println("Pontuacao no BD de disciplina alterada com sucesso.");
						return;
						
					}
					
				}
				
			}
			
		}
		
		System.out.println("Nenhum aluno está cadastrado nesta disciplina.");
		return;
	}
	
	//-----------------------------------------------------Listar-----------------------------------------------------------------
	//Método Listar Disciplinas Aluno Matriculado---------------------------------------------------------------------------------
	public void listarDisciplinasAluno(){
		Scanner in = new Scanner(System.in);
		System.out.println("Digite a matrícula do Aluno desejado: ");
		int matricula = in.nextInt();
		in.nextLine();
		in.close();
		
		Aluno aluno = this.procuraAluno(matricula);
		
		if (aluno != null) {
			for (int i = 0; i < aluno.getDisciplinasCadastradas().size(); i++) {
				System.out.println(aluno.getDisciplinasCadastradas().get(i).getDisciplina().toString() + "\n");
			}
			
			if (aluno.getDisciplinasCadastradas().size() == 0) {
				System.out.println("Este aluno não possui disciplinas cadastradas.");
			}
		} else{
			System.out.println("Erro ao encontrar o Aluno.");
		}
	}
	
	//Método Listar Disciplinas Professor-----------------------------------------------------------------------------------------
	public void listarDisciplinasProfessor(){
		Scanner in = new Scanner(System.in);
		System.out.println("Digite o CPF do Professor desejado: ");
		long cpf = in.nextLong();
		in.nextLine();
		in.close();
		
		Professor professor = this.procuraProfessor(cpf);
		
		if (professor != null) {
			for (int i = 0; i < professor.getDisciplinas().size(); i++) {
				System.out.println(professor.getDisciplinas().get(i).toString() + "\n");
			}
			
			if (professor.getDisciplinas().size() == 0) {
				System.out.println("Este professor não possui disciplinas cadastradas.");
			}
		} else{
			System.out.println("Erro ao encontrar o Professor.");
		}
	}
	
	//Método Listar Disciplinas - Procurar Professor
	public Professor procuraProfessor(long cpf){
		
		for (int i = 0; i < this.bd.getProfessores().size(); i++) {
			
			if (cpf == this.bd.getProfessores().get(i).getCpf()) {
				return this.bd.getProfessores().get(i);
			}
			
		}
		
		System.out.println("Professor não encontrado.");
		return null;
	}
	
	//----------------------------------------------------Backup------------------------------------------------------------------
	//Exibe Backup do BD----------------------------------------------------------------------------------------------------------
	public void exibeBackup(){
		System.out.println(this.bd.gerarBackup());
	}
	
	//------------------------------------------------Arquivo Importação----------------------------------------------------------
	//Método Importa Disciplina---------------------------------------------------------------------------------------------------
	public void importaDisciplina(){
		
		try{
			FileReader arquivo = new FileReader("disciplinas.txt");
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			String conteudoLinha = new String();
			limpaArquivo("erroDisciplinas.txt");
			
			while(conteudoLinha != null){
				conteudoLinha = lerArquivo.readLine();
				
				if (conteudoLinha != null) {
					converteLinhaDisciplina(conteudoLinha);
				}
			}
			System.out.println("Importação de disciplinas realizada com sucesso.");
			//System.out.println(this.bd.getDisciplinas().size());
			
			arquivo.close();
		}catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado.");
		}catch(IOException e){
			System.out.println("Erro ao abrir arquivo.");
		}catch(Exception e){
			System.out.println("Erro inesperado.");
		}
		
	}
	
	public void converteLinhaDisciplina(String conteudo){
		
		String atributosDisciplina[] = conteudo.split(";");
		
		int codigo = 0;
		String nome;
		int cargaHoraria = 0;
		
		if (atributosDisciplina.length == 3) {
			
			try{
				codigo = Integer.parseInt(atributosDisciplina[0]);
			}catch(NumberFormatException e){
				System.out.println("Código de disciplina inválido.");
			}
			
			nome = atributosDisciplina[1];
			
			try{
				cargaHoraria = Integer.parseInt(atributosDisciplina[2]);
			}catch(NumberFormatException e){
				System.out.println("Carga horária de disciplina inválida.");
			}
			
			//System.out.println(cargaHoraria);
			Disciplina disciplina = new Disciplina(codigo,nome,cargaHoraria);
			this.bd.getDisciplinas().add(disciplina);
			
		} else{
			escreveArquivoErro("erroDisciplinas.txt",conteudo);
		}
		
	}
	
	public void escreveArquivoErro(String nomeArquivo, String conteudo){
		
		try{
			FileWriter leitor = new FileWriter(nomeArquivo,true);
			BufferedWriter buffer = new BufferedWriter(leitor);
			
			buffer.write(conteudo);
			buffer.newLine();
			
			buffer.flush();
			buffer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void limpaArquivo(String nome){
		
		try{
			FileWriter leitor = new FileWriter(nome);
			BufferedWriter buffer = new BufferedWriter(leitor);
			
			buffer.write("");
			
			buffer.flush();
			buffer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Método Importa Alunos-------------------------------------------------------------------------------------------------------
	public void importaAlunos(){
		try{
			FileReader arquivo = new FileReader("alunos.txt");
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			String conteudoLinha = new String();
			limpaArquivo("erroAlunos.txt");
			
			while(conteudoLinha != null){
				conteudoLinha = lerArquivo.readLine();
				
				if (conteudoLinha != null) {
					converteLinhaAluno(conteudoLinha);
				}
			}
			lerArquivo.close();
			System.out.println("Importação de Alunos realizada com sucesso.");
		}catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado.");
		}catch(IOException e){
			System.out.println("Erro ao abrir arquivo.");
		}catch(Exception e){
			System.out.println("Erro inesperado.");
		}
	}
	
	public void converteLinhaAluno(String conteudo){
		
		String atributosAluno[] = conteudo.split(";");
		
		int matricula = 0;
		String nome;
		Date dataAluno = null;
		
		if (atributosAluno.length == 3) {
			
			try{
				matricula = Integer.parseInt(atributosAluno[0]);
			}catch(NumberFormatException e){
				System.out.println("Matrícula de aluno inválida.");
			}
			
			nome = atributosAluno[1];
			
			String formato = descobreFormatoData(atributosAluno[2]);
			
			if (formato != null) {
				SimpleDateFormat data = new SimpleDateFormat(formato);
				
				try {
					dataAluno = data.parse(atributosAluno[2]);
					
					Aluno aluno = new Aluno(matricula,nome,dataAluno);
					this.bd.getAlunos().add(aluno);

				} catch (ParseException e) {				
					e.printStackTrace();	
				}
			}
			
			
		} else{
			escreveArquivoErro("erroAlunos.txt", conteudo);
		}
		
	}
	
	public String descobreFormatoData(String conteudo){
		
		String caractereSplit = descobreSplitData(conteudo);
		
		if (caractereSplit != null) {
			String camposData[] = conteudo.split(caractereSplit);
			
			if (camposData.length == 3) {
				if (caractereSplit.equals("/")) {
					return "dd/MM/yyyy";
				}else if(caractereSplit.equals("-")){
					return "MM-dd-yyyy";
				}
			}else{
				System.out.println("Erro no formato da data.");
				return null;
			}
		}
		
		return null;
	}
	
	public String descobreSplitData(String conteudo){
		int barra = 0;
		int traco = 0;
		
		for (int i = 0; i < conteudo.length(); i++) {
			if (conteudo.charAt(i) == '/') {
				barra++;
			}else if(conteudo.charAt(i) == '-'){
				traco++;
			}
		}

		if (barra == 2) {
			return "/";
		} else if (traco == 2) {
			return "-";
		} else {
			return null;
		}
	}

	//Método Importa Professores---------------------------------------------------------------------------------------------------
	public void importaProfessores(){
		
		try{
			FileReader arquivo = new FileReader("professores.txt");
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			String conteudoLinha = new String();
			limpaArquivo("erroProfessores.txt");
			
			while(conteudoLinha != null){
				conteudoLinha = lerArquivo.readLine();
				if (conteudoLinha != null) {
					converteLinhaProfessor(conteudoLinha);
				}
			}
			
			lerArquivo.close();
			System.out.println("Importação de Professores realizada com sucesso.");
			
		}catch(FileNotFoundException e){
			System.out.println("Erro: Arquivo não encontrado.");
		}catch(IOException e){
			System.out.println("Erro: Problema de importação.");
		}catch(Exception e){
			System.out.println("Erro: Resposta não esperada.");
		}
		
	}
	
	public void converteLinhaProfessor(String conteudo){
		
		String campos[] = conteudo.split(";");
		
		String tipoProfessor = null;
		long cpf = 0;
		String nome = null;
		double salario = 0;
		//String tese = null;
		
		if (campos.length == 5) {
			
			tipoProfessor = campos[0];
			
			if (tipoProfessor.length() != 1 || tipoProfessor != "D" || tipoProfessor != "M") {
				
				try{
					cpf = Long.parseLong(campos[1]);
				}catch(NumberFormatException e){
					System.out.println("Erro na leitura do CPF.");
				}
				
				nome = campos[2];
				
				try{
					salario = Double.parseDouble(campos[3]);
				}catch(NumberFormatException e){
					System.out.println("Erro na leitura do salário.");
				}
				
				//tese = campos[4];
				
				if (tipoProfessor.equals("D")) {
					Professor professor = new Doutor(cpf,nome,salario,tipoProfessor);
					this.bd.getProfessores().add(professor);
					return;
				}else if (tipoProfessor.equals("M")) {
					Professor professor = new Mestre(cpf,nome,salario,tipoProfessor);
					this.bd.getProfessores().add(professor);
					return;
				}else{
					System.out.println("Erro na inserção no banco.");
					return;
				}
				
			}else{
				System.out.println("Erro na leitura do tipo de Professor.");
				escreveArquivoErro("erroProfessores.txt", conteudo);
				return;
			}
			
		}else{
			//System.out.println("Erro na leitura de Professor.");
			escreveArquivoErro("erroProfessores.txt", conteudo);
			return;
		}
		
	}

	
	//Métodos Acessores
	public BD getBd() {
		return bd;
	}

	public void setBd(BD bd) {
		this.bd = bd;
	}

	
	//Main
	public static void main(String[] args) {
		//Aplicacao aplicacao = new Aplicacao();
		/*int opcao;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Bem vindo ao Sistema de Cadastro da Faculdade!\n"
					+ "Digite a opção desejada:\n"
					+ "1 - Incluir Aluno\n10 - Exibir Relatório de Backup\n0 - Sair"
					+ "\nDigite: ");
			
			opcao = in.nextInt();
			in.nextLine();
			
			switch (opcao) {
				case 1: aplicacao.incluirAluno();break;
				case 10: aplicacao.exibeBackup();break;
				default: System.out.println("Erro ao identificar a opção! Digite novamente.");break;
			}
			
		} while (opcao != 0);
		in.close();*/
		//aplicacao.incluirAluno();
		//aplicacao.listarAlunos();
		//aplicacao.exibeBackup();
		//aplicacao.incluirProfessor();
		//aplicacao.listarProfessores();
		//aplicacao.incluirDisciplina();
		//aplicacao.importaDisciplina();
		//aplicacao.importaAlunos();
		//aplicacao.importaProfessores();

	}
}
