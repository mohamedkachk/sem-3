package se.kth.iv1350.amazingpos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Is responsible for finding the correct items descriptions
 * that store sells.
 */
public class ItemRegistry {
    private List<ItemDescription> itemsForSale = new ArrayList<>();

    /**
     * Creates a instance of a dummy list of
     * available items for sale in the store.
     */
    public ItemRegistry() {
        addItems();
    }

    /**
     * Get the value of itemsForSale
     *
     * @return the value of itemsForSale
     */
    public List<ItemDescription> getItemsForSale() {
        return this.itemsForSale;
    }

    /**
     * Search for a item matching the specified itemIdentifier.
     *
     * @param itemIdentifier This String contains the identifier
     *  of specific item.
     *
     * @return <code>ItemDescription</code> If itemIdentifier we looking after
     * exist, otherwise return <code>null</code> if any item match
     * the specified itemIdentifier.
     */
    public ItemDescription findItem(String itemIdentifier) {
        if (checkIfItemAlreadyExist(itemIdentifier)) {
            ItemDescription validItem = new ItemDescription();
            return validItem.ItemDescription(itemIdentifier);
        }
        return null;
    }

    /**
     * Search for a item matching the specified itemIdentifier.
     *
     * @param itemIdentifier This String contains the identifier
     *  of specific item.
     *
     * @return <code>true</code> if a item with the same
     * identifier as <code>itemIdentifier</code> was found,
     * <code>false</code> if no such item with the specified
     * identifier was found.
     */
    private boolean checkIfItemAlreadyExist(String itemIdentifier) {
        for (ItemDescription item : itemsForSale)
            if (item.getItemId().equals(itemIdentifier))
                return true;
        {
            return false;
        }
    }

    private void  addItems(){
        itemsForSale.add(new ItemDescription("apple", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("milk", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("juice", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("banana", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("Bread", 0, 1, 1 ));
    }

}
