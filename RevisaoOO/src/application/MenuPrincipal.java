package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {


	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			this.primaryStage = primaryStage;

			
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("menuPrincipal.fxml"));
	        BorderPane root = (BorderPane)loader.load();
	        MenuController menuController = loader.getController();
	        menuController.setMain(this);
	        
	        
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar
	 * OK, as mudanças são salvasno objeto pessoa fornecido e retorna true.
	 * 
	 * @return true Se o usuário clicou OK,  caso contrário false.
	 */
	public boolean showAgenciaDialogEditDialog() {
	    try {
	        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("agenciaDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Cria o palco dialogStage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Nova Agencia");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Define a pessoa no controller.
	        AgenciaDialogController controller = loader.getController();
	        controller.setStageDialog(dialogStage);

	        // Mostra a janela e espera até o usuário fechar.
	        dialogStage.showAndWait();

	        return controller.isSalvarClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	

}
