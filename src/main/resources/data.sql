-- API 테스트를 위한 초기 데이터

-- 1. User 데이터 (userId = 1)
INSERT INTO users (user_id, created_at, updated_at)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. 테스트용 Diary 데이터
INSERT INTO diaries (diary_id, user_id, leaf_type, title, original_content, created_at)
VALUES
    (1, 1, 'FAITH', '믿음의 하루', '오늘은 힘든 일이 있었지만, 포기하지 않고 끝까지 해냈다.', '2024-01-15 10:30:00'),
    (2, 1, 'LOVE', '사랑의 순간', '친구가 힘들어할 때 곁에서 함께 있어주었다.', '2024-01-16 14:20:00'),
    (3, 1, 'HOPE', '희망의 빛', '어려운 상황이지만 내일은 더 나아질 거라 믿는다.', '2024-01-17 09:15:00');

-- 3. 테스트용 Saga 데이터
INSERT INTO sagas (saga_id, diary_id, saga_content, created_at, updated_at)
VALUES
    (1, 1, '옛날 어느 마을에 한 농부가 살았습니다. 어느 날 큰 폭풍이 마을을 덮쳤지만, 농부는 믿음을 잃지 않았습니다. 그는 매일 밭을 돌보았고, 마침내 풍성한 수확을 거두었습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, '어느 작은 숲속에 두 마리의 새가 살고 있었습니다. 한 새가 다쳤을 때, 다른 새는 먹이를 물어다 주며 간호했습니다. 그들의 우정은 숲 전체에 사랑의 빛을 비췄습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, '깊은 산속 동굴에 작은 씨앗 하나가 떨어졌습니다. 어둠 속에서도 씨앗은 희망을 품고 자라났고, 마침내 동굴 밖으로 나와 아름다운 꽃을 피웠습니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
