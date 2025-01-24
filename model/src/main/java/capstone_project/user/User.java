package capstone_project.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;

}