package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.repository.StudentRepository;
import yu.rs.co.edfeahs.web.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentService {

    private String URL = "http://localhost:4002/api/user/";

    private final StudentRepository studentRepository;


    public List<Student> findParentsStudents(String parentUCN) {
        PersonDto personDto = fetchPerson(parentUCN);
        List<Student> students = personDto.getChildren()
                .stream()
                .map(studentUCN ->
                        studentRepository.findStudentByUCN(studentUCN))
                .collect(Collectors.toList());
        return students;
    }

    private PersonDto fetchPerson(String pesonUNC) {
        WebClient webClient = WebClient.builder().baseUrl(URL + pesonUNC).build();
        return webClient
                .get()
                .retrieve()
                .bodyToMono(PersonDto.class).block();
    }


}
