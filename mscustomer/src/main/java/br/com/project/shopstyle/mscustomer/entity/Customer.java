package br.com.project.shopstyle.mscustomer.entity;

import br.com.project.shopstyle.mscustomer.constants.Genre;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Genre sex;
    private Date birthdate;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;
    @OneToMany
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private List<Address> addresses;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Profile> profiles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
