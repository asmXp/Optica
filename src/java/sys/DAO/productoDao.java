/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.DAO;

import java.util.List;
import org.hibernate.Session;
import sys.model.Producto;

/**
 *
 * @author dd
 */
public interface productoDao {
    
    public List<Producto> listarProducto();
    public void newProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
    
    //Metodo utilizado en la factura facturaBean
    public Producto obtenerProductoPorCodBarra(Session session, String codBarra) throws Exception;
}
