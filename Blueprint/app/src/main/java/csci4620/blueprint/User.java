package csci4620.blueprint;

import java.io.Serializable;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class User implements Serializable {

    private String username;
    private String password;

    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }
}
