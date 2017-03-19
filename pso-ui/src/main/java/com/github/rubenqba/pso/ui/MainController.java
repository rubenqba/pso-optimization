package com.github.rubenqba.pso.ui;

import com.github.rubenqba.pso.problem.ProblemSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.WordUtils;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j2
public class MainController  implements Initializable {

    @FXML
    private Spinner<Integer> uiSwarmSize;
    @FXML
    private Spinner<Double> uiInertia;
    @FXML
    private Spinner<Double> uiDampingRatio;
    @FXML
    private Spinner<Double> uiPersonalAcceleration;
    @FXML
    private Spinner<Double> uiSocialAcceleration;
    @FXML
    private Spinner<Double> uiErrorTolerance;
    @FXML
    private Spinner<Double> uiMaxIterations;
    @FXML
    private ComboBox<ProblemSet.StopCondition> uiStopCondition;
    @FXML
    private ComboBox<FitnessFunction> uiFitnessFunction;
    @FXML
    private Spinner<Integer> uiProblemDimensions;

    public void changeSwarmSize(Integer oldValue, Integer newValue) {
        log.info("Se cambió el valor de SwarmSize a " + uiSwarmSize.getValue());
    }

    enum FitnessFunction {
        SPHERE,
        ROSENBROCK;

        private String label;

//        FitnessFunction(String label) {
//           this.label = label;
//        }

        public String toString() {
            return WordUtils.capitalizeFully(super.toString());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiSwarmSize.valueProperty().addListener((observable, oldValue, newValue) -> changeSwarmSize(oldValue, newValue));

        uiStopCondition.getItems().addAll(ProblemSet.StopCondition.values());
        uiStopCondition.setValue(ProblemSet.StopCondition.BOTH);

        uiFitnessFunction.getItems().addAll(FitnessFunction.values());
        uiFitnessFunction.setValue(FitnessFunction.SPHERE);
    }
}
