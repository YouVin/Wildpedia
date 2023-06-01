package com.example.WildPedia;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomBadWordFilter {
    private List<String> badWords;

    public CustomBadWordFilter() {
        loadBadWords();
    }

    // 비속어 목록을 로드하는 메서드
    private void loadBadWords() {
        try {
            // badwords.txt 파일을 classpath에서 찾아옴
            Resource resource = new ClassPathResource("badwords.txt");
            File file = resource.getFile();
            // 파일의 모든 줄을 읽어와서 badWords 리스트에 저장
            badWords = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            // 파일 읽기 실패 시 예외 처리
            e.printStackTrace();
        }
    }

    // 주어진 내용에 비속어가 포함되어 있는지 확인하는 메서드
    public boolean hasBadWord(String content) {
        for (String word : badWords) {
            if (content.contains(word)) {
                System.out.println("비속어가 포함되었습니다: " + word);
                return true;
            }
        }
        return false;
    }
}
