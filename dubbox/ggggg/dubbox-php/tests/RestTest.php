<?php
namespace dubbox;

use PHPUnit\Framework\TestCase;

class RestTest extends TestCase
{
    public function testHttpClientGet()
    {
		$result = \dubbox\Request::get("Order.Hello");
        var_dump($result['result']);

        $result = \dubbox\Request::get("Order.Brand.ID".array("id"=>4));
        var_dump($result);

        $result = \dubbox\Request::get("Order.Brand");
        var_dump($result['moduleNo']);
    }
    
    public function testGuzzleClientGet(){
		$result = \dubbox\Request::get("Order.Hello",false,"guzzle");
        $this->assertEquals('Hello max, response form provider: 192.168.1.194:20880', $result->result);

        $result = \dubbox\Request::get("Order.Brand",false,"guzzle");
        $this->assertEquals('580584953557a', $result->remark);

	}
    
    public function testInsert(){
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
		$result = \dubbox\Request::post("Order.BrandRegistry",$brand);
		var_dump($result->body);
	}
	
	public function testUpdate(){
		$update = array(
			"id" => 3,
			"name" => "chang update",
			"telNumber" => "33333333"
		);
		$result = \dubbox\Request::post("Order.BrandUpdate",$update);
		var_dump($result->body);
	}
	
	public function testDelete(){
		$delete = array("id" => 5);
		$result = \dubbox\Request::post("Order.BrandDelete",$delete);
		var_dump($result->body);
	}
}

?>










