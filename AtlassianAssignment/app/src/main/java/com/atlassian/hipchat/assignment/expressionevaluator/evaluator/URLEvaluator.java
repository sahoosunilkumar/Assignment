package com.atlassian.hipchat.assignment.expressionevaluator.evaluator;

import com.atlassian.hipchat.assignment.expressionevaluator.common.TitleExtractor;
import com.atlassian.hipchat.assignment.model.ChatDetails;
import com.atlassian.hipchat.assignment.model.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class URLEvaluator implements IEvaluator {
    private static final String REGEX = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
    
    private static final Pattern PATTERN = Pattern.compile(
            REGEX,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    @Override
    public void evaluate(String input, ChatDetails chatDetails) {
        Matcher matcher = PATTERN.matcher(input);
            List<Link> mentionList = new ArrayList<>();
            while(matcher.find()){
            	Link link = new Link();
            	link.setUrl(matcher.group());
            	try {
					link.setTitle(TitleExtractor.getPageTitle(link.getUrl()));
				} catch (Exception e) {
					e.printStackTrace();
				}
            	mentionList.add(link);
            }
            if(!mentionList.isEmpty()){
            	chatDetails.setLinks(mentionList);
            }
    }
}
