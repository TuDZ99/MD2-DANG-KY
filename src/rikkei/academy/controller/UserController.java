package rikkei.academy.controller;

import rikkei.academy.dto.request.SingUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();

    IRoleService roleService = new RoleServiceIMPL() {
        @Override
        public Role findByRoleName(String roleName) {
            return null;
        }
    };

    public List<User> getUserList() {
        return userService.findAll();
    }
    public ResponseMessenger register(SingUpDTO singUpDTO){
        if (userService.existsByUsername(singUpDTO.getUsername())){
            return new ResponseMessenger("User_existed");
        }
        if (userService.existsByEmail(singUpDTO.getEmail())){
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = singUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        for (String role : strRole){
            switch (role){
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("invalid_eole");
            }
        }

        User user = new User(
                singUpDTO.getId(),
                singUpDTO.getName(),
                singUpDTO.getUsername(),
                singUpDTO.getEmail(),
                singUpDTO.getPassword(),
                roles
        );

        userService.save(user);
        return new ResponseMessenger("success");
    }
}
