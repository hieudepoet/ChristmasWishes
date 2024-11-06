package com.project.models;

public class Wish {
    private Long id;
    private Long userId;
    private String message;

    public Wish (Long id, Long userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}   

    //
}
