package CDI;


import AuthService.AuthService;
import data.UserDAO;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.Serializable;

@Named
@Singleton
public class LoginCDI implements Serializable {
    private String email;
    private String password;


    @EJB
    private UserDAO userDAO;

    @EJB
    private AuthService authService;

    public void validateUsernamePassword() throws IOException {
        if(!userDAO.checkEnter(email, password)){
            return;
        }

        Cookie cookie = authService.getCookie(email);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addCookie(cookie);
        response.sendRedirect("list.xhtml");

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
