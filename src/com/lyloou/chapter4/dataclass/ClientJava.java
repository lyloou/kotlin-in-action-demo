package com.lyloou.chapter4.dataclass;

import java.util.Objects;

public class ClientJava {
    private String name;
    private int postalCode;

    public ClientJava(String name, int postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public static void main(String[] args) {
        ClientJava client1 = new ClientJava("lisi", 123456);
        ClientJava client2 = new ClientJava("lisi", 123456);

        System.out.println(client1.equals(client2));
        System.out.println(client1 == client2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientJava that = (ClientJava) o;
        return postalCode == that.postalCode &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, postalCode);
    }
}
