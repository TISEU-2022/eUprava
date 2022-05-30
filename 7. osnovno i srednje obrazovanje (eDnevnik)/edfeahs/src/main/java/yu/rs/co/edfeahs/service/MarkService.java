package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.rs.co.edfeahs.model.Attendance;
import yu.rs.co.edfeahs.model.Mark;
import yu.rs.co.edfeahs.repository.AttendanceRepository;
import yu.rs.co.edfeahs.repository.MarkRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkService {

    private final MarkRepository markRepository;


    public Mark saveMark(Mark mark) {

        Mark persistedMark = markRepository.save(mark);
        return persistedMark;
    }




}
