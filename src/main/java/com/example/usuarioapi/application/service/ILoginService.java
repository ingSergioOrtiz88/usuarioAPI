package com.example.usuarioapi.application.service;

import com.example.usuarioapi.domain.model.dto.LoginDTO;

public interface ILoginService {

    void updateLoginDate(LoginDTO loginDTO,String token);
}
