package br.com.magna.structures;

public class Node {

	private Node right;
	private Node left;
	private Node up;
	private Node down;

	private Integer value;

	public Node(Integer value) {
		this.value = value;
		right = null;
		left = null;
		up = null;
		down = null;
	}

	public Integer getValue() {
		return value;
	}

	public void setRightNode(Node rightNode) {
		right = rightNode;
	}

	public void setLeftNode(Node leftNode) {
		left = leftNode;
	}

	public void setUpNode(Node upNode) {
		up = upNode;
	}

	public void setDownNode(Node downNode) {
		down = downNode;
	}

	public Node getRightNode() {
		return right;
	}

	public Node getLeftNode() {
		return left;
	}

	public Node getUpNode() {
		return up;
	}

	public Node getDownNode() {
		return down;
	}

	public void printAdj() {
		System.out.println("Node value: " + value);

		if (right != null) {
			System.out.println("Right value: " + right.getValue());
		}

		if (left != null) {
			System.out.println("Left value: " + left.getValue());
		}

		if (up != null) {
			System.out.println("Up value: " + up.getValue());
		}

		if (down != null) {
			System.out.println("Down value: " + down.getValue());
		}
	}

}
