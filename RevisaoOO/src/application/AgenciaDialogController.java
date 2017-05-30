package application;

import java.util.Optional;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.SimuladorBD;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AgenciaDialogController {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfNumero;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	private Agencia agencia = new Agencia();

	private Stage stageDialog;
	
	private boolean salvarClicked = false;

	@FXML
	public void initialize() {

	}

	@FXML
	void onSalvar(ActionEvent event) {

		// para dar new precisa do construtor vazio no cliente
		agencia.setNome(tfNome.getText());
		agencia.setNumero(tfNumero.getText());

		SimuladorBD.insert(agencia);
		salvarClicked = true;
		stageDialog.close();

	}

	@FXML
	void onCancelar(ActionEvent event) {
		salvarClicked = false;
		stageDialog.close();
	}

	public boolean isSalvarClicked() {
		return salvarClicked;
	}
	
	public void setStageDialog(Stage stage){
		this.stageDialog= stage;
	}
}