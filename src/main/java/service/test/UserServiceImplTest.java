package service.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.User;
import service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(anyString(), anyString())).thenReturn("Регистрация пользователя testUser прошла успешно!");

        String result = userService.createUser("testUser", "testPassword");

        verify(userService, times(1)).createUser("testUser", "testPassword");
        assertThat(result).isEqualTo("Регистрация пользователя testUser прошла успешно!");
    }

    @Test
    public void testGetUser() {
        User user = new User("testUser", "testPassword");
        when(userService.getUser("testUser")).thenReturn(user);

        User result = userService.getUser("testUser");

        verify(userService, times(1)).getUser("testUser");
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testDeleteUser() {
        when(userService.deleteUser("testUser")).thenReturn("Пользователь testUser удален");

        String result = userService.deleteUser("testUser");

        verify(userService, times(1)).deleteUser("testUser");
        assertThat(result).isEqualTo("Пользователь testUser удален");
    }

    @Test
    public void testAuthorizationUser() {
        User user = new User("testUser", "testPassword");
        when(userService.authorizationUser(user)).thenReturn("Welcome!");

        String result = userService.authorizationUser(user);

        verify(userService, times(1)).authorizationUser(user);
        assertThat(result).isEqualTo("Welcome!");
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> userList = Arrays.asList(new User("testUser1", "testPassword1"), new User("testUser2", "testPassword2"));
        when(userService.getUsers()).thenReturn(userList);

        List<User> result = userService.getUsers();

        verify(userService, times(1)).getUsers();
        assertThat(result).containsExactlyElementsOf(userList);
    }

    @Test
    public void testGetUsersEmptyList() throws Exception {
        when(userService.getUsers()).thenThrow(new Exception("Список пуст!"));

        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            userService.getUsers();
        });

        verify(userService, times(1)).getUsers();
        assertThat(exception.getMessage()).isEqualTo("Список пуст!");
    }

    @Test
    public void testGetUserNotFoundException() {
        when(userService.getUser("nonExistingUser")).thenThrow(new NoSuchElementException("Пользователь nonExistingUser не найден"));

        NoSuchElementException exception = org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> {
            userService.getUser("nonExistingUser");
        });

        verify(userService, times(1)).getUser("nonExistingUser");
        assertThat(exception.getMessage()).isEqualTo("Пользователь nonExistingUser не найден");
    }
}

