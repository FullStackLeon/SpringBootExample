package example.one.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private int id;
    @JsonProperty("name")
    private String username;
    @JsonProperty("addr")
    private String address;

    public User(int id, String username, String address) {
        this.id = id;
        this.username = username;
        this.address = address;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh")
    private LocalDateTime createdAt;

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getCreatedAt(), user.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getAddress(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
