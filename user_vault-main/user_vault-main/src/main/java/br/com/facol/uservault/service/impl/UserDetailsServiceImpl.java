package br.com.facol.uservault.service.impl;

import br.com.facol.uservault.domain.UserCreate;
import br.com.facol.uservault.repository.UserCreateRepository;
import br.com.facol.uservault.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserCreateRepository repository;

    public UserDetailsServiceImpl(UserCreateRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCreate user = this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        return new UserSS(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getProfile()
        );
    }
}
