package etml.ch.waeber.resfulAPi.DAO;
import etml.ch.waeber.resfulAPi.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientDAO extends JpaRepository<ClientEntity, Integer>{
}
