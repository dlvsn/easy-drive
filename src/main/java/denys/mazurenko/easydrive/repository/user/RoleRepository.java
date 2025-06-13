package denys.mazurenko.easydrive.repository.user;

import denys.mazurenko.easydrive.model.Role;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Role.Roles name);

    Set<Role> findByIdIn(Set<Long> ids);
}
