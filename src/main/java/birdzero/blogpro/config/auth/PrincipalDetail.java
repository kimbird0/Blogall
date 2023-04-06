package birdzero.blogpro.config.auth;

import birdzero.blogpro.dto.SessionUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetail implements UserDetails, OAuth2User {

    private SessionUserDto user;
    private Map<String,Object> attributes;
    public PrincipalDetail(SessionUserDto user){
        this.user = user;
    }

    public PrincipalDetail(SessionUserDto user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{
            return "ROLE_"+user.getRole();
        });
        return collectors;
    }

    @Override
    public Map<String, Object> getAttributes(){
        return attributes;
    }

    @Override
    public String getName(){
        return null;
    }

}
