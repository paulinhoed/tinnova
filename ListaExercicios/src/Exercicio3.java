
public class Exercicio3 {

	public int fatorial(int n) {
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			return 1;
		} else {
			return n * fatorial(n - 1);
		}
	}

}
