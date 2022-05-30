package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.repository.GradeAverageRepository;

@Service
@RequiredArgsConstructor
public class GradeAverageService {


    private final GradeAverageRepository gradeAverageRepository;
}
