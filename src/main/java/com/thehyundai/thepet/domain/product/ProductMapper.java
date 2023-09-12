package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface ProductMapper {

    List<ProductVO> selectProducts(int page);

    ProductVO selectProductDetail(String id);

    Optional<ProductVO> findProductById(String id);

    List<ProductVO> findProductsBySimplePetInfo(PetVO petVO);

    List<ProductVO> findProductsByPetInfoAndOrderLog(PetVO petVO);

    List<ProductVO> findProductsByMbti(String mbtiType);

    List<ProductVO> filterProduct(FilterVO filterVO);

    Integer selectProductCount(FilterVO filterVO);

    List<ProductVO> findProductsByFavoriteProteinCode(String favoriteProtein);

    List<ProductVO> findProductsByAgeCode(String ageCmCode);
}