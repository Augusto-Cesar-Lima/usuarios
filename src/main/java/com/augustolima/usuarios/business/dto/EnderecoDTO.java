package com.augustolima.usuarios.business.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EnderecoDTO {

    private String rua;
    private String complemento;
    private String cidade;
    private String cep;
    private String estado;
    private Long numero;
}
