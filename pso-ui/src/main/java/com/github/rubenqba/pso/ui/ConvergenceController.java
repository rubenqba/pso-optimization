package com.github.rubenqba.pso.ui;

import com.github.rubenqba.pso.Movement;
import com.github.rubenqba.pso.Swarm;
import com.github.rubenqba.pso.movement.StandardMovement;
import com.github.rubenqba.pso.problem.*;
import com.github.rubenqba.pso.ui.utils.UiUtils;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.WordUtils;
import org.controlsfx.glyphfont.GlyphFont;

import java.io.FileInputStream;
import java.net.InterfaceAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class ConvergenceController implements Initializable {

    private Swarm swarm;
    private CommonProblemSet problem;
    private Movement movement;

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
    private Spinner<Integer> uiErrorTolerance;
    @FXML
    private Spinner<Integer> uiMaxIterations;
    @FXML
    private ComboBox<StopCondition> uiStopCondition;
    @FXML
    private ComboBox<FitnessFunction> uiFitnessFunction;
    @FXML
    private Spinner<Integer> uiProblemDimension;
    @FXML
    private LineChart<String, Double> uiConvergenceChart;
    @FXML
    private Button uiRunAlgorithm;


    private void changeSwarmSize() {
        log.debug("Se cambió el valor de SwarmSize a " + uiSwarmSize.getValue());
        problem.setSwarmSize(uiSwarmSize.getValue());
    }

    private void changeFitnessFunction(ActionEvent event) {
        switch (uiFitnessFunction.getValue()) {
            case SPHERE:
                problem = new SphereProblem();
                break;
            case ROSENBROCK:
                problem = new RosenbrockProblem();
                break;
            case GRIEWANK:
                problem = new GriewankProblem();
                break;
            case RASTRIGIN:
                problem = new RastriginProblem();
                break;
            case SCHAFFER6:
                problem = new Schaffer6Problem();
                break;
            case GOLDSTEIN_PRICE:
                problem = new GoldsteinPriceProblem();
        }
        log.debug(String.format("Se cambia la función objetivo por '%s'", uiFitnessFunction.getValue()));
    }

    private void changeInertiaWeigth() {
        problem.setW(uiInertia.getValue());
        log.debug("Se cambió el valor de InertiaWeigth a " + uiInertia.getValue());
    }

    private void changeDampingRatio() {
        problem.setWDamp(uiDampingRatio.getValue());
        log.debug("Se cambió el valor de Damping Ratio a " + uiDampingRatio.getValue());
    }

    private void changePersonalAcceleration() {
        problem.setC1(uiPersonalAcceleration.getValue());
        log.debug("Se cambió el valor de Personal Acceleration a " + uiPersonalAcceleration.getValue());
    }

    private void changeSocialAcceleration() {
        problem.setC2(uiSocialAcceleration.getValue());
        log.debug("Se cambió el valor de Social Acceleration a " + uiSocialAcceleration.getValue());
    }

    private void changeErrorTolerance() {
        problem.setErrorTolerance(Math.pow(10, uiErrorTolerance.getValue()));
        log.debug("Se cambió el valor de Error Tolerance a " + UiUtils.getScientificNotation(10, uiErrorTolerance.getValue()));
    }

    private void changeMaxIterations() {
        problem.setMaximumIterations(uiMaxIterations.getValue());
        log.debug("Se cambió el valor de Max Iteration a " + uiMaxIterations.getValue());
    }

    private void changeStopCondition(ActionEvent event) {
        problem.setStopCondition(uiStopCondition.getValue());
        log.debug(String.format("Se cambió el valor de Stop Condition a '%s'", uiStopCondition.getValue()));
    }

    private void changeProblemDimension() {
        problem.setProblemDimension(uiProblemDimension.getValue());
        log.debug("Se cambió el valor de Problem Dimension a " + uiProblemDimension.getValue());
    }

    public void runAlgorithm(ActionEvent event) {
        List<Double> bestFitness = new ArrayList<>();
        swarm.execute(problem, bestFitness);

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName(movement.getName());

        series.getData().addAll(IntStream.range(0, bestFitness.size())
                .mapToObj(i -> new XYChart.Data<String, Double>(Integer.toString(i), Math.log10(bestFitness.get(i))))
                .collect(Collectors.toList()));

        uiConvergenceChart.getData().add(series);
    }

    public void cleanChart(ActionEvent action) {
        uiConvergenceChart.getData().clear();
    }

    private boolean validateCoefficients() {
        double inertia = uiInertia.getValue();
        double personal = uiPersonalAcceleration.getValue();
        double social = uiSocialAcceleration.getValue();

//        TODO: revisar la condición
        if ((-1 < inertia || 1 > inertia) &&
                (personal + social < 2*(1+inertia)) &&
                (0 < personal + inertia))
            return true;
        return false;
    }

    enum FitnessFunction {
        SPHERE,
        ROSENBROCK,
        GRIEWANK,
        RASTRIGIN,
        SCHAFFER6,
        GOLDSTEIN_PRICE;

        public String toString() {
            return WordUtils.capitalizeFully(super.toString().replace('_', ' '));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        swarm = new Swarm();
        problem = new SphereProblem();
        movement = new StandardMovement();

        uiFitnessFunction.getItems().addAll(FitnessFunction.values());
        uiFitnessFunction.setOnAction(event -> changeFitnessFunction(event));
        uiFitnessFunction.setValue(FitnessFunction.SPHERE);

        uiSwarmSize.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, problem.getSwarmSize(), 50));
        uiSwarmSize.valueProperty().addListener((observable, oldValue, newValue) -> changeSwarmSize());

        InvalidationListener vCoefficients = new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (!validateCoefficients())
                    throw new IllegalArgumentException("The coefficients do not meet the assumptions of convergence.");
            }
        };

        uiInertia.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-1, 1, problem.getW(), .1));
//        uiInertia.valueProperty().addListener(vCoefficients);
        uiInertia.valueProperty().addListener((observable, oldValue, newValue) -> changeInertiaWeigth());

        uiDampingRatio.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(.1, 1, problem.getWDamp(), .1));
        uiDampingRatio.valueProperty().addListener((observable, oldValue, newValue) -> changeDampingRatio());

        uiPersonalAcceleration.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, problem.getC1(), .1));
//        uiPersonalAcceleration.valueProperty().addListener(vCoefficients);
        uiPersonalAcceleration.valueProperty().addListener((observable, oldValue, newValue) -> changePersonalAcceleration());

        uiSocialAcceleration.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, problem.getC2(), .1));
//        uiSocialAcceleration.valueProperty().addListener(vCoefficients);
        uiSocialAcceleration.valueProperty().addListener((observable, oldValue, newValue) -> changeSocialAcceleration());

        SpinnerValueFactory<Integer> errorVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(Double.MIN_EXPONENT, Double.MAX_EXPONENT, -2);
        errorVF.setConverter(new IntegerStringConverter() {
            @Override
            public Integer fromString(String value) {
                return UiUtils.getScientificExponent(value);
            }

            @Override
            public String toString(Integer value) {
                return UiUtils.getScientificNotation(10, value);
            }
        });
        uiErrorTolerance.setValueFactory(errorVF);
        uiErrorTolerance.valueProperty().addListener((observable, oldValue, newValue) -> changeErrorTolerance());

        uiMaxIterations.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(100, Integer.MAX_VALUE, problem.getMaximumIterations(), 100));
        uiMaxIterations.valueProperty().addListener((observable, oldValue, newValue) -> changeMaxIterations());

        uiStopCondition.getItems().addAll(StopCondition.values());
        uiStopCondition.setValue(StopCondition.BOTH);
        uiStopCondition.setOnAction(this::changeStopCondition);

        uiProblemDimension.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE, problem.getProblemDimension()));
        uiProblemDimension.valueProperty().addListener((observable, oldValue, newValue) -> changeProblemDimension());

        uiConvergenceChart.getXAxis().setLabel("Number of Iterations");
        uiConvergenceChart.getYAxis().setLabel("Best Swarm value");
    }
}
