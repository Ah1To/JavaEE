package CDI;

import data.UserDAO;
import model.Indication;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Singleton
public class UserCDI implements Serializable {
    private String email;
    private String password;

    private List<User> users;

    private boolean loginSuccess;
    private boolean createSuccess;

    @EJB
    private UserDAO userDAO;

    public void checkPassword() {
        loginSuccess = userDAO.checkEnter(email, password);
    }

    public void createUser() {
        createSuccess = userDAO.createNewUser(email, password);
    }

    public void buttonAction(ActionEvent actionEvent) {

        if(userDAO.createNewUser(email, password)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You is registred " + email,  null);
            addMessage(message);
        }
        else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error with registration " + email,  null);
            addMessage(message);
        }
    }

    public void addMessage(FacesMessage message) {

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<User> getAllUsers(){
        users = userDAO.getAllUsers();
        return users;
    }

    public List<Indication> getAllIndication() {
        return userDAO.getAllIndication();
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

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(Boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isCreateSuccess() {
        return createSuccess;
    }

    public void setCreateSuccess(Boolean createSuccess) {
        this.createSuccess = createSuccess;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
