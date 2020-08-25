package controler;

import java.math.BigDecimal;

import model.Layer;
import model.Neuron;

public interface Activable {
	public double activate(double in);
	public double update(Neuron neuron, boolean isOut);
}
