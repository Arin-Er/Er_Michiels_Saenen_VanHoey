package model.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

public class QuestionDBlocal implements QuestionDB{
    //gelijkaardig aan CategoryDBlocal
    private static QuestionDBlocal single_instance = null;

    private ArrayList<Question> questions;

    private QuestionDBlocal(){
        questions = new ArrayList<>();
    }

    public static QuestionDBlocal getInstance(){
        if(single_instance == null)
            single_instance = new QuestionDBlocal();
        return single_instance;
    }

    public void addQuestion(Question question){
        if(question == null){
            throw new DbException("question can not be null");
        }
        else{
            for(Question x : this.questions){
                if(x.getQuestion().equals(question.getQuestion())){
                    throw new DbException("This Question already exists");
                }
            }
        }
        try {
            FileWriter writer = new FileWriter("testdatabase/vraag.txt",true);
            writer.write(System.getProperty( "line.separator" ));
            writer.write(question.getCategory());
            writer.write(";");
            writer.write(question.getQuestion());
            writer.write(";");
            writer.write(question.getCorrectAnswer());
            writer.write(";");
            writer.write(question.getFeedback());
            writer.write(";");
            /*writer.write(question.getQuestionType());
            writer.write(";");*/
            for(CharSequence s : question.getAnswers()){
                writer.write(s.toString());
                writer.write("/");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ook hier twijfelen over een edit. staat nergens in stories

    public void deleteQuestion(Question q){
        File file = new File("testdatabase/vraag.txt");
        try {
            Scanner scannerFile = new Scanner(file);
            questions.clear();
            while(scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String category = scannerLijn.next();
                String vraag = scannerLijn.next();
                String answer = scannerLijn.next();
                String feedback = scannerLijn.next();
                String statement = scannerLijn.next();
                String[] statements = statement.split("\\/");
                ObservableList<CharSequence> test = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(statements)));
                Question question = new Question(vraag,answer,test,category,feedback);
                questions.clear();
                questions.add(question);
                ListIterator<Question> iter = questions.listIterator();
                while(iter.hasNext()){
                    if(iter.next().getQuestion().equals(q.getQuestion())){
                        iter.remove();
                    }
                }
            }
            FileWriter writer = new FileWriter("testdatabase/vraag.txt",false);
            writer.write(this.schrijf());
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String schrijf() {
        String result = "";
        for(Question que : this.questions){
            result += que.format() + "\n";
        }
        return result;
    }

    public Question getQuestion(String que){
        File file = new File("testdatabase/vraag.txt");
        try {
            Scanner scannerFile = new Scanner(file);
            questions.clear();
            while(scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String category = scannerLijn.next();
                String vraag = scannerLijn.next();
                String answer = scannerLijn.next();
                String feedback = scannerLijn.next();
                String statement = scannerLijn.next();
                String[] statements = statement.split("\\/");
                ObservableList<CharSequence> test = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(statements)));
                Question question = new Question(vraag,answer,test,category,feedback);
                questions.add(question);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Question result = null;
        for(Question x:this.questions){
            if(x.getQuestion().equals(que)){
                result = x;
            }
        }
        if(result == null){
            throw new DbException("Question was not found");
        }else{
            return result;
        }
    }


    @Override
    public int getSizeQuestionDB() {
        File file = new File("testdatabase/vraag.txt");
        try {
            Scanner scannerFile = new Scanner(file);
            questions.clear();
            while(scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String category = scannerLijn.next();
                String vraag = scannerLijn.next();
                String answer = scannerLijn.next();
                String feedback = scannerLijn.next();
                String statement = scannerLijn.next();
                String[] statements = statement.split("\\/");
                ObservableList<CharSequence> test = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(statements)));
                Question question = new Question(vraag,answer,test,category,feedback);
                questions.add(question);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        for(Question q : this.questions){
            count++;
        }
        return count;
    }

    public ArrayList<Question> getQuestions(){
        File file = new File("testdatabase/vraag.txt");
        try {
            Scanner scannerFile = new Scanner(file);
            questions.clear();
            while(scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(";");
                String category = scannerLijn.next();
                String vraag = scannerLijn.next();
                String answer = scannerLijn.next();
                String feedback = scannerLijn.next();
                String statement = scannerLijn.next();
                String[] statements = statement.split("\\/");
                ObservableList<CharSequence> test = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(statements)));
                Question question = new Question(vraag,answer,test,category,feedback);
                questions.add(question);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
