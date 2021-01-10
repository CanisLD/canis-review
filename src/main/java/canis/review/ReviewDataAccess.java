package canis.review;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.TaskExecutorBuilder;
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

  @Autowired
  private RatingRepository ratingRepository;

  public Page<Review> findBySubjectId(String subjectId, Set<String> tag, int page, int pageSize) {
    final Pageable paging = PageRequest.of(page, pageSize, Sort.by("updated").descending());
    return (tag == null || tag.isEmpty())
      ? reviewRepository.findBySubjectId(subjectId, paging)
      : reviewRepository.findBySubjectIdAndTagIn(subjectId, tag, paging);
  }

  public Optional<Review> findById(String reviewId) {
    return reviewRepository.findById(reviewId);
  }

  @Transactional
  public String save(Review candidate) {
    final Instant now = Instant.now();
    final String reviewId = Optional.ofNullable(candidate.getReviewId()).orElseGet(() -> UUID.randomUUID().toString());
    final Instant created = Optional.ofNullable(candidate.getCreated()).orElseGet(() -> now);
    final Status status = Optional.ofNullable(candidate.getStatus()).orElseGet(() -> Status.SUBMITTED);

    final Set<Media> mediaCandidate = candidate.getMedia();
    final Set<Media> mediaSubmission = (mediaCandidate != null && !mediaCandidate.isEmpty())
      ? mediaCandidate.stream()
          .filter(Objects::nonNull)
          .map(entry -> Media.builder()
                          .type(entry.getType())
                          .reference(entry.getReference())
                          .caption(entry.getCaption())
                          .build())
          .collect(Collectors.toUnmodifiableSet())
      : Collections.emptySet();

    final Set<Rating> ratingCandidate = candidate.getRating();
    final Set<Rating> ratingSubmission = (ratingCandidate != null && !ratingCandidate.isEmpty())
      ? ratingCandidate.stream()
          .filter(Objects::nonNull)
          .map(entry -> Rating.builder()
                              .topic(entry.getTopic())
                              .rating(entry.getRating())
                              .build())
          .collect(Collectors.toUnmodifiableSet())
      : Collections.emptySet();

    final Review submission = Review.builder()
      .reviewId(reviewId)
      .subjectId(candidate.getSubjectId())
      .reviewerId(candidate.getReviewerId())
      .created(created)
      .updated(now)
      .status(status)
      .header(candidate.getHeader())
      .byline(candidate.getByline())
      .body(candidate.getBody())
      .media(mediaSubmission)
      .rating(ratingSubmission)
      .tag(candidate.getTag())
      .build();
    reviewRepository.save(submission);

    return submission.getReviewId();
  }
}
