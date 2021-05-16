package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import model.GameField;

public class GameFieldFromFileFactory implements GameFieldFactory {
  private final File fieldFile;

  public GameFieldFromFileFactory(String filename) {
    this.fieldFile = new File(filename);
  }

  @Override
  public GameField create() {
    return parseGameFieldFromFile();
  }

  private GameField parseGameFieldFromFile() {
    FileInputStream contentStream = new FileInputStream(fieldFile);
    String content = new String(contentStream.readAllBytes());

  }
}
