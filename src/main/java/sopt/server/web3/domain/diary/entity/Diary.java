package sopt.server.web3.domain.diary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.server.web3.domain.user.entity.User;

@Entity
@Getter
@Table(name = "diaries")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "diary_id")
  private Long diaryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "leaf_type", nullable = false)
  private LeafType leafType;

  @Column(name = "original_content", nullable = false)
  private String originalContent;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Builder
  private Diary(User user, LeafType leafType, String originalContent, String title,
      LocalDateTime createdAt) {
    this.user = user;
    this.leafType = leafType;
    this.originalContent = originalContent;
    this.title = title;
    this.createdAt = createdAt;
  }
}
