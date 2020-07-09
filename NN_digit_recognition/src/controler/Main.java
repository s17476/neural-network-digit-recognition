/**
 * 
 */
package controler;

import java.awt.EventQueue;
import java.math.BigDecimal;

import model.Extension;
import view.MainWindow;

/**
 * @author Grzegorz Frączek
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
