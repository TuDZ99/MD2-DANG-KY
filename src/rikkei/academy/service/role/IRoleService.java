package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    void save(Role role);
    Role findByRoleName(String roleName);

    Role findByRoleName(RoleName roleName);
}
