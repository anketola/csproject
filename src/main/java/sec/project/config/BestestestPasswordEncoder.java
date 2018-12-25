/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class BestestestPasswordEncoder implements PasswordEncoder {
    
    @Override
    public String encode(CharSequence rawPassword) {
        String encrypted = rawPassword + "safe";
        return encrypted;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword.equals(encode(rawPassword))) {
            return true;
        }
        return false;
    }
 
    
    
}
