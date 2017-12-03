package otimizacao;

import java.util.Scanner;

public class Transporte {
	
	public static final Scanner Leitor = new Scanner(System.in);

	private int PesoOfertas[], PesoDemandas[];
	private double MatrizPeso[][];
	private int[] VetorResposta;
	
	
	public static int sum(int []vet){
		int s = 0;
		for(int i = 0; i < vet.length; i++)
			s += vet[i];
		return s;
	}
	
	public double[][] auxMatrix(){
		double m[][] = new double[this.PesoOfertas.length][this.PesoDemandas.length];
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				System.out.print("Digite o valor do Custo da demanda " + (j+1) + " para a oferta " + (i+1) + ". ");
				m[i][j] = Leitor.nextDouble();
			}
		}
		return m;
	}
	
	private double[][] replicaLinhas(double aux[][]){
		double m[][] = new double[sum(PesoOfertas)][aux[0].length];
		int x = 0;
		for(int i = 0; i < aux.length; i++){
			for(int j = 0; j < this.PesoOfertas[i]; j++){
				m[x + j] = aux[i];
			}
			x += this.PesoOfertas[i];
		}
		return m;
	}
	
	private double[][] transposta(double aux[][]){
		double m[][] = new double[aux[0].length][aux.length];
		for(int i = 0; i < aux.length; i++){
			for(int j = 0; j < aux[i].length; j++){
				m[j][i] = aux[i][j];
			}
		}
		return m;
	}
	
	private double[][] replicaColunas(double aux[][]){
		double m[][] = new double[sum(PesoDemandas)][aux.length];
		double[][] aux2 = transposta(aux);
		int x = 0;
		for(int i = 0; i < aux2.length; i++){
			for(int j = 0; j < this.PesoDemandas[i]; j++){
				m[x + j] = aux2[i];
			}
			x += this.PesoDemandas[i];
		}
		m = transposta(m);
		return m;
	}
	
	public double[][] setMatrix(){
		double aux[][] = this.auxMatrix(), m[][] = new double[sum(this.PesoOfertas)][sum(this.PesoDemandas)];
		double aux2[][] = this.replicaLinhas(aux);
		m = replicaColunas(aux2);
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++)
				m[i][j] *= -1;
		}
		return m;
	}
	
	public Transporte(int []po, int []pd){
		this.PesoOfertas = po;
		this.PesoDemandas = pd;
		this.MatrizPeso = this.setMatrix();
		Leilao l = new Leilao(this.MatrizPeso);
		this.VetorResposta = l.algoritmoLeilao();
		for(int i = 0; i < this.MatrizPeso.length; i++){
			for(int j = 0; j < this.MatrizPeso[i].length; j++)
				this.MatrizPeso[i][j] *= -1;
		}
	}
	
	public void ImprimeResposta(){
		System.out.println("");
		if(MatrizPeso.length <= this.MatrizPeso[0].length){
			for(int c = 0; c < this.VetorResposta.length; c++){
				System.out.println("Oferta " + c + "; Destino " + this.VetorResposta[c] + "; Custo/Benefício " + this.MatrizPeso[c][this.VetorResposta[c]]);
			}
		}
		else{
			for(int c = 0; c < this.VetorResposta.length; c++){
				System.out.println("Oferta " + this.VetorResposta[c] + "; Destino " + c + "; Custo/Benefício " + this.MatrizPeso[this.VetorResposta[c]][c]);
			}
		}
	}
}
