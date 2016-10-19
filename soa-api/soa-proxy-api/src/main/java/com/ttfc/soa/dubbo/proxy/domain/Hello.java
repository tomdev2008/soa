/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ttfc.soa.dubbo.proxy.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author lishen
 */
@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hello implements Serializable {

	@JsonProperty("status")
    @XmlElement(name = "status")
    @NotNull
    private String status;

    @JsonProperty("result")
    @XmlElement(name = "result")
    @NotNull
    private String result;

    public Hello() {
    }

    public Hello(String status, String result) {
        this.setStatus(status);
        this.setResult(result);
    }

    

    @Override
    public String toString() {
        return "response code:" +
                "code=" + status +
                ", result='" + result + "'";
    }

	private String getStatus() {
		return status;
	}

	private void setStatus(String status) {
		this.status = status;
	}

	private String getResult() {
		return result;
	}

	private void setResult(String result) {
		this.result = result;
	}
}
