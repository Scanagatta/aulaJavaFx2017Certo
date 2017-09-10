package application;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import dao.AgenciaDao;
import dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

	private static AgenciaDao agenciaDao = DaoFactory.get().agenciaDao();
	
	@FXML
	public void initialize() {

	}

	@FXML
	void onSalvar(ActionEvent event) {

		// para dar new precisa do construtor vazio no cliente
		agencia.setNome(tfNome.getText());
		agencia.setNumero(tfNumero.getText());

		agenciaDao.inserir(agencia);
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
