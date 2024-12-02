package mrp_simulator.api.controller;

import mrp_simulator.api.services.delete.CleanDatabaseSignOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    private CleanDatabaseSignOut cleanDatabaseSignOut;

    @DeleteMapping
    public ResponseEntity<String> deleteUserData(@RequestParam  String username){
        try {
            cleanDatabaseSignOut.deleteUserData(username);
            return ResponseEntity.status(HttpStatus.OK).body("User's data deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error deleting user data: " + e.getMessage());
        }
    }

}
