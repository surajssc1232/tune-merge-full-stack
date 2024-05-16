package com.tunemerge.tunemerge.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;




@Data
@Entity
public class AccessToken {

    @Id
    private Long id=1L;
    private String access_token;
    private String token_type;
    private String scope;
    private String expires_in;
    private String refresh_token;

}
