package application;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.Cliente;
import br.edu.unoesc.revisaoOO.modelo.SimuladorBD;
import javafx.collections.FXCollections;

//DA OS ID NO SCECEBUILDER
//VAI EM VIEW show sample, copia e cola aqui
//CONTROL + SHIFT+ O = tudo do JAVA FX

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClienteController {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

    @FXML
    private DatePicker dtDataNascimento;

    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnNovo;
    
    //lista que aparece na tela do scene
    @FXML 
    private ListView<Cliente> lvCliente;
    
    
    private Cliente cliente;
    
    private boolean editando;
    
    @FXML
    private ComboBox<Agencia> cbxAgencia;
    
    
    
    //sempre tem que dar initialize
    @FXML 
    public void initialize() {
    	lvCliente.setItems(FXCollections.observableArrayList(SimuladorBD.getClientes()));
    	cbxAgencia.setItems(FXCollections.observableArrayList(SimuladorBD.getAgencias()));
    	novo();
    }
    
    //ação do botao salvar, vai adicionar os nomes na lista
    @FXML
    void onSalvar(ActionEvent event) {
 
    	
    	//para dar new precisa do construtor vazio no cliente
    	cliente.setNome(tfNome.getText());
    	cliente.setCpf(tfCPF.getText());
    	cliente.setDataNascimento(dtDataNascimento.getValue());
    	//pega do combobox e guarda no objeto agencia
    	cliente.setAgenciaPreferencial(cbxAgencia.getValue());
    	if(editando){
    		lvCliente.refresh();
    	} else {
    		SimuladorBD.insert(cliente);
    		lvCliente.getItems().add(cliente);
    	}
    	novo();
    	// toString la no cliente
    }

    
    private void novo(){
    	editando=false;
    	cliente = new Cliente();
    	limparCampos(); 	
    }
    
    @FXML
    void onNovo(ActionEvent event) {
    	novo();
    }
    
    
    private void limparCampos(){
    	tfNome.setText("");
    	tfCPF.setText("");
    	dtDataNascimento.setValue(null);
    	cbxAgencia.setValue(null);
    	
    }
    @FXML
    //intercepta o clipe do mouse e popula os nomes da tela
    //lista de cliente
    //objeto cliente já populado
    void onEditar(MouseEvent mouseEvent) {
    	if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED));
    	
    	cliente = lvCliente.getSelectionModel().getSelectedItem();
    	tfNome.setText(cliente.getNome());
    	tfCPF.setText(cliente.getCpf());
    	dtDataNascimento.setValue(cliente.getDataNascimento());
    	// pega do objeto e coloca no combobox
    	cbxAgencia.setValue(cliente.getAgenciaPreferencial());
    	editando=true;
    }
    
	@FXML
	void onExcluir(ActionEvent MouseEvent) {
		
		lvCliente.getItems().remove(cliente);
		SimuladorBD.remover(cliente);
		limparCampos();
	}
}



