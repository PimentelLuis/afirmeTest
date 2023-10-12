/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.afirme.repository;

import mx.com.afirme.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pimenteles1
 */


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
