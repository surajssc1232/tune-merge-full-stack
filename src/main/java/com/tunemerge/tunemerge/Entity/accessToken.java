package com.tunemerge.tunemerge.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Data
@Entity
@Setter
public class AccessToken {

    @Id
    private String id;
    private String access_token;
    private String token_type;
    private String scope;
    private String expires_in;
    private String refresh_token;

}
