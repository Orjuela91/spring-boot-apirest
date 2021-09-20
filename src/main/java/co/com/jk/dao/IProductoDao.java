/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.dao;

import co.com.jk.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IProductoDao extends JpaRepository<Producto, Long>{
    
    @Query("select p from Producto p where lower(p.nombre) like lower(concat('%', ?1,'%'))")
    public List<Producto> filterByNombre(String term);
    
    public List<Producto> findByNombreContainingIgnoreCase(String term);
    
}
