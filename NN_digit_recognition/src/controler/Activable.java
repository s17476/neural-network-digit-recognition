package controler;

import java.math.BigDecimal;

import model.Layer;
import model.Neuron;

public interface Activable {
	public BigDecimal activate(BigDecimal in);
	public BigDecimal update(Neuron neuron, boolean isOut);
}
