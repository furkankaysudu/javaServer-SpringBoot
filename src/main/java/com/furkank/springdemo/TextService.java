package com.furkank.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TextService {
    @Autowired
    private TextRepository textRepository;


    public List<Text> findAllText() {
        return textRepository.findAll();
    }

    public String mergeAndSaveText(String firstInput, String secondInput) {
        List<String> worldList1 = wordListMaker(firstInput);
        List<String> worldList2 = wordListMaker(secondInput);

        String output = merger(worldList1, worldList2).toString();

        Text text = new Text();
        text.setFirstInput(firstInput);
        text.setSecondInput(secondInput);
        text.setOutput(output);

        textRepository.save(text);

        return output;
    }


    static List<String> wordListMaker(String text) {
        String words1[] = text.trim().split("\\s+");
        return new ArrayList<>(Arrays.asList(words1));
    }

    static StringBuilder merger(List<String> wordList1, List<String> wordList2) {
        List<String> intersection = getIntersection(wordList1, wordList2);

        List<String> diffWordList2 = new ArrayList<>(wordList2.subList(intersection.size(), wordList2.size()));
        wordList1.addAll(diffWordList2);
        return listToStringBuilder(wordList1);
    }

    static List<String> getIntersection(List<String> wordList1, List<String> wordList2) {
        int counter = 1;
        List<String> intersection = new ArrayList<>();
        String controlWord = getControlWord(wordList1, counter);
        String afterControlWord = getControlWord(wordList1, (counter + 1));

        for (int i = wordList2.size() - 1; i >= 1; i--) {
            if (wordList2.get(i).equals(controlWord)) {

                if ((wordList2.get(i - 1).equals(afterControlWord)) && i != 1) {
                    intersection.add(controlWord);
                    counter++;
                    controlWord = getControlWord(wordList1, counter);

                    if (wordList1.size() != counter) {
                        afterControlWord = getControlWord(wordList1, (counter + 1));
                    }
                    else {
                        intersection.clear();
                        return intersection;
                    }

                } else if (i == 1) {
                    intersection.add(controlWord);
                    intersection.add(afterControlWord);
                } else {
                    intersection.clear();
                }
            }
        }
        return intersection;
    }

    static StringBuilder listToStringBuilder(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append("\s");
        }
        return sb;
    }

    private static String getControlWord(List<String> wordList, int counter) {
        return wordList.get(wordList.size() - counter);
    }


}
