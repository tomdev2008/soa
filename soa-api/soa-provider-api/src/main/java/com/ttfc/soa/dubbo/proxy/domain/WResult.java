package com.ttfc.soa.dubbo.proxy.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.Response.Status.Family;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WResult  implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
    @XmlElement(name = "code")
    @NotNull
    private int code;

	@JsonProperty("istrue")
    @XmlElement(name = "istrue")
    @NotNull
	private boolean istrue;
	
	@JsonProperty("msg")
    @XmlElement(name = "msg")
	private String msg;
	
    @JsonProperty("entity")
    @XmlElement(name = "entity")
    @NotNull
    private Object entity;

    public WResult() {
    }
   

    @Override
    public String toString() {
        return "response code:" +
                "code=" + getCode() +
                ", msg='" + getMsg() + "'";
    }


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public boolean isIstrue() {
		return istrue;
	}


	public void setIstrue(boolean istrue) {
		this.istrue = istrue;
	}


	public String getMsg() {
		return msg;
		
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Object getEntity() {
		return entity;
	}


	public void setEntity(Object entity) {
		this.entity = entity;
	}
}