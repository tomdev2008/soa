<?php
namespace dubboxphp;

use PHPUnit\Framework\TestCase;

class RestTest extends TestCase
{
    public function testHttpClientGet()
    {
		$result = \dubboxphp\Request::get(OrderHello);
var_dump($result['result']);

$result = \dubboxphp\Request::get(OrderBrandBase."4.json");
var_dump($result);



$result = \dubboxphp\Request::get(OrderBrand);
var_dump($result['moduleNo']);


        $stack = [];
        $this->assertEquals(0, count($stack));

        array_push($stack, 'foo');
        $this->assertEquals('foo', $stack[count($stack)-1]);
        $this->assertEquals(1, count($stack));

        $this->assertEquals('foo', array_pop($stack));
        $this->assertEquals(0, count($stack));
    }
    
    public function testGuzzleClientGet(){
		$result = \dubboxphp\Request::get(OrderHello,false,"guzzle");
        $this->assertEquals('Hello max, response form provider: 192.168.1.194:20880', $result->result);

        $result = \dubboxphp\Request::get(OrderBrand,false,"guzzle");
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
$result = \dubboxphp\Request::post(OrderBrandRegistry,$brand);
var_dump($result->body);
	}
	
	public function testUpdate(){
		$update = array(
    "id" => 3,
    "name" => "chang update",
    "telNumber" => "33333333"
);
$result = \dubboxphp\Request::post(OrderBrandUpdate,$update);
var_dump($result->body);
	}
	
	public function testDelete(){
		$delete = array("id" => 5);
$result = \dubboxphp\Request::post(OrderBrandUpdate,$delete);
var_dump($result->body);
	}
}
?>










