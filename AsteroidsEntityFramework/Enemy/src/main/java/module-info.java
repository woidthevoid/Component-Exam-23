import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Enemy {
    requires Common;

    provides IGamePluginService with dk.sdu.mmmi.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.enemy.EnemyControlSystem;
}