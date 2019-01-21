package filters;

import AuthService.AuthService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @EJB
    AuthService authService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;



        Cookie[] reqCookies = request.getCookies();

        System.out.println("For cookies:");
        for(Cookie c: reqCookies){
            System.out.println("Name " + c.getName()+ " Value " + c.getValue());
        }



        if(authService.checkCookies(reqCookies)){
            filterChain.doFilter(servletRequest, servletResponse);

        }
        else {
            response.sendRedirect("faile.xhtml");
        }
  }

    @Override
    public void destroy() {

    }
}
