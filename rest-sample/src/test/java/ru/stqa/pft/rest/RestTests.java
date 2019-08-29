package ru.stqa.pft.rest;

import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        for (Issue issue : oldIssues) {
            skipIfNotFixed(issue.getId());
            Issue newIssue = new Issue().withSubject("Issue").withDescription("Description");
            int issueId = createIssue(newIssue);
            Set<Issue> newIssues = getIssues();
            oldIssues.add(newIssue.withId(issueId));
            assertEquals(newIssues, oldIssues);
        }
    }
}
