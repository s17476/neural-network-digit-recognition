package model;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import controler.Activable;
import controler.BinaryStepUni;
import controler.DataController;

public class NeuralNetwork implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5753254320935250755L;
	private String name;
	private Layer outputLayer;
	private Layer[] hiddenLayers;
	private int[] input = new int[17];
	private BigDecimal learningRate;
	private int networkSize;
	private Activable function;
	private int[] statistics = new int[10];
	private BigDecimal totalError;
	
	
	
	public NeuralNetwork(String name, int networkSize, int hiddenLayersSize, BigDecimal learningRate, Activable function) {
		this.name = name;
		this.hiddenLayers = new Layer[networkSize];
		this.hiddenLayers[0] = new Layer(function, hiddenLayersSize, 16, true, learningRate);
		for(int i = 1; i < this.hiddenLayers.length; i++) {
			this.hiddenLayers[i] = new Layer(function, hiddenLayersSize, hiddenLayersSize, learningRate);
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

	public int[] getInput() {
		return input;
	}

	public BigDecimal getLearningRate() {
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

				totalError = new BigDecimal(0);
				boolean isOk = true;
				input = Arrays.stream(line.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
				
				//get results from input layer
				BigDecimal[] results = hiddenLayers[0].test(input);
				
				//get results from hidden layerss
				for(int i = 1; i < hiddenLayers.length; i++) {
					results = hiddenLayers[i].test(results);
					System.out.println("res" + results[i]);
				}
				
				// total result 
				results = outputLayer.test(results);
				
				
				
				
				double[] compareResult = new double[10];
				compareResult[input[input.length-1]] = 1;
				
				
				for(int i = 0; i < results.length; i++) {
					//System.out.println(results[i]+"  "+compareResult[i]);
					if(results[i].intValue() != compareResult[i])
						isOk = false;
				}
				
				//System.out.println(input[input.length-1]+" wyniki niże");
				if(isOk) {
					statistics[input[input.length-1]]++;
					System.out.println("DDDDDDDDDDDDDDOOOOOOOOOOOOOOOOOOOOBBBBBBBBBBBRRRRRRRRRRRRRRRRRRRRRZZZZZZZZZZZZZZZZZEEEEEEEEEEEEEEE");
				}
				
				
				if(!isTest) {//////////////////////////////////////////// propagacja wsteczna błędu
					
					//output layer error
					double res = 0;
					double tmp = 0;
					for(int i = 0; i < results.length; i++) {
						
						tmp =  Math.pow((compareResult[i]-results[i].doubleValue()), 2)/2;
						res += tmp;
						outputLayer.getNeurons()[i].errorFactor = new BigDecimal(tmp);
					}
					totalError = new BigDecimal(res);
					System.out.println("Total error :                  "+totalError);
					
					//backpropagation 
					
					//output layer
					for(int i = 0; i < outputLayer.getNeurons().length; i++) {
						BigDecimal tmpError = new BigDecimal(-((compareResult[i])-outputLayer.getNeurons()[i].getfNet().doubleValue())).multiply(outputLayer.getNeurons()[i].getfNet().multiply(new BigDecimal(1).subtract(outputLayer.getNeurons()[i].getfNet())));
						//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                    fNet output" + outputLayer.getNeurons()[i].getfNet());
						//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                    err" + tmpError);
						outputLayer.getNeurons()[i].neuronError = tmpError;
						for(int j = 0; j < outputLayer.getNeurons()[0].getWeights().length; j++) {
							BigDecimal tmpWeight = outputLayer.getNeurons()[i].weights[j];
							//System.out.println("Stara" + tmpWeight);
							tmpWeight = tmpWeight.subtract(tmpError.multiply(learningRate.multiply(hiddenLayers[hiddenLayers.length-1].getNeurons()[j].getfNet())));
							//System.out.println("nowa" + tmpWeight);
						}
					}
					
					//hidden
					Layer tmpLayer = outputLayer;
					for(int i = hiddenLayers.length-1; i >= 0 ; i--) {
						for(int j = 0; j < hiddenLayers[i].getNeurons().length; j++) {
							
							
							BigDecimal[] tmpError = new BigDecimal[tmpLayer.getNeurons().length];
							
							for(int k = 0; k < tmpLayer.getNeurons().length; k++) {
								tmpError[k] = tmpLayer.getNeurons()[k].neuronError;
								//System.out.println("                           NEURON   BBBBBBBLLLLLLLLLLAAAAAAAAADDDDDDDDDDD    "+ tmpError[k]);
								tmpError[k] = tmpError[k].multiply(tmpLayer.getNeurons()[k].getWeights()[j]);
								//System.out.println("                              BBBBBBBLLLLLLLLLLAAAAAAAAADDDDDDDDDDD    "+ tmpError[k]);
								
							}
								BigDecimal s = new BigDecimal(0);
								for(BigDecimal err : tmpError) {
									s = s.add(err);
								}
								//System.out.println("               SUMA               BBBBBBBLLLLLLLLLLAAAAAAAAADDDDDDDDDDD    "+ s);
								hiddenLayers[i].getNeurons()[j].neuronError = hiddenLayers[i].getNeurons()[j].getfNet().multiply(new BigDecimal(1).subtract(hiddenLayers[i].getNeurons()[j].getfNet()));
								if(hiddenLayers[i].getNeurons()[j].neuronError.doubleValue() != 0)
								System.out.println("                                                                                           AKTUALIZUJE");
								BigDecimal input;
								for(int k= 0; k < tmpLayer.getNeurons()[j].getWeights().length; k++) {
									//System.out.println(i+ "  "+j+"    "+k);
									System.out.println("               Stara              "+ hiddenLayers[i].getNeurons()[j].getWeights()[k]);
									input = hiddenLayers[i].getNeurons()[j].getInput()[k];
									hiddenLayers[i].neurons[j].weights[k] = hiddenLayers[i].getNeurons()[j].getWeights()[k].subtract(hiddenLayers[i].getNeurons()[j].neuronError.multiply(input)).multiply(s).multiply(learningRate);
									System.out.println("               Nowa              "+ hiddenLayers[i].getNeurons()[j].getWeights()[k]);
								}
								
								
						}
						tmpLayer = hiddenLayers[i];
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
