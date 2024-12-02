package mrp_simulator.api.services.delete;

import jakarta.transaction.Transactional;
import mrp_simulator.api.dtos.auth.DTOAuth;
import mrp_simulator.api.models.Material;
import mrp_simulator.api.repositories.InforecordRepository;
import mrp_simulator.api.repositories.InventoryRepository;
import mrp_simulator.api.repositories.MaterialRepository;
import mrp_simulator.api.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CleanDatabaseSignOut {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    InforecordRepository inforecordRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void deleteUserData(@AuthenticationPrincipal Jwt jwt){

        DTOAuth user = new DTOAuth(jwt);

        Optional<Material> userMaterialOpt = materialRepository.findByUsername(user.userName());

        if(userMaterialOpt.isEmpty()){
            return;
        }

        Material userMaterial = userMaterialOpt.get();

        purchaseOrderRepository.deleteByMaterial(userMaterial);
        inventoryRepository.deleteByMaterial(userMaterial);
        inforecordRepository.deleteByMaterialCode(userMaterial.getMaterialCode());
        materialRepository.delete(userMaterial);

    }

}
