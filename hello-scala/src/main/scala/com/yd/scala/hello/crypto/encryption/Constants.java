package com.yd.scala.hello.crypto.encryption;

public class Constants {
    public Constants() {
    }

    public static enum SecretKey {
        DEFAULT("encryption.secret"),
        REGION_SYNC("encryption.secret.regions.sync");

        private String value;

        private SecretKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}