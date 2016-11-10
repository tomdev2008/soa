<?php

require "bootstrap.php";


$result = \WFFile\Client::upload(__DIR__."/test.jpg");
var_dump($result);
