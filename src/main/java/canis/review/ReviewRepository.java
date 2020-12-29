package canis.review;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ReviewRepository extends CrudRepository<Review, String> {
  
  Page<Review> findBySubject(String subject, Pageable pageable);
}
