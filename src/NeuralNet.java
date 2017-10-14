import java.util.ArrayList;
import java.util.List;

public class NeuralNet {
	List<Layer> layers;
	PostProcess postProcess;
	Utility utility;

	public NeuralNet(PostProcess postProcess, Utility utility) {
		layers = new ArrayList<>();
		this.postProcess = postProcess;
		this.utility = utility;
	}
	
	public void initializeNetwork() {
		Layer inputLayer = new Layer(null, LayerType.INPUT, postProcess, utility);
		boolean inputLayerCreated = inputLayer.initializeInputLayer();
		if(!inputLayerCreated) {
			System.out.println("Data was not pre-processed. Please pre process data and then feed.");
            return;
		}
		layers.add(inputLayer);
		for (int i = 0; i < utility.getHiddenLayerCount(); i++) {
            Layer hiddenLayer = new Layer(layers.get(layers.size() - 1), LayerType.HIDDEN, postProcess, utility);
            hiddenLayer.initializeHiddenLayer(i);
            layers.add(hiddenLayer);
        }
		Layer outputLayer = new Layer(layers.get(layers.size() - 1), LayerType.OUTPUT, postProcess, utility);
        outputLayer.initializeOutputLayer();
        layers.add(outputLayer);
	}
	
	public Double trainNeuralNet()
	{
		Double errorCount = 0.0;

		for(int i=0;i<utility.getTrainingSetLimit();i++)
		{
			forwardPass(i+1);
			errorCount = errorCount + calculateError(i);
			backwardPass(i+1);
		}
		return errorCount;
	}
	
	public Double testNeuralNet()
	{
		Double errorCount = 0.0;

		for(int i=utility.getTrainingSetLimit();i<postProcess.getInputData().size();i++)
		{
			forwardPass(i);
			errorCount = errorCount + calculateError(i);
		}
		return errorCount;
	}
	
	public void printModel()
	{
		if (this.layers.size() > 0)
		{
			System.out.println("Layer 0(Input Layer)");
			Layer inputLayer = layers.get(0);
			List<Node> nodes = inputLayer.getNodes();
			for(int i=0;i<nodes.size();i++)
			{
				System.out.print("Neuron"+(i+1)+" weights: ");
				List<Edge> forwardEdges = nodes.get(i).getAdjList();
				for(int j=0;j<forwardEdges.size();j++)
				{
					System.out.println(forwardEdges.get(j).getWeight()+",");
				}
			}
			
			for(int k=1;k<this.layers.size()-1;k++)
			{
				System.out.println("Layer "+k+"(Hidden Layer)");
				Layer hiddenLayer = layers.get(k);
				nodes = inputLayer.getNodes();
				for(int i=0;i<nodes.size();i++)
				{
					System.out.print("Neuron"+(i+1)+" weights: ");
					List<Edge> forwardEdges = nodes.get(i).getAdjList();
					for(int j=0;j<forwardEdges.size();j++)
					{
						System.out.println(forwardEdges.get(j).getWeight() +",");
					}
				}
			}
		}
	}
	
	public void forwardPass(int rowNo)
	{
		setInputLayerValues(rowNo);
		for(int i=1;i<layers.size();i++)
		{
			double sum = 0.0;
			
			layers.get(i).performForwardPass();
		}
	}
	
	public void backwardPass(int index)
	{
		Double targetValue = Double.parseDouble(postProcess.getInputData().get(index).getDataLine().get(Data.noOfAttributes));
		Layer outputLayer = layers.get(layers.size()-1);
		outputLayer.getNodes().get(0).setTargetValue(targetValue);
		outputLayer.getNodes().get(0).calculateOutputGradientValue();
		
		for(int i=layers.size()-2;i>0;i--)
		{
			Layer innerLayer = layers.get(i);
			for(Node node: innerLayer.getNodes())
			{
				double sum = 0.0;
				for (Edge edge : node.getAdjList())
				{
					sum = sum + edge.getDestination().getGradient() * edge.getWeight();
				}
				node.setGradient(sum * node.getSigmoidDerivative());
			}
		}
		
		for(int i=layers.size()-2;i>=0;i--)
		{
			Layer layer = layers.get(i);
			for(Node node: layer.getNodes())
			{
				List<Edge> adjacentList = node.getAdjList();
				for(int j=0;j<adjacentList.size();j++)
				{
					Double newWeight = adjacentList.get(j).getWeight() + (utility.getLearningRate() * node.getOutput() * adjacentList.get(j).getDestination().getGradient());
					adjacentList.get(j).setWeight(newWeight);
					adjacentList.get(j).getDestination().getReverseAdjList().get(j).setWeight(newWeight);
				}
			}
		}
	}
	
	public void setInputLayerValues(int rowNo)
	{
		Layer inputLayer = layers.get(0);
		//int noOfAttributes = preProcess.getInputData().get(0).noOfAttributes;
		int noOfAttributes = Data.noOfAttributes;
		for(int i=0;i<noOfAttributes;i++)
		{
			inputLayer.getNodes().get(i).setOutput(Double.parseDouble(postProcess.getInputData().get(rowNo).getDataLine().get(i)));
		}
	}
	
	public double calculateError(int rowNo)
	{
		Layer outputLayer = layers.get(layers.size()-1);
		Double expectedValue = outputLayer.getNodes().get(0).getOutput();
		int noOfAttributes = Data.noOfAttributes;
		Double targetValue = Double.parseDouble(postProcess.getInputData().get(rowNo).getDataLine().get(noOfAttributes));
		Double error = targetValue  - expectedValue;
		//System.out.println("Error is: " + error + "TargetValue is: " + targetValue + "Expected Value is: "+expectedValue);
		return error * error;
	}
}
