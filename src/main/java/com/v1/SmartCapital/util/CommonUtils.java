package com.v1.SmartCapital.util;


import com.v1.SmartCapital.entity.User;
import com.v1.SmartCapital.enums.Gender;
import com.v1.SmartCapital.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CommonUtils {

    private static final Logger logger = LogManager.getLogger(CommonUtils.class);
    private static UserRepository userRepository;

    @Autowired
    public CommonUtils(UserRepository userRepository) {
        CommonUtils.userRepository = userRepository;
    }

    public static User getUserById(Optional<Long> userId) {
        if (userId.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(userId.get());
            return optionalUser.orElse(null);
        }
        return null;
    }

    public static String getUsername(String userId) {
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(userId));
        return optionalUser.map(User::getUsername).orElse(null);
    }

    public static Map<String,String> getEnumMap(String name, String value)
    {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("value",value);
        return map;
    }

    public static Gender getGender(String gender) {
        if (gender.equals(Gender.Male.name()))
            return Gender.Male;
        else if (gender.equals(Gender.Female.name()))
            return Gender.Female;
        else if (gender.equals(Gender.Others.name()))
            return Gender.Others;
        return null;
    }

    public static String encodePassword(String password) {
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        return password;
    }
}