package kr.co.ict.yourdream.userdash;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDashService {

    @Autowired
    private UserDashRepository userDashRepository;


    public long getTotalMembers(){
        return userDashRepository.countTotalMembers();
    }
    
    public long getDailyVisitors() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = calendar.getTime();
        return userDashRepository.countDailyVisitors(today);
    }

    public long getTotalIntnos() {
        return userDashRepository.countTotalIntnos();
    }

    public long getTotalCount() {
        return userDashRepository.countTotal();
    }
    

    public List<DailyMemberCount> getDailyMemberCounts(Date startDate, Date endDate) {
    // Set endDate to the end of the day
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(endDate);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    Date endOfDay = calendar.getTime();

    return userDashRepository.findDailyCounts(startDate, endOfDay);
}


public List<Map<String, Object>> getMemberCountByCategcd() {
    return userDashRepository.findMemberCountByCategcd();
}
}
