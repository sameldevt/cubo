package br.com.magna.control;

import br.com.magna.entities.Face;

public class Cursor {
	private Integer linePosition;
	private Integer columnPosition;
	private boolean isPined = false;
	private Double[] cursorCoordinates;

	public Cursor() {
		linePosition = 1;
		columnPosition = 1;
		cursorCoordinates = new Double[3];
	}

	public void setIsPined(boolean condition) {
		isPined = condition;
	}

	public boolean getIsPined() {
		return isPined;
	}

	public Integer getLinePosition() {
		return linePosition;
	}

	public Integer getColumnPosition() {
		return columnPosition;
	}

	public void setLinePosition(Integer linePosition) {
		this.linePosition = linePosition;
	}

	public void setColumnPosition(Integer columnPosition) {
		this.columnPosition = columnPosition;
	}
	
	public Double[] getCursorCoordinates() {
		return cursorCoordinates;
	}

	public void setCursorCoordinates(Face face) {
		int faceNodeValue = face.getFaceNode().getValue();
		Character[][] matrix = face.getMatrix();
		
		switch (faceNodeValue) {
		case 1: {
			cursorCoordinates[0] = 1.0;
			cursorCoordinates[1] = (double) linePosition / matrix.length;
			cursorCoordinates[2] = (double) columnPosition / matrix[0].length;
			break;
		}
		case 2: {
			cursorCoordinates[0] = (double) linePosition / matrix.length;
			cursorCoordinates[1] = 1.0;
			cursorCoordinates[2] = (double)columnPosition / matrix[0].length;
			break;
		}
		case 3: {
			cursorCoordinates[0] = 0.0;
			cursorCoordinates[1] = (double) columnPosition / matrix[0].length;
			cursorCoordinates[2] = (double) linePosition / matrix.length;
			break;
		}
		case 4: {
			cursorCoordinates[0] = (double) columnPosition / matrix[0].length;
			cursorCoordinates[1] = 0.0;
			cursorCoordinates[2] = (double) linePosition / matrix.length;
			break;
		}
		case 5: {
			cursorCoordinates[0] = (double) columnPosition / matrix[0].length;
			cursorCoordinates[1] = (double) linePosition / matrix.length;
			cursorCoordinates[2] = 1.0;
			break;
		}
		case 6: {
			cursorCoordinates[0] = (double) linePosition / matrix.length;
			cursorCoordinates[1] = (double) columnPosition / matrix[0].length;
			cursorCoordinates[2] = 0.0;
			break;
		}
		}
	}
}
