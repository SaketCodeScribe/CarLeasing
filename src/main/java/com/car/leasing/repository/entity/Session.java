package com.car.leasing.repository.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String session;
    private User user;
    private Date timestamp;

    public Session(long id, String session, User user, Date timestamp) {
        this.id = id;
        this.session = session;
        this.user = user;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public String getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
