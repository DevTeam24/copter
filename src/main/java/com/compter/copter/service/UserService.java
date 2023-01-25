package com.compter.copter.service;

import com.compter.copter.domain.Company;
import com.compter.copter.domain.Role;
import com.compter.copter.domain.User;
import com.compter.copter.model.UserDTO;
import com.compter.copter.repos.CompanyRepository;
import com.compter.copter.repos.RoleRepository;
import com.compter.copter.repos.UserRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository,
            final CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("userId"));
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long userId) {
        return userRepository.findById(userId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getUserId();
    }

    public void update(final Long userId, final UserDTO userDTO) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRole(user.getUserRole() == null ? null : user.getUserRole().getRoleId());
        userDTO.setCompanyUser(user.getCompanyUser() == null ? null : user.getCompanyUser().getCompanyId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        final Role userRole = userDTO.getUserRole() == null ? null : roleRepository.findById(userDTO.getUserRole())
                .orElseThrow(() -> new NotFoundException("userRole not found"));
        user.setUserRole(userRole);
        final Company companyUser = userDTO.getCompanyUser() == null ? null : companyRepository.findById(userDTO.getCompanyUser())
                .orElseThrow(() -> new NotFoundException("companyUser not found"));
        user.setCompanyUser(companyUser);
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean passwordExists(final String password) {
        return userRepository.existsByPasswordIgnoreCase(password);
    }

}
