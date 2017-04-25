package application;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.Cliente;
import br.edu.unoesc.revisaoOO.modelo.Conta;
import br.edu.unoesc.revisaoOO.modelo.SimuladorBD;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ContaController {


	    @FXML
	    private Button btnSalvar;

	    @FXML
	    private Button btnNovo;

	    @FXML
	    private TableView<Conta> tblConta;

	    @FXML
	    private TableColumn<Conta, Number> tbcNumero;

	    @FXML
	    private TableColumn<Conta, Cliente> tbcCliente;

	    @FXML
	    private Button btnExcluir;

	    @FXML
	    private TextField tfNumero;
	    

	    @FXML
	    private ComboBox<Cliente> cbxCliente;

	    @FXML
	    private ComboBox<Agencia> cbxAgencia;
	    
		private Conta conta;

		private boolean editando;

		@FXML
		public void initialize() {
			tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
			tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
			
			cbxAgencia.setItems(FXCollections.observableArrayList(SimuladorBD.getAgencias()));
			cbxCliente.setItems(FXCollections.observableArrayList(SimuladorBD.getClientes()));
			tblConta.setItems(FXCollections.observableArrayList(SimuladorBD.getContas()));
			novo();
		}
		
		private void novo() {
			editando = false;
			conta = new Conta();
			limparCampos();
		}
		
	    @FXML
	    void onEditar(MouseEvent event) {
			if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED));
			
			conta = tblConta.getSelectionModel().getSelectedItem(); //carregou pra variavel agencia
			
			tfNumero.setText(conta.getNumero());
			
	    	cbxAgencia.setValue(conta.getAgenciaPreferencial());
	    	cbxCliente.setValue(conta.getClientePreferencial());
	    	
			editando = true;
	    }

	    @FXML
	    void onExcluir(ActionEvent event) {

			
			tblConta.getItems().remove(conta);
			SimuladorBD.remover(conta);
			limparCampos();
	    }


	    @FXML
	    void onSalvar(ActionEvent event) {
			

	    	
			conta.setNumero(tfNumero.getText());
			
			////
			conta.setCliente(cbxCliente.getValue());
			////
			
			conta.setAgenciaPreferencial(cbxAgencia.getValue());
			conta.setClientePreferencial(cbxCliente.getValue());

			if (editando) {
				tblConta.refresh(); //atualiza
			} else {
				SimuladorBD.insert(conta);
				tblConta.getItems().add(conta); //adiciona na lista
			}
			novo();
			

	    }
	    
		@FXML
		void onNovo(ActionEvent event) {
			novo();
		}

		private void limparCampos() {
			tfNumero.setText("");
			cbxAgencia.setValue(null);
			cbxCliente.setValue(null);

		}

	
	}

