/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.dao;

import co.com.jk.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
    
    public Usuario findByUsername(String username);
    
}
