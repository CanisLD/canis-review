package canis.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// JPA
@Entity

// lombok builder
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private @Getter Long ratingId;
  // private @Getter String reviewId;
  private @Getter String topic;
  private @Getter Double rating;
}
