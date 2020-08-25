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
	public double activate(double in) {
		if(in >= 0)
			return 1;
		return 0;
	}

	@Override
	public double update(Neuron neuron, boolean isOut) {//////////////////////////////////////co jeśli error =0

		return 0;
	}

}
