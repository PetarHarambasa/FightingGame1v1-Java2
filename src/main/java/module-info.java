module hr.algebra.java2.fightinggame1v1.fightinggame1v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires java.rmi;


    opens hr.algebra.java2.fightinggame1v1 to javafx.fxml;
    exports hr.algebra.java2.fightinggame1v1;
    exports hr.algebra.java2.rmi to java.rmi;
}