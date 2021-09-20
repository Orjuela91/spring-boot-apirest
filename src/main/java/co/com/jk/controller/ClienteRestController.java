/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.controller;

import co.com.jk.entity.Cliente;
import co.com.jk.entity.Region;
import co.com.jk.models.service.IUploadFileService;
import co.com.jk.service.ClienteService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CTA-PROGRAMADOR
 */
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return clienteService.findAll(pageable);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al realizar la consulta, id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "id: " + id + " no encontrado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente nuevoCliente = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.add("El campo: " + error.getField() + " " + error.getDefaultMessage());
            }

            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (cliente.empty()) {
            response.put("errors", "Campos vacios");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            nuevoCliente = clienteService.save(cliente);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al crear el nuevo cliente");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "cliente creado con exito");
        response.put("cliente", nuevoCliente);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente clienteParam, BindingResult result, @PathVariable long id)// siempre respetar el orden del @valid y Bindingresult
    {
        Cliente clienteActual = null;
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.add("El campo: '" + error.getField() + "' " + error.getDefaultMessage());
            }

            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteActual = clienteService.findById(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al realizar la consulta, id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (clienteActual == null) {
            response.put("mensaje", "Cliente id: " + id + " no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        clienteActual.setNombre(clienteParam.getNombre());
        clienteActual.setApellido(clienteParam.getApellido());
        clienteActual.setEmail(clienteParam.getEmail());
        clienteActual.setCreateAt(clienteParam.getCreateAt());
        clienteActual.setRegion(clienteParam.getRegion());

        try {
            clienteActualizado = clienteService.save(clienteActual);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al actualizar el nuevo cliente");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "cliente actualizado con exito");
        response.put("cliente", clienteActualizado);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Cliente cliente = clienteService.findById(id);
            String fotoAnterior = cliente.getFoto();

            if (fotoAnterior != null && !fotoAnterior.isEmpty()) {  
                uploadFileService.eliminar(fotoAnterior);
            }
            clienteService.delete(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al eliminar el cliente id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "cliente id: " + id + " eliminado");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            response.put("mensaje", "error al realizar la consulta, id: " + id);
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "id: " + id + " no encontrado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(archivo);
            } catch (IOException ex) {
                log.error(ex.getMessage());
                response.put("mensaje", "error al subir iamgen");
                response.put("error", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String fotoAnterior = cliente.getFoto();

            uploadFileService.eliminar(fotoAnterior);

            cliente.setFoto(nombreArchivo);

            clienteService.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "imagen subida: " + nombreArchivo);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("error", "archivo vacio");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("clientes/uploads/img/{nombreFoto:.+}")//nombre archivo con extension
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
       
        Resource recurso = null;

        try {
            recurso = uploadFileService.cargar(nombreFoto);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(ClienteRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("clientes/regiones")
    public  List<Region> listarRegiones(){
        
        return clienteService.findAllRegiones();
    }
}
