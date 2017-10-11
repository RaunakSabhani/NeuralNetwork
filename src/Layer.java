import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Layer {
	List<Node> nodes;
    LayerType layerType;
    Layer previousLayer;
    PostProcess postProcess;
    Utility utility;
    
    public Layer(Layer previousLayer, LayerType layerType, PostProcess postProcess, Utility utility) {
        nodes = new ArrayList<>();
        this.previousLayer = previousLayer;
        this.layerType = layerType;
        this.postProcess = postProcess;
        this.utility = utility;
    }
    
    public boolean initializeInputLayer() {
    	System.out.println("Input Layer Size: " + Data.noOfAttributes);
    	if(postProcess.getInputData() == null || postProcess.getInputData().size() == 0)
    		return false;
    	for(int i=0;i<Data.noOfAttributes;i++) {
    		Node inputLayerNode = new Node(this);
    		nodes.add(inputLayerNode);
    	}
    	return true;
    }
    
    public void initializeHiddenLayer(int index) {
    	for(int i=0;i<utility.getHiddenLayerNodeCount()[index];i++) {
    		Node currentNode = new Node(this);
            Random random = new Random();
            List<Node> previousNodes = previousLayer.getNodes();
            for(Node previousNode: previousNodes) {
            	float weight = random.nextFloat() * 2 - 1;
            	Edge forwardEdge = new Edge(weight, previousNode, currentNode);
                Edge reverseEdge = new Edge(weight, currentNode, previousNode);
                previousNode.addEdge(forwardEdge);
                currentNode.addRevEdge(reverseEdge);
            }
            nodes.add(currentNode);
    	}
    }
    
    public void initializeOutputLayer() {
    	Node outputLayerNode = new Node(this);
    	Random random = new Random();
        List<Node> previousNodes = previousLayer.getNodes();
        for (Node previousNode : previousNodes) {
            float weight = random.nextFloat() * 2 - 1;
            Edge forwardEdge = new Edge(weight, previousNode, outputLayerNode);
            Edge reverseEdge = new Edge(weight, outputLayerNode, previousNode);
            previousNode.addEdge(forwardEdge);
            outputLayerNode.addRevEdge(reverseEdge);
        }
        nodes.add(outputLayerNode);
    }
    
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setLayerType(LayerType layerType) {
        this.layerType = layerType;
    }
    
    public LayerType getLayerType() {
        return layerType;
    }
    
    public void performForwardPass()
    {
    	double sum;
    	for(Node node: nodes)
    	{
    		sum = node.getBias();
    		for( Edge prevEdge : node.getReverseAdjList())
    		{
    			Node previousNode = prevEdge.getDestination();
    			sum = sum + (previousNode.getOutput() * prevEdge.getWeight());
    		}
    		node.setOutput(computeSigmoidValue(sum));
    	}
    }
    
    public void performBackwardPass()
    {
    	
    }

    public double computeSigmoidValue(double sum)
    {
    	return 1 / (1 + Math.pow(Math.E, -sum));
    }
}
