package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    // 상품 등록
    Integer saveGeneralProduct(ProductVO product);

    // 상품 조회
//    List<ProductVO> selectProducts(int page);
    Optional<ProductVO> selectProductDetail(String id);
    Optional<ProductVO> findProductById(String id);

    // 상품 검색
    List<ProductVO> findProductsByCategoryAndKeyword(Map<String, String> params);
    List<ProductVO> filterProduct(FilterVO filterVO);

    // 상품 페이지네이션
    Integer selectProductCount(FilterVO filterVO);

    // 상품 추천 - 개별 반려동물 맞춤
    List<ProductVO> findProductsByPetInfoAndOrderLog(PetVO petVO);
    List<ProductVO> findProductsByFavoriteProteinCode(String favoriteProtein);
    List<ProductVO> findProductsByAgeCode(String ageCmCode);

    // 상품 추천 - MBTI 맞춤
    List<ProductVO> findProductsByMbti(String mbtiType);
    List<ProductVO> findToyProductsByMbti(String mbtiType);
    List<ProductVO> findSuppliesByMbti(String mbtiType);

}