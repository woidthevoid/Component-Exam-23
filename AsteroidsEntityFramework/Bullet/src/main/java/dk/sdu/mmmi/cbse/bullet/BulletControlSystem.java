package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        List<Event> events = gameData.getEvents();

        for (Event event : events) {
            if (event.getEventType() == EventType.BULLET) {
                Entity bullet = createBullet(event);
                gameData.removeEvent(event);
                world.addEntity(bullet);
            }
        }

        for (Entity bullet : world.getEntities(Bullet.class)) {
            TimerPart timerPart = bullet.getPart(LifePart.class);

            timerPart.reduceExpiration(1);
            if (timerPart.getExpiration() <= 0) {
                world.removeEntity(bullet);
            }

            MovingPart movingPart = bullet.getPart(MovingPart.class);
            movingPart.setUp(true);
            movingPart.process(gameData, bullet);

            PositionPart positionpart = bullet.getPart(PositionPart.class);
            positionpart.process(gameData, bullet);

            setShape(bullet);
        }
    }


    public Entity createBullet(Event event) {
        PositionPart shooterPos = event.getSource().getPart(PositionPart.class);
        MovingPart shooterMovingPart = event.getSource().getPart(MovingPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float expiration = 1000;
        float speed = 350;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        float bx = (float) cos(radians) * event.getSource().getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * event.getSource().getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        bullet.add(new TimerPart(1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }

    private void setShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = x;
        shapey[0] = y;

        shapex[1] = (float) (x + cos(radians - 4 * 3.1415f / 5));
        shapey[1] = (float) (y + sin(radians - 4 * 3.1145f / 5));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
