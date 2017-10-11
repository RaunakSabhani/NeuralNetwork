import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*import Edge;
import Layer;
import LayerType;*/

public class Node {

	double output;
	double gradient;
	Layer layer;
	boolean visited;
	double targetValue;
	List<Edge> adjList;
	List<Edge> reverseAdjList;
	double bias;

	public Node(Layer layer){
		this.layer = layer;
		adjList = new ArrayList<>();
		reverseAdjList = new ArrayList<>();
		if(this.layer.getLayerType()!=LayerType.INPUT) {
			Random random = new Random();
			bias = random.nextFloat() * 2 - 1;
		}
	}
	public void addEdge(Edge edge) {
		adjList.add(edge);
	}

	public double getOutput() {
		return output;
	}
	public void setOutput(double output) {
		this.output = output;
	}
	public double getGradient() {
		return gradient;
	}
	public void setGradient(double gradient) {
		this.gradient = gradient;
	}
	public Layer getLayer() {
		return layer;
	}
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public double getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}
	public List<Edge> getAdjList() {
		return adjList;
	}
	public List<Edge> getReverseAdjList() {
		return reverseAdjList;
	}
	public double getBias() {
		return bias;
	}
	public void setBias(double bias) {
		this.bias = bias;
	}
	public void addRevEdge(Edge edge) {
		reverseAdjList.add(edge);
	}
	
	public void calculateGradientValue()
	{
		double sum = 0.0;
		for(Edge edge : adjList)
		{
			
		}
	}
	
	public void calculateOutputGradientValue()
	{
		gradient = (this.targetValue - this.output) * getSigmoidDerivative();
	}
	
	public double getSigmoidDerivative() {
        return this.output * (1 - this.output);
    }
}
