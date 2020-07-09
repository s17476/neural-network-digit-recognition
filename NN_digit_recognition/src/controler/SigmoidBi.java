package controler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import model.Layer;
import model.Neuron;

public class SigmoidBi implements Activable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2481722739886236758L;
	String name = "Sigmoid Step Function - Bipolar";
	 
	 @Override
	 public String toString() {
		 return name;
	 }
	 
	 /**
	  * z ałożenia lambda = 1
	  */
	@Override
	public BigDecimal activate(BigDecimal in) {
		var e = Math.E;
		double net = Double.valueOf(in.toString());
		net = net*(-1);
		net = Math.pow(e, net);
		net = net+1.d;
		net = (2/net)-1;
		
		BigDecimal fNet = new BigDecimal(net);
		//System.out.println("Sigma    wynik                           "+fNet);
		return fNet;
	}

	@Override
	public BigDecimal update(Neuron neuron, boolean isOut) {
		BigDecimal fPrim = (neuron.getfNet().multiply(new BigDecimal(1).subtract(neuron.getfNet())));
		//neuron.setErrorFactor(fPrim.multiply(error));

		BigDecimal[] tmpTab = new BigDecimal[neuron.getWeights().length];
		BigDecimal result = new BigDecimal(0);
		for(int i = 0; i < neuron.getWeights().length; i++) {
				//System.out.println("stara waga" +neuron.getWeights()[i]);
				tmpTab[i] = neuron.getWeights()[i].add(neuron.getLearningRate().multiply(neuron.getErrorFactor().multiply(neuron.getInput()[i])));
		//		result.add(tmpTab[i].multiply(error));
				//System.out.println("Nowa waga" +neuron.getWeights()[i]);
			
		}
		neuron.setWeights(tmpTab);
		if(neuron.getBias() != null) neuron.setBias(neuron.getBias().add(neuron.getLearningRate().multiply(neuron.getErrorFactor())));

		return result;
	}

}
