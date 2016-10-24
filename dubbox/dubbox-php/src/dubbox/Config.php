<?php

namespace dubbox;

/**
 * @author Nate Good <me@nategood.com>
 */
class Config
{
  private static $services= array(
	"Order.Hello" => "http://localhost:8888/services/hello/max.json",
	"Order.Brand" => "http://localhost:8888/services/bran.json",
	"Order.Brand.ID"  => "http://localhost:8888/services/bran/{id}.json",
	"Order.BrandRegistry" => "http://localhost:8888/services/bran/register.json",
	"Order.BrandUpdate" => "http://localhost:8888/services/bran/update.json",
 	"Order.BrandDelete" => "http://localhost:8888/services/bran/delete.json",
  );

  public static getServer($name,$params=array()){
	  $uri = $services[$name];
	  if(empty($params)){
		  $uri = str_replace(array_keys($params), array_values($params), $uri);
	  }
	  return $uri;
  }
}
