<?php

class HomeController extends BaseController {

	/*
	|--------------------------------------------------------------------------
	| Default Home Controller
	|--------------------------------------------------------------------------
	|
	| You may wish to use controllers instead of, or in addition to, Closure
	| based routes. That's great! Here is an example controller method to
	| get you started. To route to this controller, just add the route:
	|
	|	Route::get('/', 'HomeController@showWelcome');
	|
	*/

	public function showWelcome()
	{
		return View::make('hello');
	}
	public function showLogin()
	{
		return View::make('login');
	}
	public function doLogin()
	{
		$rules = array(
			'username'    => 'required|min:3', // make sure the email is an actual email
			'password' => 'required|min:6', // password can only be alphanumeric and has to be greater than 3 characters
		);

		// run the validation rules on the inputs from the form
		$validator = Validator::make(Input::all(), $rules);
		if ($validator->fails()) {
			return Redirect::route('login')
				->withErrors($validator) // send back all errors to the login form
				->withInput(Input::except('password')); // send back the input (not the password) so that we can repopulate the form
		}
		else {

			// create our user data for the authentication
			$userdata = array(
				'username' 	=> Input::get('username'),
				'password' 	=> Input::get('password')
			);

			// attempt to do the login
			if (Auth::attempt($userdata)) {
				// validation successful!
				// redirect them to the secure section or whatever
				// return Redirect::to('secure');
				// for now we'll just echo success (even though echoing in a controller is bad)
				error_log("doorverwijzen naar welkom");
				return Redirect::route('welkom');

			} else {	 	

				// validation not successful, send back to form	
				return Redirect::route('login')
					->withErrors(array('wrong'=>Lang::get('messages.wrong')));

			}
		}
	}
	public function showWelkom()
	{
		return View::make('welkom');
	}
	public function doLogout()
	{
		Auth::logout();
		Session::flush();
		return Redirect::route('login');
	}
}
