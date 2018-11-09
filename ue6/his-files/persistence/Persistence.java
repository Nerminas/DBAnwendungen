/*
 * project    company
 * subproject persistence
 */

package company.persistence;

import javax.persistence.EntityManager;

public class Persistence extends spize.persistence.Persistence {

    public static final String persistenceUnitDefault = "company";

    public static EntityManager connect() {
        return connect(persistenceUnitDefault);
    }

    /*public static EntityManager connect (String persistenceUnit)
    {
        return connect (persistenceUnit);
    }*/

    public static EntityManager connect(String user, String password) {
        return connect(persistenceUnitDefault, user, password);
    }
}
