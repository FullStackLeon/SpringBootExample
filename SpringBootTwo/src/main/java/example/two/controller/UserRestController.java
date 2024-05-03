package example.two.controller;

import example.two.entity.Result;
import example.two.entity.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    private final RestTemplate restTemplate;

    public UserRestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id) {
        return this.restTemplate.getForObject("http://localhost:8081/user/{id}", Result.class, id);
    }

    @PostMapping(value = "add")
    public Result addUser() {
        User user = new User("Jack", "Berlin");
        ResponseEntity<Result> resultResponseEntity = this.restTemplate.postForEntity("http://localhost:8081/user/add", user, Result.class);
        return resultResponseEntity.getBody();
    }

    @PutMapping(value = "update")
    public Result updateUser() {
        User user = new User(1, "Jack", "Berlin");
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);
        ResponseEntity<Result> responseEntity = this.restTemplate.exchange("http://localhost:8081/user/update", HttpMethod.PUT, userHttpEntity, Result.class);
        return responseEntity.getBody();
    }

    @DeleteMapping(value = "/delete/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        ResponseEntity<Result> responseEntity = this.restTemplate.exchange("http://localhost:8081/user/delete/{id}", HttpMethod.DELETE, null, Result.class, id);
        return responseEntity.getBody();
    }
}
