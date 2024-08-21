package br.com.valkyrie.campus.model.dtos.responses;

import br.com.valkyrie.campus.model.enums.UsersRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponseDto {
    private String fullName;
    private String usertag;
    private UsersRole role;
    private String createdDate;
    private String lastUpdatedDate;
}
