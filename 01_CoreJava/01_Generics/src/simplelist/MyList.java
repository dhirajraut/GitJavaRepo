package simplelist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 * @param <E>
 */
public class MyList < E > extends ArrayList < E > {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Usable List.
     */
    private List < E > list = new ArrayList < E > ();
    /**
     * @param inputObject - Input Object.
     */
    public final void addObject(final E inputObject) {
        list.add(inputObject);
    }
}
