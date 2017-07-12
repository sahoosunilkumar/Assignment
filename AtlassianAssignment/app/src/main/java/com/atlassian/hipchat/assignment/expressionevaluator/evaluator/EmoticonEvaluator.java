package com.atlassian.hipchat.assignment.expressionevaluator.evaluator;

import com.atlassian.hipchat.assignment.model.ChatDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class EmoticonEvaluator implements IEvaluator {
    private static final String REGEX = "[(][a-zA-Z0-9]{1,15}[)]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void evaluate(String input, ChatDetails chatDetails) {
        Matcher matcher = PATTERN.matcher(input);
        List<String> mentionList = new ArrayList<>();
        String group;
        while(matcher.find()){
            group = matcher.group();
        	mentionList.add(group.substring(1, group.length()-1));
            System.out.println();
        }
        if(!mentionList.isEmpty()){
        	chatDetails.setEmoticons(mentionList);
        }
    }
}
