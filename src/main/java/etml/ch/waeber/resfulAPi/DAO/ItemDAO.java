package etml.ch.waeber.resfulAPi.DAO;

import etml.ch.waeber.resfulAPi.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity, Integer> {

}
