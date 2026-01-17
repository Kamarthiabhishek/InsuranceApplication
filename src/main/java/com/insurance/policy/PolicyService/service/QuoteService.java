package com.insurance.policy.PolicyService.service;

import com.insurance.policy.PolicyService.model.DTO.PremiumResponse;
import com.insurance.policy.PolicyService.model.DTO.QuoteRequest;
import com.insurance.policy.PolicyService.model.Quote;
import com.insurance.policy.PolicyService.repository.QuoteRepository;
import com.insurance.policy.PolicyService.utils.GenerateRandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private GenerateRandomNumber generateRandomNumber;

    @Autowired
    private RestTemplate restTemplate;


    public Quote createQuote(QuoteRequest request){


        Double netPremium =
                restTemplate.postForObject(
                        "http://localhost:8080/premium/calculate",
                        request,
                        Double.class
                );
        if(netPremium == null){
            throw new RuntimeException("Premium calculation went wrong");
        }
        int quoteNo = generateRandomNumber.generateNumber();

        Quote quote = Quote.builder()
                .quoteId(quoteNo)
                .productCode(request.getProductCode())
                .productVersion(request.getProductVersion())
                .idv(request.getIdv())
                .engineCC(request.getEngineCC())
                .vehicleAge(request.getVehicleAge())
                .fuelType(request.getFuelType())
                .vehicleType(request.getVehicleType())
                .cityCategory(request.getCityCategory())
                .noClaimsYear(request.getNoClaimsYear())
                .pricingType(request.getPricingType())
                .zeroDep(request.isZeroDep())
                .rsa(request.isRsa())
                .coverStartDate(request.getCoverStartDate())
                .coverEndDate(request.getCoverEndDate())
                .status("CREATED")
                .netPremium(netPremium)
                .build();

        Quote createdQuote = quoteRepository.save(quote);
        System.out.println("Created QUOTE : "+createdQuote);
        return createdQuote;
    }
}
