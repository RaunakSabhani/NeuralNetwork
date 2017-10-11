//import application.Node;

public class Edge {
	double weight;
	Node source;
	Node destination;
	double weightDelta;
	
	public Edge(float weight, Node source, Node destination) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public double getWeightDelta() {
		return weightDelta;
	}

	public void setWeightDelta(double weightDelta) {
		this.weightDelta = weightDelta;
	}
}
