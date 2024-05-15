package com.tunemerge.tunemerge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunemerge.tunemerge.Entity.AccessToken;

public interface AccesstokenRepositry extends JpaRepository<AccessToken, Long>{
    
    
}
