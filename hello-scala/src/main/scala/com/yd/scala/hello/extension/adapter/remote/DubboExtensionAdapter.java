package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;
import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;


public class DubboExtensionAdapter extends ExtensionAdapter {
    private String interfaceName;
    private String methodName;
    private int timeout;
    private int retries;

    private volatile ExtensionReferenceConfig referenceConfig;
    private volatile ExtensionGenericService genericService;

    @Override
    public void init() {
        referenceConfig = new ExtensionReferenceConfig();
        referenceConfig.setInterface(interfaceName);
        referenceConfig.setFilter("kitExtension");
        referenceConfig.setRetries(retries);
        referenceConfig.setTimeout(timeout <= 0 ? 10000 : timeout);
        genericService = referenceConfig.get();
    }

    @Override
    public void destroy() {
        if (referenceConfig != null) {
            referenceConfig.destroy();
            referenceConfig = null;
        }
    }

    @Override
    public ExtensionResult<?> doExtend(ExtensionParameter param) {
        return genericService.$invoke(methodName, param);
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
