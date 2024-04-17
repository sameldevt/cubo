package br.com.magna.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import br.com.magna.entities.Cube;
import br.com.magna.entities.CubeAnimation;
import br.com.magna.entities.Simulation;
import br.com.magna.services.LogHandler;
import br.com.magna.services.TerminalHandler;

public class Control extends JFrame implements KeyListener, Runnable {

	private static Map<Integer, Method> controls = new HashMap<>();
	private Simulation simulationInstance = new Simulation();
	private CubeAnimation ca = new CubeAnimation();

	public Control() {
	}

	private void loadRotationControls() {
		try {
			controls.put(KeyEvent.VK_UP, Control.class.getDeclaredMethod("rotateCubeUp", Cube.class));
			controls.put(KeyEvent.VK_DOWN, Control.class.getDeclaredMethod("rotateCubeDown", Cube.class));
			controls.put(KeyEvent.VK_RIGHT, Control.class.getDeclaredMethod("rotateCubeRight", Cube.class));
			controls.put(KeyEvent.VK_LEFT, Control.class.getDeclaredMethod("rotateCubeLeft", Cube.class));
			controls.put(KeyEvent.VK_ENTER, Control.class.getDeclaredMethod("selectCubeFace", Cube.class));
		} catch (NoSuchMethodException e) {
			LogHandler.error("Couldn't find method " + e.getMessage());
		}
	}

	private void loadCursorControls() {
		try {
			controls.put(KeyEvent.VK_UP, Control.class.getDeclaredMethod("moveUp", Cursor.class));
			controls.put(KeyEvent.VK_DOWN, Control.class.getDeclaredMethod("moveDown", Cursor.class));
			controls.put(KeyEvent.VK_RIGHT, Control.class.getDeclaredMethod("moveRight", Cursor.class));
			controls.put(KeyEvent.VK_LEFT, Control.class.getDeclaredMethod("moveLeft", Cursor.class));
			controls.put(KeyEvent.VK_ENTER, Control.class.getDeclaredMethod("selectCubeFace", Cursor.class));
			controls.put(KeyEvent.VK_ESCAPE, Control.class.getDeclaredMethod("removeSelections", Cursor.class));
		} catch (NoSuchMethodException e) {
			LogHandler.error("Couldn't find method " + e.getMessage());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Method action = lookup(e.getKeyCode());
		
		try {
			action.invoke(new Control(), new Cube());
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Method lookup(int key) {
		return controls.get(key);
	}

	public void selectCubeFace(Cube cube) {

	}

	public void rotateCubeRight(Cube cube) {
		TerminalHandler.clear();
		ca.rotateRight();
	}

	public void rotateCubeLeft(Cube cube) {
		TerminalHandler.clear();
		ca.rotateLeft();
	}

	public void rotateCubeUp(Cube cube) {
		TerminalHandler.clear();
		ca.rotateUp();
	}

	public void rotateCubeDown(Cube cube) {
		TerminalHandler.clear();
		ca.rotateDown();
	}

	public void moveCursorRight(Cursor cursor) {
		int currentYPosition = cursor.getYPosition();
		cursor.setyPosition(currentYPosition + 1);
	}

	public void moveCursorLeft(Cursor cursor) {
		int currentYPosition = cursor.getYPosition();
		cursor.setyPosition(currentYPosition - 1);

	}

	public void moveCursorUp(Cursor cursor) {
		int currentXPosition = cursor.getXPosition();
		cursor.setxPosition(currentXPosition - 1);

	}

	public void moveCursorDown(Cursor cursor) {
		int currentXPosition = cursor.getXPosition();
		cursor.setxPosition(currentXPosition + 1);
	}

	public void selectCursorPosition(Cursor cursor) {
		int totalPins = cursor.getTotalPins();
		int cursorXPosition = cursor.getXPosition();
		int cursorYPosition = cursor.getYPosition();
		int[] cursorCords = new int[] { cursorXPosition, cursorYPosition };

		if (totalPins > 1) {
			return;
		}

		if (totalPins == 0) {
			cursor.setPin1Cords(cursorCords);
			cursor.setTotalPins(totalPins + 1);
			return;
		}

		if (totalPins == 1) {
			cursor.setPin2Cords(cursorCords);
			cursor.setTotalPins(totalPins + 1);
			return;
		}
	}

	public void removeCursorSelections(Cursor cursor) {
		cursor.resetPositionsAndTotalPins();
	}

	private void loadFrameConfiguration() {
		addKeyListener(this);
		setFocusable(true);
		setVisible(false);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setOpacity(0.01f);
		setExtendedState(Control.MAXIMIZED_BOTH);
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void run() {
		loadRotationControls();
		loadFrameConfiguration();
		while(true) {
			
		}
		
	}
}
