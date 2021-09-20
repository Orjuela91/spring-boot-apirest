/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.auth;

import co.com.jk.entity.Usuario;
import co.com.jk.service.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 *
 * @author CTA-PROGRAMADOR
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer{
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accesToken, OAuth2Authentication authentication) {
        
        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        
        Map<String, Object> info = new HashMap<>();
        
        info.put("nombre", usuario.getNombre());
        info.put("apellido", usuario.getApellido());
        info.put("email", usuario.getEmail());
        info.put("habilitado", usuario.getEnabled());
        
        ((DefaultOAuth2AccessToken) accesToken).setAdditionalInformation(info);
        
        return accesToken;
    }
    
}
