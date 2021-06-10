package com;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Rule {
    private String name;
    private String criterionName;
    private String criterionValue;
    private String question;
    private List<String> answers;
}
