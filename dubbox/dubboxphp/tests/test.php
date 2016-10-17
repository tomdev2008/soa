<?php

require __DIR__ . '/../vendor/autoload.php';

$result = \dubboxphp\Request::get("http://localhost:8888/services/hello/max.json");
var_dump($result['result']);

$result = \dubboxphp\Request::get("http://localhost:8888/services/hello/max.json",false,"guzzle");
var_dump($result->result);

$result = \dubboxphp\Request::get("http://localhost:8888/services/bran.json");
var_dump($result['moduleNo']);

$result = \dubboxphp\Request::get("http://localhost:8888/services/bran.json",false,"guzzle");
var_dump($result->moduleNo);
