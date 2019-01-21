package AuthService;


import model.User;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Cookie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AuthService {

    private HashMap<String, Cookie> hashCookie = new HashMap();


   public Cookie getCookie(String email){


      Cookie cookie = new Cookie("uuid", UUID.randomUUID().toString());

      System.out.println("Put: " + email + " Cookie: " + cookie.getValue());

      hashCookie.put(email, cookie);

      return cookie;


   }


    public boolean checkCookies(Cookie[] cookies) {

        for(Map.Entry<String, Cookie> entry : hashCookie.entrySet()){
            for(Cookie Cookie : cookies){
                if(entry.getValue().getValue().equals(Cookie.getValue()))
                    return true;

            }

        }

        return false;
    }
}
