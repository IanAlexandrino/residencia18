package org.passwordrecovery.ap002.services;

import jakarta.persistence.EntityNotFoundException;
import org.passwordrecovery.ap002.models.User;
import org.passwordrecovery.ap002.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public User create(User user){
        user.setId(null);
        user = this.userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user){
        User newUser = this.findById(user.getId());
        newUser.setPassword(user.getPassword());
        return this.userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{

            this.userRepository.deleteById(id);

        } catch (Exception e){

            throw new RuntimeException(
                    "Não é possível excluir pois existem entidades relacionadas!"
            );

        }
    }
}
