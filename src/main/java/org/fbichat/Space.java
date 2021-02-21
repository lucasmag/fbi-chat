package org.fbichat;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.CannotFindSpaceException;
import org.openspaces.core.space.EmbeddedSpaceConfigurer;
import org.openspaces.core.space.SpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

public final class Space extends GigaSpaceConfigurer{
    private static GigaSpace space;

    public Space(SpaceConfigurer configurer) {
        super(configurer);
        System.out.println("Inicializando espaço...");
    }

    public static GigaSpace get() {
        if (space == null)
            start();
        return space;
    }

    public static void start() {
        try {
            space = new Space(new SpaceProxyConfigurer("space")).gigaSpace();
        }
        catch (CannotFindSpaceException e){
            space = new Space(new EmbeddedSpaceConfigurer("space")).gigaSpace();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}