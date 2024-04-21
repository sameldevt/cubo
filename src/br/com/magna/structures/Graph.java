package br.com.magna.structures;

import java.util.LinkedList;
import java.util.List;

import br.com.magna.structures.Node;

public class Graph {

	private List<Node> nodes = new LinkedList<>();
	private Node selectedNode = null;

	public Graph() {

	}

	public void addNodes(Node... newNodes) {
		for (Node node : newNodes) {
//			if (!nodes.isEmpty()) {
//				if (nodes.size() == 4) {
//					for (Node nodeInList : nodes) {
//						if (nodeInList.getUpNode() == null) {
//
//						} else if (nodeInList.getDownNode() == null) {
//
//						}
//					}
//				}
//				for (Node nodeInList : nodes) {
//					if (nodeInList.getRightNode() == null) {
//						
//					} else if (nodeInList.getLeftNode() == null) {
//
//					}
//
//				}
//			}
			nodes.add(node);
		}
	}

	public void removeNode(Node node) {
		nodes.remove(node);
	}

	public void setSelectedNode(Node node) {
		selectedNode = node;
	}

	public Node getSelectedNode() {
		return selectedNode;
	}

	public void gotoRightNode() {
		selectedNode = selectedNode.getRightNode();
	}

	public void gotoLeftNode() {
		selectedNode = selectedNode.getLeftNode();
	}

	public void gotoUpNode() {
		selectedNode = selectedNode.getUpNode();
	}

	public void gotoDownNode() {
		selectedNode = selectedNode.getDownNode();
	}

	public void printNodes() {
		for (Node n : nodes) {
			n.printAdj();
			System.out.println();
		}
	}
}
