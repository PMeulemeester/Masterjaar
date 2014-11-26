<?php
require '../../vendor/autoload.php';
use Parse\ParseSessionStorage;
use Parse\ParseClient;
class BaseController extends Controller {

	/**
	 * Setup the layout used by the controller.
	 *
	 * @return void
	 */
	protected function setupLayout()
	{
		ParseClient::initialize('FaxfPPAYBmcInRLsrWLNzqTFncUfNPKSkmq6SvIs', 'QvnBtQfP8azS9U9ojUMfDVA4sBamQpBIF1IIQFhx', 'P4aEZkuWgyr1joyvitn6eo4XjdwjPvMNY0gNDFlE');
		
		if ( ! is_null($this->layout))
		{
			$this->layout = View::make($this->layout);
		}
	}

}