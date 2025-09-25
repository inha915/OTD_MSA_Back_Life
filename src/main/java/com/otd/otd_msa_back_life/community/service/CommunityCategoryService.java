package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.entity.CommunityCategory;
import com.otd.otd_msa_back_life.community.repository.CommunityCategoryRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.web.dto.category.CategoryReq;
import com.otd.otd_msa_back_life.community.web.dto.category.CategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityCategoryService {

    private final CommunityCategoryRepository categoryRepo;
    private final CommunityPostRepository postRepo;

    @Transactional(readOnly = true)
    public List<CategoryRes> list() {
        return categoryRepo.findAll()
                .stream()
                .map(CategoryRes::from)
                .toList();
    }

    public CategoryRes create(CategoryReq req) {
        validateReq(req);

        if (categoryRepo.existsByCategoryKey(req.getCategoryKey())) {
            throw new IllegalArgumentException("Duplicated categoryKey: " + req.getCategoryKey());
        }

        CommunityCategory saved = categoryRepo.save(
                CommunityCategory.builder()
                        .categoryKey(req.getCategoryKey())
                        .name(req.getName())
                        .build()
        );
        return CategoryRes.from(saved);
    }

    public CategoryRes update(Long id, CategoryReq req) {
        validateReq(req);

        CommunityCategory c = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        // key 변경 시 중복 검사
        if (!c.getCategoryKey().equals(req.getCategoryKey())
                && categoryRepo.existsByCategoryKey(req.getCategoryKey())) {
            throw new IllegalArgumentException("Duplicated categoryKey: " + req.getCategoryKey());
        }

        c.setCategoryKey(req.getCategoryKey());
        c.setName(req.getName());
        return CategoryRes.from(c); // dirty checking
    }

    public void delete(Long id) {
        long used = postRepo.countByCategory_CategoryId(id);
        if (used > 0) {
            throw new IllegalStateException("Category is in use by " + used + " posts");
        }
        categoryRepo.deleteById(id);
    }

    /** 게시글 서비스 등에서 key로 카테고리 강제 조회할 때 사용 */
    @Transactional(readOnly = true)
    public CommunityCategory mustGetByKey(String key) {
        return categoryRepo.findByCategoryKey(key)
                .orElseThrow(() -> new IllegalArgumentException("Invalid categoryKey: " + key));
    }

    private void validateReq(CategoryReq req) {
        if (req.getCategoryKey() == null || req.getCategoryKey().isBlank()) {
            throw new IllegalArgumentException("categoryKey is required");
        }
        if (req.getName() == null || req.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }
}
