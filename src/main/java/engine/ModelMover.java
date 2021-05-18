package engine;

import model.GameField;
import model.Robot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModelMover {
  private final ScheduledExecutorService executorService;
  private final List<Robot> models;
  private final GameField gameField;

  public ModelMover(GameField gameField) {
    this.gameField = gameField;
    executorService = Executors.newScheduledThreadPool(4);
    models = new ArrayList<>();
  }

  public void registerModel(Robot robot) {
    models.add(robot);
  }

  public void startMovingModels() {
    for (Robot model : models) {
      Runnable task = new ModelMovingTask(model);
      executorService.scheduleAtFixedRate(task, 500, 500, TimeUnit.MILLISECONDS);
    }
  }

  public void stopMovingModels() {
    executorService.shutdown();
  }

  private class ModelMovingTask implements Runnable {
    private final Robot model;

    private ModelMovingTask(Robot model) {
      this.model = model;
    }

    @Override
    public void run() {
      model.moveByStep(gameField.getMazeEnd());
    }
  }
}
