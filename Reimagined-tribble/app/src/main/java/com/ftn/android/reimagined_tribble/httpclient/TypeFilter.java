package com.ftn.android.reimagined_tribble.httpclient;

/**
 * Created by Jozef on 7/2/2016.
 */
public enum TypeFilter {
    ALL {
        public String toString() {
            return "all";
        }
    },

    INCIDENT {
        public String toString() {
            return "incident";
        }
    }
    ,

    other {
        public String toString() {
            return "other";
        }
    }
}