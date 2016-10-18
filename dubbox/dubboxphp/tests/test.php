<?php

require __DIR__ . '/../vendor/autoload.php';

require "config.php";

$result = \dubboxphp\Request::get(OrderHello);
var_dump($result['result']);

$result = \dubboxphp\Request::get(OrderBrandBase."4.json");
var_dump($result);

$result = \dubboxphp\Request::get(OrderHello,false,"guzzle");
var_dump($result->result);

$result = \dubboxphp\Request::get(OrderBrand);
var_dump($result['moduleNo']);

$result = \dubboxphp\Request::get(OrderBrand,false,"guzzle");
var_dump($result->moduleNo);

$labid = mt_rand(1,10000);
$uuid = uniqid();
$brand = array(
   "name"=> "haiphp",
   "labId" => $labid,
   "address" => "SSdsg",
   "moduleNo" => "SSS2000",
   "telNumber" => "125604",
   "remark"    => $uuid
);
$result = \dubboxphp\Request::post(OrderBrandRegistry,$brand);
var_dump($result->body);

$update = array(
    "id" => 3,
    "name" => "chang update",
    "telNumber" => "33333333"
);
$result = \dubboxphp\Request::post(OrderBrandUpdate,$update);
var_dump($result->body);

$delete = array("id" => 5);
$result = \dubboxphp\Request::post(OrderBrandUpdate,$delete);
var_dump($result->body);
