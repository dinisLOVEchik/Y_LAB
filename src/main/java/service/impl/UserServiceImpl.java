package service.impl;

import service.User;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceImpl {
    private List<User> users = new ArrayList<>();

    public String createUser(String login, String password) throws Exception {
        for (User user : users){
            if (user.getLogin().equals(login)){
                return "Пользователь под ником " + login + " уже существует!";
            }
        }
        users.add(new User(login, password));
        return "Регистрация пользователя " + login + " прошла успешно!";
    }

    public User getUser(String login){
        for (User user : users){
            if (user.getLogin().equals(login))
                return user;
        }
        throw new NoSuchElementException("Пользователь "+ login + " не найден");
    }
    public String deleteUser(String login){
        for (User user : users){
            if (user.getLogin().equals(login))
                users.remove(user);
            return "Пользователь " + login + " удален!";
        }
        return "Пользователь "+ login + " не найден";
    }
    public String authorizationUser(User user){
        for (User user1 : users){
            if (user1.getLogin().equals(user.getLogin()) && user1.getPassword().equals(user.getPassword())){
                return "Welcome!";
            }
        }
        return "Пользователь не найден!";
    }

    public List<User> getUsers() throws Exception{
        if (!users.isEmpty())
            return users;
        else
            throw new Exception("Список пуст!");
    }
}
