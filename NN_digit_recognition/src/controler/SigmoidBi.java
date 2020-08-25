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
	public double activate(double in) {
		var e = Math.E;
		double net = in;
		net = net*(-1);
		net = Math.pow(e, net);
		net = net+1.d;
		net = (2/net)-1;
		
		double fNet = net;
		//System.out.println("Sigma    wynik                           "+fNet);
		return fNet;
	}

	@Override
	public double update(Neuron neuron, boolean isOut) {
		double fPrim = (neuron.getfNet()*(1-(neuron.getfNet())));
		//neuron.setErrorFactor(fPrim.multiply(error));

		double[] tmpTab = new double[neuron.getWeights().length];
		double result = 0;
		for(int i = 0; i < neuron.getWeights().length; i++) {
				//System.out.println("stara waga" +neuron.getWeights()[i]);
				tmpTab[i] = neuron.getWeights()[i]+(neuron.getLearningRate()*(neuron.getErrorFactor()*(neuron.getInput()[i])));
		//		result.add(tmpTab[i].multiply(error));
				//System.out.println("Nowa waga" +neuron.getWeights()[i]);
			
		}
		neuron.setWeights(tmpTab);
		if(neuron.getBias() != 0) neuron.setBias(neuron.getBias()+(neuron.getLearningRate()*(neuron.getErrorFactor())));

		return result;
	}

}
