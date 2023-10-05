package d9.traning_project.service.impl;

import d9.traning_project.model.domain.Role;
import d9.traning_project.model.domain.RoleName;
import d9.traning_project.model.domain.Users;
import d9.traning_project.model.dto.request.FormSignUpDto;
import d9.traning_project.repository.IUserRepository;
import d9.traning_project.service.IRoleService;
import d9.traning_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
@Autowired
private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public Users save(FormSignUpDto users) {
        // lấy ra danh sách các quyền và chuyển thành đối tượng Users
        Set<Role> roles  = new HashSet<>();
        if (users.getRoles()==null||users.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        }else {
            users.getRoles().stream().forEach(
                    role->{
                        switch (role){
                            case "admin":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                            case "users":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_USER));}}
            );
        }
        Users newUsers = Users.builder()
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .email(users.getEmail())
                .password(passwordEncoder.encode(users.getPassword()))
                .status(true)
                .roles(roles)
                .build();

        return userRepository.save(newUsers);
    }
}
