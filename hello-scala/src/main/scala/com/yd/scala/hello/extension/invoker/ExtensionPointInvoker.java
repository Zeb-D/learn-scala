package com.yd.scala.hello.extension.invoker;

import com.yd.scala.hello.extension.config.ExtensionDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zeb灬D
 * @date 2021/5/20 5:25 下午
 */

public class ExtensionPointInvoker {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionPointInvoker.class);

    private final FastOrderList beforeExtensions = new FastOrderList();
    private final FastOrderList replaceExtensions = new FastOrderList();
    private final FastOrderList afterExtensions = new FastOrderList();

    public ExtensionAdapterWrapper addExtension(ExtensionDefinition definition) {
        if ("before".equals(definition.getAction())) {
            return beforeExtensions.add(new ExtensionAdapterWrapper(definition));
        }

        if ("replace".equals(definition.getAction())) {
            return replaceExtensions.add(new ExtensionAdapterWrapper(definition));
        }

        if ("after".equals(definition.getAction())) {
            return afterExtensions.add(new ExtensionAdapterWrapper(definition));
        }

        return null;
    }

    public ExtensionAdapterWrapper removeExtension(String id) {
        ExtensionAdapterWrapper wrapper = beforeExtensions.remove(id);
        if (wrapper == null) {
            wrapper = replaceExtensions.remove(id);
        }
        if (wrapper == null) {
            wrapper = afterExtensions.remove(id);
        }
        return wrapper;
    }

    public List<ExtensionDefinition> getDefinitions() {
        List<ExtensionDefinition> definitions = new LinkedList<>();
        definitions.addAll(beforeExtensions.get().stream().map(ExtensionAdapterWrapper::getDefinition).collect(Collectors.toList()));
        definitions.addAll(replaceExtensions.get().stream().map(ExtensionAdapterWrapper::getDefinition).collect(Collectors.toList()));
        definitions.addAll(afterExtensions.get().stream().map(ExtensionAdapterWrapper::getDefinition).collect(Collectors.toList()));
        return definitions;
    }

    public void refresh() {
        beforeExtensions.refresh();
        replaceExtensions.refresh();
        afterExtensions.refresh();
    }

    public boolean hasBeforeExtensions() {
        return !beforeExtensions.isEmpty();
    }

    public boolean hasReplaceExtensions() {
        return !replaceExtensions.isEmpty();
    }

    public boolean hasAfterExtensions() {
        return !afterExtensions.isEmpty();
    }

    public List<ExtensionAdapterWrapper> getBeforeExtensions() {
        return beforeExtensions.get();
    }

    public List<ExtensionAdapterWrapper> getReplaceExtensions() {
        return replaceExtensions.get();
    }

    public List<ExtensionAdapterWrapper> getAfterExtensions() {
        return afterExtensions.get();
    }

    private static class FastOrderList {
        private volatile List<ExtensionAdapterWrapper> readList = new ArrayList<>();
        private final Map<String, ExtensionAdapterWrapper> writeMap = new HashMap<>();
        private boolean needRefresh;

        public synchronized ExtensionAdapterWrapper add(ExtensionAdapterWrapper wrapper) {
            needRefresh = true;
            return writeMap.put(wrapper.getDefinitionId(), wrapper);
        }

        public synchronized ExtensionAdapterWrapper remove(String id) {
            needRefresh = true;
            return writeMap.remove(id);
        }

        public synchronized void refresh() {
            if (!needRefresh) {
                return;
            }
            List<ExtensionAdapterWrapper> newList = new ArrayList<>(writeMap.values());
            newList.sort(Comparator.comparingInt(ExtensionAdapterWrapper::getOrder));
            readList = newList;
            needRefresh = false;
        }

        public List<ExtensionAdapterWrapper> get() {
            return readList;
        }

        public boolean isEmpty() {
            return readList.isEmpty();
        }
    }
}
