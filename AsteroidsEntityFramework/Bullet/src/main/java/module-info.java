import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
module Bullet {
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
}