package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Stream;
import controler.Activable;
import controler.DataController;

public class NeuralNetwork implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5753254320935250755L;
	private String name;
	private Layer outputLayer;
	private Layer[] hiddenLayers;
	private double[] input = new double[17];
	private double learningRate;
	private int networkSize;
	private Activable function;
	private int[] statistics = new int[10];
	private double totalError;
	
	
	
	public NeuralNetwork(String name, int networkSize, int hiddenLayersSize, double learningRate, Activable function) {
		this.name = name;
		this.hiddenLayers = new Layer[networkSize];
		this.hiddenLayers[0] = new Layer(function, hiddenLayersSize, 16, true, learningRate);
		for(int i = 1; i < this.hiddenLayers.length; i++) {
			this.hiddenLayers[i] = new Layer(function, hiddenLayersSize, hiddenLayersSize, true, learningRate);
		}
		this.outputLayer = new Layer(function, 10, hiddenLayersSize, learningRate);
		this.learningRate = learningRate;
		this.networkSize = networkSize;
		this.function = function;
		System.out.println("*************************************************************************NewLayer " + hiddenLayersSize);
		System.out.println("*************************************************************************Layers " + networkSize);
		System.out.println("*************************************************************************LearningRate " + learningRate);
		System.out.println("*************************************************************************Funkcja " + function);
		
		
		System.out.println(name);
	}
	
	public String toString() {
		return name;
	}

	public Layer getOutputLayer() {
		return outputLayer;
	}

	public Layer[] getHiddenLayers() {
		return hiddenLayers;
	}
	
	public int getHiddenLayersSize() {
		return hiddenLayers[hiddenLayers.length-1].getSize();
	}

	public double[] getInput() {
		return input;
	}

	public double getLearningRate() {
		return learningRate;
	}
	
	public int getNetworkSize() {
		return networkSize;
	}
	
	public Activable getFunction() {
		return function;
	}
	
	public int[] getStatistics() {
		return statistics;
	}
	
	public void recognize(int cycle) {
		for(int i = 0; i < cycle; i++)
			recognize(false);
	}
	
	public void recognize(boolean isTest) {
		int sum = 0;
		statistics = new int[10];

		Stream<String> dataStream = null;
		Neuron.i = 0;
		try {
			if(isTest)
				dataStream = DataController.getTestingData();/////////////////////////////////////
			else
				dataStream = DataController.getLearnData(); 
		} catch (IOException e1) {	
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try(Stream<String> data = dataStream;) {
			
			data.peek(System.out::println).forEach(line -> {

				totalError = 0;
				boolean isOk = true;
				input = Arrays.stream(line.split(",")).map(String::trim).mapToDouble(Double::parseDouble).toArray();
			
				//System.out.println("xxxxxxxxxxxxxxxxx   wejście                      xxxxxxxxxxxxxx"+input[0] +" "+ input[1] +" "+ input[2]);
				//get results from input layer
				double[] results = hiddenLayers[0].test(input);
				//System.out.println("1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+results[0] +" "+ results[1] +" "+ results[2] +" "+ results[3] +" "+ results[4] +" "+ results[5] +" "+ results[6] +" "+ results[7]);
				
				
				//get results from hidden layerss
				for(int i = 1; i < hiddenLayers.length; i++) {
					results = hiddenLayers[i].test(results);
		
				}
				//System.out.println("2xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+results[0] +" "+ results[1] +" "+ results[2] +" "+ results[3] +" "+ results[4] +" "+ results[5] +" "+ results[6] +" "+ results[7]);
				// total result 
				results = outputLayer.test(results);
		
				//System.out.println("3xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+results[0] +" "+ results[1] +" "+ results[2] +" "+ results[3] +" "+ results[4] +" "+ results[5] +" "+ results[6] +" "+ results[7] +" "+ results[8] +" "+ results[9]);
				
				
				double[] compareResult = new double[10];
				compareResult[(int) input[input.length-1]] = 1;
				
				
				double max = results[0];
				int index = 0;
				for(int i = 0; i < results.length; i++) {
					if(results[i] > max) {
						max = results[i];
						index = i;
					}
					System.out.println(results[i]+"  "+compareResult[i]);
				}
				//System.out.println("A to jest numer indexu :" + index);
				
				//System.out.println(input[input.length-1]+" wyniki niże");
				if(compareResult[index] == 1) {
					statistics[index]++;
					results[index] = 1;
					System.out.println("DDDDDDDDDDDDDDOOOOOOOOOOOOOOOOOOOOBBBBBBBBBBBRRRRRRRRRRRRRRRRRRRRRZZZZZZZZZZZZZZZZZEEEEEEEEEEEEEEE");
				}
				
				
				if(!isTest) {//////////////////////////////////////////// propagacja wsteczna błędu
					
					//output layer error
					double res = 0;
					double tmp = 0;
					for(int i = 0; i < results.length; i++) {
						
						tmp =  Math.pow((compareResult[i]-results[i]), 2)/2;
						res += tmp;
						outputLayer.getNeurons()[i].errorFactor = tmp;
					}
					totalError = res;
					System.out.println("Total error :                  "+totalError);
					
					//backpropagation 
					double input;
					
					//output layer
					//neutrons error
					for(int i = 0; i < outputLayer.getNeurons().length; i++) {
						//neutron error
						outputLayer.getNeurons()[i].neuronError = (compareResult[i] - outputLayer.getNeurons()[i].getfNet()) * (outputLayer.getNeurons()[i].getfNet())*(1-outputLayer.getNeurons()[i].getfNet());

						
						

						
					}
					
					//hidden
					//neutrons error
					Layer nextLayer = outputLayer;
					for(int i = hiddenLayers.length-1; i >= 0 ; i--) {
						for(int j = 0; j < hiddenLayers[i].getNeurons().length; j++) {
							hiddenLayers[i].getNeurons()[j].neuronError = 0;
							for(int k = 0; k < nextLayer.getSize(); k++) {
							
								hiddenLayers[i].getNeurons()[j].neuronError += (nextLayer.getNeurons()[k].neuronError * nextLayer.getNeurons()[k].getWeights()[j]);
								
								//System.out.println("NEXT layer neutron error" + nextLayer.getNeurons()[k].neuronError * nextLayer.getNeurons()[k].getWeights()[j]);
							
							}
							
							hiddenLayers[i].getNeurons()[j].neuronError *= (hiddenLayers[i].getNeurons()[j].getfNet())*(1-hiddenLayers[i].getNeurons()[j].getfNet());
							
							//System.out.println("hidden layer neutron error" + hiddenLayers[i].getNeurons()[j].neuronError);
						}
						nextLayer = hiddenLayers[i];
					}
					
					
					
					//weights update
					
					for(int i = 0; i < hiddenLayers.length; i++) {
						for(int j = 0; j < hiddenLayers[i].getNeurons().length; j++) {
							for(int k = 0; k < hiddenLayers[i].getNeurons()[j].getWeights().length; k++) {
								hiddenLayers[i].getNeurons()[j].getWeights()[k] = hiddenLayers[i].getNeurons()[j].getWeights()[k] + (getLearningRate() * hiddenLayers[i].getNeurons()[j].neuronError * hiddenLayers[i].layerInput[k]);
								//System.out.println(getLearningRate() +" "+ hiddenLayers[i].getNeurons()[j].neuronError +" "+ hiddenLayers[i].layerInput[k]);
								//System.out.println("kttualizacja " + getLearningRate() * hiddenLayers[i].getNeurons()[j].neuronError * hiddenLayers[i].layerInput[k] + " layer nr " + i);
							}
						}
					}
					
					for(int j = 0; j < outputLayer.getNeurons().length; j++) {
						for(int k = 0; k < outputLayer.getNeurons()[j].getWeights().length; k++) {
							//System.out.println("przed aktualizacją output layer " + outputLayer.getNeurons()[j].getWeights()[k]);
							outputLayer.getNeurons()[j].getWeights()[k] += (getLearningRate() * outputLayer.getNeurons()[j].neuronError * outputLayer.layerInput[k]);
							//System.out.println("o ile się zmienia " + getLearningRate() * outputLayer.getNeurons()[j].neuronError * outputLayer.layerInput[k]);
						}
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				
				}
					
				System.out.println("Ostatni wynik: " + results[0] + results[1] + results[2] + results[3] + results[4] + results[5] + results[6] + results[7] + results[8] + results[9]);
				System.out.println("powinno być: " + input[input.length-1]); 
				
			});
		}
		for(int i : statistics) {
			sum+=i;
		}
		System.out.println(dataStream.toString());
		System.out.println(statistics[0]+" "+statistics[1]+" "+statistics[2]+" "+statistics[3]+" "+statistics[4]+" "+statistics[5]+" "+statistics[6]+" "+statistics[7]+" "+statistics[8]+" "+statistics[9]+ "razem "+sum);
	}

	
}
