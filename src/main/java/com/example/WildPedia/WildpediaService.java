package com.example.WildPedia;


import java.util.ArrayList;

public interface WildpediaService {
    public ArrayList<AnimalDto> select();
    public AnimalDto read(int idx);
    public boolean  delete(int idx);
    public boolean insert(AnimalDto animal);
    public boolean update(AnimalDto animal);
}
