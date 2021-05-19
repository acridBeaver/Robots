package engine;

import model.GameField;
import model.MovableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModelMover {
    private final ScheduledExecutorService executorService;
    private final List<MovableModel> models;
    private final GameField gameField;

    public ModelMover(GameField gameField) {
        this.gameField = gameField;
        executorService = Executors.newScheduledThreadPool(4);
        models = new ArrayList<>();
    }

    public void registerModel(MovableModel movableModel) {
        models.add(movableModel);
    }

    public void startMovingModels() {
        for (MovableModel model : models) {
            Runnable task = new ModelMovingTask(model);
            executorService.scheduleAtFixedRate(task, 500, 1000, TimeUnit.MILLISECONDS);
        }
    }

    public void stopMovingModels() {
        executorService.shutdown();
    }

    private class ModelMovingTask implements Runnable {
        private final MovableModel model;

        private ModelMovingTask(MovableModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            model.moveByStep(gameField.getMazeEnd());
        }
    }
}
