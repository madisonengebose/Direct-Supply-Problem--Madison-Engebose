package src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetQuiz {
    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public GetQuiz() {
        questions = fetchTriviaQuestions(); // Fetch questions during initialization
    }

    private List<Question> fetchTriviaQuestions() {
        List<Question> fetchedQuestions = new ArrayList<>();
        String apiUrl = "https://opentdb.com/api.php?amount=5&category=9&type=boolean";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray questionsArray = jsonResponse.getJSONArray("results");

                for (int i = 0; i < questionsArray.length(); i++) {
                    JSONObject questionObj = questionsArray.getJSONObject(i);
                    String question = questionObj.getString("question").replaceAll("&quot;", "\"").replaceAll("&#039;", "'");
                    String correctAnswer = questionObj.getString("correct_answer");
                    fetchedQuestions.add(new Question(question, correctAnswer));
                }
            } else {
                System.out.println("Failed to fetch data. HTTP response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return fetchedQuestions;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean checkAnswer(String userAnswer) {
        if (currentQuestionIndex < questions.size()) {
            boolean isCorrect = questions.get(currentQuestionIndex).getCorrectAnswer().equalsIgnoreCase(userAnswer);
            currentQuestionIndex++;
            return isCorrect;
        }
        return false; // No more questions to check
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }
}



