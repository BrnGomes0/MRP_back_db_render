package mrp_simulator.api.services.delete;

import jakarta.transaction.Transactional;
import mrp_simulator.api.models.Material;
import mrp_simulator.api.repositories.InforecordRepository;
import mrp_simulator.api.repositories.InventoryRepository;
import mrp_simulator.api.repositories.MaterialRepository;
import mrp_simulator.api.repositories.PurchaseOrderRepository;

import java.util.Optional;

public class CleanDatabaseSignOut {

    private MaterialRepository materialRepository;
    private InforecordRepository inforecordRepository;
    private InventoryRepository inventoryRepository;
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void deleteUserData(String username){
        Optional<Material> userMaterialOpt = materialRepository.findByUsername(username);

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
