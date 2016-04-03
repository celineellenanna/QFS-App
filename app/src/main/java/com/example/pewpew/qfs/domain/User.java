package com.example.pewpew.qfs.domain;

public class User {

    private enum Role {Administrator, Moderator, Player}

    private enum Status {registered, activated, deleted}

    private String _id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private Role role;
    private Status status;

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String  getStatus() {
        return this.status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
