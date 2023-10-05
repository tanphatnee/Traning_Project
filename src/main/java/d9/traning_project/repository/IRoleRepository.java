package d9.traning_project.repository;

import d9.traning_project.model.domain.Role;
import d9.traning_project.model.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository

public interface IRoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(RoleName roleName);
}
