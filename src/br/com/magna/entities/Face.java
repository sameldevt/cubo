package br.com.magna.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.magna.control.Cursor;
import br.com.magna.services.TerminalHandler;
import br.com.magna.structures.Node;

public class Face {

	private Character[][] matrix;
	private Double[] pinCoordinates;
	private Node faceNode;
	private Cursor cursor;

	public Face(Character[][] matrix) {
		this.matrix = matrix;
		pinCoordinates = new Double[] { -1.0, -1.0, -1.0 };
		cursor = new Cursor();
	}

	public Node getFaceNode() {
		return faceNode;
	}

	public void setFaceNode(Node faceNode) {
		this.faceNode = faceNode;
	}

	private void printCursorCoordinates() {
		Double[] cursorCoordinates = cursor.getCursorCoordinates();

		double cursorXPosition = cursorCoordinates[0];
		double cursorYPosition = cursorCoordinates[1];
		double cursorZPosition = cursorCoordinates[2];

		System.out.println("cursor x= " + cursorXPosition + " y= " + cursorYPosition + " z= " + cursorZPosition);
	}

	private void printPinCoordinates() {
		if (pinCoordinates[0] == -1.0 || pinCoordinates[1] == -1.0 || pinCoordinates[2] == -1.0) {
			return;
		}

		double XPosition = pinCoordinates[0];
		double YPosition = pinCoordinates[1];
		double ZPosition = pinCoordinates[2];

		System.out.println("pin x= " + XPosition + " y= " + String.format("%.2f", YPosition) + " z= "
				+ String.format("%.2f", ZPosition));
	}

	public void print() {
		TerminalHandler.clear();
		for (int line = 0; line < matrix.length; line++) {
			for (int column = 0; column < matrix[line].length; column++) {
				System.out.print(matrix[line][column]);
			}
			System.out.println();
		}
	}

	public void manipulateFaceMatrix() {
		TerminalHandler.clear();
		int cursorLinePosition = cursor.getLinePosition();
		int cursorColumnPosition = cursor.getColumnPosition();
		
		int pinLine = cursorLinePosition;
		int pinColumn = cursorColumnPosition;
		
		System.out.println(pinLine + " " + pinColumn);

		for (int line = 0; line < matrix.length; line++) {
			for (int column = 0; column < matrix[line].length; column++) {
				if (line == cursorLinePosition && column == cursorColumnPosition) {
					System.out.print("O");
					continue;
				}

				if (line == pinLine && column == pinColumn) {
					System.out.print('X');
				}
				
				System.out.print(matrix[line][column]);
			}
			System.out.println();
		}
		printCursorCoordinates();
		printPinCoordinates();
	}

	public Cursor getCursor() {
		return cursor;
	}

	public Character[][] getMatrix() {
		return matrix;
	}

	public Double[] getPinCoordinates() {
		return pinCoordinates;
	}

	public List<Double> setPinCoordinates() {
		int faceNodeValue = faceNode.getValue();

		switch (faceNodeValue) {
		case 1: {
			pinCoordinates[0] = 1.0;
			pinCoordinates[1] = (double) cursor.getLinePosition() / matrix.length;
			pinCoordinates[2] = (double) cursor.getColumnPosition() / matrix[0].length;
			break;
		}
		case 2: {
			pinCoordinates[0] = (double) cursor.getLinePosition() / matrix.length;
			pinCoordinates[1] = 1.0;
			pinCoordinates[2] = (double) cursor.getColumnPosition() / matrix[0].length;
			break;
		}
		case 3: {
			pinCoordinates[0] = 0.0;
			pinCoordinates[1] = (double) cursor.getColumnPosition() / matrix[0].length;
			pinCoordinates[2] = (double) cursor.getLinePosition() / matrix.length;
			break;
		}
		case 4: {
			pinCoordinates[0] = (double) cursor.getColumnPosition() / matrix[0].length;
			pinCoordinates[1] = 0.0;
			pinCoordinates[2] = (double) cursor.getLinePosition() / matrix.length;
			break;
		}
		case 5: {
			pinCoordinates[0] = (double) cursor.getColumnPosition() / matrix[0].length;
			pinCoordinates[1] = (double) cursor.getLinePosition() / matrix.length;
			pinCoordinates[2] = 1.0;
			break;
		}
		case 6: {
			pinCoordinates[0] = (double) cursor.getLinePosition() / matrix.length;
			pinCoordinates[1] = (double) cursor.getColumnPosition() / matrix[0].length;
			pinCoordinates[2] = 0.0;
			break;
		}
		}
		
		List<Double> pin = new ArrayList<Double>();
		pin.add(pinCoordinates[0]);
		pin.add(pinCoordinates[1]);
		pin.add(pinCoordinates[2]);
		return pin;

	}
}
