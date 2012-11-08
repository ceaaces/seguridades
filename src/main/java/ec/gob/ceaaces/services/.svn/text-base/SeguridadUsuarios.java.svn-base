package ec.gob.ceaaces.services;

import java.util.List;

import ec.gob.ceaaces.model.dtos.seguridades.UsuarioDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;

public interface SeguridadUsuarios {

    Boolean registrarUsuario(UsuarioDTO usuarioDTO) throws ServicioException;

    UsuarioDTO obtenerUsuario(String username) throws ServicioException;

    List<UsuarioDTO> obtenerListaUsuarios() throws ServicioException;

    Boolean eliminarUsuario(String username) throws ServicioException;

    Boolean verificarUsuario(String username, String password)
            throws ServicioException;
}
