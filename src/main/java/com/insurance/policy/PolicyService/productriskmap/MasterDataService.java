package com.insurance.policy.PolicyService.productriskmap;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterDataService {

    public List<MasterData> getProducts(){

        return List.of(
                new MasterData("MTR","Motor Insurance"),
                new MasterData("COM","Commercial Insurance"),
                new MasterData("PRT","Property Insurance")
        );
    }

    public List<ProductRiskDTO> getProductRisk(){
        return List.of(
                new ProductRiskDTO(
                        "MTR",
                        List.of(
                                new MasterData("CAR", "Private Car"),
                                new MasterData("BIKE", "Two Wheeler"),
                                new MasterData("EV", "Electric Vehicle"),
                                new MasterData("TP", "Third Party Only")
                        )
                ),
                new ProductRiskDTO(
                        "PRT",
                        List.of(
                                new MasterData("BLD", "Building"),
                                new MasterData("STC", "Stock"),
                                new MasterData("FIRE", "Fire"),
                                new MasterData("FLOOD", "Flood"),
                                new MasterData("ETQ", "Earthquake"),
                                new MasterData("SHOP", "Shop")
                        )
                ),
                new ProductRiskDTO(
                        "COM",
                        List.of(
                                new MasterData("WRH", "Warehouse"),
                                new MasterData("FTY", "Factory"),
                                new MasterData("OFC", "Office"),
                                new MasterData("HTL", "Hotel/Restaurant")
                        )
                )
        );
    }
}
