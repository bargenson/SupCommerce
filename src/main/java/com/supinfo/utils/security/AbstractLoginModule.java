package com.supinfo.utils.security;

import java.io.IOException;
import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public abstract class AbstractLoginModule implements LoginModule {

	private CallbackHandler callbackHandler;
	private Subject subject;
	private boolean authenticated;
	private UserInfo currentUser;
	private boolean commited;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		try {  
			if (callbackHandler == null)
				throw new LoginException ("No callback handler");

			Callback[] callbacks = buildCallbacks();
			callbackHandler.handle(callbacks);

			String webUsername = ((NameCallback)callbacks[0]).getName();
			char[] webPassword = ((PasswordCallback)callbacks[1]).getPassword();

			if ((webUsername == null) || (webPassword == null)) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			UserInfo userInfo = getUserInfo(webUsername);

			if (userInfo == null) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			currentUser = userInfo;
			
			setAuthenticated(currentUser.checkPassword(webPassword));
			return isAuthenticated();
		} catch (IOException e) {
			throw new LoginException (e.toString());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException (e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginException (e.toString());
		}
	}

	protected abstract UserInfo getUserInfo (String username) throws Exception;

	private Callback[] buildCallbacks () {
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Username");
		callbacks[1] = new PasswordCallback("Password", false);
		return callbacks;
	}

	private void setAuthenticated (boolean authState) {
		this.authenticated = authState;
	}

	private boolean isAuthenticated () {
		return authenticated;
	}

	@Override
	public boolean commit() throws LoginException {
		if (!isAuthenticated()) {
            currentUser = null;
            setCommitted(false);
            return false;
        }
        
        setCommitted(true);
		
		subject.getPrincipals().add(new GenericPrincipal(currentUser.getUsername())); //TODO
		subject.getPrincipals().add(getRoles());
		
		return true;
	}

	private Group getRoles() {
		return new GenericGroup("Roles", currentUser.getRoleNames());
	}
	
	private void setCommitted(boolean b) {
		this.commited = b;
	}

	@Override
	public boolean abort() throws LoginException {
		this.currentUser = null;
        return (isAuthenticated() && isCommitted());
	}

	private boolean isCommitted() {
		return commited;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(new GenericPrincipal(currentUser.getUsername()));
		subject.getPrincipals().remove(getRoles());
		currentUser = null;
		return true;
	}

}
