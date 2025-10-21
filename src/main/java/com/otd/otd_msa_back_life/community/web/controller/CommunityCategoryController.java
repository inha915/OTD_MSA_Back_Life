package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.CommunityCategoryService;
import com.otd.otd_msa_back_life.community.web.dto.category.CategoryReq;
import com.otd.otd_msa_back_life.community.web.dto.category.CategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/OTD/community/categories")
public class CommunityCategoryController {

    private final CommunityCategoryService service;

    /** 카테고리 목록 */
    @GetMapping
    public List<CategoryRes> list() {
        return service.list();
    }

    /** 카테고리 생성 */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryRes create(@RequestBody CategoryReq req) {
        return service.create(req);
    }

    /** 카테고리 수정 */
    @PutMapping("/{id}")
    public CategoryRes update(@PathVariable Long id, @RequestBody CategoryReq req) {
        return service.update(id, req);
    }

    /** 카테고리 삭제 (참조 중이면 400/409로 에러 처리) */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
