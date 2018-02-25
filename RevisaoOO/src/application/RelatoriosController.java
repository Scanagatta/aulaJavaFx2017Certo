package application;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.edu.unoesc.revisaoOO.modelo.ConexaoUtil;
import dao.AgenciaDao;
import dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatoriosController {

	private static AgenciaDao agenciaDao = DaoFactory.get().agenciaDao(); // ufDao
	// interface

	@FXML
	private Button btnRelatorio;

	@FXML
	private Button btnRelatorio1;

	@FXML
	void onRelatorioAgencia(ActionEvent event) {
		URL url = getClass().getResource("/RelatorioAgencia.jasper");

		try {

			Map<String, Object> parametros = new HashMap<>(); // passa
																// parametros
			parametros.put("nomeAgencia", "%sa%");

			JasperPrint print = JasperFillManager.fillReport(url.getPath(), null, ConexaoUtil.getCon());
			// JasperPrint print =
			// JasperFillManager.fillReport("c:/RelatorioUF.jasper", parametros,
			// ConexaoUtil.getCon());

			JasperViewer.viewReport(print);
			JasperExportManager.exportReportToPdfFile(print, "relatorioAgencia.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button btnRelatorio2;

	@FXML
	void onRelatorioAgenciaObjeto(ActionEvent event) {
		URL url = getClass().getResource("/RelatorioAgenciaObjeto.jasper");

		try {

			// aqui ele pega dos objetos e da classe AgenciaDao
			Map<String, Object> parametros = new HashMap<>(); // passa
																// parametros
			parametros.put("nomeAgencia", "%sa%");

			JRDataSource dataSource = new JRBeanCollectionDataSource(agenciaDao.listar());

			JasperPrint print = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			// JasperPrint print =
			// JasperFillManager.fillReport("c:/RelatorioUF.jasper", parametros,
			// ConexaoUtil.getCon());

			JasperViewer.viewReport(print);
			JasperExportManager.exportReportToPdfFile(print, "relatorioAgencia.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onReports(ActionEvent event) {

		URL url = getClass().getResource("/RelatorioUF.jasper");

		try {

			Map<String, Object> parametros = new HashMap<>(); // passa
																// paremetros
																// pra dentro do
																// relatorio
			parametros.put("nomeUf", "%sa%");
			JasperPrint print = JasperFillManager.fillReport(url.getPath(), parametros, ConexaoUtil.getCon());
			// JasperPrint print =
			// JasperFillManager.fillReport("c:/RelatorioUF.jasper", parametros,
			// ConexaoUtil.getCon());

			JasperViewer.viewReport(print);
			JasperExportManager.exportReportToPdfFile(print, "relatorio.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
