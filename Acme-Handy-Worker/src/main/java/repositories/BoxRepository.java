
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {

	@Query("select b from Box b join b.messages s where b.name=?1 and s.sender.id=?2")
	Box findBoxByNameAndActor(String name, int actorId);
	
	@Query("select b from Customer c join c.boxes b where c.id=?1")
	Collection<Box> findBoxByCustomer(int actorId);
	
	@Query("select b from HandyWorker h join h.boxes b where h.id=?1")
	Collection<Box> findBoxByHw(int actorId);
	
	@Query("select b from Referee r join r.boxes b where r.id=?1")
	Collection<Box> findBoxByReferee(int actorId);
	
	@Query("select b from Administrator a join a.boxes b where a.id=?1")
	Collection<Box> findBoxByAdmin(int actorId);
	
	@Query("select b from Sponsor s join s.boxes b where s.id=?1")
	Collection<Box> findBoxBySponsor(int actorId);

}
