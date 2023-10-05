package d9.traning_project.service.impl;

import d9.traning_project.model.domain.Role;
import d9.traning_project.model.domain.RoleName;
import d9.traning_project.repository.IRoleRepository;
import d9.traning_project.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(()->new RuntimeException("Role Not Found"));
    }
}
