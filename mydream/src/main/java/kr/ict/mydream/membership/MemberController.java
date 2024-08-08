package kr.ict.mydream.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ict.mydream.vo.MemberVO;



@RestController
@RequestMapping("/tbmember")
public class MemberController {

    @Autowired
    private MemberService tBmemberService;

    @PostMapping("/signup")
    public ResponseEntity<?> addMember(@RequestBody MemberVO vo){
        tBmemberService.tbmemberadd(vo);
        return ResponseEntity.ok().body(vo);
    }
    
}
