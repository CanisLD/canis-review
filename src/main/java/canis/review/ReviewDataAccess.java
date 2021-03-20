package canis.review;

import java.time.Instant;
import java.util.Optional;
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

  public Page<Review> findBySubjectId(String subjectId, int page, int pageSize) {
    final Pageable paging = PageRequest.of(page, pageSize, Sort.by("updated").descending());
    return reviewRepository.findBySubjectIdAndStatus(subjectId, Review.Status.PUBLISHED, paging);
  }

  public Optional<Review> findById(String reviewId) {
    return reviewRepository.findById(reviewId);
  }

  @Transactional
  public Review save(Review candidate) {
    final Instant now = Instant.now();
    final String reviewId = Optional.ofNullable(candidate.getReviewId()).orElseGet(() -> UUID.randomUUID().toString());
    final Instant created = Optional.ofNullable(candidate.getCreated()).orElseGet(() -> now);
    final Review.Status status = Optional.ofNullable(candidate.getStatus()).orElseGet(() -> Review.Status.SUBMITTED);

    final Review submission = Review.builder()
      .reviewId(reviewId)
      .subjectId(candidate.getSubjectId())
      .reviewerId(candidate.getReviewerId())
      .created(created)
      .updated(now)
      .status(status)
      .header(candidate.getHeader())
      .byline(candidate.getByline())
      .copy(candidate.getCopy())
      .referenceType(candidate.getReferenceType())
      .reference(candidate.getReference())
      .rating(candidate.getRating())
      .build();
    reviewRepository.save(submission);

    return submission;
  }
}
