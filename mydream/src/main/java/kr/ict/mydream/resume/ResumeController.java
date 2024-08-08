package kr.ict.mydream.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ict.mydream.vo.CareerVO;
import kr.ict.mydream.vo.EducationVO;
import kr.ict.mydream.vo.ResumeVO;
import kr.ict.mydream.vo.SelfIntroVO;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/resumeList")
    public ResponseEntity<?> resumeList(Model model, @RequestBody Map<String, Integer> num) {
        int memno = num.get("memno");
        List list = new ArrayList<ResumeVO>();
        list = resumeService.resumeList(memno);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/resumeDetail")
    public ResponseEntity<?> resumeDetail(@RequestParam("num") int num) {
        List detail = new ArrayList<Map<String, Object>>();
        detail.add(resumeService.resumeDetail(num));
        detail.add(resumeService.resumeDetailEdu(num));
        detail.add(resumeService.resumeDetailCar(num));
        detail.add(resumeService.resumeDetailSelf(num));
        return ResponseEntity.ok().body(detail);
    }

    @PostMapping("/resumeAdd")
    public void resumeAdd(@RequestBody Map<String, Object> data) {
        ResumeVO resumeVO = new ResumeVO();
        EducationVO educationVO = new EducationVO();
        CareerVO careerVO = new CareerVO();
        SelfIntroVO selfIntroVO = new SelfIntroVO();

        // 기본 인적사항 저장
        resumeVO = objectMapper.convertValue(data.get("basic"), ResumeVO.class);
        resumeVO.setMemno((int) data.get("memno"));
        resumeService.resumeAdd(resumeVO);

        // 학력사항 저장
        int rsmno = resumeVO.getRsmno();
        List<Map<String, Object>> education = (List<Map<String, Object>>) data.get("education");
        List<Map<String, Object>> education_res = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> e : education) {
            Map<String, Object> res = new HashMap<>(e);
            res.put("rsmno", rsmno);
            res.remove("td1");
            res.remove("td2");
            res.remove("td3");
            res.remove("td4");
            res.remove("td5");
            education_res.add(res);
        }
        for (Map<String, Object> e : education_res) {
            int seqno = (int) educationVO.getSeqno();
            e.put("seqno", seqno);
            resumeService.resumeAddEdu(e);
        }

        // 경력 저장
        List<Map<String, Object>> career = (List<Map<String, Object>>) data.get("career");
        List<Map<String, Object>> career_res = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> e : career) {
            Map<String, Object> res = new HashMap<>(e);
            res.put("rsmno", rsmno);
            res.remove("td1");
            res.remove("td2");
            res.remove("td3");
            res.remove("td4");
            res.remove("td5");
            career_res.add(res);
        }
        for (Map<String, Object> e : career_res) {
            int seqno = (int) careerVO.getSeqno();
            e.put("seqno", seqno);
            resumeService.resumeAddCar(e);
        }

        // 자소서 저장
        List<Map<String, Object>> selfintro = (List<Map<String, Object>>) data.get("intro");
        List<Map<String, Object>> selfintro_res = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> e : selfintro) {
            Map<String, Object> res = new HashMap<>(e);
            res.put("rsmno", rsmno);
            selfintro_res.add(res);
        }
        for (Map<String, Object> e : selfintro_res) {
            int seqno = (int) selfIntroVO.getSeqno();
            e.put("seqno", seqno);
            resumeService.resumeAddSelf(e);
            System.out.println("완료");
        }
    }

    @PostMapping("/resumeUpdate")
    public void resumeUpdate(@RequestParam("num") int num, @RequestBody Map<String, Object> data) {
        System.out.println("==========================================");
        System.out.println(data.get("basic"));
        System.out.println(data.get("education"));
        System.out.println(data.get("career"));
        System.out.println(data.get("intro"));
        System.out.println("==========================================");
        ResumeVO resumeVO = new ResumeVO();
        EducationVO educationVO = new EducationVO();
        CareerVO careerVO = new CareerVO();
        SelfIntroVO selfIntroVO = new SelfIntroVO();
        int rsmno = num;

        // 인적사항 수정
        resumeService.resumeUpdate((Map<String, Object>) data.get("basic"));

        // 학력 수정
        if (((List<Map<String, Object>>) data.get("education")).size() != 0) {
            List<Map<String, Object>> education = (List<Map<String, Object>>) data.get("education");
            for (Map<String, Object> e : education) {
                if (e.get("seqno") != null) {
                    resumeService.resumeUpdataEdu(e);
                } else {
                    Map<String, Object> education_add = new HashMap<>(e);
                    int seqno = (int) educationVO.getSeqno();
                    education_add.put("rsmno", rsmno);
                    education_add.put("seqno", seqno);
                    education_add.remove("td1");
                    education_add.remove("td2");
                    education_add.remove("td3");
                    education_add.remove("td4");
                    education_add.remove("td5");
                    System.out.println(education_add);

                    resumeService.resumeAddEdu(education_add);
                    System.out.println("학력 수정");
                }
            }
        } else {
            System.out.println("학력 변동사항 없음");
        }

        // 경력 수정
        if (((List<Map<String, Object>>) data.get("career")).size() != 0) {
            List<Map<String, Object>> career = (List<Map<String, Object>>) data.get("career");
            for (Map<String, Object> e : career) {
                if (e.get("seqno") != null) {
                    resumeService.resumeUpdataCar(e);
                } else {
                    Map<String, Object> career_add = new HashMap<>(e);
                    int seqno = (int) careerVO.getSeqno();
                    career_add.put("rsmno", rsmno);
                    career_add.put("seqno", seqno);
                    career_add.remove("td1");
                    career_add.remove("td2");
                    career_add.remove("td3");
                    career_add.remove("td4");
                    career_add.remove("td5");
                    System.out.println(career_add);

                    resumeService.resumeAddCar(career_add);
                    System.out.println("경력 수정");
                }
            }
        } else {
            System.out.println("경력 변동사항 없음");
        }

        // 자소서 수정
        {
            List<Map<String, Object>> selfintro = (List<Map<String, Object>>) data.get("intro");
            for (Map<String, Object> e : selfintro) {
                if (e.containsKey("content")) {
                    if (e.get("seqno") != null) {
                        resumeService.resumeUpdataSelf(e);
                    } else {
                        Map<String, Object> selfintro_add = new HashMap<>(e);
                        int seqno = (int) careerVO.getSeqno();
                        selfintro_add.put("rsmno", rsmno);
                        selfintro_add.put("seqno", seqno);
                        System.out.println(selfintro_add);

                        resumeService.resumeAddSelf(selfintro_add);
                        System.out.println("자소서 수정");
                    }
                } else {
                    System.out.println("자소서 변동사항 없음");
                }
            }
        }
    }

    @Transactional
    @PostMapping("/resumeDelete")
    public void resumeDelete(@RequestBody Map<String, Integer> data) {
        int rsmno = data.get("rsmno");
        resumeService.resumeDelEdu(rsmno);
        resumeService.resumeDelCar(rsmno);
        resumeService.resumeDelSelf(rsmno);
        resumeService.resumeDelete(rsmno);
    }

    @PostMapping("/resumeDeleteEdu")
    public void resumeDeleteEdu(@RequestBody Map<String, Integer> data) {
        System.out.println("seqno:" + data.get("seqno"));
        System.out.println("rsmno:" + data.get("rsmno"));
        resumeService.resumeDeleteEdu(data);
    }

    @PostMapping("/resumeDeleteCar")
    public void resumeDeleteCar(@RequestBody Map<String, Integer> data) {
        System.out.println("seqno:" + data.get("seqno"));
        System.out.println("rsmno:" + data.get("rsmno"));
        resumeService.resumeDeleteCar(data);
    }
}
