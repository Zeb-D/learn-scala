package com.yd.api.doc;


import com.github.doiteasy.apidocs.Docs;
import com.github.doiteasy.apidocs.DocsConfig;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

@Mojo(name = "doc")
public class DocMojo extends AbstractMojo {
    private static final String[] INCLUDES_DEFAULT = new String[]{"properties", "xml", "java", "yml"};
    @Parameter(defaultValue = "${basedir}")
    private File baseDir;
    @Parameter(defaultValue = "${project.modules}")
    private List<String> modules;
    @Parameter(defaultValue = "${project}")
    private String projectName;
    @Parameter(
            property = "doc.name",
            defaultValue = "${project}",
            required = true,
            readonly = true)
    private String docName;
    @Parameter(
            property = "type"
    )
    private String docType;
    @Parameter(
            property = "doc.dubboPackages"
    )
    private List<String> dubboPackages;
    @Parameter(
            property = "doc.exclusionPackages"
    )
    private List<String> exclusionPackages;


    public void execute() throws MojoExecutionException, MojoFailureException {
        this.getLog().info("baseDir目录" + this.baseDir.getAbsolutePath());
        this.getLog().info("模块 " + this.modules);

        DocsConfig config = new DocsConfig();
        config.setProjectPath(this.baseDir.getAbsolutePath());
        config.setAppName(this.baseDir.getName());
        config.setDocName(this.docName);
        config.setDocsPath("api-" + this.docName);
        if (this.modules != null && this.modules.size() > 0) {
            if (this.dubboPackages != null && !this.dubboPackages.isEmpty()) {
                dubboPackages.forEach(dubboPackage -> config.addDubboPackages(dubboPackage));
                config.setFramework("dubbo");
                config.setDocsPath("api-dubbo");
                if (this.exclusionPackages != null && !this.exclusionPackages.isEmpty()) {
                    exclusionPackages.forEach(exclusionPackage -> config.addExclusionPackages(exclusionPackage));
                }
            }

            if (this.docType != null) {
                config.setFramework(this.docType);
                config.setDocsPath("api-" + this.docType);
            }


        }
        Docs.buildHtmlDocs(config);
    }
}