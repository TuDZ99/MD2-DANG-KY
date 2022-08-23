package rikkei.academy.service.user;

import rikkei.academy.model.User;
import rikkei.academy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
