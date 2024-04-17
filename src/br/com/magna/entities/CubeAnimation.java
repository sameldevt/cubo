package br.com.magna.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.com.magna.services.LogHandler;
import br.com.magna.services.TerminalHandler;

public class CubeAnimation {

	public void rotateRight() {
		String path = "";
		int i = 1;
		while (i < 8) {
			TerminalHandler.clear();
			path += "frames/horizontal/" + i + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					System.out.println(line);
				}
				Thread.sleep(100);
			} catch (FileNotFoundException fileException) {
				LogHandler.error("Couldn't find file at " + path);
			} catch (InterruptedException threadException) {
				LogHandler.error("Error handling thread " + threadException.getMessage());
			}

			path = "";
			i++;
		}
	}

	public void rotateLeft() {
		String path = "";
		int i = 7;
		while (i > 0) {
			TerminalHandler.clear();
			path += "frames/horizontal/" + i + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					System.out.println(line);
				}
				Thread.sleep(100);
			} catch (FileNotFoundException fileException) {
				LogHandler.error("Couldn't find file at " + path);
			} catch (InterruptedException threadException) {
				LogHandler.error("Error handling thread " + threadException.getMessage());
			}

			path = "";
			i--;
		}
	}

	public void rotateUp() {
		String path = "";
		int i = 1;

		while (i < 8) {
			TerminalHandler.clear();
			path += "frames/vertical/" + i + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					System.out.println(line);
				}
				Thread.sleep(100);
			} catch (FileNotFoundException fileException) {
				LogHandler.error("Couldn't find file at " + path);
			} catch (InterruptedException threadException) {
				LogHandler.error("Error handling thread " + threadException.getMessage());
			}
			path = "";
			i++;
		}
		
	}
	
	public void rotateDown() {
		String path = "";
		int i = 7;

		while (i > 0) {
			TerminalHandler.clear();
			path += "frames/vertical/" + i + ".txt";
			try (Scanner scan = new Scanner(new File(path))) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					System.out.println(line);
				}
				Thread.sleep(100);
			} catch (FileNotFoundException fileException) {
				LogHandler.error("Couldn't find file at " + path);
			} catch (InterruptedException threadException) {
				LogHandler.error("Error handling thread " + threadException.getMessage());
			}
			path = "";
			i--;
		}		
	}
}
