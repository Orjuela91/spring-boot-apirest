/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.dao;

import co.com.jk.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IFacturaDao extends JpaRepository<Factura, Long>{
    
}
