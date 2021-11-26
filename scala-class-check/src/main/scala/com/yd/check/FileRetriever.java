package com.yd.check;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileRetriever {
    public FileRetriever() {
    }

    public static List<String> getClassesFromPath(File path) {
        return path.isDirectory() ? getClassesFromDirectory(path) : getClassesFromJarFile(path);
    }

    private static String fromFileToClassName(String fileName) {
        return fileName.replaceAll("[/\\\\]", "\\.");
    }

    private static List<String> getClassesFromJarFile(File path) {
        try {
            if (path.canRead()) {
                List<String> classes = new ArrayList();
                JarFile jar = new JarFile(path);
                Enumeration en = jar.entries();

                while (en.hasMoreElements()) {
                    JarEntry entry = (JarEntry) en.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(".class") && !name.endsWith("module-info.class")) {
                        String className = fromFileToClassName(name);
                        classes.add(className);
                    }
                }

                return classes;
            }
        } catch (Exception ex) {
            throw new RuntimeException("[duplicate-class-check] Failed to read classes from jar file: " + path, ex);
        }

        return Collections.emptyList();
    }

    private static List<String> getClassesFromDirectory(File path) {
        List<String> classes = new ArrayList();
        List<File> jarFiles = listFiles(path, (dir, name) -> {
            return name.endsWith(".jar");
        }, false);
        Iterator var3 = jarFiles.iterator();

        while (var3.hasNext()) {
            File file = (File) var3.next();
            classes.addAll(getClassesFromJarFile(file));
        }

        List<File> classFiles = listFiles(path, (dir, name) -> {
            return name.endsWith(".class");
        }, true);
        int substringBeginIndex = path.getAbsolutePath().length() + 1;
        Iterator var5 = classFiles.iterator();

        while (var5.hasNext()) {
            File classfile = (File) var5.next();
            String className = classfile.getAbsolutePath().substring(substringBeginIndex);
            className = fromFileToClassName(className);

            try {
                classes.add(className);
            } catch (Throwable var9) {
                var9.printStackTrace();
            }
        }

        return classes;
    }

    private static List<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
        File[] entries = directory.listFiles();
        if (entries != null && entries.length != 0) {
            List<File> files = new ArrayList();

            for (int i = 0; i < entries.length; ++i) {
                File entry = entries[i];
                if (filter == null || filter.accept(directory, entry.getName())) {
                    files.add(entry);
                }

                if (recurse && entry.isDirectory()) {
                    files.addAll(listFiles(entry, filter, true));
                }
            }

            return files;
        } else {
            return Collections.emptyList();
        }
    }
}