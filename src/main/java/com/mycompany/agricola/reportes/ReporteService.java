package com.mycompany.agricola.reportes;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.mycompany.agricola.model.conexion.ConexionDB;

public class ReporteService {

    public byte[] generarFacturaVenta(String noFactura) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("noFactura", noFactura);
        return generarPdf("/reportes/FacturaVenta.jrxml", params);
    }

    public byte[] generarReporteInsumosCriticos() throws Exception {
        return generarPdf("/reportes/ReporteInsumosCriticos.jrxml", new HashMap<>());
    }

    private byte[] generarPdf(String rutaJrxml, Map<String, Object> params) throws Exception {
        try (InputStream in = getClass().getResourceAsStream(rutaJrxml);
                Connection conn = ConexionDB.getConexion()) {
            if (in == null) {
                throw new IllegalStateException("No se encontro el reporte: " + rutaJrxml);
            }
            JasperReport report = JasperCompileManager.compileReport(in);
            JasperPrint print = JasperFillManager.fillReport(report, params, conn);
            return JasperExportManager.exportReportToPdf(print);
        } finally {
            ConexionDB.cerrarConexion();
        }
    }
}
