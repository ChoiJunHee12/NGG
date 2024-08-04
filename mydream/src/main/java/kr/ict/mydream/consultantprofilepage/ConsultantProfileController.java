package kr.ict.mydream.consultantprofilepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultantprofile")
public class ConsultantProfileController {

    @Autowired
    private ConsultantProfileService profileService;

    @GetMapping("/{id}/profile")
    public ResponseEntity<ConsultantProfileDTO> getProfile(@PathVariable Long id) {
        ConsultantProfileDTO profile = profileService.getProfile();
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<ConsultantProfileDTO> updateProfile(@PathVariable Long id,
            @RequestBody ConsultantProfileDTO profileDTO) {
        ConsultantProfileDTO updatedProfile = profileService.updateProfile(profileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}/education")
    public ResponseEntity<List<ConsultantEducationDTO>> getEducation(@PathVariable Long id) {
        List<ConsultantEducationDTO> educationList = profileService.getEducation(id);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/{id}/career")
    public ResponseEntity<List<ConsultantCareerDTO>> getCareer(@PathVariable Long id) {
        List<ConsultantCareerDTO> careerList = profileService.getCareer(id);
        return new ResponseEntity<>(careerList, HttpStatus.OK);
    }
}
