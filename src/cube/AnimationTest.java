package cube;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnimationTest {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

		String path = "";

		while(true) {
			int i = 1;
			while(i < 8) {
				for (int j = 0; j < 50; j++) {
					System.out.println();
				}
				path+= "frames/horizontal/" + i + ".txt";
				try (Scanner scan = new Scanner(new File(path))) {
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						System.out.println(line);
					}
				}

				Thread.sleep(100);
				path = "";
				i++;
			}

			i = 7;
			while(i > 0) {
				for (int j = 0; j < 50; j++) {
					System.out.println();
				}
				path+= "frames/horizontal/" + i + ".txt";
				try (Scanner scan = new Scanner(new File(path))) {
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						System.out.println(line);
					}
				}

				Thread.sleep(100);
				path = "";
				i--;
			}

			i = 1;
			while(i < 8) {
				for (int j = 0; j < 50; j++) {
					System.out.println();
				}
				path+= "frames/vertical/" + i + ".txt";
				try (Scanner scan = new Scanner(new File(path))) {
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						System.out.println(line);
					}
				}

				Thread.sleep(200);
				path = "";
				i++;
			}

			i = 7;
			while(i > 0) {
				for (int j = 0; j < 50; j++) {
					System.out.println();
				}
				path+= "frames/vertical/" + i + ".txt";
				try (Scanner scan = new Scanner(new File(path))) {
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						System.out.println(line);
					}
				}

				Thread.sleep(200);
				path = "";
				i--;
			}
		}
	}
}
