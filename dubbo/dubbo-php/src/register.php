<?php

namespace dubbo;

require_once "invok/cluster.php";
require_once "invok/invoker.php";
use \dubbo\invok\Cluster;
use dubbo\invok\Invoker;

class Register{

public $config = array(
	'registry_address' => ''
 );

public $zookeeper = null;

protected $ip;

protected $providersCluster;

public static $ServiceMap = array();

protected  $acl = array(
                  array(
                    'perms' => \Zookeeper::PERM_ALL,
                    'scheme' => 'world',
                    'id' => 'anyone' ) );

public function __construct($options = array())
{
	$this->config = array_merge($this->config,$options);
	$this->ip = $this->getIP();
    $this->providersCluster = Cluster::getInstance();
	$this->zookeeper= $this->getZookeeper($this->config['registry_address']);
}

public function getIP(){
	return $this->is_cli() ? getHostByName(getHostName()): $_SERVER['SERVER_ADDR'];	
}

public function is_cli()
{
	if( defined('STDIN') )
	{
		return true;
	}
	
	if( empty($_SERVER['REMOTE_ADDR']) and !isset($_SERVER['HTTP_USER_AGENT']) and count($_SERVER['argv']) > 0) 
	{
		return true;
	} 
	
	return false;
}

public function getZookeeper($registry_address) {
            return new \Zookeeper ($registry_address);
 }

public function subscribe($invokDesc){
       $desc = $invokDesc->toString();
       $serviceName = $invokDesc->getService();

       $path = $this->getSubscribePath($serviceName);
       $children = $this->zookeeper->getChildren($path);
       if(count($children) > 0){
       	foreach ($children as $key => $provider) {
            $provider = urldecode($provider);
       		$this->methodChangeHandler($invokDesc, $provider);
       	}
       $this->configurators();
}

}

public function register($invokDesc,$invoker){
    $desc = $invokDesc->toString();
    if(!array_key_exists($desc,self::$ServiceMap)){
        self::$ServiceMap[$desc] = $invoker;
    }
    $this->subscribe($invokDesc);
    $providerHost = $this->providersCluster->getProvider($invokDesc);
    $invoker->setHost(Invoker::genDubboUrl($providerHost,$invokDesc));
    $registerPath = $this->getRegistryPath($invokDesc->getService());
    $this->zookeeper->create($registerPath,null,$this->acl, null);
    return true;
}


public function methodChangeHandler($invokerDesc, $provider){
    $schemeInfo = parse_url($provider);
    $providerConfig = array();
    parse_str($schemeInfo['query'],$providerConfig);

    if($invokerDesc->isMatch($providerConfig['group'],$providerConfig['version']))
    {
        $this->providersCluster->addProvider($invokerDesc,'http://'.$schemeInfo['host'].':'.$schemeInfo['port'],$schemeInfo['scheme']);
    }
}


public function getInvoker($invokerDesc){
    $desc = $invokerDesc->toString();
    var_dump(self::$ServiceMap);
    return self::$ServiceMap[$desc];
}



public function configurators(){
    return true;
}



protected function getSubscribePath($serviceName){
	return '/dubbo/' .$serviceName.'/providers';
}

protected function getRegistryAddress() {
        return $this->config['registry_address'];
}


protected function getRegistryPath($serviceName, $application = array()){
        $params = http_build_query($application);
        $url = '/dubbo/'.$serviceName.'/consumers/'.urlencode('consumer://'.$this->ip.'/'.$serviceName.'?').$params;
        return $url;
}

protected function getConfiguratorsPath($serviceName){
        return '/dubbo/'.$serviceName.'/configurators';
}

protected function  getProviderTimeout(){
        return $this->config['providerTimeout'] * 1000;
}


public function zkinfo($invokerDesc){
    echo $this->getRegistryPath($invokerDesc->getService());
    var_dump($this->providersCluster->getProviders());
    var_dump($this->providersCluster);
}

}


?>
