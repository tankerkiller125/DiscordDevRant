package ml.rhodes.bots.discord.devrant.utils;

import de.btobastian.sdcf4j.handler.Discord4JHandler;
import sx.blah.discord.api.IDiscordClient;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class NewHandler extends Discord4JHandler {

    private HashMap<String, List<String>> permissions = new HashMap<String, List<String>>();

    public NewHandler(IDiscordClient client) {
        super(client);
    }

    /**
     * Add permission to user
     *
     * @param userId     String
     * @param permission String
     */
    @Override
    public void addPermission(String userId, String permission) {
        List<String> permissions = this.permissions.get(userId);
        if (permissions == null) {
            permissions = new ArrayList<String>();
            this.permissions.put(userId, permissions);
        }
        permissions.add(permission);
    }

    /**
     * Check user has permission
     *
     * @param userId     String
     * @param permission String
     * @return Boolean
     */
    @Override
    public boolean hasPermission(String userId, String permission) {
        if (!permission.equals("none") && !permission.equals("")) {
            List<String> permissions = this.permissions.get(userId);
            if (permissions == null) {
                return false;
            } else {
                Iterator var4 = permissions.iterator();

                String perm;
                do {
                    if (!var4.hasNext()) {
                        return false;
                    }

                    perm = (String) var4.next();
                } while (!this.checkPermission(perm, permission));

                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Check permissions and wild card
     *
     * @param has      String
     * @param required String
     * @return Boolean
     */
    private boolean checkPermission(String has, String required) {
        String[] splitHas = has.split("\\.");
        String[] splitRequired = required.split("\\.");
        int lower = splitHas.length > splitRequired.length ? splitRequired.length : splitHas.length;

        for (int i = 0; i < lower; ++i) {
            if (!splitHas[i].equalsIgnoreCase(splitRequired[i])) {
                return splitHas[i].equals("*");
            }
        }

        return splitRequired.length == splitHas.length;
    }

    /**
     * Remove user permission
     *
     * @param userId     String
     * @param permission String
     */
    public void removePermission(String userId, String permission) {
        List<String> permissions = this.permissions.get(userId);
        if (permission == null) {
            permissions = new ArrayList<String>();
            this.permissions.put(userId, permissions);
        } else {
            permissions.remove(permission);
        }
    }

    /**
     * Saves user permissions in XML format
     */
    public void savePermissions() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("permissions.xml");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
            xmlEncoder.writeObject(this.permissions);
            xmlEncoder.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Loads user permissions from XML file
     */
    public void loadPermissions() {
        try {
            FileInputStream fileInputStream = new FileInputStream("permissions.xml");
            XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
            this.permissions = (HashMap<String, List<String>>) xmlDecoder.readObject();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}