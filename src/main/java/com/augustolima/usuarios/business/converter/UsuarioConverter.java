package com.augustolima.usuarios.business.converter;


import com.augustolima.usuarios.business.dto.EnderecoDTO;
import com.augustolima.usuarios.business.dto.TelefoneDTO;
import com.augustolima.usuarios.business.dto.UsuarioDTO;
import com.augustolima.usuarios.infrastructure.entity.Endereco;
import com.augustolima.usuarios.infrastructure.entity.Telefone;
import com.augustolima.usuarios.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component /* É um componente a mais, mas não é uma service, não possui regras de negócio */

public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO  usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();

        /* Esse metodo acima é a forma mais avancada de construir o objeto, abaixo está a forma padrão
        Usuario usuario = new Usuario();
        usuario.setEmail(UsuarioDTO.getEmail());
    */
    }


    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
        // return enderecoDTOS.stream().map(this::paraEndereco); Essa é a forma mais avançada de construir a conversão feita acima.
    }



    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .complemento(enderecoDTO.getComplemento())
                .numero(enderecoDTO.getNumero())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS) {
        List<Telefone> telefones = new ArrayList<>();
        for (TelefoneDTO telefoneDTO : telefoneDTOS) {
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;

    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }




    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO) {
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones()))
                .build();

        /* Esse metodo acima é a forma mais avancada de construir o objeto, abaixo está a forma padrão
        Usuario usuario = new Usuario();
        usuario.setEmail(UsuarioDTO.getEmail());
    */
    }


    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        for (Endereco enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEnderecoDTO(enderecoDTO));
        }
        return enderecos;
        // return enderecoDTOS.stream().map(this::paraEndereco); Essa é a forma mais avançada de construir a conversão feita acima.
    }



    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO) {
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .complemento(enderecoDTO.getComplemento())
                .numero(enderecoDTO.getNumero())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS) {
        List<TelefoneDTO> telefones = new ArrayList<>();
        for (Telefone telefoneDTO : telefoneDTOS) {
            telefones.add(paraTelefoneDTO(telefoneDTO));
        }
        return telefones;

    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO) {
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
}
