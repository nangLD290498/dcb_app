package dcb_app.demo.repository;

import dcb_app.demo.model.StandardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRequestRepository extends JpaRepository<StandardRequest, Long> {

}
