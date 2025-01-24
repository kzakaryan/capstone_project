package capstone_project.user;

import lombok.*;
import java.util.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    private List<String> enrolledCourses;

}
