module com.example.cropoptimizr {
    requires javafx.controls;
    requires javafx.fxml;

            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires commons.math3;
    requires pmml4s;

    opens com.example.cropoptimizr to javafx.fxml;
    exports com.example.cropoptimizr;
    exports com.example.cropoptimizr.model;
    opens com.example.cropoptimizr.model to javafx.fxml;
}