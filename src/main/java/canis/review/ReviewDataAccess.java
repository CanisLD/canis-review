package canis.review;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewDataAccess {

  @Autowired
  private ReviewRepository reviewRepository;

  public Collection<Review> findBySubject(String subject) {
    return reviewRepository.findBySubject(subject);
  }

  public Optional<Review> findById(String review) {
    return reviewRepository.findById(review);
  }

  public String save(Review candidate) {
    final Review review = new Review.ReviewBuilder()
      .instant(Instant.now())
      .review(UUID.randomUUID().toString())
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
