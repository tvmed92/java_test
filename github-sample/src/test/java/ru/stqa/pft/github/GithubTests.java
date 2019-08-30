package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("25d2d0240d58ea39e6d5c7f389ab05cf553a1dc0");
        RepoCommits commits = github.repos().get(new Coordinates
                .Simple("tvmed92", "java_test")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap
                .Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
