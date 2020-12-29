package canis.review;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewDataAccess {

  @Autowired
  private ReviewRepository reviewRepository;

  public Page<Review> findBySubject(String subject, int page, int pageSize) {
    final Pageable paging = PageRequest.of(page, pageSize, Sort.by("instant").descending());
    return reviewRepository.findBySubject(subject, paging);
  }

  public Optional<Review> findById(String review) {
    return reviewRepository.findById(review);
  }

  public String save(Review candidate) {
    final String id = Optional.ofNullable(candidate.getReview()).orElseGet(() -> UUID.randomUUID().toString());
    final Review review = new Review.ReviewBuilder()
      .instant(Instant.now())
      .review(id)
      .author(candidate.getAuthor())
      .subject(candidate.getSubject())
      .header(candidate.getHeader())
      .byline(candidate.getByline())
      .body(candidate.getBody())
      .link(candidate.getLink())
      .score(candidate.getScore())
      .tag(candidate.getTag())
      .build();
    reviewRepository.save(review);
    return review.getReview();
  }
  
}
