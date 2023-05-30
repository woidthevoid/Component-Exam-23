import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires gdx;
    provides IPostEntityProcessingService with dk.sdu.mmi.cbse.collision.CollisionSystem;
}