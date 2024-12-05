package mrp_simulator.api.controller;

import mrp_simulator.api.dtos.auth.DTOAuth;
import mrp_simulator.api.services.delete.CleanDatabaseSignOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/delete")
@CrossOrigin(origins = "https://posautomation.vercel.app")
public class DeleteController {

    @Autowired
    CleanDatabaseSignOut cleanDatabaseSignOut;


    @DeleteMapping
    public ResponseEntity<String> deleteUserData(@AuthenticationPrincipal Jwt jwt){

        DTOAuth user = new DTOAuth(jwt);

        try {
            cleanDatabaseSignOut.deleteUserData(jwt);
            return ResponseEntity.status(HttpStatus.OK).body("User's data deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error deleting user data: " + e.getMessage());
        }
    }

}
