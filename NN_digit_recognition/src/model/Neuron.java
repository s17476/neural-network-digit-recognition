package model;

import java.math.BigDecimal;
import java.math.MathContext;


public class Neuron {
	
	BigDecimal[] weights;
	BigDecimal bias;
	
	Neuron(int weightsSize){

		this.weights = new BigDecimal[weightsSize];
		for(int i = 0; i < weights.length; i++) {
			weights[i] = generateRandomBigDecimalFromRange(
				    new BigDecimal(-5),
				    new BigDecimal(5)////////////////////.setScale(2)
					).round(new MathContext(4));
			System.out.println("To losowe: " + weights[i]);
		}
		bias = generateRandomBigDecimalFromRange(
			    new BigDecimal(-5),
			    new BigDecimal(5)////////////////////.setScale(2)
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
	
	public static Neuron[] createNeurons(int size, int hiddenLayersSize) {
		Neuron[] result = new Neuron[size];
		for(Neuron tmp : result) {
			tmp = new Neuron(hiddenLayersSize);
			System.out.println(tmp);
		}
		return result;
	}
}

