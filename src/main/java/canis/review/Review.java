package canis.review;

import java.time.Instant;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// JPA
@Entity

// lombok builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {

    @Id
    private String review;
    private String subject;
    private String author;
    
    private Instant instant;

    @Column(length=1024)
    private String header;

    @Column(length=512)
    private String byline;

    @Column(length=65536)
    private String body;
    
    @Column(length=2048)
    private String link;

    private Float score;
    
    @ElementCollection
    @CollectionTable(name="tag", joinColumns=@JoinColumn(name="review"))
    private List<String> tag;
}