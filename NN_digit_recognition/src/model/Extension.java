package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Extension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4531868761067059799L;
	private List<NeuralNetwork> extension = new ArrayList<>();
	
	public void add(NeuralNetwork nw) {
		extension.add(nw);
	}
	
	public List<NeuralNetwork> getExtension(){
		return extension;
	}
	
	public void remove(int index) {
		extension.remove(index);
	}
	
	public String toString() {
		return extension.toString();
	}
	
	public int getSize() {
		return extension.size();
	}
	
	public void writeExtents(ObjectOutputStream stream) throws IOException {
		stream.writeObject(extension);
	}
	
	@SuppressWarnings("unchecked")
	public void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		extension = (List<NeuralNetwork>) stream.readObject();
	}
}
