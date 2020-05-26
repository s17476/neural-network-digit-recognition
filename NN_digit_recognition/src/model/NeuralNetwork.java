package model;

import controler.BinaryStepUni;

public class NeuralNetwork {
	
	private String name;
	private Layer outputLayer;
	private Layer[] hiddenLayers;
	private int[] input = new int[16];
	
	
	public NeuralNetwork(String name, int hiddenLayersSize) {
		this.name = name;
		this.outputLayer = new Layer(new BinaryStepUni(),10, hiddenLayersSize);
		
		
		
		System.out.println(name);
	}
}
