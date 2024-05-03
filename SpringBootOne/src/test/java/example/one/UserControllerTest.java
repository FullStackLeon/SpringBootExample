package example.one;

import example.one.entity.User;
import example.one.entity.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class UserControllerTest {
    private final RestTemplate restTemplate = new RestTemplate();
    @Test
    void getUserTest() {
        Result<User> result = this.restTemplate.getForObject("http://localhost:8080/user/{id}", Result.class, 1);
        if (result != null) {
            Assertions.assertEquals(200, result.getCode(), "接口返回状态码应为200");
        }
        System.out.println(result);
    }


    @Test
    void addUser() {
        User user = new User("Jack", "Berlin");
        ResponseEntity<Result> resultResponseEntity = this.restTemplate.postForEntity("http://localhost:8080/user/add", user, Result.class);
        if (resultResponseEntity.getBody() != null) {
            Assertions.assertEquals(200, resultResponseEntity.getBody().getCode(), "接口返回状态码应为200");
        }
        System.out.println(resultResponseEntity.getBody());
    }
    @Test
    void updateUser() {
        User user = new User(1, "Jack", "Berlin");
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);
        ResponseEntity<Result> responseEntity = this.restTemplate.exchange("http://localhost:8080/user/update", HttpMethod.PUT, userHttpEntity, Result.class);
        if (responseEntity.getBody() != null) {
            Assertions.assertEquals(200, responseEntity.getBody().getCode(), "接口返回状态码应为200");
        }
        System.out.println(responseEntity.getBody());
    }

    @Test
   void deleteUser() {
        ResponseEntity<Result> responseEntity = this.restTemplate.exchange("http://localhost:8080/user/delete/{id}", HttpMethod.DELETE, null, Result.class,1);
        if (responseEntity.getBody() != null) {
            Assertions.assertEquals(200, responseEntity.getBody().getCode(), "接口返回状态码应为200");
        }
       System.out.println(responseEntity.getBody());
    }
}
