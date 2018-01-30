package com.kongbig.dao;

import org.springframework.stereotype.Repository;

import com.kongbig.model.OAuthUser;

/**
 * 
 * @author kongbig
 *
 */
@Repository
public interface OAuthUserDao {

	OAuthUser getUserModel(String username, String password);

	OAuthUser getOAuthUserByName(String username);

	OAuthUser getOAuthUserById(String id);

	boolean addOAuthUser(OAuthUser oAuthUser);

	boolean updateOAuthUser(OAuthUser oAuthUser);

	boolean deleteOAuthUser(String[] ids);
	
}
