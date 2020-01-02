package com.test.test.Services;

import com.sun.tools.javac.util.Pair;
import com.test.test.model.User;
import com.test.test.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceUser {

    private static final Logger log = Logger.getLogger(ServiceUser.class);

    @Autowired
    public UsersRepository usersRepository;

    public List<String> getAllUsers() {
         return usersRepository.findAll().stream().map(u -> u.toString()).collect(Collectors.toList());
    }

    public User saveNewUser(String name, String secondname, String email) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = new User(name,secondname,email, "defailt_image", "", null);
        usersRepository.save(user);

        return user;
    }

    public User getUserById(Integer id) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user;
        try {
            user = usersRepository.getOne(id);
        } catch (EntityNotFoundException ex) {
            user = null;
            log.info(String.format("Пользователь с id:%d не найден.", id));
        }

        return user;
    }


    // метод возвращает пару <обновлённый пользователь, его старое состояние>
    public Pair<User, String> changeUserByIdAndStatus(Integer id, String status) {
        //TODO Переделать процесс обновления информации пользователя
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Pair<User, String> pair;
        User user;

        try {
            user = usersRepository.getOne(id);
            String oldStatus = user.getStatus();
            usersRepository.deleteById(id);
            user.setStatus("Online".equals(status) ? "Online" : "Offline");
            user.setTimeChange(new Date());
            usersRepository.save(user);

            pair = new Pair<>(user, oldStatus);

        } catch (EntityNotFoundException ex) {
            user = null;
            pair = new Pair<>(user, null);
            log.info(String.format("Пользователь с id:%d не найден.", id));
        }

        return pair;
    }


    public List<User> findUsersByStatus(String status) {
        return usersRepository.findByStatus(status);
    }

    public List<User> filterUserByStatusAndDate(String status, Date date) {
        //симулируем долгий запрос
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<User> list =  usersRepository.findByTimeChangeGreaterThan(date).
                stream().
                filter(u -> u.getStatus().equals(status)).collect(Collectors.toList());
        return list;
    }

}
