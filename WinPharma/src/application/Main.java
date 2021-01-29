package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			stage.centerOnScreen();
			stage.initStyle(StageStyle.UNDECORATED);
			
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setX(300);
			stage.setY(180);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
