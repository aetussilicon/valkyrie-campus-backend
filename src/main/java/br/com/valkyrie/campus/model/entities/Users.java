package br.com.valkyrie.campus.model.entities;

import br.com.valkyrie.campus.model.enums.UsersRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
public class Users {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "full_name")
    private String fullName;
    private String usertag;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UsersRole role;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;
}
