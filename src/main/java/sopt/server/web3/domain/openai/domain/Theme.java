package sopt.server.web3.domain.openai.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Theme {
	FAITH("믿음"),
	LOVE("사랑"),
	HOPE("희망");

	private final String description;
}
