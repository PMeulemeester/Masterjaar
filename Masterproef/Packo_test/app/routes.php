<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

$languages = array('nl','fr');
	$locale = Request::segment(1);
	if(in_array($locale, $languages)){
		\App::setLocale($locale);
	}else{
		$locale = null;
	}
Route::group(array('prefix'=>$locale),function(){
	Route::get('/', 'HomeController@showWelcome');
	Route::get('login', array('as'=>'login','uses'=>'HomeController@showLogin'))->before('guest');
	Route::post('dologin', array('as'=>'dologin','uses'=>'HomeController@doLogin'));
	Route::get('logout', array('as'=>'logout','uses'=>'HomeController@doLogout'));
});

Route::group(array('prefix'=>$locale,'before'=>'auth'),function(){
	Route::get('welkom', array('as'=>'welkom','uses'=>'HomeController@showWelkom'));
	Route::get('logout', array('as'=>'logout','uses'=>'HomeController@doLogout'));
});


