package br.com.trab3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
public interface Comando {

    void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}
