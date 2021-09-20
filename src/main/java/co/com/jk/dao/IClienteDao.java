/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.dao;

import co.com.jk.entity.Cliente;
import co.com.jk.entity.Region;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IClienteDao extends JpaRepository<Cliente, Long>{
    
    @Query("from Region")//aqui se trabaja con el nombre de las clases, no de las tablas
    public List<Region> findAllRegiones();
}
