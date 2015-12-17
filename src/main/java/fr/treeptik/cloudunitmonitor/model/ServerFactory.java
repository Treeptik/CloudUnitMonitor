package fr.treeptik.cloudunitmonitor.model;

import fr.treeptik.cloudunitonitor.model.action.*;

public class ServerFactory {

    /**
     * Return new module with ModuleAction initialized
     *
     * @param
     * @return
     */
    public static Server getServer(String imageName) {

        Server server = new Server();

        server.setServerAction(getServerAction(imageName, server));

        return server;
    }

    /**
     * Update module with ModuleAction initialized
     *
     * @param
     * @return
     */
    public static Server updateServer(Server server) {
        server.setServerAction(getServerAction(server.getImage().getName(),
                server));
        return server;
    }

    private static ServerAction getServerAction(String imageName, Server server) {

        ServerAction result = null;

        // TODO : HACK TO REMOVE MODULE VERSION
        if (imageName.toLowerCase().contains("tomcat")) {
            result = new TomcatAction(server);
        } else if (imageName.toLowerCase().contains("jboss-5")) {
            result = new JBossAction5(server);
        } else if (imageName.toLowerCase().contains("jboss")) {
            result = new JBossAction(server);
        } else if (imageName.toLowerCase().contains("fatjar")) {
            result = new FatJarAction(server);
        }

        return result;

    }

}
