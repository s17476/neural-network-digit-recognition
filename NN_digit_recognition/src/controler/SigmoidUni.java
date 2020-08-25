package controler;

import java.io.Serializable;
import java.math.BigDecimal;

import model.Layer;
import model.Neuron;

public class SigmoidUni implements Activable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188617299515195301L;
	String name = "Sigmoid Step Function - Unipolar";
	 
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
		//net = net*(-1);
		
		
		
		//System.out.println("NET= "+net);
		//System.out.println("E= "+Math.E);
		
		
		net = Math.pow(e, net);
		
		//System.out.println("NET po potędze = "+net + "    " + 1.d/net);
		
		net = 1.d/net;
		
		//System.out.println("NET po potędze i minusie= "+net);
		
		net+=1;
		
		//System.out.println("NET plus 1= "+net);
		
		
		net = (1.d/net);
		double fNet = net;
		//System.out.println("Sigma  wynik   "+fNet);
		return fNet;
	}

	@Override
	public double update(Neuron neuron, boolean isOut) {
		double fPrim = (neuron.getfNet()*(1-(neuron.getfNet())));
		//neuron.setErrorFactor(fPrim.multiply(error));
		//neuron.setErrorFactor(error.multiply(fPrim).multiply(weight));////////////////////////////////////////////////////////////////////
		//System.out.println("Współczynnik prim: " + neuron.getfNet().multiply(new BigDecimal(1).subtract(neuron.getfNet())));
		//System.out.println("Błąd neuronu: " + neuron.getErrorFactor());
		//System.out.println("Stara waga" + neuron.getWeights()[0]);
//////////////////////////////////////////////////////////////////////////////////nowe wagi
		double[] tmpTab = new double[neuron.getWeights().length];
		double result = 0;
		for(int i = 0; i < neuron.getWeights().length; i++) {

			//	tmpTab[i] = neuron.getWeights()[i].subtract(neuron.getLearningRate().multiply(fPrim.multiply(error).multiply(neuron.getInput()[i])));
			//	result.add(tmpTab[i].multiply(error));
				
			
		}
		neuron.setWeights(tmpTab);
		//if(neuron.getBias() != null) neuron.setBias(neuron.getBias().add(neuron.getLearningRate().multiply(neuron.getErrorFactor())));
		//System.out.println("nowa waga" + neuron.getWeights()[0]);
	//	if(neuron.getBias() != null) neuron.setBias(neuron.getBias().subtract(neuron.getLearningRate().multiply(error)));
		return result;
	}

}
