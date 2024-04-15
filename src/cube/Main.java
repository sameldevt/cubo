package cube;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		int i = 1;
		while (i != 5) {
			String path = "cubes/cube";
			
			path += i + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					System.out.println(line);
				}
			}
			i++;
			Thread.sleep(1000);
		}
		
		System.out.println("==================================================");
		double b = 0.87;
		double c = 0.73;

		double a = Math.pow(b, 2) + Math.pow(c, 2);
		double result = Math.sqrt(a);
		
		System.out.println("b = " + b + "cm");
		System.out.println("c = " + c + "cm");
		System.out.println();
		System.out.println("x^2 = b^2 + c^2");
		System.out.println("x^2 = (" + b + ")^2 + (" + c + ")^2");
		System.out.println("x^2 = " + Math.pow(b, 2) + " + " + Math.pow(c, 2));
		System.out.println("x^2 = " + a);
		System.out.println("  x = " + "sqrt(" + a + ")");
		System.out.println("  x = " + String.format("%.2f", result) + "cm");
		System.out.println("==================================================");

	}
}
