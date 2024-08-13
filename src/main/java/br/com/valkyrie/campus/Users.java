package br.com.valkyrie.campus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private UsersRole role;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;
}
