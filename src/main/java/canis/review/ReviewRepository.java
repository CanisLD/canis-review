package canis.review;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ReviewRepository extends CrudRepository<Review, String> {;

  Page<Review> findBySubjectId(String subjectId, Pageable paging);

  Page<Review> findBySubjectIdAndTagIn(String subjectId, Set<String> tag, Pageable paging);
}
