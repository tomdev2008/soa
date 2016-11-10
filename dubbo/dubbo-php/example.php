<?php
/**
 * @description
 * dubbo php client example
 *
 * @author quickj
 * @date 2016-1-20
 */

require_once "src/dubboClient.php";
use \dubbo\dubboClient;

// options for register consumer
// 注册消费者配置
$options= array(
    "registry_address" => "127.0.0.1:2181"
);

$dubboCli = new dubboClient($options);
$testService = $dubboCli->getService("com.ttfc.soa.dubbo.provider.service.DemoService","1.0.0-SNAPSHOT",null);
$ret = $testService->sayHello("dubbo php client");
echo $ret;

?>
