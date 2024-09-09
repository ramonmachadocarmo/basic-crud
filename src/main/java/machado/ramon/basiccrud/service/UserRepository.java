package machado.ramon.basiccrud.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import machado.ramon.basiccrud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
