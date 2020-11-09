
public class Exercicio1 {
	
	public String votosValidos(Votos votos) {
		return porcentagem(votos.validos,votos.totalEleitores);
	}
	
	public String votosBrancos(Votos votos) {
		return porcentagem(votos.brancos,votos.totalEleitores);
	}
	
	public String votosNulos(Votos votos) {
		return porcentagem(votos.nulos,votos.totalEleitores);
	}
	
	private String porcentagem(int dividendo, int divisor) {
		return 100*(double)dividendo/divisor + "%";
	}

}
