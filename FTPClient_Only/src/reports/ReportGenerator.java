package reports;

import db.DBConnection;
import java.io.InputStream;
import java.sql.Connection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer; 

/**
 *
 * @author manjeet
 */
public class ReportGenerator {
    public ReportGenerator(String fileName) {
        InputStream is = getClass().getResourceAsStream(fileName);
        try {
            Connection con = DBConnection.connect();
            JasperPrint jasperPrint = JasperFillManager.fillReport(is, null, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println("Exception in ReportGenerator : " + e);
        }
    }
}
