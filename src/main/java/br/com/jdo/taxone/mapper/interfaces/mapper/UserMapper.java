package br.com.jdo.taxone.mapper.interfaces.mapper;

import br.com.jdo.taxone.mapper.domain.entity.UserDomain;
import br.com.jdo.taxone.mapper.infrastructure.entity.User;

public class UserMapper {

    public static UserDomain map(User u) {
        UserDomain ud = new UserDomain();
        ud.setId(u.getId());
        ud.setName(u.getName());
        ud.setPassword(u.getPassword());
        ud.setCreationDate(u.getCreationDate());
        return ud;
    }

    public static User map(UserDomain ud) {
        User u = new User();
        u.setId(ud.getId());
        u.setName(ud.getName());
        u.setPassword(ud.getPassword());
        u.setCreationDate(ud.getCreationDate());
        return u;
    }

}
