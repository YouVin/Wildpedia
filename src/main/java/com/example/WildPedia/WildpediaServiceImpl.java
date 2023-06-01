package com.example.WildPedia;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class WildpediaServiceImpl implements WildpediaService {
    private ArrayList<AnimalDto> db = new ArrayList<>();
    private CustomBadWordFilter badWordFilter;
    public WildpediaServiceImpl() {
        System.out.println("WildpediaServiceImpl 객체 생성");
        db.add(new AnimalDto(1,  "animal1.jpg", "고양이귀여워", "고양이" ));
        db.add(new AnimalDto(2,  "animal2.jpg", "시고르자브종","강아지"));
        db.add(new AnimalDto(3,  "animal3.jpg", "사자는사자","사자"));
        db.add(new AnimalDto(4,  "animal4.jpg", "k-tiger","호랑이"));

        badWordFilter = new CustomBadWordFilter();
    }
    @Override
    public ArrayList<AnimalDto>  select() {
        return db;
    };
    @Override
    public AnimalDto read(int idx) {
        AnimalDto ret = db.stream().filter(m -> m.getIdx() == idx).findAny().get() ;
        return ret;
    }
    @Override
    public boolean delete(int idx) {
        db.remove(db.stream().filter(m -> m.getIdx() == idx).findAny().get());
        return true;
    }
    @Override
    public boolean insert(AnimalDto animal) {
        if (
                badWordFilter.hasBadWord(Integer.toString(animal.getIdx()))||
                badWordFilter.hasBadWord(animal.getImage()) ||
                badWordFilter.hasBadWord(animal.getContent()) ||
                badWordFilter.hasBadWord(animal.getName()))
        {
            return false;
        }
        db.add(animal);
        return true;
    }
    @Override
    public boolean update(AnimalDto animal) {

        AnimalDto temp = db.stream().filter(m -> m.getIdx() == animal.getIdx()).findAny().get();
        String newName = animal.getName();
        String newImage = animal.getImage();
        String newContent = animal.getContent();
        if (
                badWordFilter.hasBadWord(newName) ||
                        badWordFilter.hasBadWord(newImage) ||
                        badWordFilter.hasBadWord(newContent)) {
            return false;
        }
        temp.setName(newName);
        temp.setImage(newImage);
        temp.setContent(newContent);
        return true;
    }
}
