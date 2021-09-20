/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.controller;

import co.com.jk.entity.Factura;
import co.com.jk.entity.Producto;
import co.com.jk.service.ClienteService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CTA-PROGRAMADOR
 */
@RestController
@RequestMapping("/api")
public class FacturaRestController {
    
    private final Logger log = LoggerFactory.getLogger(FacturaRestController.class);
    
    @Autowired
    private ClienteService clienteService;
    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/facturas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Factura factura = null;
        Map<String, Object> response = new HashMap<>();

        try {
            factura = clienteService.FindFacturaById(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al realizar la consulta, id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (factura == null) {
            response.put("mensaje", "id: " + id + " no encontrado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(factura, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            clienteService.deleteFacturaById(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al eliminar la factura id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "factura id: " + id + " eliminado");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/facturas/filtrar-productos/{term}")
    public ResponseEntity<?> filtrarProductos(@PathVariable String term) {
        List<Producto> productosFiltrados = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            productosFiltrados = clienteService.filterProductoByNombre(term);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al filtrar por: " + term);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(productosFiltrados, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    public ResponseEntity<?> create(@Valid @RequestBody Factura factura, BindingResult result) {
        Factura nuevaFactura = null;
        Map<String, Object> response = new HashMap<>();
        
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.add("Factura El campo: " + error.getField() + " " + error.getDefaultMessage());
            }

            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            nuevaFactura = clienteService.saveFactura(factura);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al crear la factura");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "factura creada con exito");
        response.put("factura", nuevaFactura);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
}
