package otimizacao;

public class Leilao {
	
	private double Matriz[][];
	
	public Leilao(double m[][]){
		this.Matriz = m;
	}
	
	public double[][] getMatriz(){
		return Matriz;
	}
	
	public int []initResposta(int r[]){
		for(int c = 0; c < r.length; c++){
			r[c] = -1;
		}
		return r;
	}
	
	public int respostaNAtribuida(int []Resposta){
		int c;
		for(c = 0; c < Resposta.length; c++){
			if(Resposta[c] == -1)
				return c;
		}
		return c;
	}
	public int[] algoritmoLeilao(){
		double Custo[];
		int Resposta[];
		double Epson;
		int i, max, max2;
		boolean d = (this.Matriz.length > this.Matriz[0].length);
		if(this.Matriz.length > this.Matriz[0].length){
			Epson = 1.0/((double)this.Matriz.length + 1.0);
			Resposta = new int[this.Matriz[0].length];
			Custo = new double[this.Matriz.length];
		}
		else{
			Resposta = new int[this.Matriz.length];
			Custo = new double[this.Matriz[0].length];
			Epson = 1.0/(1.0 + (double)this.Matriz[0].length);
			
		}
		Resposta = initResposta(Resposta);
		while(((i = respostaNAtribuida(Resposta)) < Resposta.length)&&(!d)){
			max = 0;
			max2 = 1;
			for(int j = 1; j < this.Matriz[i].length; j++){
				if((this.Matriz[i][j] - Custo[j]) > (this.Matriz[i][max] - Custo[max])){
					max2 = max;
					max = j;
				}
				else if((this.Matriz[i][j] - Custo[j]) > (this.Matriz[i][max2]-Custo[max2])){
					max2 = j;
				}
			}
			Custo[max] = (Matriz[i][max] + Epson) - (Matriz[i][max2] - Custo[max2]);
			Resposta[i] = max;
			for(int c = 0; c < Resposta.length; c++){
				if((Resposta[i] == Resposta[c])&&(i != c)){
					Resposta[c] = -1;
				}
			}
		}
		while(((i = respostaNAtribuida(Resposta)) < Resposta.length)&&(d)){
			max = 0;
			max2 = 1;
			for(int j = 1; j < Matriz.length; j++){
				if((this.Matriz[j][i] - Custo[j]) > (this.Matriz[max][i] - Custo[max])){
					max2 = max;
					max = j;
				}
				else if((this.Matriz[j][i] - Custo[j]) > (this.Matriz[max2][i] - Custo[max2])){
					max2 = j;
				}
			}
			Custo[max] = (Matriz[max][i] + Epson) - (Matriz[max2][i] - Custo[max2]);
			Resposta[i] = max;
			for(int c = 0; c < Resposta.length; c++){
				if((Resposta[i] == Resposta[c])&&(i != c)){
					Resposta[c] = -1;
				}
			}
		}
		return Resposta;
	}
	
	public void ImprimeResposta(int r[]){
		System.out.println("");
		if(Matriz.length <= this.Matriz[0].length){
			for(int c = 0; c < r.length; c++){
				System.out.println("Pessoa " + c + "; Objeto " + r[c] + "; Custo/Benefício " + this.Matriz[c][r[c]]);
			}
		}
		else{
			for(int c = 0; c < r.length; c++){
				System.out.println("Pessoa " + r[c] + "; Objeto " + c + "; Custo/Benefício " + this.Matriz[r[c]][c]);
			}
		}
	}

}
