/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.afirme.service.impl;

import mx.com.afirme.model.Usuario;
import mx.com.afirme.repository.UsuarioRepository;
import mx.com.afirme.service.UsuarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author pimenteles1
 */

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public Usuario getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Usuario> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    @Override
    public Usuario updateUser(Long id, Usuario user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null; 
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
