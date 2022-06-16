package yu.rs.co.edfeahs.util.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yu.rs.co.edfeahs.model.Mark;
import yu.rs.co.edfeahs.model.enums.Semester;
import yu.rs.co.edfeahs.repository.AttendanceRepository;
import yu.rs.co.edfeahs.repository.MarkRepository;
import yu.rs.co.edfeahs.web.dto.CreateMarkDto;

@Component
@RequiredArgsConstructor
public class MarkMapper {

    private final AttendanceRepository attendanceRepository;

    public Mark toEntity(CreateMarkDto createMarkDto) {
        Mark mark = new Mark();

        mark.setAttendance(attendanceRepository.findAttendanceByStudentIdAndSubjectId(createMarkDto.getStudentId(), createMarkDto.getSubjectId()));
        mark.setValue(createMarkDto.getValue());
        mark.setSemester(createMarkDto.getSemester());

        return mark;
    }
}
