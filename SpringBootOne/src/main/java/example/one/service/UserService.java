package example.one.service;

import example.one.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Map<Integer, User> userMap = new HashMap<>();
    static {
        userMap.put(1 ,new User(1,"John","Beijing"));
        userMap.put(2 ,new User(2,"Jane","Shanghai"));
        userMap.put(3 ,new User(3,"Tom","Shenzhen"));
        userMap.put(4 ,new User(4,"Jace","Guangzhou"));
        userMap.put(5 ,new User(5,"Rose","Shenzhen"));
    }

    public Map<Integer, User> AddUser(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        int id = userMap.size() + 1;
        user.setId(id);
        user.setCreatedAt(LocalDateTime.now());
        userMap.put(id, user);
        return userMap;
    }

    public User getUserById(Integer id) {
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        }
        return null;
    }

    public Map<Integer, User> updateUser(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
        }
        return userMap;
    }

    public Map<Integer,User> deleteUser(Integer id) {
        userMap.remove(id);
        return userMap;
    }
}
