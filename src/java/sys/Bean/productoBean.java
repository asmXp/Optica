/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.Bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import sys.DAO.productoDao;
import sys.imp.productoDaoImp;
import sys.model.Producto;

/**
 *
 * @author dd
 */
@Named(value = "productoBean")
@SessionScoped
public class productoBean implements Serializable {

    /**
     * Creates a new instance of productoBean
     */
    private List<Producto> listaProductos;
    private Producto producto;
    
    public productoBean() {
        producto= new Producto();
    }
    
    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public List<Producto> getListaProductos() {
        productoDao pDao=new productoDaoImp();
        listaProductos = pDao.listarProducto();
        return listaProductos;
    }
    
    public void prepararNuevoProducto(){
	producto=new Producto();

}

    public void nuevoProducto(){
            productoDao vDao=new productoDaoImp();
            vDao.newProducto(producto);

    }

    public void modificarProducto(){
            productoDao vDao=new productoDaoImp();
            vDao.updateProducto(producto);
            producto=new Producto();

    }

    public void eliminarProducto(){
            productoDao vDao=new productoDaoImp();
            vDao.deleteProducto(producto);
           producto=new Producto();

    }
    
    
    
}
