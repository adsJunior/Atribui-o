package aplication;

import otimizacao.*;
import java.util.Scanner;

public class Main {
	
	public static Scanner Leitor = new Scanner(System.in);
	
	public static void opLeil�o(){

		System.out.println("Algoritmo de leil�o. \n");
		int op;
		do{
			System.out.println("O programa ser� para: ");
			System.out.println("1 - Maximizar Benef�cios;");
			System.out.println("2 - Minimizar Custos; ");
			op = Leitor.nextInt();
			if((op != 1)&&(op != 2))
				System.out.println("VALOR INV�LIDO! ");
		}while((op != 1)&&(op != 2));
		
		System.out.print("Digite o n�mero de pessoas no leil�o: ");
		int p = Leitor.nextInt();
		System.out.print("Digite agora o n�mero de objetos leiloados: ");
		int o = Leitor.nextInt();
		double m[][] = gerarMatriz(p,o);
		
		if(op == 2){
			for(int i = 0; i < m.length; i++){
				for(int j = 0; j < m[i].length; j++)
					m[i][j] *= -1;
			}
		}
		
		Leilao L = new Leilao(m);
		
		if(op == 2){
			for(int i = 0; i < m.length; i++){
				for(int j = 0; j < m[i].length; j++)
					m[i][j] *= -1;
			}
		}
		
		L.ImprimeResposta(L.algoritmoLeilao());		
	}
	
	public static void opTransporte(){
		
		System.out.println("Algoritmo de Leil�o aplicado ao problema de transporte: ");
		
		System.out.print("Digite o n�mero de ofertas: ");
		int ofertas = Leitor.nextInt();
		System.out.print("Digite o n�mero de demandas: ");
		int demandas = Leitor.nextInt();
		int pesoofertas[] = new int[ofertas];
		int pesodemandas[] = new int[demandas];
		int c;
		for(c = 0; c < pesoofertas.length; c++){
			System.out.print("Digite o peso da Oferta "+(c+1)+". ");
			pesoofertas[c] = Leitor.nextInt();
		}
		for(c = 0; c < pesodemandas.length; c++){
			System.out.print("Digite o peso da demanda "+(c+1)+". ");
			pesodemandas[c] = Leitor.nextInt();
		}
		Transporte t = new Transporte(pesoofertas, pesodemandas);
		t.ImprimeResposta();
		
	}
	
	public static double[][] gerarMatriz(int x, int y){
		double Matriz[][] = new double[x][y];
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				System.out.print("Digite o valor do Benef�cio/Custo do objeto " + (j+1) + " para a pessoa " + (i+1) + ". ");
				Matriz[i][j] = Leitor.nextDouble();
			}
		}
		return Matriz;
	}
	
	public static void main(String[] Args){
		opLeil�o();
		opTransporte();
	}

}
