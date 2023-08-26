module Core {
    opens dk.sdu.mmmi.cbse.main to spring.core;
    requires Common;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    exports dk.sdu.mmmi.cbse.main;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
}