
public class Exercicio4 {

	public int somaMult3Ou5(int x) {
		int soma = 0;
		for (int i = 0; i<x; i++) {
            if(i%3 == 0 || i%5 == 0) 
                soma+=i;
            
        }
		return soma;
	}

}
