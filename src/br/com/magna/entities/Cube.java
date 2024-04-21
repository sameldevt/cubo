package br.com.magna.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.magna.services.LogHandler;
import br.com.magna.structures.Graph;
import br.com.magna.structures.Node;

public class Cube {

	private List<Face> faces = new ArrayList<>();
	private Graph facesGraph = new Graph();

	public Cube() {
		loadFaces();
		loadGraphConfig();
	}
	
	public void printCubeFace(Integer faceId) {
		Face face = faces.get(faceId - 1);
		face.print();
	}
	
	public Graph getFacesGraph() {
		return facesGraph;
	}

	public List<Face> getFaces() {
		return faces;
	}

	private void loadFaces() {
		int faceValue = 1;
		String path = "";

		while (faceValue < 7) {
			Character[][] face = new Character[25][50];
			path += "cube/faces/" + faceValue + ".txt";
			
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
				LogHandler.error("Error reading face archive number " + faceValue);
			}
			
			path = "";
			Face cubeFace = new Face(face);
			cubeFace.setFaceNode(new Node(faceValue));
			faces.add(cubeFace);
			faceValue++;
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
	
	private void loadGraphConfig() {
		Node nodeFace1 = faces.get(0).getFaceNode();
		Node nodeFace2 = faces.get(1).getFaceNode();
		Node nodeFace3 = faces.get(2).getFaceNode();
		Node nodeFace4 = faces.get(3).getFaceNode();
		Node nodeFace5 = faces.get(4).getFaceNode();
		Node nodeFace6 = faces.get(5).getFaceNode();

		nodeFace6.setUpNode(nodeFace3);
		nodeFace6.setDownNode(nodeFace1);
		nodeFace6.setRightNode(nodeFace2);
		nodeFace6.setLeftNode(nodeFace4);

		nodeFace5.setUpNode(nodeFace1);
		nodeFace5.setDownNode(nodeFace3);
		nodeFace5.setRightNode(nodeFace4);
		nodeFace5.setLeftNode(nodeFace2);

		nodeFace4.setUpNode(nodeFace6);
		nodeFace4.setDownNode(nodeFace5);
		nodeFace4.setRightNode(nodeFace3);
		nodeFace4.setLeftNode(nodeFace1);

		nodeFace3.setUpNode(nodeFace6);
		nodeFace3.setDownNode(nodeFace5);
		nodeFace3.setRightNode(nodeFace2);
		nodeFace3.setLeftNode(nodeFace4);

		nodeFace2.setUpNode(nodeFace6);
		nodeFace2.setDownNode(nodeFace5);
		nodeFace2.setRightNode(nodeFace1);
		nodeFace2.setLeftNode(nodeFace3);

		nodeFace1.setUpNode(nodeFace6);
		nodeFace1.setDownNode(nodeFace5);
		nodeFace1.setRightNode(nodeFace4);
		nodeFace1.setLeftNode(nodeFace2);

		facesGraph.addNodes(nodeFace1, nodeFace2, nodeFace3, nodeFace4, nodeFace5, nodeFace6);
		
		facesGraph.setSelectedNode(nodeFace1);
	}
}