<?php

require "bootstrap.php";

\dubbox\Request::zkserver("localhost:2181");
$delete = array("id" => "7");
list($status,$result) = \dubbox\Request::get("Order.Demo");
var_dump($status,$result);
