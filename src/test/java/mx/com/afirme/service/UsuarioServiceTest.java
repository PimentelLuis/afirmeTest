/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.afirme.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import mx.com.afirme.model.Usuario;
import mx.com.afirme.repository.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author pimenteles1
 */
@SpringBootTest
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService userService;

    @Mock
    private UsuarioRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        usuario.setNombre("Luis Ernesto");
        usuario.setApellido("Pimentel Sarmiento");
        usuario.setCorreoElectronico("pime@gmail.com");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = "20-12-1989";
        
         try {
            Date date = sdf.parse(dateString);
            usuario.setFechaNacimiento(date);
        } catch (Exception e) {
            usuario.setFechaNacimiento(new Date());
        }
        

        when(userRepository.findById(userId)).thenReturn(Optional.of(usuario));

        Usuario result = userService.getUserById(userId);

        assertEquals(userId, result.getId());
        assertEquals("Luis Ernesto", result.getNombre());
        assertEquals("Pimentel Sarmiento", result.getApellido());
        assertEquals("pime@gmail.com", result.getCorreoElectronico());
    }
    
    @Test
    public void testCreateUser() {
        // Test para alta de usuario
        Usuario userToCreate = new Usuario();
        userToCreate.setNombre("Luis Ernesto");
        userToCreate.setApellido("Pimentel Sarmiento");
        userToCreate.setCorreoElectronico("pime@gmail.com");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = "20-12-1989";
        
         try {
            Date date = sdf.parse(dateString);
            userToCreate.setFechaNacimiento(date);
        } catch (Exception e) {
            userToCreate.setFechaNacimiento(new Date());
        }
         
        
        // Simula el usuario después de ser guardado en la base de datos
        Usuario userAfterCreate = new Usuario();
        userAfterCreate.setNombre("Luis Ernesto");
        userAfterCreate.setApellido("Pimentel Sarmiento");
        userAfterCreate.setCorreoElectronico("pime@gmail.com");
        
         try {
            Date date = sdf.parse(dateString);
            userAfterCreate.setFechaNacimiento(date);
        } catch (Exception e) {
            userAfterCreate.setFechaNacimiento(new Date());
        }
        
        // Simula el comportamiento del repositorio
        when(userRepository.save(userToCreate)).thenReturn(userAfterCreate);

        // Llama al método createUser del servicio
        Usuario createdUser = userService.createUser(userToCreate);

        // Verifica que se haya creado el usuario correctamente
        assertEquals(1L, createdUser.getId().longValue());
        assertEquals("Luis Ernesto", createdUser.getNombre());
        assertEquals("Pimentel Sarmiento", createdUser.getApellido());
        assertEquals("pime@gmail.com", createdUser.getCorreoElectronico());

    }
    
    @Test
    public void testGetAllUsersWithPagination() {
        // Simula una lista de usuarios
        List<Usuario> userList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Usuario user = new Usuario();
            user.setId((long) i);
            user.setNombre("Usuario" + i);
            user.setApellido("Apellido" + i);
            userList.add(user);
        }

        // Simula una página de usuarios utilizando PageImpl
        Page<Usuario> userPage = new PageImpl<>(userList);

        // Simula el comportamiento del repositorio para devolver la página
        when(userRepository.findAll((Pageable) Pageable.unpaged())).thenReturn(userPage);

        // Llama al método getAllUsers con paginación
        Pageable pageable = Pageable.unpaged(); // Página no paginada
        Page<Usuario> result = userService.getAllUsers(pageable);

        // Verifica que el número de usuarios en la página sea 10
        assertEquals(10, result.getTotalElements());
    }
}
