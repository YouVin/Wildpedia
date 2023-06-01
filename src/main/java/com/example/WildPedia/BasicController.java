package com.example.WildPedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    @Autowired
    private WildpediaService service;

    @Autowired
    private AnimalInformationService animalInformationService;

    // 홈 페이지 요청 처리
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // 동물 목록 페이지 요청 처리
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("animals", service.select());
        return "list";
    }

    // 동물 상세 페이지 요청 처리
    @RequestMapping("/read/{idx}")
    public  String read(@PathVariable int idx, Model model) {
        model.addAttribute("animal", service.read(idx));
        return "read.html";
    }

    // 동물 삭제 요청 처리
    @RequestMapping("/delete/{idx}")
    public  String delete(@PathVariable int idx) {
        service.delete(idx);
        return "redirect:/list";
    }

    // 동물 등록 폼 요청 처리
    @RequestMapping("/insertForm")
    public  String insertForm() {
        return "insertForm";
    }

    // 동물 등록 요청 처리
    @RequestMapping("/insert")
    public  String insert(AnimalDto animal) {
        boolean success = service.insert(animal);
        if (success) {
            return "redirect:/list";
        } else {
            return "redirect:/bad-word-page"; // 욕설 경고 페이지를 표시하는 원하는 URL로 변경
        }
    }

    // 동물 수정 폼 요청 처리
    @RequestMapping("/updateForm/{idx}")
    public  String updateForm(@PathVariable int idx, Model model) {
        model.addAttribute("animal", service.read(idx));
        return "updateForm";
    }

    // 동물 수정 요청 처리
    @RequestMapping("/update")
    public  String update(AnimalDto animal) {
        boolean success = service.update(animal);
        if (success) {
            return "redirect:/read/" + animal.getIdx();
        } else {
            return "redirect:/bad-word-page"; // 욕설 경고 페이지를 표시하는 원하는 URL로 변경
        }
    }

    // 욕설 경고 페이지 요청 처리
    @GetMapping("/bad-word-page")
    public String handleBadWordPage() {
        return "bad-word-page"; // 이동할 페이지의 뷰 이름으로 변경해주세요
    }

    // 동물 정보 페이지 요청 처리
    @RequestMapping("/AnimalInformation")
    public String AnimalInformation(Model model) {
        model.addAttribute("animals", animalInformationService.select());
        return  "AnimalInformation";
    }

    // 동물 정보 상세 페이지 요청 처리
    @RequestMapping("/AnimalInformationDetail/{idx}")
    public  String AnimalInformationDetail(@PathVariable int idx, Model model) {
        model.addAttribute("animal", animalInformationService.read(idx));
        return "AnimalInformationDetail";
    }

    // 동물 정보 삭제 요청 처리
    @RequestMapping("/AnimalInformation/delete/{idx}")
    public  String Informationdelete(@PathVariable int idx) {
        animalInformationService.delete(idx);
        return "redirect:/AnimalInformation";
    }

    // 동물 정보 등록 폼 요청 처리
    @RequestMapping("/AnimalInformation/insertForm")
    public  String InformationinsertForm() {
        return "InformationinsertForm";
    }

    // 동물 정보 등록 요청 처리
    @RequestMapping("/AnimalInformation/insert")
    public  String Informationinsert(AnimalInformationDto animalInformationDto) {
        boolean success = animalInformationService.Animalinsert(animalInformationDto);
        if (success) {
            return "redirect:/AnimalInformation";
        } else {
            return "redirect:/bad-word-page"; // 욕설 경고 페이지를 표시하는 원하는 URL로 변경
        }
    }

    // 동물 정보 수정 폼 요청 처리
    @RequestMapping("/AnimalInformation/updateForm/{idx}")
    public  String InformationupdateForm(@PathVariable int idx, Model model) {
        model.addAttribute("animal", animalInformationService.read(idx));
        return "InformationupdateForm";
    }

    // 동물 정보 수정 요청 처리
    @RequestMapping("/AnimalInformation/update")
    public  String Informationupdate(AnimalInformationDto animalInformationDto) {
        boolean success = animalInformationService.Animalupdate(animalInformationDto);
        if (success) {
            return "redirect:/AnimalInformationDetail/" + animalInformationDto.getIdx();
        } else {
            return "redirect:/bad-word-page"; // 욕설 경고 페이지를 표시하는 원하는 URL로 변경
        }
    }
}
