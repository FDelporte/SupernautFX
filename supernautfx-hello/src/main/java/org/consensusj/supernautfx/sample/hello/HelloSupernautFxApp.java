package org.consensusj.supernautfx.sample.hello;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.consensusj.supernautfx.FxmlLoaderFactory;
import org.consensusj.supernautfx.SupernautFxApp;
import org.consensusj.supernautfx.SupernautFxLauncher;
import org.consensusj.supernautfx.sample.hello.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;

/**
 * A Supernaut FX App, implements SupernautFxApp but is not required to.
 */
@Singleton
public class HelloSupernautFxApp implements SupernautFxApp {
    private static final Logger log = LoggerFactory.getLogger(HelloSupernautFxApp.class);
    private final GreetingService greetingService;
    private final FxmlLoaderFactory loaderFactory;


    public static void main(String[] args) {
        SupernautFxLauncher.superLaunch(HelloSupernautFxApp.class, args);
    }

    public HelloSupernautFxApp(FxmlLoaderFactory loaderFactory, GreetingService greetingService) {
        this.greetingService = greetingService;
        this.loaderFactory = loaderFactory;
    }

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = loaderFactory.get();
        loader.setLocation(getFXMLUrl("MainWindow.fxml"));
        log.debug("MainWindow root FXML: {}", loader.getLocation());
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setTitle(greetingService.greeting());
        primaryStage.show();
    }

    @Override
    public void stop() {
    }

    private URL getFXMLUrl(String fileName) {
        return HelloSupernautFxApp.class.getResource(fileName);
    }

}
