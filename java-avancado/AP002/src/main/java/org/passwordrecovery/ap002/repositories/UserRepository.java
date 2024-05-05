package org.passwordrecovery.ap002.repositories;

import org.passwordrecovery.ap002.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
