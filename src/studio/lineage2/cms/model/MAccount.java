package studio.lineage2.cms.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "master_account")
@Data
public class MAccount implements UserDetails {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private long id;

  @NotEmpty(message = "Заполните поле")
  @Email(message = "Неверный формат E-mail")
  @Column(name = "username")
  private String username;

  @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
  @Column(name = "password")
  private String password;

  @Transient
  private String repeatPassword;

  @Column(name = "admin")
  boolean admin = false;

  @Column(name = "vote_name")
  private String voteName;

  @Column(name = "forum_user_id")
  private String forumUserId;

  @Column(name = "wheel_ticket", columnDefinition = "int default 0")
  private int wheelTicket;

  @Transient
  private ReentrantLock items = new ReentrantLock();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (isAdmin()) {
      return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    }
    return AuthorityUtils.createAuthorityList("ROLE_USER");
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
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
