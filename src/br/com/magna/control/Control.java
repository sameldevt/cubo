package br.com.magna.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import br.com.magna.entities.Animation;
import br.com.magna.entities.Calc;
import br.com.magna.entities.Face;
import br.com.magna.entities.Simulation;
import br.com.magna.services.LogHandler;

public class Control extends JFrame implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	
	private static Map<Integer, Method> controls = new HashMap<>();
	private Simulation simulation = Simulation.getInstance();

	public Control() {
	}

	@Override
	public void run() {
		loadRotationControls();
		loadFrameConfiguration();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		synchronized (simulation) {
			Method action = lookup(e.getKeyCode());
			simulation.setAction(action);
			simulation.notify();
		}
	}

	public Method lookup(int key) {
		return controls.get(key);
	}
	
	public void executeCalculation() {
		List<List<Double>> pinlist = simulation.getPins();
		
		Double[] pin1 = (Double[]) pinlist.get(0).toArray();
		Double[] pin2 = (Double[]) pinlist.get(1).toArray();
		
		new Calc((Double[])pinlist.get(0).toArray(), (Double[])pinlist.get(1).toArray()).execute();
	}
	
	public void selectCubeFace() {
		simulation.setIsFaceSelected(true);
		loadCursorControls();
	}

	public void unselectCubeFace() {
		simulation.setIsFaceSelected(false);
		loadRotationControls();
	}

	public void rotateCubeRight(Animation animation) {
		simulation.setCurrentFace("Right");
		Face currentFace = simulation.getCurrentFace();
		animation.rotateRight(currentFace);
	}

	public void rotateCubeLeft(Animation animation) {
		simulation.setCurrentFace("Left");
		Face currentFace = simulation.getCurrentFace();
		animation.rotateLeft(currentFace);
	}

	public void rotateCubeUp(Animation animation) {
		simulation.setCurrentFace("Up");
		Face currentFace = simulation.getCurrentFace();
		animation.rotateUp(currentFace);
	}

	public void rotateCubeDown(Animation animation) {
		simulation.setCurrentFace("Down");
		Face currentFace = simulation.getCurrentFace();
		animation.rotateDown(currentFace);
	}

	public void moveCursorRight(Face face) {
		Cursor cursor = face.getCursor();
		int currentYPosition = cursor.getColumnPosition();
		cursor.setColumnPosition(currentYPosition + 1);
		cursor.setCursorCoordinates(face);
	}

	public void moveCursorLeft(Face face) {
		Cursor cursor = face.getCursor();
		int currentYPosition = cursor.getColumnPosition();
		cursor.setColumnPosition(currentYPosition - 1);
		cursor.setCursorCoordinates(face);
	}

	public void moveCursorUp(Face face) {
		Cursor cursor = face.getCursor();
		int currentXPosition = cursor.getLinePosition();
		cursor.setLinePosition(currentXPosition - 1);
		cursor.setCursorCoordinates(face);
	}

	public void moveCursorDown(Face face) {
		Cursor cursor = face.getCursor();
		int currentXPosition = cursor.getLinePosition();
		cursor.setLinePosition(currentXPosition + 1);
		cursor.setCursorCoordinates(face);
	}

	public void selectCoordinates(Face face) {
		Cursor cursor = face.getCursor();
		boolean isCursorPined = cursor.getIsPined();

		if (isCursorPined) {
			return;
		}
		simulation.addPin(face.setPinCoordinates());
	}

	public void removeCursorSelections(Face face) {
		Cursor cursor = face.getCursor();
		//cursor.resetPositionsAndTotalPins();
	}

	private void loadFrameConfiguration() {
		addKeyListener(this);
		setFocusable(true);
		setVisible(false);
		// setAlwaysOnTop(true);
		setUndecorated(true);
		setOpacity(0.01f);
		// setExtendedState(Control.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void loadRotationControls() {
		if (!controls.isEmpty()) {
			controls.clear();
		}

		try {
			controls.put(KeyEvent.VK_UP, Control.class.getDeclaredMethod("rotateCubeUp", Animation.class));
			controls.put(KeyEvent.VK_DOWN, Control.class.getDeclaredMethod("rotateCubeDown", Animation.class));
			controls.put(KeyEvent.VK_RIGHT, Control.class.getDeclaredMethod("rotateCubeRight", Animation.class));
			controls.put(KeyEvent.VK_LEFT, Control.class.getDeclaredMethod("rotateCubeLeft", Animation.class));
			controls.put(KeyEvent.VK_SPACE, Control.class.getDeclaredMethod("executeCalculation"));
			controls.put(KeyEvent.VK_ENTER, Control.class.getDeclaredMethod("selectCubeFace"));
		} catch (NoSuchMethodException e) {
			LogHandler.error("Couldn't find method " + e.getMessage());
		}
	}

	private void loadCursorControls() {
		if (!controls.isEmpty()) {
			controls.clear();
		}

		try {
			controls.put(KeyEvent.VK_UP, Control.class.getDeclaredMethod("moveCursorUp", Face.class));			
			controls.put(KeyEvent.VK_DOWN, Control.class.getDeclaredMethod("moveCursorDown", Face.class));			
			controls.put(KeyEvent.VK_RIGHT, Control.class.getDeclaredMethod("moveCursorRight", Face.class));			
			controls.put(KeyEvent.VK_LEFT, Control.class.getDeclaredMethod("moveCursorLeft", Face.class));			
			controls.put(KeyEvent.VK_ENTER, Control.class.getDeclaredMethod("selectCoordinates", Face.class));	
			controls.put(KeyEvent.VK_SPACE, Control.class.getDeclaredMethod("executeCalculation"));
			controls.put(KeyEvent.VK_ESCAPE, Control.class.getDeclaredMethod("unselectCubeFace"));	
		} catch (NoSuchMethodException e) {
			LogHandler.error("Couldn't find method " + e.getMessage());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
