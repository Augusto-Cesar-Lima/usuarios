package com.augustolima.usuarios.business;


import com.augustolima.usuarios.business.converter.UsuarioConverter;
import com.augustolima.usuarios.business.dto.UsuarioDTO;
import com.augustolima.usuarios.infrastructure.entity.Usuario;
import com.augustolima.usuarios.infrastructure.repository.UsuarioRepository;
import com.augustolima.usuarios.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
