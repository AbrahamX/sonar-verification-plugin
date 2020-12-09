package org.sonarsource.plugins.example.scm;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yinong Xun on 2020/11/30.
 */
public class JGitTest {
    public static void main(String[] args) throws IOException {
//        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
//        Repository repository = repositoryBuilder.setGitDir(new File("D:\\IdeaProjects\\sonar-verification-plugin\\.git"))
//                .readEnvironment() // scan environment GIT_* variables
//                .findGitDir() // scan up the file system tree
//                .setMustExist(true)
//                .build();


//        Git.open(new File("D:\\IdeaProjects\\sonar-verification-plugin\\.git"))
//                .checkout();
//        Repository repository = git.getRepository();
    }
}
