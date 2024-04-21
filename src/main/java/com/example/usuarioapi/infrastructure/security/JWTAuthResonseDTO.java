package com.example.usuarioapi.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTAuthResonseDTO {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";

    public JWTAuthResonseDTO(String tokenDeAcceso) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
    }


}
