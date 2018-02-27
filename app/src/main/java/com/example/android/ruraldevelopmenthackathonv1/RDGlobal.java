package com.example.android.ruraldevelopmenthackathonv1;

import android.app.Application;

/**
 * Created by psivapra on 2/26/2018.
 */

public class RDGlobal extends Application {
    private String currentRole;

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }
}
