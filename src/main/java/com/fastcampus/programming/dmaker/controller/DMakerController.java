package com.fastcampus.programming.dmaker.controller;

import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.dto.EditDeveloper;
import com.fastcampus.programming.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService; // 서비스라는 디펜던시 의존하는(사용하는) 컨트롤러가 동작하기 위해선 서비스가 존재해야함 정의만 하면 동작을 하는게 안되지만
    // 생성자만 만들어 놓으면 알아서 컨트롤러를 만들어줌, 이것이 DI + IoC임, 누군가가 만들어 놓은 서비스만 넣으면 동작이 된다.
    // Spring 의 application context가 여러개의 bean들을 등록해있다가 컨트롤러가 필요한 서비스를 스프링이 알아서 만들어준다.

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
      log.info("GET / developers HTTP/1.1");

      return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(@PathVariable final String memberId) {
        return dMakerService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(@Valid @RequestBody final CreateDeveloper.Request request) {
        log.info("request : {}", request);

        return dMakerService.createDeveloper(request);
    }
    
    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(@PathVariable final String memberId, @Valid @RequestBody final EditDeveloper.Request request) {
        return dMakerService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(@PathVariable final String memberId) {
        return dMakerService.deleteDeveloper(memberId);
    }
}
