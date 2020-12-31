package canis.review;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class ReviewDataAccess {

  @Autowired
  private ReviewRepository reviewRepository;

  public Page<Review> findBySubject(String subject, Set<String> tag, int page, int pageSize) {
    final Pageable paging = PageRequest.of(page, pageSize, Sort.by("instant").descending());
    return (tag == null || tag.isEmpty())
      ? reviewRepository.findBySubject(subject, paging)
      : reviewRepository.findBySubjectAndTagIn(subject, tag, paging);
  }

  public Optional<Review> findById(String review) {
    return reviewRepository.findById(review);
  }

  @Transactional
  public String save(Review candidate) {
    final String id = Optional.ofNullable(candidate.getReview()).orElseGet(() -> UUID.randomUUID().toString());
    final Review review = new Review.ReviewBuilder()
      .instant(Instant.now())
      .review(id)
      .reviewer(candidate.getReviewer())
      .subject(candidate.getSubject())
      .header(candidate.getHeader())
      .byline(candidate.getByline())
      .body(candidate.getBody())
      .tag(candidate.getTag())
      .build();
    reviewRepository.save(review);
    return review.getReview();
  }
}
