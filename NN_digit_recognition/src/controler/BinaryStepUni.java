package controler;

import java.io.Serializable;
import java.math.BigDecimal;

import model.Layer;
import model.Neuron;

public class BinaryStepUni implements Activable, Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1954857076828560827L;
	String name = "Binary Step Function - Unipolar";
	 
	 @Override
	 public String toString() {
		 return name;
	 }

	@Override
	public BigDecimal activate(BigDecimal in) {
		if(in.compareTo(new BigDecimal(0)) >= 0)
			return new BigDecimal(1);
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal update(Neuron neuron, boolean isOut) {//////////////////////////////////////co jeśli error =0
		for(int i =0; i < neuron.getWeights().length; i++) {
			neuron.getWeights()[i] = neuron.getWeights()[i].add(neuron.getLearningRate().multiply(neuron.neuronError).multiply(neuron.input[i]));
		}
		
		return null;
	}

}
