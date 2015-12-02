package ca.uottawa.eecs.seg2505.benevolapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class OffreContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<OffreItem> ITEMS = new ArrayList<OffreItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, OffreItem> ITEM_MAP = new HashMap<String, OffreItem>();

    static {
        // Add 3 sample items.
        addItem(new OffreItem("1", "Exemple 1"));
        addItem(new OffreItem("2", "Example 2"));
        addItem(new OffreItem("3", "Example 3"));
        addItem(new OffreItem("4", "Example 4"));
        addItem(new OffreItem("5", "Example 5"));
        addItem(new OffreItem("6", "Example 6"));
    }

    private static void addItem(OffreItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class OffreItem {
        public String id;
        public String titre;

        public OffreItem(String id, String titre) {
            this.id = id;
            this.titre = titre;
        }

        @Override
        public String toString() {
            return titre;
        }
    }
}
