import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static BigInteger n, m;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = new BigInteger(tokens.nextToken());
		m = new BigInteger(tokens.nextToken());
		System.out.println(n.divide(m)+"\n"+n.remainder(m));
	}
}