package com.example.usuarioapi.application.service.impl;

import com.example.usuarioapi.application.service.ILoginService;
import com.example.usuarioapi.domain.model.dto.LoginDTO;
import com.example.usuarioapi.domain.model.repositories.ILoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class LoginImpl implements ILoginService {

    @Autowired
    private ILoginRepository loginRepository;

    @Override
    public void updateLoginDate(LoginDTO loginDTO, String token) {
        loginRepository.updateLoginDate(loginDTO, token);

    }
}
