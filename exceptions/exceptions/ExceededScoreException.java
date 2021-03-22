package exceptions;

import java.io.IOException;

/**
 * @author Rubén Ramírez Rivera
 *
 */
public class ExceededScoreException extends IOException {
  public ExceededScoreException(String msg) {
    super(msg);
  }
}
