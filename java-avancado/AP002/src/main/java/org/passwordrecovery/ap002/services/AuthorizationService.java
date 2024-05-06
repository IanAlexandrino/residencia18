package org.passwordrecovery.ap002.services;

import org.passwordrecovery.ap002.repositories.UserRepository;
import org.passwordrecovery.ap002.services.exceptions.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        return this.userRepository.findByEmail(email);
    }
}
