package br.com.magna.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.magna.control.Control;
import br.com.magna.services.LogHandler;
import br.com.magna.structures.Graph;

public class Simulation implements Runnable {

	private static Simulation instance = null;
	private Method action = null;
	private Cube cube = new Cube();
	private Face currentFace = cube.getFaces().get(0);
	private Graph facesGraph = cube.getFacesGraph();
	private List<List<Double>> pins = new ArrayList<>();
	private int faceGraphValue = 1;
	private boolean isFaceSelected = false;

	private Simulation() {
	}

	public static Simulation getInstance() {
		if (instance == null) {
			instance = new Simulation();
			return instance;
		}

		return instance;
	}

	@Override
	public void run() {
		new Thread(new Control()).start();
		simulationLoop();
	}
	
	public void addPin(List<Double> pinCords) {
		pins.add(pinCords);
	}
	

	public List<List<Double>> getPins() {
		return pins;		
	}

	public Face getCurrentFace() {
		return currentFace;
	}

	public boolean getIsFaceSelected() {
		return isFaceSelected;
	}

	public void setIsFaceSelected(boolean condition) {
		isFaceSelected = condition;
	}

	public void setCurrentFace(String node) {
		switch (node) {
		case "Right": {
			facesGraph.gotoRightNode();
			faceGraphValue = facesGraph.getSelectedNode().getValue();
			currentFace = cube.getFaces().get(faceGraphValue - 1);
			break;
		}
		case "Left": {
			facesGraph.gotoLeftNode();
			faceGraphValue = facesGraph.getSelectedNode().getValue();
			currentFace = cube.getFaces().get(faceGraphValue - 1);
			break;
		}
		case "Up": {
			facesGraph.gotoUpNode();
			faceGraphValue = facesGraph.getSelectedNode().getValue();
			currentFace = cube.getFaces().get(faceGraphValue - 1);
			break;
		}
		case "Down": {
			facesGraph.gotoDownNode();
			faceGraphValue = facesGraph.getSelectedNode().getValue();
			currentFace = cube.getFaces().get(faceGraphValue - 1);
			break;
		}
		}
	}

	public void setAction(Method action) {
		this.action = action;
	}

	private void simulationLoop() {
		try {
			synchronized (instance) {
				while (true) {
					if (!isFaceSelected) {
						currentFace.print();
						instance.wait();
						rotateAction();
						continue;
					}
					
					instance.wait();
					matrixAction();
				}
			}
		} catch (InterruptedException threadException) {
			LogHandler.error("Error handling thread. " + threadException.getMessage());
		}
	}

	private void rotateAction() {
		try {
			if (action != null) {
				if (action.getName().equals("selectCubeFace")) {
					action.invoke(new Control());
					return;
				}

				action.invoke(new Control(), new Animation());
				return;
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException reflectionException) {
			LogHandler.error("Couln't handle method ");
			reflectionException.printStackTrace();
		}
	}

	private void matrixAction() {
		try {
			if (action != null) {
				if (action.getName().equals("unselectCubeFace")) {
					action.invoke(new Control());
					currentFace.manipulateFaceMatrix();
					return;
				}
				
				if(pins.size() == 2) {
					action.invoke(new Control());
					currentFace.manipulateFaceMatrix();
					return;
				}

				action.invoke(new Control(), currentFace);
				currentFace.manipulateFaceMatrix();
				return;
			}
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException reflectionException) {
			LogHandler.error("Couln't handle method at matrixAction ");
			reflectionException.printStackTrace();
		}
	}

}
