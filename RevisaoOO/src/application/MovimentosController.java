package application;

import javax.swing.JLabel;

import br.edu.unoesc.revisaoOO.modelo.Conta;
import br.edu.unoesc.revisaoOO.modelo.Movimento;
import br.edu.unoesc.revisaoOO.modelo.SimuladorBD;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MovimentosController {

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnNovo;

	@FXML
	private TableView<Movimento> tblMovimentos;

	@FXML
	private TableColumn<Conta, Conta> tbcConta;

	@FXML
	private TableColumn<Movimento, String> tbcTipo;

	@FXML
	private TableColumn<Movimento, String> tbcValor;

	@FXML
	private TextField tfValor;

	@FXML
	private ComboBox<Conta> cbxConta;

	@FXML
	private TextField tfSaldo;

	@FXML
	private JLabel jlbSaldo;

	@FXML
	private RadioButton rdSaque;

	@FXML
	private RadioButton rdDeposito;

	private Movimento movimento;

	private boolean editando;

	private boolean sacando;

	private boolean depositando;

	@FXML
	void OnSaque(ActionEvent event) {

		if (rdSaque.isSelected()) {
			rdDeposito.setSelected(false);
		}

		movimento.setTipo("Saque");
		sacando = true;

	}

	@FXML
	void onDeposito(ActionEvent event) {

		if (rdDeposito.isSelected()) {
			rdSaque.setSelected(false);
		}

		movimento.setTipo("Deposito");
		depositando = true;

	}

	@FXML
	public void initialize() {
		tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tbcConta.setCellValueFactory(new PropertyValueFactory<>("conta"));

		cbxConta.setItems(FXCollections.observableArrayList(SimuladorBD.getContas()));

		tblMovimentos.setItems(FXCollections.observableArrayList(SimuladorBD.getMovimentos()));
		novo();

	}

	private void novo() {
		editando = false;
		sacando = false;
		depositando = false;
		movimento = new Movimento();
		limparCampos();
	}

	private void limparBotoes() {
		rdSaque.setSelected(false);
		rdDeposito.setSelected(false);

	}

	@FXML
	void onEditar(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED));

		movimento = tblMovimentos.getSelectionModel().getSelectedItem(); // carregou
																			// pra
																			// variavel
																			// agencia

		tfValor.setText(Double.toString(movimento.getValor()));

		cbxConta.setValue(movimento.getConta());

		tfSaldo.setText(Double.toString(movimento.getConta().getSaldo()));

		editando = true;
		limparBotoes();
	}

	@FXML
	void onSalvar(ActionEvent event) {

		if (editando == true) {
			novo();

		} else {
			limparBotoes();

			movimento.setConta(cbxConta.getValue());

			if (depositando == true) {
				// conta.depositar(Double.parseDouble(tfValor.getText()));
				movimento.getConta().depositar(Double.parseDouble(tfValor.getText()));
			} else {
				// sacando = conta.sacar(Double.parseDouble(tfValor.getText()));
				sacando = movimento.getConta().sacar(Double.parseDouble(tfValor.getText()));
			}

			movimento.setValor(Double.parseDouble(tfValor.getText()));

			if (depositando || sacando) {

				if (editando) {
					SimuladorBD.atualizarMovimentos();
					tblMovimentos.refresh(); // atualiza

				} else {
					SimuladorBD.insert(movimento);
					tblMovimentos.getItems().add(movimento); // adiciona na
																// lista
				}
			}

			novo();
		}
	}

	@FXML
	void onNovo(ActionEvent event) {
		novo();
	}

	private void limparCampos() {
		tfValor.setText("");
		tfSaldo.setText("");
		limparBotoes();
		cbxConta.setValue(null);

	}

}
