package banurr.final_project.services;

import banurr.final_project.models.Role;
import banurr.final_project.models.User;
import banurr.final_project.repositories.RoleRepository;
import banurr.final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserService implements UserDetailsService
{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> allUsers()
    {
        return userRepository.findAll();
    }

    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return user;
    }

    public String addUser(User user, String rePassword)
    {
        if(userRepository.findByEmail(user.getEmail()) != null) return "register?exist";
        if(!user.getPassword().equals(rePassword)) return "register?typo";
        user.setPassword(passwordEncoder.encode(rePassword));
        Role role = roleRepository.findRoleUser();
        user.setRoles(List.of(role));
        userRepository.save(user);
        return "sign-in?success";
    }
}
