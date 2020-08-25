package com.martinez.examencash.services;

import com.martinez.examencash.dtos.RequiredFieldDto;
import com.martinez.examencash.dtos.UserDto;
import com.martinez.examencash.exceptions.UserNotFoundException;
import com.martinez.examencash.exceptions.UserRequireParametersException;
import com.martinez.examencash.models.User;
import com.martinez.examencash.repositories.UserRepository;
import com.martinez.examencash.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación para manejar los usuarios.
 * Operaciones que contiene: findUserById, addUser y deleteUserById.
 */
@Service
public class UserServiceImpl implements UserService {

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca usuario con sus prestamos mediante id de usuario.
     *
     * @param id
     * @return Optional<UserDto>
     */
    @Override
    public Optional<UserDto> findUserById(Integer id) {
        LOG.info(Constants.STRING_EMPTY);
        LOG.info(Constants.MESSAGE_USER_SEARCH, id);
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            LOG.info(Constants.MESSAGE_USER_NOT_FOUND);
            throw new UserNotFoundException(Constants.MESSAGE_USER_NOT_FOUND);
        }
        LOG.info(Constants.MESSAGE_USER, user.get().toString());

        return user.map(this::toUserDTO);
    }

    /**
     * Agrega usuario en la tabla user. Para que la operacion se realice los atributos
     * que contiene el UserDto: email, firstName, lastName no pueden estar vacios.
     *
     * @param userDto
     */
    @Override
    public void addUser(Optional<UserDto> userDto) {
        LOG.info(Constants.STRING_EMPTY);
        validateUserDto(userDto.get());
        Optional<User> user = userDto.map(this::toUser);
        userRepository.save(user.get());
    }

    /**
     * Elimina usuario mediante id.
     *
     * @param id
     */
    @Override
    public void deleteUserById(Integer id) {
        LOG.info(Constants.STRING_EMPTY);
        LOG.info(Constants.MESSAGE_USER_DELETE, id);
        userRepository.deleteById(id);
        LOG.info(Constants.MESSAGE_USER_DELETE_SUCCESS, id);

    }

    private void validateUserDto(UserDto userDto) {
        List<RequiredFieldDto> errors = new ArrayList<>();

        if (userDto.getEmail() == null || userDto.getEmail().equals(Constants.STRING_EMPTY)) {
            LOG.error(Constants.REQUIRED_EMAIL);
            errors.add(new RequiredFieldDto(Constants.TXT_EMAIL, Constants.REQUIRED_EMAIL));
        }
        if (userDto.getFirstName() == null || userDto.getFirstName().equals(Constants.STRING_EMPTY)) {
            LOG.error(Constants.REQUIRED_FIRSTNAME);
            errors.add(new RequiredFieldDto(Constants.TXT_FIRSTNAME, Constants.REQUIRED_FIRSTNAME));
        }
        if (userDto.getLastName() == null || userDto.getLastName().equals(Constants.STRING_EMPTY)) {
            LOG.error(Constants.REQUIRED_LASTNAME);
            errors.add(new RequiredFieldDto(Constants.TXT_LASTNAME, Constants.REQUIRED_LASTNAME));
        }

        if (errors.size() > 0) {
            throw new UserRequireParametersException(errors);
        }
    }

    private UserDto toUserDTO(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getLoans()
        );
    }

    private User toUser(UserDto userdto) {
        return new User(
                userdto.getEmail(),
                userdto.getFirstName(),
                userdto.getLastName()
        );
    }

}
