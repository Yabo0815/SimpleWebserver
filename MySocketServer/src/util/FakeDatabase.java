package util;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    private static List<UserInfo> users = new ArrayList<UserInfo>();
    
    public static boolean add(UserInfo userInfo) {
        users.add(userInfo);
        return true;
    }
    
    public static boolean delete(String userName, String password) {
        
        return true;
    }
    
    public static boolean modify(String userName, String password) {
        return true;
    }
    
    public static String find(String userName, String password) {
        String nickName = null;
        
        return nickName;
    }

}
