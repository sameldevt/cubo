package br.com.magna.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.magna.services.LogHandler;

public class Cube {

	private List<Character[][]> faces = new ArrayList<>();
	private char[][] cube_3d = new char[30][60];

	public Cube() {
		loadFaces();
	}

	public void printCubeFace(int faceId) {
		Character[][] face = faces.get(faceId);
		for (Character[] element : face) {
			for (int column = 0; column < element.length; column++) {
				System.out.print(element[column]);
			}
			System.out.println();
		}
	}

	public List<Character[][]> getFaces() {
		return faces;
	}

	private void loadFaces() {
		int faceNumber = 1;
		String path = "";

		while (faceNumber < 7) {
			Character[][] face = new Character[25][50];
			path += "cube/faces/" + faceNumber + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNext()) {
					for (int line = 0; line < face.length; line++) {
						String read = scan.nextLine();

						for (int column = 0; column < face[line].length; column++) {
							face[line][column] = read.charAt(column);
						}
					}

				}
			} catch (FileNotFoundException e) {
				LogHandler.error("Error reading face archive number " + faceNumber);
			}
			path = "";
			faces.add(face);
			faceNumber++;
		}
	}

	private void loadCube() {
		try (Scanner scan = new Scanner(new File("cubes/cube.txt"))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			LogHandler.error("Error reading archive 'cubes/cube.txt'");
		}
	}
}