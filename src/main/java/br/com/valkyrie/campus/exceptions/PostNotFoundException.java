package br.com.valkyrie.campus.exceptions;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException() {
    super("Post not found");
  }

  public PostNotFoundException(String message) {
    super(message);
  }
}
