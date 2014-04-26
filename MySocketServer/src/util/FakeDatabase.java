package util;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    List<UserInfo> users = new ArrayList<UserInfo>();
    
    public boolean add(UserInfo userInfo) {
        users.add(userInfo);
        return true;
    }
    
    public boolean delete(String userName, String password) {
        return true;
    }
    
    public boolean modify(String userName, String password) {
        return true;
    }
    
    public boolean find(String userName) {
        return true;
    }

}
