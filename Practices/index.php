<?php
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Headers: *');
	header('Access-Control-Allow-Methods: GET');
		
	$path = './events.json';
	echo file_get_contents($path);
?>