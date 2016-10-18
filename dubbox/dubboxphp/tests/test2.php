<?php

require __DIR__ . '/../vendor/autoload.php';

require "config.php";




$delete = array("id" => "7");
$result = \dubboxphp\Request::post(OrderBrandDelete,$delete);
var_dump($result->body);
