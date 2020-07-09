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
		public BigDecimal activate(BigDecimal in) {
			if(in.compareTo(new BigDecimal(0)) >= 0)
				return new BigDecimal(1);
			return new BigDecimal(-1);
		}

	@Override
	public BigDecimal update(Neuron neuron, boolean isOut) {
		// TODO Auto-generated method stub
		return null;
	}

}
