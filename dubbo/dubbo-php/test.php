<?php 
require __DIR__ . '/vendor/autoload.php';

use \dubbo\dubboClient;
// options for register consumer
// 注册消费者配置
$options= array(
    "registry_address" => "127.0.0.1:2181"
);
$dubboCli = new dubboClient($options);
$HelloService = $dubboCli->getService("com.cskk.api.service.DemoService","1.0.0",null);
$ret = $HelloService->hello("dubbo php client");
echo $ret;
