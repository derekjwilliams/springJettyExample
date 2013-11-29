package com.digitalglobe.view;

import com.digitalglobe.librarymonitor.database.LibraryRequestItem;

import java.util.List;

public class LibraryRequestItemsViewModel {

    private List<LibraryRequestItem> libraryRequestItems;

    public List<LibraryRequestItem> getLibraryRequestItems() {
        return libraryRequestItems;
    }

    public void setLibraryRequestItems(List<LibraryRequestItem> libraryRequestItems) {
        this.libraryRequestItems = libraryRequestItems;
    }

}
