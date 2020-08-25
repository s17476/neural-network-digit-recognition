package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

import controler.Activable;

public class Layer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1163474557853695861L;
	Neuron[] neurons;
	Activable function;
	double learningRate;
	double[] errorTab;
	double[] layerInput;
	
	Layer(Activable function, int neuronsSize, int hiddenLayersSize, double learningRate){
		this(function, neuronsSize, hiddenLayersSize, true, learningRate);
	}
	
	Layer(Activable function, int neuronsSize, int hiddenLayersSize, boolean bias, double learningRate){
		this.neurons = Neuron.createNeurons(function, neuronsSize, hiddenLayersSize, bias, learningRate);
		
		//System.out.println("NewLayer+++++++++++++++++++++++++++++++++++++" + neurons[0]);
	}
	
	public int getSize() {
		return neurons.length;
	}
	
	public Neuron[] getNeurons() {
		return neurons;
	}
	
	public double[] test(int[] input) {
		layerInput = Arrays.copyOf(Arrays.stream(input).asDoubleStream().toArray(), input.length);
		double[] results = new double[neurons.length];
		for(int i = 0; i < neurons.length; i++) {
			results[i] = neurons[i].test(input);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxccccccccccccccccccccccccccccccccccccccccccccccint"+results[i]);
		}
		return results;
	}
	
	public double[] test(double[] input) {
		layerInput = Arrays.copyOf(input, input.length);
		double[] results = new double[neurons.length];
		for(int i = 0; i < neurons.length; i++) {
			results[i] = neurons[i].test(input);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxccccccccccccccccccccccccccccccccccccccccccccccdouble"+results[i]);
		}
		return results;
	}
	
	public double[] teach(boolean isOut) {
			errorTab = new double[neurons.length];
				for(int i = 0; i < neurons.length; i++) {
					errorTab[i] = neurons[i].teach(isOut);////////////warstwy
				}
		
		return null;
	}
}
