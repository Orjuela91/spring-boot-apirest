/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IUploadFileService {
    
    public Resource cargar(String nombreArchivo) throws MalformedURLException;
    public String copiar(MultipartFile archivo) throws IOException;
    public boolean eliminar(String nombreArchivo);
    public Path getPath(String nombreArchivo);
}
