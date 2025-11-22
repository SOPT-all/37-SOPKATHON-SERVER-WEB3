package sopt.server.web3.domain.openai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.openai.dto.request.ChatRequest;
import sopt.server.web3.domain.openai.dto.request.DiaryToStoryRequest;
import sopt.server.web3.domain.openai.dto.response.ChatResponse;
import sopt.server.web3.domain.openai.dto.response.DiaryToStoryResponse;
import sopt.server.web3.domain.openai.service.DiaryToStoryService;
import sopt.server.web3.domain.openai.service.SimpleAIResponseGenerator;

@Tag(name = "OpenAI", description = "OpenAI API 테스트")
@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAIController {

	private final SimpleAIResponseGenerator aiResponseGenerator;
	private final DiaryToStoryService diaryToStoryService;

	@Operation(summary = "채팅 메시지 전송", description = "OpenAI에 메시지를 전송하고 응답을 받습니다.")
	@PostMapping("/chat")
	public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
		String aiResponse = aiResponseGenerator.generateResponse(request.message());
		return ResponseEntity.ok(new ChatResponse(aiResponse));
	}

	@Operation(summary = "일기를 설화로 변환", description = "주제(Faith, Love, Hope)에 맞춰 일기 내용을 설화로 변환합니다.")
	@PostMapping("/diary-to-story")
	public ResponseEntity<DiaryToStoryResponse> convertDiaryToStory(@RequestBody DiaryToStoryRequest request) {
		String story = diaryToStoryService.convertDiaryToStory(request.theme(), request.diaryContent());
		return ResponseEntity.ok(new DiaryToStoryResponse(story));
	}
}
