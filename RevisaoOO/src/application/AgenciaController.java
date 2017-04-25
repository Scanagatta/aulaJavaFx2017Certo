package application;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.SimuladorBD;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AgenciaController {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfNumero;

	@FXML
	private Button btnSalvar;

	@FXML
	private TableView<Agencia> tblAgencia;
	
	@FXML
	// lista tabela
	private TableColumn<Agencia, Number> tbcNumero;
	
	@FXML
	private TableColumn<Agencia, String> tbcNome;

	//
	@FXML
	private Button btnNovo;

	@FXML
	private Button btnExcluir;

	private Agencia agencia;

	private boolean editando;

	@FXML
	public void initialize() {
		tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
				
		tblAgencia.setItems(FXCollections.observableArrayList(SimuladorBD.getAgencias()));
		novo();
	}

	// ação do botao salvar, vai adicionar os nomes na lista
	@FXML
	void onSalvar(ActionEvent event) {


		// para dar new precisa do construtor vazio no cliente
		agencia.setNome(tfNome.getText());
		agencia.setNumero(tfNumero.getText());

		if (editando) {
			tblAgencia.refresh(); //atualiza
		} else {
			SimuladorBD.insert(agencia);
			tblAgencia.getItems().add(agencia); //adiciona na lista
		}
		novo();
		
	}

	private void novo() {
		editando = false;
		agencia = new Agencia();
		limparCampos();
	}

	@FXML
	void onNovo(ActionEvent event) {
		novo();
	}

	private void limparCampos() {
		tfNome.setText("");
		tfNumero.setText("");

	}

	@FXML
	// intercepta o clipe do mouse e popula os nomes da tela
	// lista de cliente
	// objeto cliente já populado
	
	void onEditar(MouseEvent mouseEvent) {
		if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED));
		
		agencia = tblAgencia.getSelectionModel().getSelectedItem(); //carregou pra variavel agencia
		tfNome.setText(agencia.getNome());
		tfNumero.setText(agencia.getNumero());

		editando = true;
	}

	@FXML
	void onExcluir(ActionEvent MouseEvent) {
		
		tblAgencia.getItems().remove(agencia);
		SimuladorBD.remover(agencia);
		limparCampos();
	}

}
