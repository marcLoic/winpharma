package controller;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FmxlLoader {

	private Pane view;
	
	public Pane getPage(String filename) {
		
		try {
			URL fileUrl = OpenAcceuil.class.getResource("/views/" + filename + ".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file can't be found");
			}
			
			view = FXMLLoader.load(fileUrl);
			
		} catch (Exception e) {
			System.out.println("No page " + filename + " please check FxmlLoader");
		}
		System.out.println("/views/" + filename + ".fxml");
		return view;
	}
}
