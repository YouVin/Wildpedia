package com.example.WildPedia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public interface AnimalInformationService {

    public ArrayList<AnimalInformationDto> select();
    public AnimalInformationDto read(int idx);
    public boolean  delete(int idx);

    public boolean Animalinsert(AnimalInformationDto dto);

    public boolean Animalupdate(AnimalInformationDto dto);
}
