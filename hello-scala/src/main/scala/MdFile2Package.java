import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 将/Users/xxx/IdeaProjects/my/my-review 目录下的md
 * 文件进行生成markdown 目录
 *
 * @author created by Zeb灬D on 2022-03-19 20:49
 */
public class MdFile2Package {
    static String url = "https://github.com/Zeb-D/my-review/blob/master";

    public static void main(String[] args) {
        String root = "/Users/xxx/IdeaProjects/my/my-review";

        printUrl(root, "/tool");
//        System.out.println("/java".split("/").length-1);
    }

    static FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return !name.contains(".idea") && !name.contains(".git")
                    && !name.contains("image") && !name.contains(".DS_Store") && !name.contains("模板.md");
        }
    };
    static String format = "%s* [%s](%s)";

    static List<String> printUrl(String root, String path) {
        List<String> ret = new LinkedList<>();
        int level = path.split("/").length - 1;
        String l = lev(level);
        for (String s : find(root + path)) {
            String fName = s.substring(s.lastIndexOf("/") + 1);
            String n = url + path + "/" + fName;
            fName = fName.replace(".md", "");
            String name = String.format(format, l, fName, n);
//            System.out.println(name);
            ret.add(name);
        }
        Collections.sort(ret, String::compareTo);
        ret.stream().forEach(q -> System.out.println(q));
        return ret;
    }

    static String lev(int i) {
        return StringUtils.repeat("\t", i);
    }

    static List<String> find(String path) {
        File f = new File(path);
        System.out.println(f.exists());

        List<String> ret = new LinkedList<>();
        func(f, ret);
        System.out.println(ret.size());
        return ret;
    }

    private static void func(File file, List<String> ret) {
        File[] fs = file.listFiles(filter);
        for (File f : fs) {
            if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
                func(f, ret);
            if (f.isFile())        //若是文件，直接打印
            {
                ret.add(f.toString());
                // System.out.println(f);
            }
        }
    }

    static content newCs(File f, content root) {
        content nc = new content();
        root.setC(g(f, root));
        return nc;
    }

    static mdContent g(File file, content root) {
        mdContent m = new mdContent();
        if (root != null) {
            m.setLevel(root.level + 1);
        }
        m.setFileName(file.getName());
        m.setRPath(file.getPath());

        return m;
    }

    @Data
    static class content {
        List<content> cs;//目录
        mdContent c;//目录
        int level;//第几层
    }

    @Data
    static class mdContent {
        int level;//第几层
        String rPath;//相对路径
        String fileName;//文件全名称
        boolean directory;//是否目录
    }
}
