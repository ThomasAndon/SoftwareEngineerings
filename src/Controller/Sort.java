package Controller;

import Entity.Session;

import java.util.Comparator;

public class Sort implements Comparator< Session > {
    /**
     * @description compare and sort the session by its date.
     */
    @Override
    public int compare(Session o1, Session o2) {
        if ( o1.equals( o2 ) || o1.getTime().equals(o2.getTime()) ) {
            return 0;
        }
        else if ( o1.getTime().compareTo(o2.getTime())>0 ) {
            return 1;
        }
        else {
            return -1;
        }
    }

}
