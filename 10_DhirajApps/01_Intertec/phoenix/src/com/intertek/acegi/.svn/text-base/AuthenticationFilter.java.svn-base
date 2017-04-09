package com.intertek.acegi;

import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import com.intertek.util.StringEncrypter;

public class AuthenticationFilter extends AuthenticationProcessingFilter {

    protected String obtainUsername(javax.servlet.http.HttpServletRequest request) {
        String userName = request.getParameter("j_username");
        String[] tokens = parseToken(request);
        if (tokens != null) {
            return tokens[0];
        }
        return userName;
    }

    protected String obtainPassword(javax.servlet.http.HttpServletRequest request) {
        String password = request.getParameter("j_password");
        String[] tokens = parseToken(request);
        if (tokens != null) {
            return tokens[1];
        }
        return password;
    }

    private String[] parseToken(javax.servlet.http.HttpServletRequest request) {
        String[] tokens = new String[2];
        String token = request.getParameter("token");

        // System.out.println("encrypted token="+token);
        if (token != null && !token.trim().equals("")) {
            token = StringEncrypter.doDecrypt(token);
            // System.out.println("decrypted token="+token);
            String sep = ";@;";
            int index = token.indexOf(sep);
            if (index > 0) {
                tokens[0] = token.substring(0, index);
                tokens[1] = token.substring(index + sep.length());
                return tokens;
            }
        }
        return null;
    }
}
