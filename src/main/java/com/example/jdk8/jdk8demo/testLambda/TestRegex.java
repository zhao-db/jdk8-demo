package com.example.jdk8.jdk8demo.testLambda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/11/10
 * @since 3.0.1
 */
public class TestRegex {

    public static final Pattern triggerTaskTriggerItriggerTaskTriggerInstructionPatternnstructionPattern = Pattern.compile("\\w+\\[" + 0 + "\\:[\\w,]+\\]+");

    public static void main(String[] args) {
        String msg = "OPERATION_MODEL[0:1]FIFTH1[20:]BOX_REDAY[0:1]FIFTH10[20:]FIFTH2[20:]FIFTH3[20:]FIFTH4[20:]FIFTH5[20:]FIFTH6[20:]FIFTH7[20:]FIFTH8[20:]FIFTH9[20:]FIRST1[20:]FIRST10[20:]FIRST2[20:]FIRST3[20:]FIRST4[20:]FIRST5[20:]FIRST6[20:]FIRST7[20:]FIRST8[20:]FIRST9[20:]FOURTH1[20:]FOURTH10[20:]FOURTH2[20:]FOURTH3[20:]FOURTH4[20:]FOURTH5[20:]FOURTH6[20:]FOURTH7[20:]FOURTH8[20:]FOURTH9[20:]SECOND1[20:]SECOND10[20:]SECOND2[20:]SECOND3[20:]SECOND4[20:]SECOND5[20:]SECOND6[20:]SECOND7[20:]SECOND8[20:]SECOND9[20:]SIXTH1[20:]SIXTH10[20:]SIXTH2[20:]SIXTH3[20:]SIXTH4[20:]SIXTH5[20:]SIXTH6[20:]SIXTH7[20:]SIXTH8[20:]SIXTH9[20:]THIRD1[20:]THIRD10[20:]THIRD2[20:]THIRD3[20:]THIRD4[20:]THIRD5[20:]THIRD6[20:]THIRD7[20:]THIRD8[20:]THIRD9[20:]FIFTH_MARK[20:]FIRST_MARK[20:]FOURTH_MARK[20:]SECOND_MARK[20:]SIXTH_MARK[20:]THIRD_MARK[20:]TASK_CODE[20:]";
        Matcher matcher = triggerTaskTriggerItriggerTaskTriggerInstructionPatternnstructionPattern.matcher(msg);
        while (matcher.find()) {
            System.out.println("matcher.group().split(\"\\\\[\")[0]; = " + matcher.group().split("\\[")[0]);
        }

    }
}
