package com.example.WildPedia;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnimalInformationDto {

    private int idx;
    private String name;
    private String title;
    private String content;
    private String date;

}
