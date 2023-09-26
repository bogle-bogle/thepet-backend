package com.thehyundai.thepet.external.ocrnlp;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class OcrNlpService {
    private final WebClient webClient;

//    @Value("${nlp.api.baseurl}")
    private String ocrNlpApiUrl = "https://ocr-nlp.thepet.thehyundai.site";

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
