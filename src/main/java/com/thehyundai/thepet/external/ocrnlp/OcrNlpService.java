package com.thehyundai.thepet.external.ocrnlp;

import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OcrNlpService {
    private final WebClient webClient;

//    @Value("${nlp.api.baseurl}")
    private String ocrNlpApiUrl = "https://ocr-nlp.thepet.thehyundai.site";
//    private String ocrNlpApiUrl = "http://localhost:8000";

    @Autowired
    public OcrNlpService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ocrNlpApiUrl)
                                         .build();
    }

    public Mono<OcrNlpResultVO> analyzeImageAndFetchProductList(ImgRequestVO imgRequestVO) {
        Mono<OcrNlpResultVO> result = webClient.post()
                                               .uri("/ai/img-to-similarity")
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .bodyValue(imgRequestVO)
                                               .retrieve()
                                               .bodyToMono(OcrNlpResultVO.class);
        return result;
    }

}
