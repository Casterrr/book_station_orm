package cacadores.ifal.poo.book_station.service;

import cacadores.ifal.poo.book_station.dto.User.UserCreateDTO;
import cacadores.ifal.poo.book_station.dto.User.UserResponseDTO;
import cacadores.ifal.poo.book_station.dto.User.UserUpdateDTO;
import cacadores.ifal.poo.book_station.exception.EmailAlreadyExistsException;
import cacadores.ifal.poo.book_station.model.entity.User;
import cacadores.ifal.poo.book_station.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> updateUser(String id, UserUpdateDTO userUpdateDTO) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userUpdateDTO.getName());
            user.setEmail(userUpdateDTO.getEmail());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}