<?php

require "bootstrap.php";

$delete = array("id" => "7");
$result = \dubbox\Request::post("Order.BrandDelete",$delete);
var_dump($result);
