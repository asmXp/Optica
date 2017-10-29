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

import javax.inject.Named;
import sys.DAO.clienteDao;
import sys.imp.clienteDaoImp;
import sys.model.Cliente;

/**
 *
 * @author dd
 */

@Named(value = "clienteBean")
@SessionScoped
public class clienteBean implements Serializable {

    private List<Cliente> listaClientes;
    private Cliente cliente;
    
    public clienteBean() {
        
        cliente=new Cliente();
        
    }

    

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaClientes = listaCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List<Cliente> getListaCliente() {
        clienteDao cDao= new clienteDaoImp();
        listaClientes=cDao.listarClientes();
        return listaClientes;
    }
    
    
    public void prepararNuevoCliente(){
	cliente=new Cliente();

}

    public void nuevoCliente(){
            clienteDao cDao=new clienteDaoImp();
            cDao.newCliente(cliente);

    }

    public void modificarCliente(){
            clienteDao cDao= new clienteDaoImp();           
            cDao.updateCliente(cliente);
            cliente=new Cliente();

    }

    public void eliminarCliente(){
            clienteDao cDao= new clienteDaoImp();
            cDao.deleteCliente(cliente);
            cliente= new Cliente();

    }
    
    
}
