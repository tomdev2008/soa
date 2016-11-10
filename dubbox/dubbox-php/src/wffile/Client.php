<?php

namespace WFFile;

class Client
{
	private static $_instance;
	private $fdfs, $server;
	
	private function __construct(){
		$this->connect();
	}

	public static function init(){
		if(self::$_instance == null){
            self::$_instance = new self();
        }        
        
        return self::$_instance;    		
    }
	
	public static function upload($file){
	   return 	self::init()->saveFile($file);
	}
	
	private function connect(){
		$this->fdfs = new \FastDFS();
		//var_dump($this->fdfs);
		$tracker = $this->fdfs->tracker_get_connection();
		//var_dump($tracker);
		if($tracker){
		    $this->server = $this->fdfs->connect_server($tracker['ip_addr'], $tracker['port']);
		   //var_dump($this->server);	
		}		
	}
	
	public function saveFile($file){
		$storage_file= false;
		if(!$this->server){
		   return false;	
		}
		
		$file_info = $this->fdfs->storage_upload_by_filename($file);
        if ($file_info)
        {
	       $group_name = $file_info['group_name'];
	       $remote_filename = $file_info['filename'];

	       //var_dump($file_info);
	       //var_dump($this->fdfs->get_file_info($group_name, $remote_filename));
	       if($this->fdfs->storage_file_exist($group_name, $remote_filename)){
			   $storage_file =  $group_name . "/" . $remote_filename;
		   }
        }
        return $storage_file;
  }
	
	function __destruct() {
       echo 'tracker_close_all_connections result: ' . $this->fdfs->tracker_close_all_connections() . "\n";
   }
}
?>
