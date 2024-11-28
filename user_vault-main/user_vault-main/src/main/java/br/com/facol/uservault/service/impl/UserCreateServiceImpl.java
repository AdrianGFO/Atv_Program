package br.com.facol.uservault.service.impl;

import br.com.facol.uservault.convertes.UserCreateToUserCreateResponseDTO;
import br.com.facol.uservault.domain.UserCreate;
import br.com.facol.uservault.domain.factory.UserCreateFactory;
import br.com.facol.uservault.dto.UserCreateRequestDTO;
import br.com.facol.uservault.dto.UserCreateResponseDTO;
import br.com.facol.uservault.repository.UserCreateRepository;
import br.com.facol.uservault.service.UserCreateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCreateServiceImpl implements UserCreateService {

    private final UserCreateRepository repository;
    private final UserCreateToUserCreateResponseDTO converter;
    private final UserCreateFactory userCreateFactory;

    public UserCreateServiceImpl(UserCreateRepository repository, UserCreateToUserCreateResponseDTO converter, UserCreateFactory userCreateFactory) {
        this.repository = repository;
        this.converter = converter;
        this.userCreateFactory = userCreateFactory;
    }

    @Override
    public List<UserCreateResponseDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(converter::map)
                .toList();
    }

    @Override
    public UserCreateResponseDTO create(UserCreateRequestDTO userCreateRequestDTO) {

        UserCreate user = userCreateFactory.createUser(userCreateRequestDTO);
        UserCreate userCreated = repository.save(user);

        return converter.map(userCreated);
    }

    @Override
    public List<UserCreateResponseDTO> findAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllUsers'");
    }
}
