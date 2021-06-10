package com;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    private static ObjectMapper mapper = new ObjectMapper();
    private static List<Rule> rules = new ArrayList<>();
    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json")) {
            rules = Arrays.asList(mapper.readValue(in, Rule[].class));
            Rule rule = rules.get(0);

            while (true) {
                printQuestion(rule);
                printAnswers(rule.getAnswers());
                String answer = getAnswer(rule);
                rule = findRule(rule.getName(), answer);
                if (rule.getAnswers() == null) {
                    System.out.println(rule.getName());
                    break;
                }
            }
            printHistory();
        }
    }

    private static void printQuestion(Rule rule) {
        String question = rule.getQuestion();
        System.out.print(question + " : ");
        history.add(rule.getName());
    }

    private static void printAnswers(List<String> answers) {
        System.out.println(answers.stream()
                .reduce("", (x, y) -> x + y + ", "));
    }

    private static String getAnswer(Rule rule) {
        Scanner in = new Scanner(System.in);
        String selectedAnswer = in.nextLine();
        history.add(selectedAnswer);
        return selectedAnswer;

    }

    private static Rule findRule(String criterionName, String criterionValue) {
        return rules.stream()
                .filter(rule -> criterionName.equals(rule.getCriterionName()) && criterionValue.equals(rule.getCriterionValue()))
                .findFirst()
                .get();
    }

    private static void printHistory() {
        for (int i = 0; i < history.size(); i = i + 2) {
            String question = history.get(i);
            String answer = history.get(i + 1);
            System.out.println(question + " : " + answer);
        }
    }

}
