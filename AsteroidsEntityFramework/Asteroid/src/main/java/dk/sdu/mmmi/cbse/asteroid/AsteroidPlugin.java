package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SplitterPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import static dk.sdu.mmmi.cbse.asteroid.AsteroidType.LARGE;

public class AsteroidPlugin implements IGamePluginService, IPostEntityProcessingService {
    private Entity[] asteroids;

    @Override
    public void start(GameData gameData, World world) {
        asteroids = new Entity[3];

        for (Entity asteroid : asteroids) {
            asteroid = createLargeAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : asteroids) {
            world.removeEntity(asteroid);
        }
    }

    @Override
    public void process(GameData gameData, World world) {

    }

    private Asteroid createLargeAsteroid(GameData gameData){
        float speed = (float) Math.random() * 10f + 40f;
        float radians = 3.1415f / 2 + (float) Math.random();

        float x = gameData.getDisplayWidth() / 2 + (float) Math.random() * (250 - 50) + 50;
        float y = gameData.getDisplayHeight() / 2 + (float) Math.random() * (200 - 50) + 50;
        Entity asteroid = new Asteroid(LARGE);

        asteroid.add(new MovingPart(0, speed, speed, 0));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(1));
        asteroid.add(new SplitterPart());
        asteroid.setRadius(15);

        return (Asteroid) asteroid;
    }
}
