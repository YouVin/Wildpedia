package com.example.WildPedia;




import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalInformationImpl implements AnimalInformationService {
    private ArrayList<AnimalInformationDto> db = new ArrayList<>();

    private CustomBadWordFilter badWordFilter;

    public  ArrayList<String> titles = new ArrayList<>(
            List.of("꽃과 산책",
                    "산 속 피크닉",
                    "새로운 동포",
                    "행복한 주말",
                    "서점의 모험",
                    "해변의 힐링"
            )
    );
    public  ArrayList<String> contents = new ArrayList<>(
            List.of("우리 집 고양이가 귀여운 모습으로 산책을 하던 중, 꽃냄새에 푹 빠져버렸어요. 너무 사랑스럽게 꽃들을 냄새 맡고 다니는 걸 보니 행복하네요!",
                    "반려견과 함께 산 속에서 피크닉을 즐겼어요. 우리 반려견이 자유롭게 뛰어다니며 몸을 힐링시켜 주었고, 푸른 하늘 아래서 신선한 공기를 마셨어요.",
                    "새로운 반려동물을 키우기 시작했는데, 어색한 시간들도 있지만 서로 조금씩 적응하고 있어요. 앞으로 우리의 소중한 추억이 쌓일 것 같아 기대돼요!",
                    "반려동물과 함께 마음 편한 주말을 보내고 있어요. 동물들의 사랑과 행복이 주는 에너지는 정말 대단하죠. 우리 모두 함께 행복한 시간을 보내요!",
                    "오늘은 반려동물과 함께 서점에 갔어요. 책을 고르는 동안 반려동물은 나랑 함께 책장을 둘러보고 냄새를 맡으며 즐거워했어요. 책에 푹 빠져서 시간 가는 줄 몰랐어요.",
                    "오늘은 반려동물과 함께 해변을 방문했어요. 파도 소리를 들으며 모래 위에서 뛰어놀고 바다 물결에 발을 담그니 기분이 정말 좋았어요. 이런 순간들이 진정한 힐링이네요!"
                    )
    );

    public AnimalInformationImpl(){
        System.out.println("AnimalInterfaceImpl 객체 생성");
        db.add(new AnimalInformationDto(0, "이름1", titles.get(0), contents.get(0), "2023.01.28"));
        db.add(new AnimalInformationDto(1, "이름2", titles.get(1),  contents.get(1), "2023.02.28"));
        db.add(new AnimalInformationDto(2, "이름3", titles.get(2), contents.get(2), "2023.03.28"));
        db.add(new AnimalInformationDto(3, "이름4", titles.get(3),  contents.get(3), "2023.04.28"));
        db.add(new AnimalInformationDto(4, "이름5", titles.get(4), contents.get(4), "2023.05.28"));
        db.add(new AnimalInformationDto(5, "이름6", titles.get(5), contents.get(5), "2023.06.28"));
        badWordFilter = new CustomBadWordFilter();
    }

    @Override
    public ArrayList<AnimalInformationDto> select() {
        return db;
    }

    @Override
    public AnimalInformationDto read(int idx) {
        AnimalInformationDto ret = db.stream().filter(m -> m.getIdx() == idx).findAny().get();
        return ret;
    }

    @Override
    public boolean delete(int idx) {
        db.remove(db.stream().filter(m -> m.getIdx() == idx).findAny().get());
        return true;
    }

    @Override
    public boolean Animalinsert(AnimalInformationDto dto) {
        if (
                badWordFilter.hasBadWord(Integer.toString(dto.getIdx()))||
                        badWordFilter.hasBadWord(dto.getName())||
                        badWordFilter.hasBadWord(dto.getTitle()) ||
                        badWordFilter.hasBadWord(dto.getContent()) ||
                        badWordFilter.hasBadWord(dto.getDate()))
        {
            return false;
        }
        db.add(dto);
        return true;
    }

    @Override
    public boolean Animalupdate(AnimalInformationDto dto) {


        AnimalInformationDto temp = db.stream().filter(m -> m.getIdx() == dto.getIdx()).findAny().get();
        String newName = dto.getName();
        String newTitle = dto.getTitle();
        String newContent = dto.getContent();
        String newDate = dto.getDate();
        if (
                badWordFilter.hasBadWord(newName) ||
                        badWordFilter.hasBadWord(newTitle) ||
                        badWordFilter.hasBadWord(newContent) ||
                        badWordFilter.hasBadWord(newDate)) {
            return false;
        }
        temp.setName(newName);
        temp.setTitle(newTitle);
        temp.setContent(newContent);
        temp.setDate(newDate);
        return true;
    }
}