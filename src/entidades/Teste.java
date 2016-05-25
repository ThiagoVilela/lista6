package entidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Teste {
	
	ArrayList<Integer> codigos = new ArrayList<Integer>();
	ArrayList<String> nomes = new ArrayList<String>();
	ArrayList<Integer> cargasHorarias = new ArrayList<Integer>();

	
	public void leArquivo(){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Digite o nome do arquivo: ");
		String nomeArquivo = in.nextLine();
		System.out.println();
		in.close();
		
		try{
			FileReader arquivo = new FileReader(nomeArquivo);
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			String conteudoLinha = lerArquivo.readLine();
			
			while (conteudoLinha != null) {
				System.out.println(conteudoLinha);
				conteudoLinha = lerArquivo.readLine();
			}
			
			arquivo.close();
		}catch(FileNotFoundException e){
			
			System.err.println("Arquivo não existe. Causa: " + e.getMessage());
			
		}catch(IOException e){
			
			System.err.println("Erro na abertura do arquivo. Causa: " + e.getMessage());
			
		}catch(Throwable e){
			
			System.err.println("Erro inesperado. Causa: " + e.getMessage());
			
		}
		
		System.out.println();
	}
	
	public void escreveArquivo(){
	
		try{
			
			FileWriter arquivo = new FileWriter("teste.txt");
			PrintWriter gravarArquivo = new PrintWriter(arquivo);
			
			String conteudo = new String("001;Estrutura de Dados I;60");
			gravarArquivo.println(conteudo);
			
			conteudo = "002;Engenharia de Software I;60";
			gravarArquivo.println(conteudo);
			
			conteudo = "003;Fundamentos Teóricos da Computação;120";
			gravarArquivo.println(conteudo);
			
			arquivo.close();
			
		}catch(IOException e){
			System.err.println("Erro ao abrir o arquivo. Causa: " + e.getMessage());
		}catch(Exception e){
			System.err.println("Erro não esperado. Causa: " + e.getMessage());
		}
	}
	
	public void splita(){
		
		String str = "001;Estrutura de Dados I;60";
		String array[] = str.split( ";" );
		
		String codigo = new String();
		String nome = new String();
		String carga = new String();
		
		int codigoFinal = 0;
		int cargaHoraria = 0;
		
		for (int i = 0; i < array.length; i++) {
			switch(i){
				case 0: codigo = array[i];
						try{
							codigoFinal = Integer.parseInt(codigo);
						}catch(NumberFormatException e){
							System.out.println("Valor inválido.");
						}
						break;
				case 1: nome = array[i];break;
				case 2: carga = array[i];
						try{
							cargaHoraria = Integer.parseInt(carga);
						}catch(NumberFormatException e){
							System.out.println("Valor inválido.");
						}
						break;
				default: break;
			}
		}
		
		System.out.println(codigoFinal);
		System.out.println(nome);
		System.out.println(cargaHoraria);
		
		this.codigos.add(codigoFinal);
		this.nomes.add(nome);
		this.cargasHorarias.add(cargaHoraria);
		
		System.out.println(this.cargasHorarias.get(0));
	}
	
	public ArrayList<Integer> getCodigos() {
		return codigos;
	}

	public void setCodigos(ArrayList<Integer> codigos) {
		this.codigos = codigos;
	}

	public ArrayList<String> getNomes() {
		return nomes;
	}

	public void setNomes(ArrayList<String> nomes) {
		this.nomes = nomes;
	}

	public ArrayList<Integer> getCargasHorarias() {
		return cargasHorarias;
	}

	public void setCargasHorarias(ArrayList<Integer> cargasHorarias) {
		this.cargasHorarias = cargasHorarias;
	}

	public static void main(String[] args) {
		//Teste teste = new Teste();
		//teste.leArquivo();
		//teste.escreveArquivo();
		//teste.splita();
		//System.out.println(100%100);
	
	}
}
