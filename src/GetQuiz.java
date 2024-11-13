/*
 * Main Screen Controller Class
 * Name: Madison Engebose
 * Created: 11/12/24
 */

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

/**
 * This class gets the quiz from the API
 */
public class GetQuiz {
    private final List<Question> questions;
    private int currentQuestionIndex = 0;

    /**
     * This method initializes the list containing
     * all the question objects
     */
    public GetQuiz() {
        questions = getTriviaQuestions();
    }

    private List<Question> getTriviaQuestions() {
        final int responseCode = 200;
        List<Question> fetchedQuestions = new ArrayList<>();
        String apiUrl = "https://opentdb.com/api.php?amount=5&category=9&type=boolean";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == responseCode) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray questionsArray = jsonResponse.getJSONArray("results");

                for (int i = 0; i < questionsArray.length(); i++) {
                    JSONObject questionObj = questionsArray.getJSONObject(i);
                    String question = questionObj.getString(
                            "question").replaceAll("&quot;", "\"").replaceAll("&#039;", "'");
                    String correctAnswer = questionObj.getString("correct_answer");
                    fetchedQuestions.add(new Question(question, correctAnswer));
                }
            } else {
                System.out.println("Failed to fetch data. " +
                        "HTTP response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: Failed to load information");
        }
        return fetchedQuestions;
    }

    /**
     * Gets the current question based on the current index
     * @return The current question object from the list
     */
    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    /**
     * This method checks whether the user got the question right.
     * @param userAnswer A string containing the user's answer
     * @return True or false depending on whether they got the answer right
     */
    public boolean checkAnswer(String userAnswer) {
        if (currentQuestionIndex < questions.size()) {
            boolean isCorrect = questions.get(
                    currentQuestionIndex).getCorrectAnswer().equalsIgnoreCase(userAnswer);
            currentQuestionIndex++;
            return isCorrect;
        }
        return false;
    }

    /**
     * This method determines whether
     * there are more questions for the user
     * @return True or false depending on whether there are more questions.
     */
    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }
}



