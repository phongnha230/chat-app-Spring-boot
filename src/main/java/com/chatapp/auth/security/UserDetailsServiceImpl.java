package com.chatapp.auth.security;
import com.chatapp.user.entity.User;
import com.chatapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserRepository userRepository;


    @Transactional
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException{
          User user = (userRepository.findByName(nameOrEmail)).or(() -> userRepository.findByEmail(nameOrEmail)).orElseThrow(() -> new UsernameNotFoundException(("User not found")));

          return UserDetailsImpl.build(user);
    }
}
