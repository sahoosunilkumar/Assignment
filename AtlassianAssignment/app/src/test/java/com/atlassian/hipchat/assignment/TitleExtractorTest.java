package com.atlassian.hipchat.assignment;

import com.atlassian.hipchat.assignment.expressionevaluator.common
        .TitleExtractor;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class TitleExtractorTest {
    @Test
    public void testTitle(){
        try {
            String title = TitleExtractor.getPageTitle("http://www.nbcolympics.com");
            Assert.assertNotNull(title);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertFalse(true);
        }
    }
}
