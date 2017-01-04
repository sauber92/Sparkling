package com.sparkling.entity;

/**
 * Created by gain on 2017. 1. 4..
 */

        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;
        import org.springframework.data.mongodb.core.mapping.Field;

        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;


@Document(collection = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;
    @Field
    private String id;
    @Field
    private String login;

    public User() {
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}