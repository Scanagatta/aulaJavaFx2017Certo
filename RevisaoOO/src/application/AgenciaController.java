package application;

import java.util.Optional;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import dao.AgenciaDao;
import dao.DaoFactory;
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

	private static AgenciaDao agenciaDao = DaoFactory.get().agenciaDao(); // ufDao
																			// interface

	@FXML
	public void initialize() {
		tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		// tblAgencia.setItems(FXCollections.observableArrayList(SimuladorBD.getAgencias()));

		tblAgencia.setItems(FXCollections.observableArrayList(agenciaDao.listar()));
		// mostra a lsita do banco de dados

		novo();
	}


	// a��o do botao salvar, vai adicionar os nomes na lista
	@FXML
	void onSalvar(ActionEvent event) {

		// para dar new precisa do construtor vazio no cliente
		agencia.setNome(tfNome.getText());
		agencia.setNumero(tfNumero.getText());

		if (editando) {
			// quando fizer isso esse metodo vai ser executado na Agencias e
			// atualiza o arquivo
			agenciaDao.alterar(agencia);
			// SimuladorBD.atualizarAgencias();
			tblAgencia.refresh(); // atualiza
		} else {
			agenciaDao.inserir(agencia);
			tblAgencia.getItems().add(agencia); // adiciona na lista
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
	// objeto cliente j� populado

	void onEditar(MouseEvent mouseEvent) {
		if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED))
			;

		agencia = tblAgencia.getSelectionModel().getSelectedItem(); // carregou
																	// pra
																	// variavel
																	// agencia
		tfNome.setText(agencia.getNome());
		tfNumero.setText(agencia.getNumero());

		editando = true;
	}

	@FXML
	void onExcluir(ActionEvent MouseEvent) {

		Alert alerta = new Alert(AlertType.CONFIRMATION, "Deseja relamente excluir?", ButtonType.CANCEL, ButtonType.OK);

		// Desativando o comportamento padrao n�o � obrigatorio
		Button okButton = (Button) alerta.getDialogPane().lookupButton(ButtonType.OK);
		okButton.setDefaultButton(false);

		// optional do java 8 executa o show e fica aguardando o click do botao
		final Optional<ButtonType> result = alerta.showAndWait();
		// se o click foi no ok executa os comandos abaixo
		if (result.get() == ButtonType.OK) {

			tblAgencia.getItems().remove(agencia);
			// SimuladorBD.remover(agencia);
			agenciaDao.excluir(agencia.getCodigo());

			limparCampos();
			novo();
		}
	}

}
