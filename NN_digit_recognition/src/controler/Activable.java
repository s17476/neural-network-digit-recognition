package controler;


import model.Neuron;

public interface Activable {
	public double activate(double in);
	public double update(Neuron neuron, boolean isOut);
}
