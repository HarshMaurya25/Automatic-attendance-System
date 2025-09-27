package com.project.Attendance_System.Service;

import com.project.Attendance_System.Domain.Entity.User;
import com.project.Attendance_System.Domain.Entity.UserPrincipal;
import com.project.Attendance_System.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("%s email is not found" , username));
        }

        return new UserPrincipal(user);
    }
}
