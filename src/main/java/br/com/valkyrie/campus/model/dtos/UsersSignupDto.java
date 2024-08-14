package br.com.valkyrie.campus.model.dtos;

import br.com.valkyrie.campus.model.enums.UsersRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsersSignupDto {

    private String fullName;
    private String usertag;
    private String email;
    private String password;
    private UsersRole role;
    private Date createdDate;
    private Date lastUpdatedDate;
}
