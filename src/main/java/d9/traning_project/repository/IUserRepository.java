package d9.traning_project.repository;

import d9.traning_project.model.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {
//    boolean existsByUsername(String username);

    Optional<Users> findByEmail(String email);

}
