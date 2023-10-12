/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.afirme.service;

import mx.com.afirme.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author pimenteles1
 */
public interface UsuarioService {
    
    Usuario getUserById(Long id);

    public Page<Usuario> getAllUsers(Pageable pageable);

    Usuario createUser(Usuario user);

    Usuario updateUser(Long id, Usuario user);

    void deleteUser(Long id);
    
}
