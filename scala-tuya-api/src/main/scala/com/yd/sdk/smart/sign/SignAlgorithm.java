
package com.yd.sdk.smart.sign;


import com.yd.sdk.smart.model.ApiRequestDO;

public interface SignAlgorithm {

	public String getSignString(ApiRequestDO apiRequestDo);

	public String getSignInput(ApiRequestDO apiRequestDo);
}
