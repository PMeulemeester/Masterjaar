<!doctype html>
<html>
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="js/test.js"></script>
	<title>Login Packo</title>
</head>
<body>
	<div class="container">

		<div class="row" style="margin-top:20px">
		    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				{{ Form::open(array('route'=>'dologin')) }}
					<h1><?php echo Lang::get("messages.login"); ?></h1>

					<!-- if there are login errors, show them here -->
					<p>
						{{ $errors->first('email') }}
						{{ $errors->first('username') }}
						{{ $errors->first('wrong') }}
					</p>
					<div class="form-group">
							{{ Form::text('username', Input::old('username'), array('placeholder' => Lang::get("messages.username"),'name'=>"username",'id'=>"username",'class'=>"form-control input-lg")) }}
					</div>
					<div class="form-group">
						{{ Form::password('password',array( 'name'=>"password", 'id'=>"password", 'class'=>"form-control input-lg", 'placeholder'=>Lang::get("messages.password"))) }}
					</div>

					<p>{{ Form::submit(Lang::get("messages.login").'!',array('class'=>'btn btn-lg btn-primary btn-block')) }}</p>
				{{ Form::close() }}
			</div>
		</div>
	</div>


</body>
</html>
