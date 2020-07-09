package model;

import java.io.Serializable;
import java.math.BigDecimal;

import controler.Activable;

public class Layer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1163474557853695861L;
	Neuron[] neurons;
	Activable function;
	BigDecimal learningRate;
	BigDecimal[] errorTab;
	
	Layer(Activable function, int neuronsSize, int hiddenLayersSize, BigDecimal learningRate){
		this(function, neuronsSize, hiddenLayersSize, false, learningRate);
	}
	
	Layer(Activable function, int neuronsSize, int hiddenLayersSize, boolean bias, BigDecimal learningRate){
		this.neurons = Neuron.createNeurons(function, neuronsSize, hiddenLayersSize, bias, learningRate);
		
		System.out.println("NewLayer+++++++++++++++++++++++++++++++++++++" + neurons[0]);
	}
	
	public int getSize() {
		return neurons.length;
	}
	
	public Neuron[] getNeurons() {
		return neurons;
	}
	
	public BigDecimal[] test(int[] input) {
		BigDecimal[] results = new BigDecimal[neurons.length];
		for(int i = 0; i < neurons.length; i++) {
			results[i] = neurons[i].test(input);
		}
		return results;
	}
	
	public BigDecimal[] test(BigDecimal[] input) {
		BigDecimal[] results = new BigDecimal[neurons.length];
		for(int i = 0; i < neurons.length; i++) {
			results[i] = neurons[i].test(input);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxcccccccccccccccccccccccccccccccccccccccccccccc"+results[i]);
		}
		return results;
	}
	
	public BigDecimal[] teach(boolean isOut) {
			errorTab = new BigDecimal[neurons.length];
				for(int i = 0; i < neurons.length; i++) {
					errorTab[i] = neurons[i].teach(isOut);////////////warstwy
				}
		
		return null;
	}
}
