package br.com.facol.uservault.domain.factory;

import br.com.facol.uservault.domain.UserCreate;
import br.com.facol.uservault.dto.UserCreateRequestDTO;

public interface UserCreateFactory {
    UserCreate createUser(UserCreateRequestDTO requestDTO);
}
