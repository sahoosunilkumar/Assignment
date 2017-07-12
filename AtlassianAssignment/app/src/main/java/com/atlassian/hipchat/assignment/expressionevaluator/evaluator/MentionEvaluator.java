package com.atlassian.hipchat.assignment.expressionevaluator.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atlassian.hipchat.assignment.model.ChatDetails;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class MentionEvaluator implements IEvaluator {
    private static final String REGEX = "@[a-zA-Z0-9]+";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    @Override
    public void evaluate(String input, ChatDetails chatDetails) {

        Matcher matcher = PATTERN.matcher(input);
        List<String> mentionList = new ArrayList<>();
        while(matcher.find()){
        	mentionList.add(matcher.group().substring(1));
            System.out.println();
        }
        if(!mentionList.isEmpty()){
        	chatDetails.setMentions(mentionList);
        }
        
    }
}
