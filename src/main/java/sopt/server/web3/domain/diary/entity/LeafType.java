package sopt.server.web3.domain.diary.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeafType {
  FAITH("믿음"),
  HOPE("희망"),
  LOVE("사랑");

  private final String description;
}
