package br.com.valkyrie.campus.exceptions;

public class PostAnswerNotFoundException extends RuntimeException {
    public PostAnswerNotFoundException(String message) {
        super(message);
    }

    public PostAnswerNotFoundException() {
        super("Comentário não encontrado");
    }
}
