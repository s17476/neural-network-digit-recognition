package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import controler.Activable;


public class Neuron implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5182658550333819685L;
	double[] weights;
	double bias;
	//to net
	double output;
	//to po aktywacji
	double fNet;
	public double[] input;
	Double learningRate;

	public double neuronError;
	double errorFactor;
	static int i = 0;
	
	public double getBias() {
		return bias;
	}



	public void setBias(double bias) {
		this.bias = bias;
	}


	public double getLearningRate() {
		return learningRate;
	}



	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public double[] getWeights() {
		return weights;
	}



	public void setWeights(double[] weights) {
		this.weights = weights;
	}
	public double[] getInput() {
		return input;
	}



	public void setInput(double[] input) {
		this.input = input;
	}



	public double getfNet() {
		return fNet;
	}



	public void setfNet(double fNet) {
		this.fNet = fNet;
	}

	
	public double getErrorFactor() {
		return errorFactor;
	}



	public void setErrorFactor(double errorFactor) {
		this.errorFactor = errorFactor;
	}

	Activable function;
	
	private Neuron(Activable function, int weightsSize, boolean bias, double learningRate){
		this.neuronError = 0;
		this.function = function;
		this.learningRate = learningRate;
		this.weights = new double[weightsSize];
		for(int i = 0; i < weights.length; i++) {
			double random = new Random().nextDouble();
			double result = ((int)(random * (5)))/100d;
			weights[i] = result;
			//System.out.println("To losowe: " + weights[i]);
		}
		if(bias) {
			double random = new Random().nextDouble();
			double result = ((int)(random * (5)))/100d;
			this.bias = result;
		}
	}
	

	
	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
	    return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	}
	
	public String toString() {
		String result = "Wagi:\n";
		for(double weight : weights) {
			result += weight + "\n";
		}
		result += bias + "\n";
		return result;
	}
	
	public static Neuron[] createNeurons(Activable function, int size, int hiddenLayersSize, boolean bias, double learningRate) {
		Neuron[] result = new Neuron[size];
		for(int i = 0; i < result.length; i++) {
			result[i] = new Neuron(function, hiddenLayersSize, bias, learningRate);
			System.out.println(result[i] + "nowy neuron\n\n");
		}
		//System.out.println(result[0] + "nowy neuron w nowej tablicy\n\n");
		return result;
	}
	
	public void setOutput(double out) {
		output = out;
	}
	
	public double getOutput() {
		return output;
	}
	
	public void setError(double error) {
		neuronError = error;
	}
	
	public double getError() {
		return neuronError;
	}
	
	public double test(int[] input) {
		double[] tmpTab = new double[input.length];
		for(int i = 0; i < input.length; i++) {
			tmpTab[i] = input[i];
			
		}
		return test(tmpTab);
	}
	
	public double test(double[] input) {
		//for(int i = 0; i < input.length-1; i++)
			//input[i]/=100.d;
		this.input = input;
		//System.out.println("xxxxxxxxxxxxxx        input xxxxxxxx"+input[0] +" "+ input[1] +" "+ input[2] +" "+ input[3]);
		//System.out.println("xxxxxxxxxxxxxx        input wagi"+weights[0] +" "+ weights[1] +" "+ weights[2] +" "+ weights[3]+" "+weights[5] +" "+ weights[6]);
		output = 0;
		double tmp;
		for (int i = 0; i < weights.length; i++) {
			//System.out.println("to" + weights[i] + " mnoże razy to " + input[i]);
			tmp = weights[i]*(input[i]);
			output += tmp;
			//System.out.println("\n\n"+tmp+" total: "+ output);
			
			
		}
		
		if(bias != 0) output += bias;
		//System.out.println("xxxxxxxxxxxxxx        output xxxxxxxx"+output);
		
		
		fNet =  function.activate(output);
		//System.out.println("Zwraca nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeettttttttttttttttttttttttttttttttttttttttttttttttt" + fNet);
		//zwraca aktywowany net
		
		return fNet;
	}
	
	public double teach(boolean isOut) {
		
		return 0;
		//else
		//return function.update(error, this, isOut);
	}
}

