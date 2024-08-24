package br.com.valkyrie.campus.model.dtos.responses;

import br.com.valkyrie.campus.model.enums.UsersRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsersResponseDto {
    private String fullName;
    private String usertag;
    private UsersRole role;
    private Date createdDate;
    private Date lastUpdatedDate;
}
