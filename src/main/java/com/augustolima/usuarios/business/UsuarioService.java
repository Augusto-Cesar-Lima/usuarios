package com.augustolima.usuarios.business;


import com.augustolima.usuarios.business.converter.UsuarioConverter;
import com.augustolima.usuarios.business.dto.UsuarioDTO;
import com.augustolima.usuarios.infrastructure.entity.Usuario;
import com.augustolima.usuarios.infrastructure.exceptions.ConflictException;
import com.augustolima.usuarios.infrastructure.exceptions.ResourceNotFoundException;
import com.augustolima.usuarios.infrastructure.repository.UsuarioRepository;
import com.augustolima.usuarios.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public void emailExiste(String email) {
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Esse email: " + email + " já existe.");
            }
        } catch (ConflictException e){
            throw new ConflictException("O email já está cadastrado: " + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return  usuarioRepository.existsByEmail(email);
    }

    public void deletaUsuarioPorEmail(String email) {
            usuarioRepository.deleteByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + email));
    }
}
