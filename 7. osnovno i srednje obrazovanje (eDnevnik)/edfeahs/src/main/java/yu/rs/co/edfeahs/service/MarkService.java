package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.rs.co.edfeahs.model.Attendance;
import yu.rs.co.edfeahs.model.Mark;
import yu.rs.co.edfeahs.repository.AttendanceRepository;
import yu.rs.co.edfeahs.repository.MarkRepository;
import yu.rs.co.edfeahs.web.dto.MarkSearchParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final AttendanceRepository attendanceRepository;


    public Mark saveMark(Mark mark) {

        Mark persistedMark = markRepository.save(mark);
        return persistedMark;
    }


    public List<Mark> findMarks(MarkSearchParam searchParam) {
        Attendance attendance = attendanceRepository.
                findAttendanceByStudentIdAndSubjectId(
                        searchParam.getStudentId(),
                        searchParam.getSubjectId()
                );
        List<Mark> result =
                attendance.getMarks().stream().filter(
                        mark -> mark.getSemester().equals(
                                searchParam.getSemester()
                        )
                ).collect(Collectors.toList());

        Collections.sort(result, Comparator.comparing(Mark::getDate));
        Collections.reverse(result);

        return result;
    }


}
