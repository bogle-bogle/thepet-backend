package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    List<ProductVO> findProductsByCategoryAndKeyword(Map<String, String> params);

    Integer saveGeneralProduct(ProductVO product);

    List<ProductVO> selectProducts(int page);

    Optional<ProductVO> selectProductDetail(String id);

    Optional<ProductVO> findProductById(String id);

    List<ProductVO> findProductsByPetInfoAndOrderLog(PetVO petVO);

    List<ProductVO> findProductsByMbti(String mbtiType);

    List<ProductVO> filterProduct(FilterVO filterVO);

    Integer selectProductCount(FilterVO filterVO);

    List<ProductVO> findProductsByFavoriteProteinCode(String favoriteProtein);

    List<ProductVO> findProductsByAgeCode(String ageCmCode);


}