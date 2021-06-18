module at.htl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.naming;
    requires java.mail;
    opens at.htl.getAPet.gui.controllers to javafx.fxml;
    exports at.htl.getAPet.gui;
}