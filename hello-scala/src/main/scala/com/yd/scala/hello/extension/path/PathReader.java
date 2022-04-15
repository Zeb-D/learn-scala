package com.yd.scala.hello.extension.path;


import org.apache.dubbo.common.utils.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zeb灬D
 * @date 2021/5/18 5:05 下午
 */

public class PathReader {
    private final ConcurrentHashMap<String, PathInode> INODE_MAP = new ConcurrentHashMap<>();

    private final static Pattern arrayPattern = Pattern.compile("^\\[([1-9][0-9]*|[0])]$");

    public Object read(Object obj, String path) {
        try {
            PathInode pathInode = INODE_MAP.computeIfAbsent(path, this::parseInode);
            if (pathInode.hasError()) {
                throw new PathException("invalid path");
            }
            return getValue(obj, pathInode.getInode());
        } catch (PathException pathException) {
            throw pathException;
        } catch (Exception exception) {
            throw new PathException(path, exception);
        }
    }

    private Object getValue(Object obj, Inode inode) {
        Object val = obj;
        for (; inode != null; inode = inode.next()) {
            if (val == null) {
                return null;
            }
            if (inode.hasKey()) {
                if (val instanceof Map) {
                    val = getMapValue((Map<?, ?>) val, inode);
                } else {
                    val = getObjectValue(val, inode);
                }
            }
            if (inode.hasIndex()) {
                if (val instanceof List) {
                    val = ((List<?>) val).get(inode.getIndex());
                } else {
                    val = Array.get(obj, inode.getIndex());
                }
            }
        }
        return val;
    }

    private Object getMapValue(Map<?, ?> map, Inode inode) {
        return map.get(inode.getKey());
    }

    private Object getObjectValue(Object obj, Inode inode) {
        Class<?> clazz = obj.getClass();
        Method method;
        String suffix = inode.getKey().substring(0, 1).toUpperCase() + inode.getKey().substring(1);
        try {
            try {
                method = clazz.getMethod("get" + suffix);
                return method.invoke(obj);
            } catch (NoSuchMethodException e) {
                method = clazz.getMethod("is" + suffix);
                return method.invoke(obj);
            }
        } catch (Exception exception) {
            throw new PathException(inode, exception);
        }
    }

    /**
     * $[0].user.name
     *
     * @param path
     * @return
     */
    private PathInode parseInode(String path) {
        path = path.trim();
        byte[] bytes = path.getBytes();
        if (bytes[0] != '$') {
            return errorInode();
        }
        ByteBuilder builder = new ByteBuilder();
        boolean hasTrans = false;
        boolean hasError = false;

        List<Inode> inodes = new LinkedList<>();
        for (int i = 1; i < bytes.length; ++i) {
            byte b = bytes[i];
            if (hasTrans) {
                if (b == '\\' || b == '[' || b == ']' || b == '.') {
                    builder.append(b);
                    hasTrans = false;
                } else {
                    hasError = true;
                    break;
                }
            } else if (b == '\\') {
                hasTrans = true;
            } else if (b == '.' || b == '[') {
                Inode inode = buildInode(builder.toString());
                if (inode != null) {
                    inodes.add(inode);
                }
                builder.clear();
                if (b == '[') {
                    builder.append(b);
                }
            } else {
                builder.append(b);
            }
        }

        if (hasError) {
            return errorInode();
        }

        Inode inode = buildInode(builder.toString());
        if (inode != null) {
            inodes.add(inode);
        }
        if (inodes.isEmpty()) {
            return errorInode();
        }
        Inode inode1 = inodes.get(0);
        inode1.setLongName("$" + inode1.getName());
        for (int i = 1; i < inodes.size(); ++i) {
            Inode inode2 = inodes.get(i);
            if (inode2.hasIndex()) {
                inode2.setLongName(inode1.getLongName() + inode2.getName());
            } else {
                inode2.setLongName(inode1.getLongName() + "." + inode2.getName());
            }
            inode1.setNext(inode2);
            inode1 = inode2;
        }
        PathInode pathInode = new PathInode();
        pathInode.setInode(inodes.get(0));
        return pathInode;
    }

    private Inode buildInode(String nodeName) {
        if (StringUtils.isBlank(nodeName)) {
            return null;
        }
        Inode inode = new Inode();
        inode.setName(nodeName);
        Matcher matcher = arrayPattern.matcher(nodeName);
        if (matcher.find()) {
            inode.setIndex(Integer.parseInt(matcher.group(1)));
        } else {
            inode.setKey(nodeName);
        }
        return inode;
    }

    private PathInode errorInode() {
        PathInode inode = new PathInode();
        inode.setError();
        return inode;
    }

    private static class ByteBuilder {
        private final byte[] bytes = new byte[255];
        private int index = 0;

        public ByteBuilder append(byte b) {
            bytes[index++] = b;
            return this;
        }

        public void clear() {
            index = 0;
        }

        @Override
        public String toString() {
            return new String(bytes, 0, index);
        }
    }
}
