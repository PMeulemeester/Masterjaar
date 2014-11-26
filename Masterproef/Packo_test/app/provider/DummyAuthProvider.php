<?php

namespace provider;
use Illuminate\Auth\GenericUser;
use Illuminate\Auth\UserInterface;
use Illuminate\Auth\UserProviderInterface;
use Parse\ParseObject;
use Parse\ParseUser;
use Parse\ParseException;
use Parse\ParseSessionStorage;
use Parse\ParseClient;


class DummyAuthProvider implements UserProviderInterface
{

    public function __construct(){
    }
    /**
   * Retrieve a user by their unique identifier.
   *
   * @param  mixed  $id
   * @return \Illuminate\Auth\UserInterface|null
   */
  public function retrieveById($id)
  {


    // 50% of the time, fail
    return null;
  }

  /**
   * Retrieve a user by the given credentials.
   * DO NOT TEST PASSWORD HERE!
   *
   * @param  array  $credentials
   * @return \Illuminate\Auth\UserInterface|null
   */
  public function retrieveByCredentials(array $credentials)
  {
    /*$query = ParseUser::query();
    $query->equalTo("username", $credentials['username']); 
    $results = $query->find();
    if(count($results)==1){
      $attributes = array(
        'id' =>$results[0]->getObjectId(),
        'username' => $results[0]->get('username'),
        'password' => \Hash::make($results[0]->get('password')),
        'remember_token' => null
      );
      return new GenericUser($attributes);
    }else{
      return null;
    }*/
    $attributes = array(
        'id' =>1,
        'username' => 'pieter',
        'password' => \Hash::make('lol'),
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
    /*try {
      $user = ParseUser::logIn($credentials['username'], $credentials['password']);
      error_log("ok validatie");
      return true;
    } catch (ParseException $error) {
      error_log("error validatie");
      return false;
    }*/
    return true;
  }


  /**
   * Needed by Laravel 4.1.26 and above
   */
  public function retrieveByToken($identifier, $token)
  {
    /*session_start();
    ParseClient::setStorage(new ParseSessionStorage());
    $user=ParseUser::getCurrentUser();
    if($user!=null){
      $attributes = array(
        'id' =>$user->getObjectId(),
        'username' => $user->get('username'),
        'password' => \Hash::make($user->get('password')),
        'name' => 'Dummy User',
        'remember_token' => $token
      );
      return new GenericUser($attributes);
    }*/
    return null;
  }

  /**
   * Needed by Laravel 4.1.26 and above
   */
  public function updateRememberToken(UserInterface $user, $token)
  {
      /*$query = ParseUser::query();
      $query->equalTo("objectId", $user->getAuthIdentifier()); 
      $results = $query->find();
      $users=$results[0];
      $users->set("remember_token",$token);
      $users->save();
      $user->setRememberToken($token);*/
  }
}