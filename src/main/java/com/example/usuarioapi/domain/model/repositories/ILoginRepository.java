package com.example.usuarioapi.domain.model.repositories;

import com.example.usuarioapi.domain.model.dto.LoginDTO;

public interface ILoginRepository {

    void updateLoginDate(LoginDTO loginDTO);
}
