package com.yd.check;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilder;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilderException;
import org.apache.maven.shared.dependency.graph.DependencyNode;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

/**
 * 实现思路: 默认是在maven 的 verify阶段进行检查，然后将项目的依赖信息(是 mvn dependency:tree 的信息)获取到，然后进行分析.
 * failOnDuplicate	boolean	true	当有duplicate的时候，是否需要失败. 默认
 * excludeGroupIds	数组
 * 不需要检查的groupIds
 * excludeArtifactIds	数组
 * 不需要检查的 artifactIds
 * excludeClassPrefixes	数组
 * 不需要检查的 类名字前缀。这里的类名字是类的全名, 比如: com.yd.xxx.XXVO
 */
@Mojo(
        name = "class-check",
        defaultPhase = LifecyclePhase.VALIDATE
)
public class DuplicateClassCheckMojo extends AbstractMojo {
    @Parameter(
            defaultValue = "${project}",
            readonly = true,
            required = true
    )
    private MavenProject project;
    @Parameter(
            defaultValue = "${session}",
            readonly = true,
            required = true
    )
    private MavenSession session;
    @Component(
            hint = "default"
    )
    private DependencyGraphBuilder dependencyGraphBuilder;
    @Parameter(
            defaultValue = "true"
    )
    private boolean failOnDuplicate;
    @Parameter
    private String[] excludeGroupIds;
    @Parameter
    private String[] excludeArtifactIds;
    @Parameter
    private String[] excludeClassPrefixes;

    public DuplicateClassCheckMojo() {
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DependencyNode dependencyNode = this.getDependencyNode();
        if (dependencyNode == null) {
            this.getLog().info("[duplicate-class-check] dependencyNode is null");
        } else {
            Set<Artifact> totalArtifactIds = this.getArtifacts(dependencyNode);
            this.resolveDependencies(totalArtifactIds);
        }
    }

    private DependencyNode getDependencyNode() {
        ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest(this.session.getProjectBuildingRequest());
        buildingRequest.setProject(this.project);

        try {
            return this.dependencyGraphBuilder.buildDependencyGraph(buildingRequest, (ArtifactFilter) null);
        } catch (DependencyGraphBuilderException ex) {
            this.getLog().info("[duplicate-class-check] get dependency tree info failed: " + ex.getMessage());
            return null;
        }
    }

    private Set<Artifact> getArtifacts(DependencyNode dependencyNode) {
        Artifact artifact = dependencyNode.getArtifact();
        if (!this.isAllowedArtifact(artifact)) {
            return Collections.emptySet();
        } else {
            List<DependencyNode> children = dependencyNode.getChildren();
            Set<Artifact> totalArtifacts = Sets.newHashSetWithExpectedSize(children != null ? children.size() + 1 : 1);
            totalArtifacts.add(artifact);
            if (children != null && !children.isEmpty()) {
                Iterator var5 = children.iterator();

                while (true) {
                    List myChildren;
                    do {
                        DependencyNode child;
                        Artifact childArtifact;
                        do {
                            if (!var5.hasNext()) {
                                return totalArtifacts;
                            }

                            child = (DependencyNode) var5.next();
                            childArtifact = child.getArtifact();
                        } while (!this.isAllowedArtifact(childArtifact));

                        totalArtifacts.add(childArtifact);
                        myChildren = child.getChildren();
                    } while (this.isCollectionEmpty(myChildren));

                    Iterator var9 = myChildren.iterator();

                    while (var9.hasNext()) {
                        DependencyNode myChild = (DependencyNode) var9.next();
                        Set<Artifact> artifactFromChild = this.getArtifacts(myChild);
                        if (!this.isCollectionEmpty(artifactFromChild)) {
                            totalArtifacts.addAll(artifactFromChild);
                        }
                    }
                }
            } else {
                return totalArtifacts;
            }
        }
    }

    private boolean isAllowedArtifact(Artifact artifact) {
        return artifact != null && !"test".equals(artifact.getScope());
    }

    private boolean isCollectionEmpty(Collection myChildren) {
        return myChildren == null || myChildren.isEmpty();
    }

    private void resolveDependencies(Set<Artifact> totalArtifactIds) throws MojoFailureException, MojoExecutionException {
        if (totalArtifactIds != null && !totalArtifactIds.isEmpty()) {
            Set<String> skipCheckGroupIds = this.excludeGroupIds == null ? Collections.emptySet() : Sets.newHashSet(this.excludeGroupIds);
            Set<String> skipArtifactIds = this.excludeArtifactIds == null ? Collections.emptySet() : Sets.newHashSet(this.excludeArtifactIds);
            Set<String> skipClassPrefixes = this.excludeClassPrefixes == null ? Collections.emptySet() : Sets.newHashSet(this.excludeClassPrefixes);
            Map<String, String> classArtifactIdMap = Maps.newHashMapWithExpectedSize(1024);
            Map<String, Set<String>> classArtifactIdListMap = Maps.newHashMapWithExpectedSize(1024);
            Map<String, Set<String>> classDistinctArtifactIdCountMap = Maps.newHashMapWithExpectedSize(1024);
            totalArtifactIds.forEach((item) -> {
                File file = item.getFile();
                if (file != null) {
                    String groupId = item.getGroupId();
                    String artifactId = item.getArtifactId();
                    if (!skipArtifactIds.contains(artifactId) && !skipCheckGroupIds.contains(groupId)) {
                        String gaItem = groupId + ":" + artifactId;
                        String gavItem = gaItem + ":" + item.getVersion();
                        List<String> classes = FileRetriever.getClassesFromPath(file);
                        Iterator var14 = classes.iterator();

                        while (true) {
                            String foundClassItem;
                            while (true) {
                                do {
                                    if (!var14.hasNext()) {
                                        return;
                                    }

                                    foundClassItem = (String) var14.next();
                                } while (this.isClassShouldSkip(foundClassItem, skipClassPrefixes));

                                if (!classArtifactIdMap.containsKey(foundClassItem)) {
                                    break;
                                }

                                String lastGavItem = classArtifactIdMap.get(foundClassItem);
                                if (!lastGavItem.equals(gavItem)) {
                                    Set<String> existingGavItems = (Set) classArtifactIdListMap.getOrDefault(foundClassItem, new HashSet());
                                    if (!existingGavItems.contains(gavItem)) {
                                        existingGavItems.add(lastGavItem);
                                        existingGavItems.add(gavItem);
                                        classArtifactIdListMap.put(foundClassItem, existingGavItems);
                                        String lastGaItem = this.getGaInfo(lastGavItem);
                                        Set<String> existingGaSet = (Set) classDistinctArtifactIdCountMap.getOrDefault(foundClassItem, new HashSet());
                                        existingGaSet.add(gaItem);
                                        existingGaSet.add(lastGaItem);
                                        classDistinctArtifactIdCountMap.put(foundClassItem, existingGaSet);
                                        break;
                                    }
                                }
                            }

                            classArtifactIdMap.put(foundClassItem, gavItem);
                        }
                    }
                }
            });
            this.log(classArtifactIdListMap, classDistinctArtifactIdCountMap);
            if (!classArtifactIdListMap.isEmpty() && this.failOnDuplicate) {
                throw new MojoFailureException("[duplicate-class-check] there are classes existing in multiple artifact, please check!");
            }
        }
    }

    private boolean isClassShouldSkip(String foundClassItem, Set<String> skipClassPrefixes) {
        if (this.isCollectionEmpty(skipClassPrefixes)) {
            return false;
        } else {
            return skipClassPrefixes.stream().filter(StringUtils::isNotBlank).anyMatch(foundClassItem::startsWith);
        }
    }

    private void log(Map<String, Set<String>> classArtifactIdListMap, Map<String, Set<String>> classDistinctArtifactIdCountMap) {
        if (!classArtifactIdListMap.isEmpty()) {
            this.getLog().warn("[duplicate-class-check] duplicate class size: " + classArtifactIdListMap.keySet().size());
            classArtifactIdListMap.forEach((classItem, artifactIds) -> {
                Set<String> distinctGaItems = (Set) classDistinctArtifactIdCountMap.get(classItem);
                if (distinctGaItems.size() > 1) {
                    Consumer<String> printFun = this.failOnDuplicate ? this.getLog()::error : this.getLog()::warn;
                    this.printDuplicateClassInfo(printFun, classItem, artifactIds);
                } else {
                    this.printDuplicateClassInfo(this.getLog()::warn, classItem, artifactIds);
                }

            });
        }
    }

    private String getGaInfo(String lastGavItem) {
        int lastIndex = lastGavItem.lastIndexOf(":");
        return lastGavItem.substring(0, lastIndex);
    }

    private void printDuplicateClassInfo(Consumer<String> consumer, String classItem, Set<String> artifactIds) {
        String title = "[duplicate-class-check] class[" + classItem + "] exist in following artifact:";
        consumer.accept(title);
        Iterator var5 = artifactIds.iterator();

        while (var5.hasNext()) {
            String artifactId = (String) var5.next();
            String content = "------------>: " + artifactId;
            consumer.accept(content);
        }

    }
}
