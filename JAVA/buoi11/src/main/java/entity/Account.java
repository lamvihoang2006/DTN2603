package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int accountID;
    private String email;
    private String userName;
    private String fullName;
    private Department department;
    private Position position;
}