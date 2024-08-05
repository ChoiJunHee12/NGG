package kr.ict.mydream.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainpage")
public class InterviewController {

    private final InterviewReportService interviewReportService;

    @Autowired
    public InterviewController(InterviewReportService interviewReportService) {
        this.interviewReportService = interviewReportService;
    }

    @GetMapping
    public ResponseEntity<InterviewReportDTO> getInterviewReport(@RequestParam(name = "id") String id) {
        InterviewReportDTO report = interviewReportService.getInterviewReport(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}