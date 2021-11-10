package com.mkyong.session;

import com.mkyong.auth.Auth;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class SessionRepository {

    private static LinkedHashMap<String, String> usersSessions = new LinkedHashMap<>();

    public static String getUserAccess(Session session){
        String access = "";
        //String userName = getUserName(session);
        //for (Map.Entry<String, LinkedHashMap<String, Boolean>> userEntry:usersAccess.entrySet()) {
        //    if(userEntry.getKey().equals(userName)){
        //        //access = userEntry.getValue();
        //        for (Map.Entry<String, Boolean> userEntryAccess:userEntry.getValue().entrySet()) {
//
        //        }
        //        break;
        //    }
        //}
        return access;
    }

    //public static String getUserName(Session session){
    //    String userName = "";
    //    for (Map.Entry<String, String> userEntry:usersSessions.entrySet()) {
    //        if(userEntry.getValue().equals(session.getSession())){
    //            userName = userEntry.getKey();
    //            break;
    //        }
    //    }
    //    return userName;
    //}

    public static Map.Entry<String, String> validateSession(Session session){
        boolean exists = false;
        for (Map.Entry<String, String> userEntry:usersSessions.entrySet()) {
            if(userEntry.getValue().equals(session.getSession())){
                exists = true;
                break;
            }
        }
        Map.Entry<String, String> userData = null;
        //if(exists){
        //    String userName = getUserName(session.getSession());
        //    for (Map.Entry<String, String> userEntry:usersAccess.entrySet()) {
        //        if(userEntry.getKey().equals(userName)){
        //            userData = userEntry;
        //            break;
        //        }
        //    }
        //}
        return userData;
    }

    public static boolean validateUser(Auth auth){
        boolean exists = false;
        //for (Map.Entry<String, String> userEntry:users.entrySet()) {
        //    if(userEntry.getKey().equals(auth.getUser()) && userEntry.getValue().equals(auth.getPassword())){
        //        exists = true;
        //        break;
        //    }
        //}
        return exists;
    }


    public static String addNewUserSession(String user){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        usersSessions.put(user, uuidAsString);
        return uuidAsString;
    }


}
