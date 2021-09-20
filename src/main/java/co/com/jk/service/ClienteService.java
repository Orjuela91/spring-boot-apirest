/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.service;

import co.com.jk.dao.IClienteDao;
import co.com.jk.dao.IFacturaDao;
import co.com.jk.dao.IProductoDao;
import co.com.jk.entity.Cliente;
import co.com.jk.entity.Factura;
import co.com.jk.entity.Producto;
import co.com.jk.entity.Region;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CTA-PROGRAMADOR
 */
@Service
public class ClienteService {
    
    @Autowired
    private IClienteDao clienteDao;
    
    @Autowired
    private IFacturaDao facturaDao;
    
    @Autowired
    private IProductoDao productoDao;
    
    @Transactional(readOnly = true)
    public List<Cliente> findAll(){
        return clienteDao.findAll();
    }
    
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable){
        return clienteDao.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public Cliente findById(Long id){
        return clienteDao.findById(id).orElse(null);
    }
    
    @Transactional
    public Cliente save(Cliente cliente){
        return clienteDao.save(cliente);
    }
    
    @Transactional
    public void delete(Long id){
        clienteDao.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones(){
        return clienteDao.findAllRegiones();
    }
    
    @Transactional(readOnly = true)
    public Factura FindFacturaById(Long id){
        return facturaDao.findById(id).orElse(null);
    }
    
    @Transactional
    public Factura saveFactura(Factura factura){
        return facturaDao.save(factura);
    }
    
    @Transactional
    public void deleteFacturaById(Long id){
        facturaDao.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Producto> filterProductoByNombre(String term){
        return productoDao.filterByNombre(term);
    }
}
