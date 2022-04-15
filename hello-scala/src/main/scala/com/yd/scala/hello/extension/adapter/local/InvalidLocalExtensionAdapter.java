package com.yd.scala.hello.extension.adapter.local;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;
import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;
import com.yd.scala.hello.extension.exception.ExtensionRuntimeException;


public class InvalidLocalExtensionAdapter extends ExtensionAdapter {

    @Override
    public ExtensionResult<?> doExtend(ExtensionParameter param) {
        throw new ExtensionRuntimeException("invalid extension");
    }
}
