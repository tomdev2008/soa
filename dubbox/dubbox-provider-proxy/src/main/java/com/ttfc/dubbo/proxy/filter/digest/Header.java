package com.ttfc.dubbo.proxy.filter.digest;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;

public class Header {
	private String Cnonce;
	private String Nonce;
	private String Realm;
	private String UserName;
	private String Uri;
	private String Response;
	private String Method;
	private String NounceCounter;

	public Header() {

	}

	public Header(String header, String method) {
		
		Map<String, String> map= Splitter.on(",").withKeyValueSeparator("=").split(header);
		for (Map.Entry<String, String> entry : map.entrySet())
		{
		    //System.out.println(entry.getKey().trim() + "/" + entry.getValue().trim());
			String key = entry.getKey().trim();
			String value = entry.getValue().trim();

             switch (key)
             {
                 case "username": this.setUserName(StringUtils.strip(value, "\""));  break;
                 case "realm": this.setRealm(StringUtils.strip(value, "\"")); break;
                 case "nonce": this.setNonce(StringUtils.strip(value, "\"")); break;
                 case "uri": this.setUri(StringUtils.strip(value, "\"")); break;
                 case "nc": this.setNounceCounter(StringUtils.strip(value, "\"")); break;
                 case "cnonce": this.setCnonce(StringUtils.strip(value, "\"")); break;
                 case "response": this.setResponse(StringUtils.strip(value, "\"")); break;
                 case "method": this.setMethod(StringUtils.strip(value, "\"")); break;
             }
         }

         if (null == this.Method){
        	 this.setMethod(method);;
         }
             
	}

	
	public  String toString()
     {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("realm=\"%s\"", Realm));
		builder.append(String.format(", nonce=\"%s\"", Nonce));
		builder.append(String.format(", qop=\"%s\"", "auth"));
        return builder.toString();
    }

	public static Header UnauthorizedResponseHeader()
     {
		 Header header =  new Header();
		 header.setRealm("RealmOfBadri");
		 header.setNonce(com.ttfc.dubbo.proxy.filter.digest.Nonce.Generate());
         return header;
     }

	public static Header ErrorauthorizedResponseHeader(String nonce)
    {
		 Header header =  new Header();
		 header.setRealm("RealmOfBadri");
		 header.setNonce(nonce);
        return header;
    }
	
	public String getCnonce() {
		return Cnonce;
	}

	public void setCnonce(String cnonce) {
		Cnonce = cnonce;
	}

	public String getNonce() {
		return Nonce;
	}

	public void setNonce(String nonce) {
		Nonce = nonce;
	}

	public String getRealm() {
		return Realm;
	}

	public void setRealm(String realm) {
		Realm = realm;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String uri) {
		Uri = uri;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String response) {
		Response = response;
	}

	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public String getNounceCounter() {
		return NounceCounter;
	}

	public void setNounceCounter(String nounceCounter) {
		NounceCounter = nounceCounter;
	};
}
