package canis.review;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

// JPA
@Entity
public class Review {

  public enum Status {
    SUBMITTED,
    UPDATED,
    PENDING,
    PUBLISHED,
    SUSPENDED,
    WITHDRAWN
  }

  public enum ReferenceType {
    INVALID,
    IMAGE,
    EMBED_HTML
  }

  @Id
  private final String reviewId;
  private final String subjectId;
  private final String reviewerId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  private final Instant created;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  private final Instant updated;

  @Enumerated(EnumType.STRING)
  private final Status status;

  @Column(length=1024)
  private final String header;

  @Column(length=1024)
  private final String byline;

  @Column(length=65536)
  private final String copy;

  @Enumerated(EnumType.STRING)
  private final ReferenceType referenceType;

  @Column(length=66536)
  private final String reference;

  private final Double rating;

  public Review() {
    reviewId = null;
    subjectId = null;
    reviewerId = null;
    created = null;
    updated = null;
    status = null;
    header = null;
    byline = null;
    copy = null;
    referenceType = null;
    reference = null;
    rating = null;
  }
  public Review(
    String reviewId,
    String subjectId,
    String reviewerId,
    Instant created,
    Instant updated,
    Status status,
    String header,
    String byline,
    String copy,
    ReferenceType referenceType,
    String reference,
    Double rating
  ) {
    this.reviewId = reviewId;
    this.subjectId = subjectId;
    this.reviewerId = reviewerId;
    this.created = created;
    this.updated = updated;
    this.status = status;
    this.header = header;
    this.byline = byline;
    this.copy = copy;
    this.referenceType = referenceType;
    this.reference = reference;
    this.rating = rating;
  }
  private Review(Builder builder) {
    reviewId = builder.reviewId;
    subjectId = builder.subjectId;
    reviewerId = builder.reviewerId;
    created = builder.created;
    updated = builder.updated;
    status = builder.status;
    header = builder.header;
    byline = builder.byline;
    copy = builder.copy;
    referenceType = builder.referenceType;
    reference = builder.reference;
    rating = builder.rating;
  }

  public String getReviewId() {
    return reviewId;
  }
  public String getSubjectId() {
    return subjectId;
  }
  public String getReviewerId() {
    return reviewerId;
  }
  public Instant getCreated() {
    return created;
  }
  public Instant getUpdated() {
    return updated;
  }
  public Status getStatus() {
    return status;
  }
  public String getHeader() {
    return header;
  }
  public String getByline() {
    return byline;
  }
  public String getCopy() {
    return copy;
  }
  public ReferenceType getReferenceType() {
    return referenceType;
  }
  public String getReference() {
    return reference;
  }
  public Double getRating() {
    return rating;
  }
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String reviewId;
    private String subjectId;
    private String reviewerId;
    private Instant created;
    private Instant updated;
    private Status status;
    private String header;
    private String byline;
    private String copy;
    private ReferenceType referenceType;
    private String reference;
    private Double rating;
    
    public Builder reviewId(String reviewId) {
      this.reviewId = reviewId;
      return this;
    }
    public Builder subjectId(String subjectId) {
      this.subjectId = subjectId;
      return this;
    }
    public Builder reviewerId(String reviewerId) {
      this.reviewerId = reviewerId;
      return this;
    }
    public Builder created(Instant created) {
      this.created = created;
      return this;
    }
    public Builder updated(Instant updated) {
      this.updated = updated;
      return this;
    }
    public Builder status(Status status) {
      this.status = status;
      return this;
    }
    public Builder header(String header) {
      this.header = header;
      return this;
    }
    public Builder byline(String byline) {
      this.byline = byline;
      return this;
    }
    public Builder copy(String copy) {
      this.copy = copy;
      return this;
    }
    public Builder referenceType(ReferenceType referenceType) {
      this.referenceType = referenceType;
      return this;
    }
    public Builder reference(String reference) {
      this.reference = reference;
      return this;
    }
    public Builder rating(Double rating) {
      this.rating = rating;
      return this;
    }
    public Review build() {
      return new Review(this);
    }
  }       
}