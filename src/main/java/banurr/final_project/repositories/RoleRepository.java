package banurr.final_project.repositories;

import banurr.final_project.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    @Query("SELECT r FROM Role r where r.name='user'")
    Role findRoleUser();
}
