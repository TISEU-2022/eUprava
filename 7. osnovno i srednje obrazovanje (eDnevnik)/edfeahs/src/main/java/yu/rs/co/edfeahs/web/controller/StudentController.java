package yu.rs.co.edfeahs.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.util.converter.StudentMapper;
import yu.rs.co.edfeahs.web.dto.FoundStudentDto;
import yu.rs.co.edfeahs.web.dto.StudentSearchParam;
import yu.rs.co.edfeahs.service.StudentService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping(path = "{jmbg}/diploma/{tip_ustanove}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getDiploma(
            @PathVariable(name = "jmbg") String ucn,
            @PathVariable(name = "tip_ustanove") String institutionType) throws SQLException {
//    TODO: Remove - for testing
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://db:3306/edfeahs?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//
//        Connection connection = dataSource.getConnection();
//        connection.isValid(5);

        Pattern pattern = Pattern.compile("^([0-9]{13})$");
        if(!pattern.matcher(ucn).matches())
            return ResponseEntity.badRequest().build();

        if((ucn.length() == 13 && (institutionType.equals("osnovna") || institutionType.equals("srednja")))) {
            Map<String, Object> response = new HashMap<>();

            response.put("ime_ucenika", "Efraim");
            response.put("prezime_ucenika", "Kišon");
            response.put("jmbg", ucn);
            response.put("ime_ustanove", "Vasa Stajić");
            response.put("tip_ustanove", institutionType);
            response.put("prosek", Map.of("1", 3.00, "2", 3.00, "3", 3.00, "4", 3.00));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping
//    public ResponseEntity<List<Student>> findAll() {
//        List<Student> result = studentService.findAll();
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<FoundStudentDto>> findStudents(@Valid StudentSearchParam searchParam) {
        List<Student> students = studentService.findStudents(searchParam);
        System.out.println(students);
        List<FoundStudentDto> result = students.stream().map(student -> studentMapper.toFoundStudentDto(student, searchParam.getSubjectId())).collect(Collectors.toList());
        System.out.println("Hello World!!");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
