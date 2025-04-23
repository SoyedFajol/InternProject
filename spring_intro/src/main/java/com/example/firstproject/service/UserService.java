package com.example.firstproject.service;

import com.example.firstproject.model.dto.UserDTO;
import com.example.firstproject.model.entity.User;
import com.example.firstproject.model.mapper.UserMapper;
import com.example.firstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;


    public void save(UserDTO dto) {
        User user = mapper.toEntity(dto);
        userRepository.save(user);
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(mapper::toDTO);
    }

    public void update(Long id, UserDTO dto){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setUserName(dto.getUserName());
            updatedUser.setPassword(dto.getPassword());
            userRepository.save(updatedUser);
        }
    }

    public String deleteById(Long id){
        if(!userRepository.existsById(id)){
            return "User not found with id: " + id;
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
