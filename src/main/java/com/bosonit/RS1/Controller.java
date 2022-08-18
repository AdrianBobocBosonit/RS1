package com.bosonit.RS1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    User usuario;

    @Autowired
    List<User> listaUsuarios;

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        usuario = user;
        System.out.println("ESTE ES EL USUARIO RECIBIDO: " + user.toString());
        System.out.println("ESTE ES EL USUARIO QUE SE ENVIA: " + usuario.toString());
        return usuario;
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable int userId) {
        System.out.println("EL ID ENVIADO AQUI ES EL: " + userId);

        User usuarioSeleccionado;

        if (userId==1) {
            usuarioSeleccionado = listaUsuarios.get(0);
        } else if (userId==2) {
            usuarioSeleccionado = listaUsuarios.get(1);
        } else if (userId==3) {
            usuarioSeleccionado = listaUsuarios.get(2);
        } else {
            usuarioSeleccionado = new User(0, "UNKNOWN");
        }

        System.out.println("EL USUARIO SELECCIONADO PARA ENVIAR DE VUELTA ES EL: " + usuarioSeleccionado);
        return usuarioSeleccionado;
    }

    @Bean
    private List<User> generarListaUsuarios() {
        listaUsuarios = new ArrayList<>();

        listaUsuarios.add(new User(1,"Juan"));
        listaUsuarios.add(new User(2,"Pedro"));
        listaUsuarios.add(new User(3,"Paco"));

        return  listaUsuarios;
    }

    @PutMapping("/user")
    public List<User> putUser(@RequestParam int userId, @RequestParam String name) {
        System.out.println("ESTE ES EL USERID RECIBIDO: " + userId);
        System.out.println("ESTE ES EL NAME RECIBIDO: " + name);

        listaUsuarios.add(new User(userId, name));

        return listaUsuarios;
    }

    @DeleteMapping("/user/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        System.out.println("EL ID RECIBIDO PARA HACER DELETE ES EL: " + id);

        if (id==1) {
            listaUsuarios.remove(0);
        } else if (id == 2) {
            listaUsuarios.remove(1);
        } else if (id == 3) {
            listaUsuarios.remove(2);
        }

        return listaUsuarios;
    }
}
