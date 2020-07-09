package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

import controler.Activable;


public class Neuron implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5182658550333819685L;
	BigDecimal[] weights;
	BigDecimal bias;
	//to net
	BigDecimal output;
	//to po aktywacji
	BigDecimal fNet;
	public BigDecimal[] input;
	BigDecimal learningRate;

	public BigDecimal neuronError;
	BigDecimal errorFactor;
	static int i = 0;
	
	public BigDecimal getBias() {
		return bias;
	}



	public void setBias(BigDecimal bias) {
		this.bias = bias;
	}


	public BigDecimal getLearningRate() {
		return learningRate;
	}



	public void setLearningRate(BigDecimal learningRate) {
		this.learningRate = learningRate;
	}

	public BigDecimal[] getWeights() {
		return weights;
	}



	public void setWeights(BigDecimal[] weights) {
		this.weights = weights;
	}
	public BigDecimal[] getInput() {
		return input;
	}



	public void setInput(BigDecimal[] input) {
		this.input = input;
	}



	public BigDecimal getfNet() {
		return fNet;
	}



	public void setfNet(BigDecimal fNet) {
		this.fNet = fNet;
	}

	
	public BigDecimal getErrorFactor() {
		return errorFactor;
	}



	public void setErrorFactor(BigDecimal errorFactor) {
		this.errorFactor = errorFactor;
	}

	Activable function;
	
	private Neuron(Activable function, int weightsSize, boolean bias, BigDecimal learningRate){
		this.neuronError = new BigDecimal(0);
		this.function = function;
		this.learningRate = learningRate;
		this.weights = new BigDecimal[weightsSize];
		for(int i = 0; i < weights.length; i++) {
			weights[i] = generateRandomBigDecimalFromRange(
				    new BigDecimal(-1),
				    new BigDecimal(1)////////////////////.setScale(2)
					).round(new MathContext(4));
			//System.out.println("To losowe: " + weights[i]);
		}
		if(bias)
			this.bias = generateRandomBigDecimalFromRange(
				    new BigDecimal(-1),
				    new BigDecimal(1)////////////////////.setScale(2)
					).round(new MathContext(4));
	}
	

	
	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
	    return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	}
	
	public String toString() {
		String result = "Wagi:\n";
		for(BigDecimal weight : weights) {
			result += weight + "\n";
		}
		result += bias + "\n";
		return result;
	}
	
	public static Neuron[] createNeurons(Activable function, int size, int hiddenLayersSize, boolean bias, BigDecimal learningRate) {
		Neuron[] result = new Neuron[size];
		for(int i = 0; i < result.length; i++) {
			result[i] = new Neuron(function, hiddenLayersSize, bias, learningRate);
			System.out.println(result[i] + "nowy neuron\n\n");
		}
		System.out.println(result[0] + "nowy neuron w nowej tablicy\n\n");
		return result;
	}
	
	public void setOutput(BigDecimal out) {
		output = out;
	}
	
	public BigDecimal getOutput() {
		return output;
	}
	
	public void setError(BigDecimal error) {
		neuronError = error;
	}
	
	public BigDecimal getError() {
		return neuronError;
	}
	
	public BigDecimal test(int[] input) {
		BigDecimal[] tmpTab = new BigDecimal[input.length];
		for(int i = 0; i < input.length; i++) {
			tmpTab[i] = new BigDecimal(input[i]);
			
		}
		return test(tmpTab);
	}
	
	public BigDecimal test(BigDecimal[] input) {
		this.input = input;
		output = new BigDecimal(0);
		BigDecimal tmp;
		for (int i = 0; i < weights.length; i++) {
			tmp = weights[i].multiply(input[i]);
			
			output = output.add(tmp);
			
		}
		
		if(bias != null) output.add(bias);
		
		
		
		fNet =  function.activate(output);
		//System.out.println("Zwraca nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeettttttttttttttttttttttttttttttttttttttttttttttttt" + fNet);
		//zwraca aktywowany net
		return fNet;
	}
	
	public BigDecimal teach(boolean isOut) {
		if(isOut)
					function.update(this, isOut);////////////warstwy
		else
			neuronError = neuronError.add(function.update(this, isOut));
				//System.out.println("Nowa"+neurons[i].getWeights()[i]);
		return null;
		//else
		//return function.update(error, this, isOut);
	}
}

