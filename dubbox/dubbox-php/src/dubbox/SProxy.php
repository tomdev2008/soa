<?php

namespace dubbox;

/**
 * @author Nate Good <me@nategood.com>
 */
class SProxy
{
	const  HOST="http://localhost:8888";
	
	const DemoServiceProxy="com.ttfc.soa.dubbo.proxy.service.DemoServiceProxy"
	const BranchCompanyServiceProxy="com.ttfc.soa.dubbo.proxy.service.BranchCompanyServiceProxy"
	
    private static $SERVERS= array(
	  "Order.Demo" =>array(DemoServiceProxy, "/services/hello/max.json"),
	  "Order.Brand" =>array(BranchCompanyServiceProxy,"/services/bran.json"),
	  "Order.Brand.ID"  => array(BranchCompanyServiceProxy,"/services/bran/{id}.json"),
	  "Order.Brand.Registry" => array(BranchCompanyServiceProxy"/services/bran/register.json"),
	  "Order.Brand.Update" => array(BranchCompanyServiceProxy"/services/bran/update.json"),
 	  "Order.Brand.Delete" => array(BranchCompanyServiceProxy"/services/bran/delete.json"),
    );

  
  
  public static function get($name,$params=array()){
	  if(!isset(self::$SERVERS[$name])){
		 return false;  
	  }
	  $uri = self::$SERVERS[$name];
	  if(!empty($params)){
		  $uri = str_replace(array_keys($params), array_values($params), $uri);
	  }
	  return self::HOST . $uri;
  }
}
