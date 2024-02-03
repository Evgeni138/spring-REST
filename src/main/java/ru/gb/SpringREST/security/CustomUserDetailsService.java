package ru.gb.SpringREST.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.SpringREST.model.Role;
import ru.gb.SpringREST.model.User;
import ru.gb.SpringREST.repository.RoleRepository;
import ru.gb.SpringREST.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
//                List.of(new SimpleGrantedAuthority(roleRepository.findById(user.getRoleId()))));
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Role userRole = roleRepository.findById(user.getRoleId())
                .orElseThrow(() -> new IllegalStateException("Role not found for user with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                getAuthorities(userRole));
    }

        private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
            return List.of(new SimpleGrantedAuthority(role.getName()));
        }
}
