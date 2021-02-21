package org.fbichat.entrys;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import org.fbichat.Space;
import org.fbichat.utils.UserResult;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SpaceClass
public class User implements Serializable {
    private String name;
    private String id; //id para controle do Gigaspace

    public User() {}

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @SpaceId(autoGenerate = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserResult save() {
        if(Space.get().read(this) == null) {
            Space.get().write(this);
            return UserResult.CREATED;
        }

        return UserResult.USER_ALREADY_EXISTS;
    }
    public static List<User> getAll(){
        return Arrays.asList(Space.get().readMultiple(new User(null)).clone());
    }
}
