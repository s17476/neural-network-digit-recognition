package model;

import controler.Activable;

public class Layer {
	Activable function;
	Neuron[] neurons;
	
	Layer(Activable function, int neutronsSize, int hiddenLayersSize){
		this.function = function;
		this.neurons = Neuron.createNeurons(neutronsSize, hiddenLayersSize);
	}
}
