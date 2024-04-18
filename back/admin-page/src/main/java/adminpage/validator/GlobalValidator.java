package adminpage.validator;

import adminpage.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GlobalValidator {

    private final UserRepository userRepository;

    @Autowired
    public GlobalValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkLoginExists(String login) {
        if(userRepository.existsByLogin(login)){
            throw new IllegalStateException("Login already exists");
        }
    }

    public void checkAlreadyPresent(Optional<?> object){
        if(object.isPresent()){
            throw new IllegalStateException("Entity of type" + object.getClass() + "already exists");
        }
    }

    public void checkListEmpty(Object list) {
        if (list == null || ((List) list).isEmpty()) {
            throw new IllegalArgumentException("List of items is empty");
        }
    }

    public void checkExistsById(JpaRepository repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    public void checkListsSameSize(List<?> listOne, List<?> listTwo, String customMessage) {
        if (listOne.size() != listTwo.size()) {
            System.out.println("List one size: " + listOne.size());
            System.out.println("List two size: " + listTwo.size());
            throw new IllegalArgumentException(customMessage);
        }
    }
}
