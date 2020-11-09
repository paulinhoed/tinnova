import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Exercício 1 - Votos em relação ao total de eleitores");
		Exercicio1 ex1 = new Exercicio1();
		Votos votos = new Votos();
		System.out.println("Votos válidos: "+ex1.votosValidos(votos));
		System.out.println("Votos brancos: "+ex1.votosBrancos(votos));
		System.out.println("Votos nulos: "+ex1.votosNulos(votos));
		System.out.println("-----------------------------------------------------");
		System.out.println();
		

		System.out.println("Exercício 2 - Algorítmo de ordenação Buble Sort");
		Exercicio2 ex2 = new Exercicio2();
		int[] listaDesordenada = {5, 3, 2, 4, 7, 1, 0, 6};
		System.out.println("Lista original: "+ Arrays.toString(listaDesordenada));
		int[] listaOrdenada = ex2.ordena(listaDesordenada);
		System.out.println("Lista ordenada: "+ Arrays.toString(listaOrdenada));
		System.out.println("-----------------------------------------------------");
		System.out.println();
		

		System.out.println("Exercício 3 - Fatorial");
		Exercicio3 ex3 = new Exercicio3();
		int n0 = 0;
		int n1 = 1;
		int n2 = 2;
		int n3 = 3;
		int n4 = 4;
		int n5 = 5;
		int n6 = 6;
		System.out.println(n0+"! = "+ex3.fatorial(n0));
		System.out.println(n1+"! = "+ex3.fatorial(n1));
		System.out.println(n2+"! = "+ex3.fatorial(n2));
		System.out.println(n3+"! = "+ex3.fatorial(n3));
		System.out.println(n4+"! = "+ex3.fatorial(n4));
		System.out.println(n5+"! = "+ex3.fatorial(n5));
		System.out.println(n6+"! = "+ex3.fatorial(n6));
		System.out.println("-----------------------------------------------------");
		System.out.println();
		

		System.out.println("Exercício 4 - Soma dos múltiplos de 3 ou 5");
		Exercicio4 ex4 = new Exercicio4();
		int x1 = 9;
		int x2 = 10;
		int x3 = 20;
		System.out.println("Soma dos múltiplos de 3 ou 5 para ("+x1+") = "+ex4.somaMult3Ou5(x1));
		System.out.println("Soma dos múltiplos de 3 ou 5 para ("+x2+") = "+ex4.somaMult3Ou5(x2));
		System.out.println("Soma dos múltiplos de 3 ou 5 para ("+x3+") = "+ex4.somaMult3Ou5(x3));
		System.out.println("-----------------------------------------------------");
		System.out.println();
	}

}

class Votos {
	int totalEleitores = 1000;
	int validos = 80;
	int brancos = 150;
	int nulos = 50;
}
