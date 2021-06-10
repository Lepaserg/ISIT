package com;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class Question {
    private Long id;
    private String name;
    private List<Answer> answers;
}
