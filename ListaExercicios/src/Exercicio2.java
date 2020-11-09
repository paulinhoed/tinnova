
public class Exercicio2 {

	public int[] ordena(int[] lista) {
		int tamanho = lista.length;
		int n = tamanho - 1;
		while(n > 0) {
			for(int i=0; i<tamanho; i++) {
				int proximo = i+1;
				if(proximo < tamanho) {
					int v1 = lista[i];
					int v2 = lista[proximo];
					if(v1>v2) {
						lista[i] = v2;
						lista[proximo] = v1;
					}
				}
			}
			n--;
		}
		
		return lista;
	}
}
