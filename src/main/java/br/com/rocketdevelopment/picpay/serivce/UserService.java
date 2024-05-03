package br.com.rocketdevelopment.picpay.serivce;

import br.com.rocketdevelopment.picpay.domain.users.User;
import br.com.rocketdevelopment.picpay.api.dto.UserDTO;
import br.com.rocketdevelopment.picpay.domain.users.UserType;
import br.com.rocketdevelopment.picpay.domain.wallet.Wallet;
import br.com.rocketdevelopment.picpay.exception.ValidadeException;
import br.com.rocketdevelopment.picpay.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO) {

        User createdUser = userRepository.save(createUser(userDTO));
        log.info("User created: name: "+ createdUser.getFullName()+" ID: "+ createdUser.getId());
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        allUser.forEach(user ->
                userDTOS.add(new UserDTO(user.getFullName(),
                        user.getDocument(), user.getEmail(),
                        user.getPassword(), user.getWallet().getBalance(),
                        user.getUserType()))
        );
        allUser.forEach(user -> log.info("User: "+ user.getFullName()+" ID: "+ user.getId()));
        return userDTOS;
    }
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    public void isPayerValid(User payer,BigDecimal mount) throws Exception {
        if(payer.getUserType().equals(UserType.MERCHANT)){
            throw new ValidadeException("Merchant can't be payer");
        }
        if(payer.getWallet().getBalance().compareTo(mount) < 0){
            throw new ValidadeException("Insufficient balance");
        }

    }

    private User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.fullName());
        user.setDocument(userDTO.document());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setUserType(userDTO.userType());

        Wallet wallet = new Wallet();
        wallet.setBalance(userDTO.balance());
        user.setWallet(wallet);
        return user;
    }
}
