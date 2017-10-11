
public class Utility {
	int hiddenLayerCount;
	int[] hiddenLayerNodeCount;
	float learningRate;
	int trainingSetLimit;
	int noOfIterations;
	String inputFilePath;
	
	public int getNoOfIterations() {
		return noOfIterations;
	}
	public void setNoOfIterations(int noOfIterations) {
		this.noOfIterations = noOfIterations;
	}
	public int getHiddenLayerCount() {
		return hiddenLayerCount;
	}
	public void setHiddenLayerCount(int hiddenLayerCount) {
		this.hiddenLayerCount = hiddenLayerCount;
		this.hiddenLayerNodeCount = new int[hiddenLayerCount];
	}
	public int[] getHiddenLayerNodeCount() {
		return hiddenLayerNodeCount;
	}
	public void setHiddenLayerNodeCount(int hiddenLayerNodeCount, int index) {
		this.hiddenLayerNodeCount[index] = hiddenLayerNodeCount;
	}
	public float getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(float learningRate) {
		this.learningRate = learningRate;
	}
	public int getTrainingSetLimit() {
		return trainingSetLimit;
	}
	public void setTrainingSetLimit(int trainingSetLimit) {
		this.trainingSetLimit = trainingSetLimit;
	}
	
	public String getInputFilePath()
	{
		return this.inputFilePath;
	}
	
	public void setInputFilePath(String path)
	{
		this.inputFilePath = path;
	}
}
