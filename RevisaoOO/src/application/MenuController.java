package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuController {

    @FXML
    private BorderPane bpPrincipal;

    @FXML
    private MenuItem miAgencia;

    @FXML
    private MenuItem miCliente;
    
    @FXML
    private MenuItem miConta;
    
    @FXML
    private MenuItem miMovimentos;
    
    @FXML
    private MenuItem miRelatorio;


    @FXML
    void onRelatorio(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("relatorios.fxml"));
    	try{
    		AnchorPane agenciaView = (AnchorPane) loader.load();
    		bpPrincipal.setCenter(agenciaView);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}

    	
    }
    

   
    
    @FXML
    void onAgencia(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("agencia.fxml"));
    	try{
    		AnchorPane agenciaView = (AnchorPane) loader.load();
    		bpPrincipal.setCenter(agenciaView);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}

    }

    @FXML
    void onCliente(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("clienteForm.fxml"));
    	
    	try{
    		AnchorPane agenciaView = (AnchorPane) loader.load();
    		bpPrincipal.setCenter(agenciaView);
    		
        	
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    
    @FXML
    void onConta(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("conta.fxml"));
    	try{
    		AnchorPane agenciaView = (AnchorPane) loader.load();
    		bpPrincipal.setCenter(agenciaView);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    
    @FXML
    void onMovimentos(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("movimentos.fxml"));
    	try{
    		AnchorPane agenciaView = (AnchorPane) loader.load();
    		bpPrincipal.setCenter(agenciaView);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    

}
