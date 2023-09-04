package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    List<ProductVO> selectProducts(int page);

    ProductVO selectProductDetail(int id);

    Integer selectProductCount();

    Optional<ProductVO> findProductById(Integer id);

    List<ProductVO> findProductsBySimplePetInfo(PetVO petVO);

    List<ProductVO> findProductsByPetInfoAndOrderLog(PetVO petVO);

}