package sopt.server.web3.domain.diary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "sagas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saga_id")
    private Long sagaId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false)
    private Diary diary;

    @Column(name = "saga_content", nullable = false, columnDefinition = "TEXT")
    private String sagaContent;

    @Builder
    private Saga(Diary diary, String sagaContent) {
        this.diary = diary;
        this.sagaContent = sagaContent;
    }
}
