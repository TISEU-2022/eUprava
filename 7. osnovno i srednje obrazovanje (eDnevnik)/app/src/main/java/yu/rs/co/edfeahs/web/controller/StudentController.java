package yu.rs.co.edfeahs.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/ucenik")
public class StudentController {

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

}
