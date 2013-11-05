package com.digitalglobe.view;

import com.digitalglobe.database.LibraryRequest;

import java.util.List;

public class LibraryRequestsViewModel {

    private List<LibraryRequest> libraryRequests;

    public List<LibraryRequest> getLibraryRequests() {
        return libraryRequests;
    }

    public void setLibraryRequests(List<LibraryRequest> libraryRequests) {
        this.libraryRequests = libraryRequests;
    }

}
