package kr.ict.mydream.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewReportService interviewReportService;

    @Autowired
    public InterviewController(InterviewReportService interviewReportService) {
        this.interviewReportService = interviewReportService;
    }

    @GetMapping
    public InterviewReportDTO getInterviewReport(@RequestParam(name = "id") String id) {
        return interviewReportService.getInterviewReport(id);
    }
}
