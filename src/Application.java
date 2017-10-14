import java.io.File;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utility utility = new Utility();
		utility.setInputFilePath(args[0]);
		//utility.setLearningRate(Float.parseFloat(args[1]));
		utility.setNoOfIterations(Integer.parseInt(args[2]));
		utility.setHiddenLayerCount(Integer.parseInt(args[3]));
		int argIndex = 3;
		for(int i=0;i<Integer.parseInt(args[3]);i++)
		{
			argIndex = argIndex + 1;
			utility.setHiddenLayerNodeCount(Integer.parseInt(args[argIndex]), i);
		}
		File inputFile = new File(utility.getInputFilePath());
		PostProcess postProcess = new PostProcess(inputFile);
		postProcess.readData();
		utility.setTrainingSetLimit((int)((postProcess.getInputData().size()-1) * Integer.parseInt(args[1]) / 100));
		NeuralNet neuralNetwork = new NeuralNet(postProcess, utility);
		neuralNetwork.initializeNetwork();
	
		int noOfIter = 0;
		Double error = Double.MAX_VALUE;
		//System.out.println("No of iterations is: " );
		while(noOfIter < utility.getNoOfIterations() && error != 0.0)
		{
			System.out.println("Iteration no: " + noOfIter);
			error = neuralNetwork.trainNeuralNet();
			noOfIter = noOfIter + 1;
		}
		
		neuralNetwork.printModel();
		System.out.println("Learning rate is: " + utility.getLearningRate());
		System.out.println("No of instance: " + utility.getTrainingSetLimit());
		System.out.println("Mean squared training error: " + (error/utility.getTrainingSetLimit()));
		error = neuralNetwork.testNeuralNet();
		System.out.println("No of instnace: " + (postProcess.getInputData().size() - utility.getTrainingSetLimit()));
		System.out.println("Mean squared test error: " + (error/(postProcess.getInputData().size() - utility.getTrainingSetLimit())));
	}

}
