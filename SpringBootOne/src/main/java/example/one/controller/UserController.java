package example.one.controller;

import example.one.entity.Result;
import example.one.entity.User;
import example.one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/add")
    public Result<Map<Integer, User>> addUser(@RequestBody User user) {
        String message = messageSource.getMessage("user.add.success",null,LocaleContextHolder.getLocale());
        return new Result<>(200, message, userService.AddUser(user));
    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        String message = messageSource.getMessage("user.get.success",null,LocaleContextHolder.getLocale());
        User user = userService.getUserById(id);
        return new Result<>(200, message, user);
    }

    @PutMapping("/update")
    public Result<Map<Integer, User>> updateUser(@RequestBody User user) {
        String message = messageSource.getMessage("user.update.success",null,LocaleContextHolder.getLocale());
        return new Result<>(200, message, userService.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Map<Integer, User>> deleteUser(@PathVariable Integer id) {
        String message = messageSource.getMessage("user.delete.success",null,LocaleContextHolder.getLocale());
        return new Result<>(200, message, userService.deleteUser(id));
    }
}
