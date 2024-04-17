package adminpage.validator;

import adminpage.repository.UserRepository;
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
            throw new RuntimeException("Login already exists");
        }
    }

    public void checkListEmpty(Object list) {
        if (list == null || ((List) list).isEmpty()) {
            throw new RuntimeException("List is empty");
        }
    }

    public void checkExistsById(JpaRepository repository, Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found");
        }
    }

    public void checkListsSameSize(List<?> listOne, List<?> listTwo) {
        checkListsSameSize(listOne, listTwo, "The two provided lists do not have the same size");
    }

    public void checkListsSameSize(List<?> listOne, List<?> listTwo, String customMessage) {
        if (listOne.size() != listTwo.size()) {
            throw new RuntimeException(customMessage);
        }
    }

    public void checkAlreadyPresent(Optional<?> object){
        if(object.isPresent()){
            throw new RuntimeException("Entity of type" + object.getClass() + "already exists");
        }
    }

}
