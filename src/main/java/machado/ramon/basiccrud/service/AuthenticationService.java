package machado.ramon.basiccrud.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.User;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User signup(User input) {

    User user = new User();
    user.setLogin(input.getLogin());
    user.setPassword(passwordEncoder.encode(input.getPassword()));

    return userRepository.save(user);
  }

  public User authenticate(User input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getLogin(),
            input.getPassword()));

    return userRepository.findById(input.getLogin())
        .orElseThrow();
  }
}