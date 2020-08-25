package controler;

import java.io.Serializable;
import java.math.BigDecimal;

import model.Layer;
import model.Neuron;

public class BinaryStepBi implements Activable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1129873727815627365L;
	String name = "Binary Step Function - Bipolar";
	 
	 @Override
	 public String toString() {
		 return name;
	 }

	 @Override
		public double activate(double in) {
			if(in >= 0)
				return 1;
			return -1;
		}

	@Override
	public double update(Neuron neuron, boolean isOut) {
		// TODO Auto-generated method stub
		return 0;
	}

}
