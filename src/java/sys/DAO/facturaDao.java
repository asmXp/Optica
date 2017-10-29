/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.DAO;

import org.hibernate.Session;
import sys.model.Factura;

/**
 *
 * @author dd
 */
public interface facturaDao {
    //Obtener el ultimo reguistro en la tabla factura en la base de datos
    public Factura obtenerUltimoRegistro(Session session)throws Exception;
    //Averiguar si la tabla factura posee registros
    public Long obtenerTotalRegistrosEnFactura(Session session);
    
    //Metodo para guardar el registro en la tabla factura de la db
    public boolean guardarVentaFactura(Session session, Factura factura)throws Exception;
    
}
