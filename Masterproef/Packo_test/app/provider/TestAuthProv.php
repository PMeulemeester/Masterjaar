<?php

namespace provider;
use Illuminate\Auth\GenericUser;
use Illuminate\Auth\UserInterface;
use Illuminate\Auth\UserProviderInterface;

class TestAuthProv implements UserProviderInterface
{
	public function __construct(){
    }
    public function retrieveById($id)
	  {


	    // 50% of the time, fail
	    return null;
	  }
	  public function retrieveByToken($identifier, $token)
	{
		return null;
	}

	/**
	 * Update the "remember me" token for the given user in storage.
	 *
	 * @param  \Illuminate\Auth\UserInterface  $user
	 * @param  string  $token
	 * @return void
	 */
	public function updateRememberToken(UserInterface $user, $token)
	{
		
	}

	/**
	 * Retrieve a user by the given credentials.
	 *
	 * @param  array  $credentials
	 * @return \Illuminate\Auth\UserInterface|null
	 */
	public function retrieveByCredentials(array $credentials)
	{
		// First we will add each credential element to the query as a where clause.
		// Then we can execute the query and, if we found a user, return it in a
		$attributes = array(
        'id' =>'aaaaaa',
        'username' => 'pieter',
        'password' => \Hash::make('talitha'),
        'remember_token' => null
      );
		return new GenericUser($attributes);
	}

	/**
	 * Validate a user against the given credentials.
	 *
	 * @param  \Illuminate\Auth\UserInterface  $user
	 * @param  array  $credentials
	 * @return bool
	 */
	public function validateCredentials(UserInterface $user, array $credentials)
	{
		return true;
	}
}