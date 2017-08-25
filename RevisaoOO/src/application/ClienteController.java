package application;

import br.edu.unoesc.componente.ListCellBean;
import br.edu.unoesc.componente.StringConverterBean;
import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.Cliente;
import dao.AgenciaDao;
import dao.ClienteDao;
import dao.DaoFactory;
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
import javafx.stage.Stage;

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
    
    @FXML
    private Button btnNovaAgencia;
    

    private ClienteDao clienteDao = DaoFactory.get().clienteDao();
    private AgenciaDao agenciaDao = DaoFactory.get().agenciaDao();
    
    
    //sempre tem que dar initialize
    @FXML 
    public void initialize() {
    	lvCliente.setItems(FXCollections.observableArrayList(clienteDao.listar()));
    	
    	cbxAgencia.setCellFactory((comboBox)-> {return new ListCellBean<Agencia>();});
    	
    	cbxAgencia.setConverter(new StringConverterBean<>());
    	
    	cbxAgencia.setItems(FXCollections.observableArrayList(agenciaDao.listar()));
    	novo();
    }
    
    @FXML
    public void onNovaAgencia (ActionEvent event){
    	
    	Stage stageDono = (Stage)btnNovaAgencia.getScene().getWindow();
    	AgenciaDialogFabrica agenciaDialogFabrica = 
    			new AgenciaDialogFabrica(stageDono);
    	
    	boolean salvarClicked = agenciaDialogFabrica.showAgenciaDialogEditDialog();
    	
    	if(salvarClicked){
    		cbxAgencia.getItems().clear();
    		cbxAgencia.getItems().addAll(agenciaDao.listar());
    	}
    }
    
    //a��o do botao salvar, vai adicionar os nomes na lista
    @FXML
    void onSalvar(ActionEvent event) {
 
    	
    	//para dar new precisa do construtor vazio no cliente
    	cliente.setNome(tfNome.getText());
    	cliente.setCpf(tfCPF.getText());
    	cliente.setDataNascimento(dtDataNascimento.getValue());
    	//pega do combobox e guarda no objeto agencia
    	cliente.setAgenciaPreferencial(cbxAgencia.getValue());
    	
    	if(editando){
    		clienteDao.alterar(cliente);
    		lvCliente.refresh();
    	} else {
    		clienteDao.inserir(cliente);
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
    //objeto cliente j� populado
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
		clienteDao.excluir(cliente.getCodigo());
		limparCampos();
	}
}



